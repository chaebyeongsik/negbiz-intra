/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.PasswdUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;

/**
 * 담당자 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("담당자 관리")
@RequestMapping(value = { "/intra/mngr" })
public class MngrController extends IntraController {

    @Resource(name = "opMngrService")
    private MngrService opMngrService;

    /**
     * 담당자 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMngrList.do")
    public void selectMngrTreeList(HttpServletRequest request, Model model, MngrVO mngrVo) {

    }

    /**
     * 담당자 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "INC_selectMngrList.do" })
    public void selectMngrList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opMngrService.selectMngrPageList(mngrVo));
    }

    /**
     * 담당자 검색 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "PD_selectMngrList.do" })
    public void selectMngrPDList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opMngrService.selectMngrPageList(mngrVo));
    }

    /**
     * 담당자 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "PD_insertMngrForm.do" })
    public void insertMngrPDForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

    }

    /**
     * 담당자 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_insertMngrForm.do" })
    public void insertMngrINCForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        return;
    }

    /**
     * 담당자 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertMngr.do", method = RequestMethod.POST)
    public String insertMngr(HttpServletRequest request, Model model, MngrVO mngrVo)
        throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        if(holder.isValid()) {

            // 중복코드검사
            if(0 < opMngrService.selectDplctChckId(mngrVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.dplctChckFalse"));
            }

            // 비밀번호 유효성 체크
            if(!PasswdUtil.isAllowPasswd(mngrVo.getPicPswd())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
            }

            LoginVO loginVo = (LoginVO) getMgrSession();
            mngrVo.setRgtrId(loginVo.getPicId());
            mngrVo.setIpAddr(getIpAddr());

            // 등록 실행
            Integer key = opMngrService.insertMngr(mngrVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 담당자 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "PD_updateMngrForm.do" })
    public void updateMngrForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opMngrService.selectMngr(mngrVo));
    }

    /**
     * 담당자 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateMngr.do", method = RequestMethod.POST)
    public String updateMngr(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        if(holder.isValid()) {

            LoginVO loginVo = (LoginVO) getMgrSession();
            mngrVo.setMdfrId(loginVo.getPicId());
            mngrVo.setIpAddr(getIpAddr());

            // 새 비밀번호로 변경시
            if(Validate.isNotEmpty(mngrVo.getChangePasswd())) {
                // 비밀번호 유효성 체크
                if(!PasswdUtil.isAllowPasswd(mngrVo.getChangePasswd())) {
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
                }
                // 새 비밀번호 확인
                if(Validate.isEmpty(mngrVo.getConfirmPasswd()) || !mngrVo.getChangePasswd().equals(mngrVo.getConfirmPasswd())) {
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
                }
            }

            // 수정 실행
            Integer key = opMngrService.updateMngr(mngrVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }

        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 담당자본인 수정 폼
     */
    @RequestMapping(value = { "PD_updateChargerForm.do" })
    public void updateChargerForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        mngrVo.addParam("q_picId", loginVo.getPicId());
        MngrVO dataVo = opMngrService.selectMngr(mngrVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getPicId())) {
            // 스크립트단 validator 정보
            ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
            model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

            model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);
        }
    }

    /**
     * 담당자본인 수정
     * @throws Exception
     */
    @RequestMapping(value = "ND_updateCharger.do", method = RequestMethod.POST)
    public String updateCharger(HttpServletRequest request, Model model, MngrVO mngrVo) throws Exception {

        LoginVO loginVo = (LoginVO) getMgrSession();
        mngrVo.addParam("q_picId", loginVo.getPicId());
        MngrVO dataVo = opMngrService.selectMngr(mngrVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getPicId())) {

            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
            if(holder.isValid()) {
                mngrVo.setMdfrId(loginVo.getPicId());
                mngrVo.setIpAddr(getIpAddr());

                // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
                if (Validate.isNotEmpty(mngrVo.getPicPswd())) {
                    mngrVo.setPicPswd(CryptoUtil.decrypt(String.valueOf(mngrVo.getPicPswd())));
                }
                if (Validate.isNotEmpty(mngrVo.getChangePasswd())) {
                    mngrVo.setChangePasswd(CryptoUtil.decrypt(String.valueOf(mngrVo.getChangePasswd())));
                }
                if (Validate.isNotEmpty(mngrVo.getConfirmPasswd())) {
                    mngrVo.setConfirmPasswd(CryptoUtil.decrypt(String.valueOf(mngrVo.getConfirmPasswd())));
                }

                // 기존 비밀번호 확인 PicPswd
                //if(!PasswdUtil.matche(mngrVo.getPicPswd(), dataVo.getPicPswd())) {
                if(!PasswdUtil.matches(mngrVo.getPicPswd(), dataVo.getPicPswd())) {
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
                }

                // 새 비밀번호로 변경시
                if(Validate.isNotEmpty(mngrVo.getChangePasswd())) {
                    // 비밀번호 유효성 체크
                    if(!PasswdUtil.isAllowPasswd(mngrVo.getChangePasswd())) {
                        return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
                    }
                    // 새 비밀번호 확인
                    if(Validate.isEmpty(mngrVo.getConfirmPasswd()) || !mngrVo.getChangePasswd().equals(mngrVo.getConfirmPasswd())) {
                        return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
                    }
                }

                // 수정 실행
                Integer key = opMngrService.updateCharger(mngrVo);
                if(key != 1) {
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
        }
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 인증서 등록 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "PD_updateMngrCrtfcAcntForm.do" })
    public void updateMngrCrtfcAcntForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        LoginVO user = (LoginVO) OpHelper.getMgrSession();
        mngrVo.addParam("q_picId", user.getPicId());
        MngrVO dataVo = opMngrService.selectMngr(mngrVo);

        if(AuthSupport.isAuth(dataVo.getPicId())) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);
        }
    }

    /**
     * 인증서 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "ND_updateMngrCrtfcAcnt.do" })
    public String updateMngrCrtfcAcnt(HttpServletRequest request, HttpServletResponse response, Model model, MngrVO mngrVo) {

        LoginVO user = (LoginVO) OpHelper.getMgrSession();
        mngrVo.addParam("q_picId", user.getPicId());
        MngrVO dataVo = opMngrService.selectMngr(mngrVo);

        if(AuthSupport.isAuth(dataVo.getPicId())) {

            GPKIHttpServletResponse gpkiresponse = null;
            GPKIHttpServletRequest gpkirequest = null;

            try {
                gpkiresponse = new GPKIHttpServletResponse(response);
                gpkirequest = new GPKIHttpServletRequest(request);
                gpkiresponse.setRequest(gpkirequest);

                X509Certificate cert = null;
                // 인증서 명칭
                String acntNm = null;

                int message_type = gpkirequest.getRequestMessageType();
                if(message_type == GPKIHttpServletRequest.ENCRYPTED_SIGNDATA || message_type == GPKIHttpServletRequest.LOGIN_ENVELOP_SIGN_DATA ||
                    message_type == GPKIHttpServletRequest.ENVELOP_SIGNDATA || message_type == GPKIHttpServletRequest.SIGNED_DATA) {

                    cert = gpkirequest.getSignerCert();
                    acntNm = cert.getSubjectDN();
                    // 인증서
                    mngrVo.setAcntNm(acntNm);

                    // 이전에 동일 인증서가 등록되어 있으면 삭제한다.
                    opMngrService.updateCrtfcAcnt(mngrVo);
                    // 인증서가 등록
                    Integer key = opMngrService.updateMngrCrtfcAcnt(mngrVo);
                    if(key != 1) {
                        return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                    }
                }
            } catch (Exception e) {
                logger.error("인증서 등록 오류. Exception : {}", e);
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
            return alertAndClose(model, MessageUtil.getMessage("common.updateOk"));
        }
        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 담당자 삭제
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteMngr.do", method = RequestMethod.POST)
    public String deleteMngr(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 삭제 실행
        Integer cnt = opMngrService.deleteMngr(mngrVo);
        if(cnt != 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 담당자 삭제(목록에서 다중삭제)
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteListMngr.do", method = RequestMethod.POST)
    public String deleteListMngr(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 삭제 실행
        Integer key = opMngrService.deleteListMngr(mngrVo);
        if(key < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 담당자 사용여부 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateUseYn.do", method = RequestMethod.POST)
    public String updateUseYn(Model model, HttpServletRequest request, MngrVO mngrVo) {

        LoginVO loginVo = (LoginVO) OpHelper.getMgrSession();
        mngrVo.setMdfrId(loginVo.getPicId());

        // 수정
        Integer key = opMngrService.updateUseYn(mngrVo);
        if(key != 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 담당자아이디 중복 검사
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectDplctChckId.do")
    public String selectDplctChckId(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validateField(mngrVo, "picId");
        if(!holder.isValid()) {
            if(mngrVo.getPicId().length() > 30 || mngrVo.getPicId().length() < 2) {
                String range[] = {"2", "30"};
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("validator.rangelength", range));
            } else {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("validator.alphanumeric"));
            }
        } else {
            if(opMngrService.selectDplctChckId(mngrVo) == 0) {
                return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
            } else {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.dplctChckFalse"));
            }
        }
    }

    /**
     * 비밀번호 유효성 체크
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @RequestMapping(value = "ND_allowPasswd.do")
    public String allowPasswd(HttpServletRequest request, Model model, MngrVO mngrVo) {

        String passwd = (String) mngrVo.getParam("q_chargerPasswd");
        boolean result = PasswdUtil.isAllowPasswd(passwd);

        return responseJson(model, result);
    }

    /**
     * 담당자 부서이동 폼
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_updateDeptTrnsferForm.do")
    public void updateDeptTrnsferForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(mngrVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opMngrService.selectDeptTrnsferList(mngrVo));

        return;
    }

    /**
     * 담당자 부서이동
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateDeptTrnsfer.do")
    public String updateDeptTrnsfer(HttpServletRequest request, Model model, MngrVO mngrVo) {

        LoginVO loginVo = (LoginVO) OpHelper.getMgrSession();
        mngrVo.setMdfrId(loginVo.getPicId());

        // 수정
        Integer key = opMngrService.updateDeptTrnsfer(mngrVo);
        if(key < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 권한지정 폼
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "PD_updateAuthorAsgnForm.do")
    public void updateAuthorAsgnForm(HttpServletRequest request, Model model, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opMngrService.selectAuthorList(mngrVo));
        model.addAttribute("authorAsgnList", opMngrService.selectAuthorAsgnList(mngrVo));

    }

    /**
     * 담당자 권한지정
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateAuthorAsgn.do")
    public String updateAuthorAsgn(HttpServletRequest request, Model model, MngrVO mngrVo) {

        LoginVO loginVo = (LoginVO) OpHelper.getMgrSession();
        mngrVo.setRgtrId(loginVo.getPicId());

        // 수정
        Integer key = opMngrService.updateAuthorAsgn(mngrVo);
        if(key < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));

    }

    /**
     * 담당자 권한할당된 메뉴목록
     *
     * @param request
     * @param model
     * @param mngrVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectAuthorAsgnMenuList.do")
    public void selectAuthorAsgnMenuList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        // 권한코드 목록
        model.addAttribute(BaseConfig.KEY_AUTH_LIST, AuthSupport.getAuthCodeList());
        // 권한할당된 메뉴목록
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opMngrService.selectAuthorAsgnMenuList(mngrVo));
        // 할당된 권한목록
        model.addAttribute("authorAsgnList", opMngrService.selectAuthorAsgnList(mngrVo));

    }

    /**
     * 비밀번호 보안등급정책 팝업
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_ScrtyGrad.do")
    public void scrtyGradPop(HttpServletRequest request, Model model) {
        String SPECIAL_CHAR = Config.getString("passwd-config.passwdChars.specials");
        int PASSWD_MIN_LENGTH = Config.getInt("passwd-config.passwdMinLength");
        int PASSWD_MAX_LENGTH = Config.getInt("passwd-config.passwdMaxLength");

        model.addAttribute("SPECIAL_CHAR", SPECIAL_CHAR);
        model.addAttribute("PASSWD_MIN_LENGTH", PASSWD_MIN_LENGTH);
        model.addAttribute("PASSWD_MAX_LENGTH", PASSWD_MAX_LENGTH);
    }
}
