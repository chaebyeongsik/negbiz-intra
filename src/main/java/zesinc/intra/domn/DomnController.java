/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.domn;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.domn.domain.DomnOrgVO;
import zesinc.intra.domn.domain.DomnVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 도메인 정보 컨트롤러 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-27.    방기배   최초작성
 * </pre>
 * @see
 */
@Controller("사이트관리")
@RequestMapping(value = { "/intra/domn" })
public class DomnController extends IntraController {

    @Resource(name = "opDomnService")
    private DomnService opDomnService;

    /**
     * 담당자목록 조회(Auto Complete 용)
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectMngrList.do")
    public String selectMngrList(HttpServletRequest request, Model model, DomnOrgVO domnOrgVo) {

        List<DomnOrgVO> orgList = opDomnService.selectMngrList(domnOrgVo);

        return responseJson(model, orgList);
    }

    /**
     * 도메인 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectDomnList.do")
    public void selectDomnList(HttpServletRequest request, Model model, DomnVO domnVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opDomnService.selectDomnList(domnVo));
    }

    /**
     * 도메인 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_insertDomnForm.do" })
    public void insertDomnForm(HttpServletRequest request, Model model, DomnVO domnVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(domnVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 도메인 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertDomn.do", method = RequestMethod.POST)
    public String insertDomn(HttpServletRequest request, Model model, DomnVO domnVo)
        throws Exception {

        String url = "BD_selectDomnList.do?" + OpHelper.getSearchQueryString(request);

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(domnVo);
        if(holder.isValid()) {

            LoginVO loginVo = (LoginVO) getMgrSession();
            domnVo.setRgtrId(loginVo.getPicId());
            domnVo.setIpAddr(getIpAddr());

            // 등록 실행
            Integer cnt = opDomnService.insertDomn(domnVo);
            if(cnt.equals(-1)) {
                return alertAndRedirect(model, MessageUtil.getMessage("common.overlapCode"), url);
            }
            if(cnt != 1) {
                return alertAndRedirect(model, MessageUtil.getMessage("common.processFail"), url);
            }

            // 캐시 리로드
            opDomnService.reloadCache();
        } else {
            return alertAndRedirect(model, MessageUtil.getMessage("common.validateFail"), url);
        }

        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 도메인 수정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "INC_updateDomnForm.do" })
    public void updateDomnForm(HttpServletRequest request, Model model, DomnVO domnVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(domnVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opDomnService.selectDomn(domnVo));
    }

    /**
     * 도메인 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateDomn.do", method = RequestMethod.POST)
    public String updateDomn(HttpServletRequest request, Model model, DomnVO domnVo) {

        DomnVO dataVo = opDomnService.selectDomn(domnVo);
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(domnVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                domnVo.setMdfrId(loginVo.getPicId());
                domnVo.setIpAddr(getIpAddr());

                // 수정 실행
                Integer cnt = opDomnService.updateDomn(domnVo);
                if(cnt.equals(-1)) {
                    return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.overlapCode"));
                }
                if(cnt != 1) {
                    return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
                }

                // 캐시 리로드
                opDomnService.reloadCache();
            } else {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
            }
        } else {
            return responseJson(model, MessageUtil.getMessage("common.notAllow"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 도메인 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteDomn.do", method = RequestMethod.POST)
    public String deleteDomn(HttpServletRequest request, Model model, DomnVO domnVo) {

        String url = "BD_selectDomnList.do?" + OpHelper.getSearchQueryString(request);

        DomnVO dataVo = opDomnService.selectDomn(domnVo);
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 삭제 실행
            Integer cnt = opDomnService.deleteDomn(domnVo);
            if(cnt.equals(-1)) {
                return alertAndRedirect(model, "레이아웃관리에서 사이트 레이아웃을 먼저 삭제하세요.(개발자 처리)", url);
            } else if(cnt.equals(-2)) {
                return alertAndRedirect(model, "사용자메뉴관리에서 사이트 사용자메뉴를 먼저 삭제하세요.", url);
            }
            if(cnt != 1) {
                return alertAndRedirect(model, MessageUtil.getMessage("common.processFail"), url);
            }

            // 캐시 리로드
            opDomnService.reloadCache();
        } else {
            return alertAndRedirect(model, MessageUtil.getMessage("common.notAllow"), url);
        }

        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

}
