/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userManage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.user.grad.UserGradService;
import zesinc.intra.user.hist.UserHistService;
import zesinc.intra.user.hist.domain.UserHistCheckResultVO;
import zesinc.intra.user.support.UserConsts;
import zesinc.intra.user.userInfoIemEstbs.UserInfoIemEstbsService;
import zesinc.intra.user.userManage.domain.UserManageVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.PasswdUtil;
import zesinc.web.utils.RequestUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자 정보 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 27.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자 관리")
@RequestMapping(value = { "/intra/user/userManage" })
public class UserManageController extends IntraController {

    @Resource(name = "opUserManageService")
    private UserManageService opUserManageService;

    @Resource(name = "opUserGradService")
    private UserGradService opUserGradService;

    @Resource(name = "opUserHistService")
    private UserHistService opUserHistService;

    @Resource(name = "opUserInfoIemEstbsService")
    private UserInfoIemEstbsService opUserInfoIemEstbsService;

    /**
     * 사용자 페이지 목록
     *
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectUserList.do")
    public String selectUserList(HttpServletRequest request, Model model, UserManageVO userManageVo) throws Exception {

        // 로그타입이 검색로그, 사용여부도 사용일 경우 벨리데이션 체크
        if(UserConsts.LOG_TYPE.equals("searchLog") && UserConsts.LOG_USE_YN.equals("Y")) {
            boolean accessVal = opUserHistService.accessValidate(request);
            if(accessVal) opUserHistService.removeValidate(request); // 벨리데이션 세션 삭제

            UserHistCheckResultVO checkResult = opUserHistService.checkSearchCnd(request, userManageVo.getParamMap());
            // 이전의 검색조건과 같지 않은 검색조건이 있는데, 벨리데이션을 통과하지 못한 경우
            if(!checkResult.isEquals() && !accessVal) {
                return alertAndRedirect(model, "잘못된 접근입니다.", request.getRequestURI());
            }
        }

        // 처음에 들어온 경우(파라미터가 없는 경우)
        if(Validate.isEmpty(RequestUtil.getQueryString(request))) {
            // 초기목록이 보이는 케이스
            if(UserConsts.INIT_LIST_SHOW.equals("Y")) {
                model.addAttribute(BaseConfig.KEY_PAGER, opUserManageService.selectUserList(userManageVo));
            }
        } else {
            model.addAttribute(BaseConfig.KEY_PAGER, opUserManageService.selectUserList(userManageVo));
        }

        model.addAttribute("userGradInUseList", opUserGradService.selectUserGradInUseList());
        model.addAttribute("userGradList", opUserGradService.selectUserGradList());

        // 검색조건이 있고, 벨리데이션 통과된 경우만

        return "/intra/user/userManage/BD_selectUserList";
    }

    /**
     * 개인 사용자 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "PD_insertIndvdlUserForm.do")
    public void insertIndvdlUserForm(HttpServletRequest request, Model model, UserManageVO userManageVo) {

        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute("userGradList", opUserGradService.selectUserGradInUseList());
        model.addAttribute("indvdlUserIemEstbs", opUserInfoIemEstbsService.selectIndvdlUserInfoIemEstbsYn());
    }

    /**
     * 개인 사용자 등록 처리
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertIndvdlUser.do", method = RequestMethod.POST)
    public String insertIndvdlUser(HttpServletRequest request, Model model, UserManageVO userManageVo) throws Exception {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        if(holder.isValid()) {
            // 자동작성방지 검사...skip...
            // 중복아이디 검사
            if(0 < opUserManageService.selectDupCheckUserId(userManageVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userManageVo.getUserPswd())) {
                userManageVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userManageVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userManageVo.getConfirmPassword())) {
                userManageVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userManageVo.getConfirmPassword())));
            }

            // 비밀번호 유효성 체크
            if(!PasswdUtil.isAllowPasswd(userManageVo.getUserPswd())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
            }

            // 비밀번호와 비밀번호 확인 일치 검사
            if(!userManageVo.getUserPswd().equals(userManageVo.getConfirmPassword())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
            }

            // 등록자 아이디 셋팅
            LoginVO loginVo = (LoginVO) getMgrSession();
            userManageVo.setRgtrId(loginVo.getPicId());

            Integer key = opUserManageService.insertIndvdlUser(userManageVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 개인 사용자 정보 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "PD_updateIndvdlUserForm.do")
    public void updateIndvdlUserForm(HttpServletRequest request, Model model, UserManageVO userManageVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserManageService.selectIndvdlUserInfo(userManageVo));
        model.addAttribute("userGradList", opUserGradService.selectUserGradInUseList());

        model.addAttribute("indvdlUserIemEstbs", opUserInfoIemEstbsService.selectIndvdlUserInfoIemEstbsYn());
    }

    /**
     * 개인 사용자 정보 수정 처리
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateIndvdlUser.do", method = RequestMethod.POST)
    public String updateIndvdlUser(HttpServletRequest request, Model model, UserManageVO userManageVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        if(holder.isValid()) {
            // 등록자 아이디 셋팅
            LoginVO loginVo = (LoginVO) getMgrSession();
            userManageVo.setMdfrId(loginVo.getPicId());
            Integer key = 0;
            key = opUserManageService.updateIndvdlUser(userManageVo);

            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 기업 사용자 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "PD_insertEntrprsUserForm.do")
    public void insertEntrprsUserForm(HttpServletRequest request, Model model, UserManageVO userManageVo) {

        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute("entrprsUserIemEstbs", opUserInfoIemEstbsService.selectEntrprsUserInfoIemEstbsYn());
    }

    /**
     * 기업 사용자 등록 처리
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertEntrprsUser.do")
    public String insertEntrprsUser(HttpServletRequest request, Model model, UserManageVO userManageVo) throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        if(holder.isValid()) {
            // 자동작성방지 검사...skip...
            // 중복아이디 검사
            if(0 < opUserManageService.selectDupCheckUserId(userManageVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userManageVo.getUserPswd())) {
                userManageVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userManageVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userManageVo.getConfirmPassword())) {
                userManageVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userManageVo.getConfirmPassword())));
            }

            // 비밀번호 유효성 체크
            if(!PasswdUtil.isAllowPasswd(userManageVo.getUserPswd())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
            }

            // 비밀번호와 비밀번호 확인 일치 검사
            if(!userManageVo.getUserPswd().equals(userManageVo.getConfirmPassword())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
            }

            // 등록자 아이디 셋팅
            LoginVO loginVo = (LoginVO) getMgrSession();
            userManageVo.setRgtrId(loginVo.getPicId());

            Integer key = opUserManageService.insertEntrprsUser(userManageVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 기업사용자 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "PD_updateEntrprsUserForm.do")
    public void updateEntrprsUserForm(HttpServletRequest request, Model model, UserManageVO userManageVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserManageService.selectEntrprsUserInfo(userManageVo));
        model.addAttribute("entrprsUserIemEstbs", opUserInfoIemEstbsService.selectEntrprsUserInfoIemEstbsYn());
    }

    /**
     * 기업사용자 수정 처리
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateEntrprsUser.do")
    public String updateEntrprsUser(HttpServletRequest request, Model model, UserManageVO userManageVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userManageVo);
        if(holder.isValid()) {
            // 등록자 아이디 셋팅
            LoginVO loginVo = (LoginVO) getMgrSession();
            userManageVo.setMdfrId(loginVo.getPicId());
            Integer key = opUserManageService.updateEntrprsUser(userManageVo);

            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 사용자 아이디 중복체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDupCheckUserId.do", method = RequestMethod.POST)
    public String selectDupCheckUserId(HttpServletRequest request, Model model, UserManageVO userManageVo) {
        return responseJson(model, opUserManageService.selectDupCheckUserId(userManageVo));
    }

    /**
     * 사용자 삭제
     *
     * @param request
     * @param model
     * @param userManageVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteUser.do", method = RequestMethod.POST)
    public String deleteEntrprsUser(HttpServletRequest request, Model model, UserManageVO userManageVo) {
        int result = opUserManageService.deleteEntrprsUser(userManageVo);
        if(result < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

}
