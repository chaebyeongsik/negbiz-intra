/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userInfoIemEstbs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.user.support.UserType;
import zesinc.intra.user.userInfoIemEstbs.domain.UserInfoIemEstbsVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자정보항목설정 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자정보항목설정 관리")
@RequestMapping(value = { "/intra/user/userInfoIemEstbs" })
public class UserInfoIemEstbsController extends IntraController {

    @Resource(name = "opUserInfoIemEstbsService")
    private UserInfoIemEstbsService opUserInfoIemEstbsService;

    /**
     * 사용자정보항목설정 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectUserInfoIemEstbsList.do")
    public void selectUserInfoIemEstbsList(HttpServletRequest request, Model model, UserInfoIemEstbsVO userInfoIemEstbsVo) {

        //model.addAttribute(BaseConfig.KEY_DATA_LIST, opUserInfoIemEstbsService.selectUserInfoIemEstbsList(userInfoIemEstbsVo));
    }

    /**
     * 사용자정보항목설정 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateUserInfoIemEstbs.do", method = RequestMethod.POST)
    public String updateUserInfoIemEstbs(HttpServletRequest request, Model model, UserInfoIemEstbsVO userInfoIemEstbsVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userInfoIemEstbsVo);
        if(holder.isValid()) {
            // 수정 실행
            Integer key = opUserInfoIemEstbsService.updateUserInfoIemEstbs(userInfoIemEstbsVo);
            if(key < 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectUserInfoIemEstbsList.do?userTypeNm="+userInfoIemEstbsVo.getUserTypeNm();
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        
    }

    /**
     * 사용자정보항목설정 목록 - 인클루드
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_selectUserInfoIemEstbs.do")
    public void selectUserInfoIemEstbs(HttpServletRequest request, Model model, UserInfoIemEstbsVO userInfoIemEstbsVo) {
        if(Validate.isEmpty(userInfoIemEstbsVo.getUserTypeNm())){
            userInfoIemEstbsVo.setUserTypeNm(UserType.INDVDL.getUserType());
        }
        model.addAttribute("estbsList", opUserInfoIemEstbsService.selectUserInfoIemEstbsList(userInfoIemEstbsVo));
    }
}
