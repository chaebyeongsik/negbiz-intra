/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mssage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.support.UploadHelper;
import zesinc.intra.mssage.domain.MssageVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 메시지 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-09.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("메시지 관리")
@RequestMapping(value = { "/intra/mssage" })
public class MssageController extends IntraController {

    @Resource(name = "opMssageService")
    private MssageService opMssageService;

    /**
     * 메시지 상세
     * @param request
     * @param model
     * @param mssageVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMssage.do")
    public void selectMssage(HttpServletRequest request, Model model, MssageVO mssageVo) {

        // 원본글 상세
        model.addAttribute(BaseConfig.KEY_DATA_VO, opMssageService.selectMssage(mssageVo));
    }

    /**
     * 메시지 페이지 목록
     * @param request
     * @param model
     * @param mssageVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMssageList.do")
    public void selectMssageList(HttpServletRequest request, Model model, MssageVO mssageVo) {
        LoginVO loginVo = (LoginVO) getMgrSession();
        mssageVo.setPicId(loginVo.getPicId());

        model.addAttribute("flag", mssageVo.getFlag());
        model.addAttribute(BaseConfig.KEY_PAGER, opMssageService.selectMssagePageList(mssageVo));
    }

    /**
     * 메시지 입력 폼
     * @param request
     * @param model
     * @param mssageVo
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_insertMssageForm.do" })
    public void insertMssageForm(HttpServletRequest request, Model model, MssageVO mssageVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(mssageVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 메시지 등록
     * @param request
     * @param model
     * @param mssageVo
     * @return
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertMssage.do", method = RequestMethod.POST)
    public String insertMssage(HttpServletRequest request, Model model, MssageVO mssageVo)
        throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(mssageVo);
        if(holder.isValid()) {

            LoginVO loginVo = (LoginVO) getMgrSession();
            mssageVo.setSndptyId(loginVo.getPicId());
            mssageVo.setFileList(UploadHelper.upload(request, "mssage"));

            // 등록 실행
            Integer key = opMssageService.insertMssage(mssageVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 메시지 수신 처리
     * @param request
     * @param model
     * @param mssageVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateRcptnDt.do", method = RequestMethod.POST)
    public String updateRcptnDt(HttpServletRequest request, Model model, MssageVO mssageVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        mssageVo.setPicId(loginVo.getPicId());

        Integer key = opMssageService.updateRcptnDt(mssageVo);
        if(key != 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 메시지 삭제
     * @param request
     * @param model
     * @param mssageVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateMssageDeleteAt.do", method = RequestMethod.POST)
    public String updateMssageDeleteAt(HttpServletRequest request, Model model, MssageVO mssageVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        mssageVo.setPicId(loginVo.getPicId());

        // 삭제 실행
        Integer cnt = opMssageService.updateMssageDeleteAt(mssageVo);
        if(cnt != 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 메시지 목록 삭제
     * @param request
     * @param model
     * @param mssageVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateMssageDeleateAtList.do", method = RequestMethod.POST)
    public String updateMssageDeleateAtList(HttpServletRequest request, Model model, MssageVO mssageVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        mssageVo.setPicId(loginVo.getPicId());

        // 삭제 실행
        Integer cnt = opMssageService.updateMssageDeleateAtList(mssageVo);
        if(cnt < 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }
        return responseJson(model, Boolean.TRUE, cnt, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 수신대상 담당자 검색(자동완성)
     * @param request
     * @param model
     * @param mssageVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectReceptionChargerList.do", method = RequestMethod.POST)
    public String selectReceptionChargerList(HttpServletRequest request, Model model, MssageVO mssageVo) {
        List<MssageVO> chargerList = opMssageService.selectReceptionChargerList(mssageVo);

        return responseJson(model, chargerList);
    }

}
