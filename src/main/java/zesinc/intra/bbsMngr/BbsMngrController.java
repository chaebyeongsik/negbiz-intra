/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsMngr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.common.cache.bbsconfig.impl.BbsConfigCache;
import zesinc.core.cache.CacheService;
import zesinc.core.lang.Validate;
import zesinc.intra.bbsMngr.domain.BbsMngrVO;
import zesinc.intra.cms.support.CmsReference;
import zesinc.intra.cms.support.domain.CmsReferenceVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import zesinc.web.vo.cache.BbsDomnCacheVO;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 게시판설정 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-20.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판설정관리")
@RequestMapping(value = { "/intra/bbsMngr" })
@CmsReference(name = "게시판", method = "getCmsRefrence")
public class BbsMngrController extends IntraController {

    @Resource(name = "opBbsMngrService")
    private BbsMngrService opBbsMngrService;

    /**
     * 사용자 메뉴관리에서 본 기능의 링크를 획득하기 위한 함수
     *
     * @return 게시판 목록(url, 게시판명)
     */
    public List<CmsReferenceVO> getCmsRefrence(CmsReferenceVO cmsRVO) {
        // 게시판 목록
        @SuppressWarnings("unchecked")
        List<BbsConfigCacheVO> boardConfList = (List<BbsConfigCacheVO>) CacheService.get(BbsConfigCache.BBS_CACHE_KEY);

        List<CmsReferenceVO> reference = new ArrayList<CmsReferenceVO>();
        CmsReferenceVO cmsReferenceVO;

        for(BbsConfigCacheVO boardConf : boardConfList) {
            Map<Integer, BbsDomnCacheVO> skinMap = boardConf.getSkins();
            if(Validate.isNotEmpty(skinMap) && Validate.isNotEmpty(skinMap.get(cmsRVO.getSiteSn()))) {
                cmsReferenceVO = new CmsReferenceVO();
                /*
                 * 각 구분마다의 키를 할당한다. 게시판은 당연히 게시판 코드
                 * 키로 사용할 무언가가 없다면 임의의 문자열을 설정한다.
                 * 예 : notice 와 같이 서비스명 등을 등록한다.
                 * 필수로 key 를 등록해야 한다. 없는 경우 CMS에서 오작동 함
                 */
                cmsReferenceVO.setKey(String.valueOf(boardConf.getBbsSn()));
                cmsReferenceVO.setName(boardConf.getBbsNm());
                // 대표 URL
                cmsReferenceVO.setUserMainMenuUrl("/user/bbs/BD_selectBbsList.do?q_bbsSn=" + boardConf.getBbsSn());
                // 자식 URL
                List<String> lwprtMenuUrlAddr = new ArrayList<String>();
                lwprtMenuUrlAddr.add("/user/bbs/BD_selectBbs.do?q_bbsSn=" + boardConf.getBbsSn());
                lwprtMenuUrlAddr.add("/user/bbs/BD_insertBbsForm.do?q_bbsSn=" + boardConf.getBbsSn());
                lwprtMenuUrlAddr.add("/user/bbs/BD_updateBbsForm.do?q_bbsSn=" + boardConf.getBbsSn());
                lwprtMenuUrlAddr.add("/user/bbs/BD_replyBbsForm.do?q_bbsSn=" + boardConf.getBbsSn());
                // lwprtMenuUrlAddr.add("/user/bbs/BD_passwordBbsForm.do?q_bbsSn=" +
                // boardConf.getBbsSn());

                cmsReferenceVO.setLwprtMenuUrlAddr(lwprtMenuUrlAddr);

                cmsReferenceVO.setMngrMenuUrlAddr("/intra/bbs/PD_selectBbsList.do?q_bbsSn=" + boardConf.getBbsSn());

                reference.add(cmsReferenceVO);
            }
        }

        return reference;
    }

