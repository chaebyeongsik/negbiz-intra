/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mymenu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.mymenu.domain.MyMenuVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 내메뉴 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-21.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
@Controller("바로가기관리")
@RequestMapping(value = { "/intra/mymenu" })
public class MyMenuController extends IntraController {

    @Resource(name = "opMyMenuService")
    private MyMenuService opMyMenuService;

    /**
     * 내메뉴 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMyMenuList.do")
    public void selectMyMenuList(HttpServletRequest request, Model model, MyMenuVO myMenuVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        myMenuVo.setPicId(loginVo.getPicId());

        model.addAttribute(BaseConfig.KEY_BASE_LIST, opMyMenuService.selectAuthMenuList(myMenuVo));
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opMyMenuService.selectMyMenuList(myMenuVo));
    }

    /**
     * 내메뉴 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertMyMenu.do", method = RequestMethod.POST)
    public String insertMyMenu(HttpServletRequest request, Model model, MyMenuVO myMenuVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(myMenuVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            myMenuVo.setPicId(loginVo.getPicId());

            Integer key = opMyMenuService.insertMyMenu(myMenuVo);
            if(key < -1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 내메뉴 세션 케시를 다시 생성하기 위하여 세션을 삭제시킴(IntraMenuSelector에서 값이 없는 경우 자동 생성)
        OpHelper.removeSession(request, BaseConfig.MY_MENU_KEY);

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

}
