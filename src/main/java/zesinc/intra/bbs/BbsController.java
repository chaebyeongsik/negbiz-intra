/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.FileService;
import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.lang.Validate;
import zesinc.intra.bbs.domain.BbsVO;
import zesinc.intra.bbs.support.BbsValidate;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.spring.resolver.ParamArgumentResolver;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.support.pager.Pager;
import zesinc.web.support.tag.bbsTmplat.support.BbsSearchSupport;
import zesinc.web.utils.Converter;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.XssUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import zesinc.web.vo.cache.BbsCtgryCacheVO;
import zesinc.web.vo.cache.BbsDomnCacheVO;
import zesinc.web.vo.cache.BbsItemCacheVO;

/**
 * 게시판 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-08.    황신욱   최초작성
 *  2019-10-25.    woogi 첨부파일허용확장자 대소문자구분없이 체크
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판 관리")
@RequestMapping(value = { "/intra/bbs" })
public class BbsController extends IntraController {

    @Resource(name = "opBbsService")
    private BbsService opBbsService;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    /**
     * 각 템플릿별 베이스 URL
     */
    private String getTemplateFolderName(String skinPath) {
        return ("common/bbsTmplats/" + skinPath + "/");
    }

    /**
     * 게시판 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_selectBbsList.do", "PD_selectBbsList.do" })
    public String selectBbsList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigCacheVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = new Integer("0");
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        model.addAttribute(BaseConfig.BBS_CONFIG_VO_KEY, bbsConfigCacheVo);

        if(Validate.isEmpty(request.getParameter("q_rowPerPage"))) {
            bbsVo.addParam("q_rowPerPage", bbsConfigCacheVo.getPagePstCnt());
            setPageParam(bbsVo);
        }

        // 게시판 항목설정에 따른 검색 대상 및 검색 유형 설정
        String searchKeyTy = bbsVo.getString("q_searchKeyTy");
        if(Validate.isNotEmpty(searchKeyTy)) {
            if(searchKeyTy.indexOf("___") > -1) {
                String[] search = searchKeyTy.split("___");
                bbsVo.addParam("q_searchKey", BbsSearchSupport.getDBColumnNm(search[0]));
                bbsVo.addParam("q_srchType", search[1]);
            }
        }

        // 일반글 목록
        bbsVo.setNtcPstYn("N");
        Pager<BbsVO> pager = opBbsService.selectBbsPageList(bbsVo);
        model.addAttribute(BaseConfig.KEY_PAGER, pager);

        // 공지사용시 게시물에 공지사항 목록을 추가. 단! 1페이지에만 표시
        if(bbsConfigCacheVo.getNtcUseYn().equals("Y")) {
            Integer currPage = bbsVo.getInteger("q_currPage");
            if(currPage < 2) {
                addNoticeBbsList(bbsVo, model);
            }
        }

        String path = getTemplateFolderName(domnCacheVo.getLstTmpltNm());
        
        
        return path + "PD_bbs.list";
    }

    /**
     * 공지리스트 리스트를 셋팅
     */
    private void addNoticeBbsList(BbsVO bbsVo, Model model) {
        bbsVo.setNtcPstYn("Y");
        List<BbsVO> noticeList = opBbsService.selectBbsNoticeList(bbsVo);

        model.addAttribute(BaseConfig.KEY_NOTICE_LIST, noticeList);
    }

    /**
     * 게시판 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_selectBbs.do", "PD_selectBbs.do" })
    public String selectBbs(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigCacheVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = new Integer("0");
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        BbsVO dataVo = opBbsService.selectBbs(bbsVo);
        // 대상 게시물이 없는 경우
        if(Validate.isEmpty(dataVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        // 에디터사용이 N일 때 \n이 있으면 <br/> 태그로 전환
        if("N".equals(bbsConfigCacheVo.getMngrEdtrUseYn())) {
            dataVo.setDocContsCn(Converter.translateBR(dataVo.getDocContsCn()));
        }

        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);
        model.addAttribute(BaseConfig.BBS_CONFIG_VO_KEY, bbsConfigCacheVo);

        String path = getTemplateFolderName(domnCacheVo.getPstTmpltNm());
        return path + "PD_bbs.view";
    }

    /**
     * 게시판 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_insertBbsForm.do", "PD_insertBbsForm.do" })
    public String insertBbsForm(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigCacheVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = new Integer("0");
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        model.addAttribute(BaseConfig.BBS_CONFIG_VO_KEY, bbsConfigCacheVo);

        ValidateResultHolder holder = BbsValidate.validate(bbsConfigCacheVo, bbsVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        BbsVO dataVo = new BbsVO();
        dataVo.setBbsSn(bbsVo.getBbsSn());
        LoginVO loginVo = (LoginVO) getMgrSession();
        dataVo.setRgtrId(loginVo.getPicId());
        dataVo.setRgtrNm(loginVo.getPicNm());
        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);

        model.addAttribute("ACTION", "ND_insertBbs.do");

        String path = getTemplateFolderName(domnCacheVo.getInptTmpltNm());
        return path + "PD_bbs.form";
    }

    /**
     * 게시판 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertBbs.do", method = RequestMethod.POST)
    public String insertBbs(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        /*
         * 환경설정에 따른 게시물 입력값 검증
         */
        ValidateResultHolder holder = BbsValidate.validate(bbsConfigCacheVo, bbsVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsVo.setPicId(loginVo.getPicId());
            bbsVo.setPicNm(loginVo.getPicNm());
            bbsVo.setDeptCdId(loginVo.getDeptCdId());
            bbsVo.setDeptNm(loginVo.getDeptNm());
            bbsVo.setRgtrId(loginVo.getPicId());
            bbsVo.setRgtrNm(loginVo.getPicNm());

            bbsVo.setIpAddr(getIpAddr());

            List<FileVO> fileList = UploadHelper.upload(request, "bbs");
            bbsVo.setFileList(fileList);

            BbsItemCacheVO bbsItemCacheVo = bbsConfigCacheVo.getBbsItemMap().get("file");
            if ("Y".equals(bbsItemCacheVo.getEsntlYn()) && fileList.size() == 0) {
                String colNm = bbsItemCacheVo.getColNm();
                if (Validate.isNotEmpty(bbsItemCacheVo.getScrnNm())) {
                    colNm = bbsItemCacheVo.getScrnNm();
                }
                return alertAndBack(model, colNm + " : " + MessageUtil.getMessage("validator.required"));
            }

            // 파일 확장자 체크
            boolean isExt = false;
            for(FileVO baseFileVo : fileList) {
                String ext = baseFileVo.getFileExtnNm().toLowerCase();
                String strExtList = bbsConfigCacheVo.getPrmsnFileExtnMttr().toLowerCase();
                if( !(strExtList.indexOf(ext) > -1) ) isExt = true;
            }
            if (isExt) { // 허용확장자가 아닌경우 업로드한 파일 삭제
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, "허용된 확장자만 첨부할 수 있습니다.");
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 폼 업로드시 업로드 파일 용량을 체크한다.
            if(!checkUploadFileSize(fileList, bbsConfigCacheVo)) {
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, MessageUtil.getMessage("file.fileSizeOver"));
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 썸네일 사용시
            if(Validate.isNotEmpty(fileList) && bbsConfigCacheVo.getThmbUseYn().equals("Y")) {
                // 워터마크 사용시
                if(bbsConfigCacheVo.getWtmkUseYn().equals("Y") && Validate.isNotEmpty(bbsConfigCacheVo.getWtmkFileNm())) {
                    UploadHelper.makeWarterMark(fileList, bbsConfigCacheVo.getWdthSz(),
                        bbsConfigCacheVo.getVrtcSz(), bbsConfigCacheVo.getWtmkFileNm(), bbsConfigCacheVo.getWtmkPstnNm(),
                        bbsConfigCacheVo.getWtmkTrnspc());
                } else {
                    UploadHelper.makeThumbNail(fileList, bbsConfigCacheVo.getWdthSz(), bbsConfigCacheVo.getVrtcSz());
                }
            }

            // 에디터 사용 상태가 아닌 경우 HTML 태그 제거
            if(bbsConfigCacheVo.getMngrEdtrUseYn().equals("N")) {
                bbsVo.setDocContsCn(XssUtil.cleanTag(bbsVo.getDocContsCn(), XssUtil.TYPE.ALL));
            }

            // CntnBrwsrNm 정보
            String cntnBrwsrNm = request.getHeader("User-Agent");
            if(Validate.isNotEmpty(cntnBrwsrNm)) {
                bbsVo.setCntnBrwsrNm(cntnBrwsrNm);
            }

            Integer key = opBbsService.insertBbs(bbsVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 게시판 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_updateBbsForm.do", "PD_updateBbsForm.do", "BD_replyBbsForm.do", "PD_replyBbsForm.do" })
    public String updateBbsForm(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigCacheVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = new Integer("0");
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        BbsVO dataVo = null;

        model.addAttribute(BaseConfig.BBS_CONFIG_VO_KEY, bbsConfigCacheVo);

        ValidateResultHolder holder = BbsValidate.validate(bbsConfigCacheVo, bbsVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        // URI로 기능 구분
        String requestUri = request.getRequestURI();
        if(requestUri.endsWith("BD_updateBbsForm.do") || requestUri.endsWith("PD_updateBbsForm.do")) {
            dataVo = opBbsService.selectBbs(bbsVo);
            // 대상 게시물이 없는 경우
            if(Validate.isEmpty(dataVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.noData"));
            }
            // 권한 확인
            if(!AuthSupport.isAuth(dataVo.getRgtrId())) {
                return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
            }

            model.addAttribute("ACTION", "ND_updateBbs.do");
        } else if(requestUri.endsWith("BD_replyBbsForm.do") || requestUri.endsWith("PD_replyBbsForm.do")) {

            dataVo = new BbsVO();
            dataVo.setBbsSn(bbsVo.getInteger("q_bbsSn"));
            dataVo.setBbsDocNo(bbsVo.getString("q_bbsDocNo"));

            model.addAttribute("ACTION", "ND_insertBbs.do");
        }

        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);

        String path = getTemplateFolderName(domnCacheVo.getInptTmpltNm());
        return path + "PD_bbs.form";
    }

    /**
     * 게시판 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateBbs.do", method = RequestMethod.POST)
    public String updateBbs(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        /*
         * 환경설정에 따른 게시물 입력값 검증
         */
        ValidateResultHolder holder = BbsValidate.validate(bbsConfigCacheVo, bbsVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsVo.setMdfrId(loginVo.getPicId());
            bbsVo.setUpdusrNm(loginVo.getPicNm());
            bbsVo.setIpAddr(getIpAddr());

            BbsVO dataVo = opBbsService.selectBbs(bbsVo);
            // 대상 게시물이 없는 경우
            if(Validate.isEmpty(dataVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.noData"));
            }
            // 권한 확인
            if(!AuthSupport.isAuth(dataVo.getRgtrId())) {
                return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
            }

            List<FileVO> fileList = UploadHelper.upload(request, "bbs");
            bbsVo.setFileList(fileList);

            BbsItemCacheVO bbsItemCacheVo = bbsConfigCacheVo.getBbsItemMap().get("file");
            if ("Y".equals(bbsItemCacheVo.getEsntlYn()) && fileList.size() == 0) {
                String colNm = bbsItemCacheVo.getColNm();
                if (Validate.isNotEmpty(bbsItemCacheVo.getScrnNm())) {
                    colNm = bbsItemCacheVo.getScrnNm();
                }
                return alertAndBack(model, colNm + " : " + MessageUtil.getMessage("validator.required"));
            }

            // 파일 확장자 체크
            boolean isExt = false;
            for(FileVO baseFileVo : fileList) {
                String ext = baseFileVo.getFileExtnNm().toLowerCase();
                String strExtList = bbsConfigCacheVo.getPrmsnFileExtnMttr().toLowerCase();
                if( !(strExtList.indexOf(ext) > -1) ) isExt = true;
            }
            if (isExt) { // 허용확장자가 아닌경우 업로드한 파일 삭제
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, "허용된 확장자만 첨부할 수 있습니다.");
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 폼 업로드시 업로드 파일 용량을 체크한다.
            if(!checkUploadFileSize(fileList, bbsConfigCacheVo)) {
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, MessageUtil.getMessage("file.fileSizeOver"));
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 썸네일 사용시
            if(Validate.isNotEmpty(fileList) && bbsConfigCacheVo.getThmbUseYn().equals("Y")) {
                // 워터마크 사용시
                if(bbsConfigCacheVo.getWtmkUseYn().equals("Y")) {
                    UploadHelper.makeWarterMark(fileList, bbsConfigCacheVo.getWdthSz(),
                        bbsConfigCacheVo.getVrtcSz(), bbsConfigCacheVo.getWtmkFileNm(), bbsConfigCacheVo.getWtmkPstnNm(),
                        bbsConfigCacheVo.getWtmkTrnspc());
                } else {
                    UploadHelper.makeThumbNail(fileList, bbsConfigCacheVo.getWdthSz(), bbsConfigCacheVo.getVrtcSz());
                }
            }

            // 에디터 사용 상태가 아닌 경우 HTML 태그 제거
            if(bbsConfigCacheVo.getMngrEdtrUseYn().equals("N")) {
                bbsVo.setDocContsCn(XssUtil.cleanTag(bbsVo.getDocContsCn(), XssUtil.TYPE.ALL));
            }

            // 수정 실행
            Integer key = opBbsService.updateBbs(bbsVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbs.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 게시판 플래그삭제
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteFlagDelete.do", method = RequestMethod.POST)
    public String deleteFlagDelete(HttpServletRequest request, Model model, BbsVO bbsVo) {

        Integer cnt = opBbsService.deleteFlagBbs(bbsVo);
        if(cnt < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.processOk"), url);
    }

    /**
     * 게시판 플래그삭제(목록삭제)
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteFlagBbsList.do", method = RequestMethod.POST)
    public String deleteFlagDeleteList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        Integer cnt = opBbsService.deleteFlagBbsList(bbsVo);
        if(cnt < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.processOk"), url);
    }

    /**
     * 게시판 완전삭제
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteBbs.do", method = RequestMethod.POST)
    public String deleteBbs(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        BbsVO dataVo = opBbsService.selectBbs(bbsVo);
        // 대상 게시물이 없는 경우
        if(Validate.isEmpty(dataVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opBbsService.deleteBbs(bbsVo);
            if(cnt != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 게시판 완전삭제(목록삭제)
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteBbsList.do", method = RequestMethod.POST)
    public String deleteBbsList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        Integer cnt = opBbsService.deleteBbsList(bbsVo);
        if(cnt < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String prefix = "PD_";
        String refererUrl = request.getHeader("referer");
        if (Validate.isNotEmpty(refererUrl)) {
            if (refererUrl.indexOf("BD_") > -1) {
                prefix = "BD_";
            }
        }

        String url = prefix + "selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 게시판 이동수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "PD_updateMoveBbsForm.do")
    public void updateMoveBbsForm(HttpServletRequest request, Model model, BbsVO bbsVo) {

        model.addAttribute(BaseConfig.KEY_BASE_LIST, opBbsService.selectBbsconfigList(bbsVo));
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opBbsService.selectRefrnList(bbsVo));
    }

    /**
     * 게시판 이동수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateMoveBbs.do", method = RequestMethod.POST)
    public String updateMoveBbs(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
        }

        Integer updateCnt = opBbsService.updateMoveBbs(bbsVo);
        if(updateCnt < 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.processOk"));
    }

    /**
     * 게시글 만족도평가하기
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_updateStsfdgEvl.do")
    public String updateStsfdgEvl(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateStsfdgEvl(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo);
    }

    /**
     * 게시글 추천하기
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_updateRecomend.do")
    public String updateRecoment(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateRecoment(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo.getRcmdtnCnt());
    }

    /**
     * 게시글 신고하기
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_updateSttemnt.do")
    public String updateSttemnt(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateSttemnt(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo.getDclrCnt());
    }

    /**
     * 카테고리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "selectClsfNo.do")
    public String selectClsfNo(HttpServletRequest request, HttpServletResponse response, Model model, BbsCtgryCacheVO bbsCtgryVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsCtgryVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
        }

        List<BbsCtgryCacheVO> ctgryList = bbsConfigCacheVo.getBbsCtgryList();
        if(Validate.isEmpty(ctgryList)) {
            ctgryList = new ArrayList<BbsCtgryCacheVO>();
        }

        return responseJson(model, Boolean.TRUE, ctgryList);
    }

    /**
     * 하위 카테고리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "selectLwrkClsfSn.do")
    public String selectLwrkClsfSn(HttpServletRequest request, HttpServletResponse response, Model model, BbsCtgryCacheVO bbsCtgryVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsCtgryVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
        }

        List<BbsCtgryCacheVO> lwprtCtgryList = new ArrayList<BbsCtgryCacheVO>();
        Integer clsfNo = bbsCtgryVo.getInteger("q_clsfNo");

        List<BbsCtgryCacheVO> ctgryList = bbsConfigCacheVo.getBbsCtgryList();
        if(Validate.isNotEmpty(ctgryList)) {
            for(BbsCtgryCacheVO bbsCtgryCacheVo : ctgryList) {
                if(bbsCtgryCacheVo.getClsfNo().equals(clsfNo)) {
                    lwprtCtgryList = bbsCtgryCacheVo.getLwprtCtgryList();
                }
            }
        }

        return responseJson(model, Boolean.TRUE, lwprtCtgryList);
    }

    // 폼 업로드시 업로드 파일 용량을 체크한다.
    private boolean checkUploadFileSize(List<FileVO> fileList, BbsConfigCacheVO bbsconfigVo) {

        Long allFileSize = 0L;
        boolean uploadAt = true;

        // 업로드 파일 사이즈 제한이 있는 경우(사이즈 0일 경우 무제한 용량)
        for(FileVO baseFileVo : fileList) {
            try {
                Long fileSzNm = baseFileVo.getByteFileSz();

                if(bbsconfigVo.getLmtFileSz() > 0) {
                    Long lmtFileSz = Long.valueOf(((Integer) (bbsconfigVo.getLmtFileSz() * 1024 * 1024)).toString());
                    if(fileSzNm > lmtFileSz) {
                        uploadAt = false;
                    }
                }
                allFileSize += fileSzNm;
            } catch (Exception e) {
                uploadAt = false;
            }
        }

        // 업로드 총 파일 사이즈 제한이 있는 경우
        if(bbsconfigVo.getWholUldSz() > 0) {
            Long allMxmmFileSize = Long.valueOf(((Integer) (bbsconfigVo.getWholUldSz() * 1024 * 1024)).toString());
            if(allFileSize > allMxmmFileSize) {
                return false;
            }
        }

        return uploadAt;
    }

    /**
     * 페이징 정보가 없는 경우 자동 설정
     * 기본은 프레임워크에서 하지만, 최초 페이지를 열때 지정된 페이징 수가 없는 경우에 한하여 적용
     *
     * @param bbsVo
     */
    private void setPageParam(BbsVO bbsVo) {
        Map<String, Object> paramMap = bbsVo.getParamMap();

        int currPage = bbsVo.getInteger(BaseConfig.PREFIX_SEARCH_PARAM + "currPage");
        int rowPerPage = bbsVo.getInteger(BaseConfig.PREFIX_SEARCH_PARAM + "rowPerPage");
        int iStartNum = Pager.getStartNum(currPage, rowPerPage) + ParamArgumentResolver.addStartNum;
        int iEndNum = Pager.getEndNum(iStartNum, rowPerPage);

        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "rowPerPage", rowPerPage);
        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "pagingStartNum", Integer.valueOf(iStartNum));
        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "pagingEndNum", Integer.valueOf(iEndNum));
    }
}