    /**
     * 게시판설정 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsMngr.do")
    public void selectBbsMngr(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsMngrService.selectBbsMngr(bbsMngrVo));
    }

    /**
     * 게시판설정 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsMngrList.do")
    public void selectBbsMngrList(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        // 도메인 목록
        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        // 게시판환경설정목록
        model.addAttribute("bbsSetupList", opBbsMngrService.selectBbsSetupList(bbsMngrVo));

        model.addAttribute(BaseConfig.KEY_PAGER, opBbsMngrService.selectBbsMngrList(bbsMngrVo));
    }

    /**
     * 게시판설정 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_insertBbsMngrForm.do" })
    public void insertBbsMngrForm(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        ValidateResultHolder holder = ValidateUtil.validate(bbsMngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        // 도메인 목록
        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        // 게시판 템플릿 종류
        model.addAttribute("listTmplats", opBbsMngrService.selectBbsTmplatList("L"));
        model.addAttribute("redngTmplats", opBbsMngrService.selectBbsTmplatList("V"));
        model.addAttribute("formTmplats", opBbsMngrService.selectBbsTmplatList("F"));

        // 게시판도메인 목록
        model.addAttribute("bbsDomnList", opBbsMngrService.selectBbsDomnList(bbsMngrVo));
        // 게시판환경설정목록
        model.addAttribute("bbsSetupList", opBbsMngrService.selectBbsSetupList(bbsMngrVo));
    }

    /**
     * 게시판설정 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertBbsMngr.do", method = RequestMethod.POST)
    public String insertBbsMngr(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsMngrVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsMngrVo.setRgtrId(loginVo.getPicId());
            bbsMngrVo.setRgtrNm(loginVo.getPicNm());

            bbsMngrVo.setIpAddr(getIpAddr());
            
            Integer key = opBbsMngrService.insertBbsMngr(bbsMngrVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsMngrService.reloadCache();

        String url = "BD_selectBbsMngrList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 게시판설정 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_updateBbsMngrForm.do" })
    public void updateBbsMngrForm(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        ValidateResultHolder holder = ValidateUtil.validate(bbsMngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        // 도메인 목록
        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        // 게시판 템플릿 종류
        model.addAttribute("listTmplats", opBbsMngrService.selectBbsTmplatList("L"));
        model.addAttribute("redngTmplats", opBbsMngrService.selectBbsTmplatList("V"));
        model.addAttribute("formTmplats", opBbsMngrService.selectBbsTmplatList("F"));

        // 게시판도메인 목록
        model.addAttribute("bbsDomnList", opBbsMngrService.selectBbsDomnList(bbsMngrVo));
        // 게시판환경설정목록
        model.addAttribute("bbsSetupList", opBbsMngrService.selectBbsSetupList(bbsMngrVo));

        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsMngrService.selectBbsMngr(bbsMngrVo));
    }

    /**
     * 게시판설정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsMngr.do", method = RequestMethod.POST)
    public String updateBbsMngr(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsMngrVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsMngrVo.setMdfrId(loginVo.getPicId());
            bbsMngrVo.setUpdusrNm(loginVo.getPicNm());
            bbsMngrVo.setIpAddr(getIpAddr());

            // 수정 실행
            Integer key = opBbsMngrService.updateBbsMngr(bbsMngrVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsMngrService.reloadCache();

        String url = "BD_updateBbsMngrForm.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 게시판설정 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteBbsMngrList.do", method = RequestMethod.POST)
    public String deleteBbsMngr(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) throws Exception {

        Integer cnt = opBbsMngrService.deleteBbsMngrList(bbsMngrVo);
        if(cnt < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsMngrService.reloadCache();

        String url = "BD_selectBbsMngrList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 게시판 옵션별 사용여부 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateUseYn.do", method = RequestMethod.POST)
    public String updateUseYn(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        bbsMngrVo.setMdfrId(loginVo.getPicId());
        bbsMngrVo.setUpdusrNm(loginVo.getPicNm());
        bbsMngrVo.setIpAddr(getIpAddr());

        Integer affected = opBbsMngrService.updateUseYn(bbsMngrVo);

        if(affected == 0) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        // 게시판 캐쉬를 리로드
        opBbsMngrService.reloadCache();

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 게시판 설정에 따른 썸네일 생성. 크기, 워터마크 설정등 모두 적용.
     * 게시판내의 모든 이미지 파일에 대하여 생성한다.
     * 선택 옵션으로 없는 것만 생성하거나 완전 재생성 두가지로 나뉜다.
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "PD_createThumbNail.do")
    public void createThumbNail(HttpServletRequest request, Model model, BbsMngrVO bbsMngrVo) {

        Integer bbsSn = bbsMngrVo.getBbsSn();
        if(Validate.isNotEmpty(bbsSn)) {
            model.addAttribute(BaseConfig.KEY_DATA_LIST, opBbsMngrService.createThumbNail(bbsMngrVo));
        }
    }
}
