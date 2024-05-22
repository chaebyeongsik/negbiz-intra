/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.config.Config;
import zesinc.intra.popup.domain.PopupVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.JsonUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 팝업 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-20.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("팝업 관리")
@RequestMapping(value = { "/intra/popup" })
public class PopupController extends IntraController {

    /** javascript 변수명 */
    private static final String POPUP_VAR_NM = "var opPopupConfigList = ";
    private static final String POPUP_SCRIPT = Config.getString("popup-config.script");

    @Resource(name = "opPopupService")
    private PopupService opPopupService;

    /**
     * 팝업 미리보기 메인창
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectPopup.do")
    public void selectPopup(HttpServletRequest request, Model model, PopupVO popupVo) {

        model.addAttribute("DATE", System.currentTimeMillis());
        model.addAttribute(BaseConfig.KEY_DATA_VO, opPopupService.selectPopup(popupVo));
    }

    /**
     * 팝업 미리보기 스크립트
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectPopupPreview.do")
    public String selectPopupPreview(HttpServletRequest request, HttpServletResponse response, Model model, PopupVO popupVo) {

        response.setContentType("text/javascript");

        List<PopupVO> popupList = new ArrayList<PopupVO>();
        popupList.add(opPopupService.selectPopup(popupVo));

        String json = POPUP_VAR_NM + JsonUtil.toJson(popupList);
        json += ";\n\r";
        json += POPUP_SCRIPT;

        return responseText(model, json);
    }

    /**
     * 팝업 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectPopupList.do")
    public void selectPopupList(HttpServletRequest request, Model model, PopupVO popupVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        model.addAttribute(BaseConfig.KEY_PAGER, opPopupService.selectPopupPageList(popupVo));
    }

    /**
     * 팝업 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_insertPopupForm.do" })
    public void insertPopupForm(HttpServletRequest request, Model model, PopupVO popupVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        // 기본값 설정
        popupVo.setyAxis(Config.getInt("popup-config.default-values.yAxis"));
        popupVo.setxAxis(Config.getInt("popup-config.default-values.xAxis"));
        popupVo.setScrollYn(Config.getString("popup-config.default-values.scrollYn"));
        popupVo.setPopupSzChgYn(Config.getString("popup-config.default-values.popupSzChgYnY"));
        popupVo.setPopupRpttSeNo(Config.getString("popup-config.default-values.popupRpttSeNo"));
        popupVo.setPopupTypeNo(Config.getString("popup-config.default-values.popupTypeNo"));
        popupVo.setWrtTypeNm(Config.getString("popup-config.default-values.wrtTypeNm"));
        popupVo.setUseYn(Config.getString("popup-config.default-values.useYnY"));

        model.addAttribute(BaseConfig.KEY_DATA_VO, popupVo);

        ValidateResultHolder holder = ValidateUtil.validate(popupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 팝업 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertPopup.do", method = RequestMethod.POST)
    public String insertPopup(HttpServletRequest request, Model model, PopupVO popupVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(popupVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            popupVo.setRgtrId(loginVo.getPicId());
            popupVo.setRgtrNm(loginVo.getPicNm());
            popupVo.setIpAddr(getIpAddr());
            popupVo.setFileList(UploadHelper.upload(request, "popup", Boolean.TRUE));

            Integer key = opPopupService.insertPopup(popupVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectPopupList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 팝업 수정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_updatePopupForm.do" })
    public void updatePopupForm(HttpServletRequest request, Model model, PopupVO popupVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        ValidateResultHolder holder = ValidateUtil.validate(popupVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opPopupService.selectPopup(popupVo));
    }

    /**
     * 팝업 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updatePopup.do", method = RequestMethod.POST)
    public String updatePopup(HttpServletRequest request, Model model, PopupVO popupVo) {

        PopupVO dataVo = opPopupService.selectPopup(popupVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(popupVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                popupVo.setMdfrId(loginVo.getPicId());
                popupVo.setUpdusrNm(loginVo.getPicNm());
                popupVo.setIpAddr(getIpAddr());
                popupVo.setFileList(UploadHelper.upload(request, "popup", Boolean.TRUE));

                // 수정 실행
                Integer key = opPopupService.updatePopup(popupVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }

            String url = "BD_updatePopupForm.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 팝업 삭제
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deletePopup.do", method = RequestMethod.POST)
    public String deletePopup(HttpServletRequest request, Model model, PopupVO popupVo) {
        PopupVO dataVo = opPopupService.selectPopup(popupVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opPopupService.deletePopup(popupVo);
            if(cnt != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }

            String url = "BD_selectPopupList.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 팝업 위치와 크기 자동 설정 창
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectSizeAndPosition.do")
    public void selectSizeAndPosition(HttpServletRequest request, Model model) {
        // jsp에서 javascript로 처리
    }
}
