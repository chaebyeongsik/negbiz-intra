/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.grad;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.user.grad.domain.UserGradVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자 등급 관리
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 8.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자 등급 관리")
@RequestMapping(value = { "/intra/user/grad" })
public class UserGradController extends IntraController {

    @Resource(name = "opUserGradService")
    private UserGradService opUserGradService;

    /**
     * ajax 방식의 등급 중복 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDplctChckGrad.do")
    public String selectDplctChckGrad(HttpServletRequest request, Model model, UserGradVO userGradVo) {

        Integer cnt = opUserGradService.selectDplctChckGrad(userGradVo);
        ValidateResultHolder holder = ValidateUtil.validateField(userGradVo, "userGrdCdId");

        if(cnt == 0 && holder.isValid()) {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
        }
        if(!holder.isValid()) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.dplctChckFalse"));
    }

    /**
     * 사용자 등급 메인화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectUserGradList.do")
    public void selectUserGradList(HttpServletRequest request, Model model, UserGradVO userGradVo) {
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opUserGradService.selectUserGradList(userGradVo));
    }

    /**
     * 사용자 등급 상세화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateUserGradForm.do")
    public void updateUserGradForm(HttpServletRequest request, Model model, UserGradVO userGradVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userGradVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserGradService.selectUserGrad(userGradVo));
    }

    /**
     * 사용자 등급 등록화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_insertUserGradForm.do")
    public void insertUserGradForm(HttpServletRequest request, Model model, UserGradVO userGradVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userGradVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 사용자 등급 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertUserGrad.do", method = RequestMethod.POST)
    public String insertUserGrad(HttpServletRequest request, Model model, UserGradVO userGradVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userGradVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            userGradVo.setRgtrId(loginVo.getPicId());

            // 등록 실행
            key = opUserGradService.insertUserGrad(userGradVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 사용자 등급 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateUserGrad.do", method = RequestMethod.POST)
    public String updateUserGrad(HttpServletRequest request, Model model, UserGradVO userGradVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userGradVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            userGradVo.setMdfrId(loginVo.getPicId());

            key = opUserGradService.updateUserGrad(userGradVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

}
