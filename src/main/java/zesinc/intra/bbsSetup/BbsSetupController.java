/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.lang.ReflectionUtil;
import zesinc.core.lang.Validate;
import zesinc.intra.bbsSetup.domain.BbsSetupItemVO;
import zesinc.intra.bbsSetup.domain.BbsSetupVO;
import zesinc.intra.bbsSetup.support.BbsSetupConstant;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 게시판 환경설정 관리 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 30.    woogi   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판환경설정관리")
@RequestMapping(value = { "/intra/bbsSetup" })
public class BbsSetupController extends IntraController {

    @Resource(name = "opBbsSetupService")
    private BbsSetupService opBbsSetupService;

    /**
     * 게시판환경설정 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsSetupList.do")
    public void selectBbsSetupList(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        model.addAttribute(BaseConfig.KEY_PAGER, opBbsSetupService.selectBbsSetupPageList(bbsSetupVo));
    }

    /**
     * 게시판환경설정 등록 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "BD_insertBbsSetupForm.do")
    public void insertBbsSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 게시판환경설정 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertBbsSetup.do", method = RequestMethod.POST)
    public String insertBbsSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsSetupVo.setRgtrId(loginVo.getPicId());
            bbsSetupVo.setRgtrNm(loginVo.getPicNm());
            bbsSetupVo.setIpAddr(getIpAddr());

            // 워터마크 파일
            List<FileVO> fileList = UploadHelper.upload(request, "warterMark", Boolean.TRUE);
            if(Validate.isNotEmpty(fileList)) {
                bbsSetupVo.setWtmkFileNm(fileList.get(0).getFileUrlAddr());
            }

            // 등록 실행
            Integer key = opBbsSetupService.insertBbsSetup(bbsSetupVo);
            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }
        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), "BD_selectBbsSetupList.do");
    }

    /**
     * 게시판환경설정 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsSetup.do")
    public void selectBbsSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {
        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_GLOBAL);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
    }

    /**
     * 게시판환경설정 기본정보 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateBbsGlobalSetupForm.do")
    public void updateBbsSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_GLOBAL);

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
    }

    /**
     * 게시판환경설정 목록설정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateBbsListSetupForm.do")
    public void updateBbsListSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_LIST);

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        // 시스템 설정 페이징 정보 설정(system-commons-config.xml 파일 참조)
        model.addAttribute("RPP_NUM", BaseConfig.DEFAULT_PAGE_NUMS);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
    }

    /**
     * 게시판환경설정 상세조회설정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateBbsViewSetupForm.do")
    public void updateBbsViewSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_VIEW);

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
    }

    /**
     * 게시판환경설정 입력폼설정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateBbsFormSetupForm.do")
    public void updateBbsFormSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_FORM);

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
    }

    /**
     * 게시판환경설정 항목설정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateBbsItemSetupForm.do")
    public void updateBbsItemSetupForm(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_ITEM);

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsSetupService.selectBbsSetup(bbsSetupVo));
        model.addAttribute(BaseConfig.KEY_DATA_LIST, BbsSetupConstant.SEARCH_TYPE_LIST);
    }

    /**
     * 게시판 기본정보 환경설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsSetup.do", method = RequestMethod.POST)
    public String updateBbsSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsSetupVo.setMdfrId(loginVo.getPicId());
            bbsSetupVo.setUpdusrNm(loginVo.getPicNm());
            bbsSetupVo.setIpAddr(getIpAddr());

            // 워터마크 파일
            List<FileVO> fileList = UploadHelper.upload(request, "warterMark", Boolean.TRUE);
            if(Validate.isNotEmpty(fileList)) {
                bbsSetupVo.setNewWtmkFileNm(fileList.get(0).getFileUrlAddr());
            }

            // 수정 실행
            Integer key = opBbsSetupService.updateBbsSetup(bbsSetupVo);
            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        String url = "BD_selectBbsSetup.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 게시판 환경설정 목록설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsListSetup.do", method = RequestMethod.POST)
    public String updateBbsListSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        if(holder.isValid()) {

            // 수정 실행
            int updateCnt = opBbsSetupService.updateBbsListSetup(bbsSetupVo);
            if(updateCnt < 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 게시판 환경설정 상세조회설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsViewSetup.do", method = RequestMethod.POST)
    public String updateBbsViewSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        if(holder.isValid()) {

            // 수정 실행
            int affected = opBbsSetupService.updateBbsViewSetup(bbsSetupVo);
            if(affected < 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 게시판 환경설정 입력폼설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsFormSetup.do", method = RequestMethod.POST)
    public String updateBbsFormSetup(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsSetupVo);
        if(holder.isValid()) {

            // 수정 실행
            int affected = opBbsSetupService.updateBbsFormSetup(bbsSetupVo);
            if(affected < 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 게시판 환경설정 항목설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsItemSetup.do", method = RequestMethod.POST)
    public String updateBbsItemSetup(HttpServletRequest request, Model model, BbsSetupItemVO bbsItemVo) {

        // 수정 실행
        int affected = opBbsSetupService.updateBbsItemSetup(request, bbsItemVo);
        if(affected < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 게시판환경설정 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteBbsSetupList.do", method = RequestMethod.POST)
    public String deleteBbsSetupList(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        Integer affected = opBbsSetupService.deleteBbsSetupList(bbsSetupVo);
        String url = "BD_selectBbsSetupList.do?" + OpHelper.getSearchQueryString(request);

        String msg = "";
        if(affected > 0) {
            msg = "사용중인 " + affected + "개의 설정은 삭제되지 않았습니다.";
        } else if(affected == 0) {
            msg = MessageUtil.getMessage("common.deleteOk");
        }
        opBbsSetupService.reloadCache();

        return alertAndRedirect(model, msg, url);
    }

    /**
     * 게시판 옵션별 사용여부 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateUseYn.do", method = RequestMethod.POST)
    public String updateUseYn(HttpServletRequest request, Model model, BbsSetupVO bbsSetupVo) {

        // 선택한 컬럼명에 맞춰 사용여부 상태값 자동설정
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(bbsSetupVo.getColId(), bbsSetupVo.getUseYn());
        ReflectionUtil.fieldsBind(bbsSetupVo, dataMap);

        Integer affected = opBbsSetupService.updateUseYn(bbsSetupVo);

        if(affected == 0) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsSetupService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

}
