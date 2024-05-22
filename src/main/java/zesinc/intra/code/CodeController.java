/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.JsonUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.TreeVO;

/**
 * 코드 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("코드 관리")
@RequestMapping(value = { "/intra/code" })
public class CodeController extends IntraController {

    @Resource(name = "opCodeService")
    private CodeService opCodeService;

    /**
     * ajax 방식의 코드 중복 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDplctChckCode.do")
    public String selectDplctChckCode(HttpServletRequest request, Model model, CodeVO codeVo) {

        Integer cnt = opCodeService.selectDplctChckCode(codeVo);
        ValidateResultHolder holder = ValidateUtil.doFiledValidate(codeVo, "cdId");

        if(cnt == 0 && holder.isValid()) {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
        }
        if(!holder.isValid()) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.dplctChckFalse"));
    }

    /**
     * ajax 방식의 코드선택 중복 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDplctChckCodeChoice.do")
    public String selectDplctChckCodeChoice(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {

        Integer cnt = opCodeService.selectDplctChckCodeChoice(codeChoiceVo);
        ValidateResultHolder holder = ValidateUtil.doFiledValidate(codeChoiceVo, "cdChcId");

        if(cnt == 0 && holder.isValid()) {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
        }
        if(!holder.isValid()) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.dplctChckFalse"));
    }

    /**
     * 코드 메인화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectCodeList.do")
    public void selectCodeBDList(HttpServletRequest request, Model model, CodeVO codeVo) {

    }

    /**
     * 코드 관리화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectCodeList.do")
    public void selectCodePDList(HttpServletRequest request, Model model, CodeVO codeVo) {

    }

    /**
     * 코드 트리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectCodeTreeList.do")
    public String selectCodeTreeList(HttpServletRequest request, Model model, CodeVO codeVo) {

        return responseJson(model, opCodeService.selectCodeTreeList(codeVo));
    }

    /**
     * 코드 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectSearchCodeList.do")
    public void selectSearchCodeList(HttpServletRequest request, Model model, CodeVO codeVo) {

        model.addAttribute(BaseConfig.KEY_BASE_LIST, opCodeService.selectHghrkCdIdList(codeVo));
        if(request.getParameter("q_searchVal") != null) {
            model.addAttribute(BaseConfig.KEY_DATA_LIST, opCodeService.selectCodeList(codeVo));
        } else {
            model.addAttribute(BaseConfig.KEY_DATA_LIST, new ArrayList<String>());
        }
    }

    /**
     * 코드 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_insertCodeForm.do" })
    public void insertCodeForm(HttpServletRequest request, Model model, CodeVO codeVo) {

        ValidateResultHolder holder = ValidateUtil.validate(codeVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 코드 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertCode.do", method = RequestMethod.POST)
    public String insertCode(HttpServletRequest request, Model model, CodeVO codeVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(codeVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            codeVo.setRgtrId(loginVo.getPicId());
            codeVo.setRgtrNm(loginVo.getPicNm());

            // 등록 실행
            key = opCodeService.insertCode(codeVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 코드 수정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "INC_updateCodeForm.do" })
    public void updateCodeForm(HttpServletRequest request, Model model, CodeVO codeVo) {

        ValidateResultHolder holder = ValidateUtil.validate(codeVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opCodeService.selectCode(codeVo));
    }

    /**
     * 코드 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateCode.do", method = RequestMethod.POST)
    public String updateCode(HttpServletRequest request, Model model, CodeVO codeVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(codeVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            codeVo.setMdfrId(loginVo.getPicId());
            codeVo.setUpdusrNm(loginVo.getPicNm());

            key = opCodeService.updateCode(codeVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 코드 정렬순서 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateCodeSortOrder.do")
    public String updateCodeSortOrder(HttpServletRequest request, Model model, CodeVO codeVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        codeVo.setMdfrId(loginVo.getPicId());
        codeVo.setUpdusrNm(loginVo.getPicNm());

        Boolean success = opCodeService.updateCodeSortOrder(codeVo);

        if(!success) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 코드 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteCode.do", method = RequestMethod.POST)
    public String deleteCode(HttpServletRequest request, Model model, CodeVO codeVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        codeVo.setMdfrId(loginVo.getPicId());
        codeVo.setUpdusrNm(loginVo.getPicNm());

        Integer cnt = opCodeService.deleteCode(codeVo);
        if(cnt != 1) {
            return responseJson(model, Boolean.FALSE, codeVo.getParam("q_cdId"), MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, codeVo.getParam("q_cdId"), MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 코드선택 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectCodeChoiceList.do")
    public void selectCodeChoicePDList(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {

        List<CodeChoiceVO> dataList = opCodeService.selectCodeChoiceList(codeChoiceVo);

        model.addAttribute(BaseConfig.KEY_DATA_LIST, dataList);
    }

    /**
     * 코드선택 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_insertCodeChoiceForm.do" })
    public void insertCodeChoiceForm(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {

        ValidateResultHolder holder = ValidateUtil.validate(codeChoiceVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        List<TreeVO> dataList = opCodeService.selectCodeChoiceInsertTreeList(codeChoiceVo);

        model.addAttribute(BaseConfig.KEY_DATA_LIST, JsonUtil.toJson(dataList));

        CodeVO codeVo = new CodeVO();
        codeVo.setParamMap(codeChoiceVo.getParamMap());
        model.addAttribute(BaseConfig.KEY_DATA_VO, opCodeService.selectCode(codeVo));
    }

    /**
     * 코드선택 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertCodeChoice.do", method = RequestMethod.POST)
    public String insertCodeChoice(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(codeChoiceVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            codeChoiceVo.setRgtrId(loginVo.getPicId());
            codeChoiceVo.setRgtrNm(loginVo.getPicNm());
            codeChoiceVo.setIpAddr(getIpAddr());

            // 등록 실행
            key = opCodeService.insertCodeChoice(codeChoiceVo);
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 코드선택 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_updateCodeChoiceForm.do" })
    public void updateCodeChoiceForm(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {

        ValidateResultHolder holder = ValidateUtil.validate(codeChoiceVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        List<TreeVO> dataList = opCodeService.selectCodeChoiceUpdateTreeList(codeChoiceVo);

        model.addAttribute(BaseConfig.KEY_DATA_LIST, JsonUtil.toJson(dataList));

        CodeVO codeVo = new CodeVO();
        codeVo.setParamMap(codeChoiceVo.getParamMap());
        model.addAttribute(BaseConfig.KEY_DATA_VO, opCodeService.selectCode(codeVo));
    }

    /**
     * 코드선택 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateCodeChoice.do", method = RequestMethod.POST)
    public String updateCodeChoice(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(codeChoiceVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            codeChoiceVo.setRgtrId(loginVo.getPicId());

            key = opCodeService.updateCodeChoice(codeChoiceVo);
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 코드 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteCodeChoice.do", method = RequestMethod.POST)
    public String deleteCodeChoice(HttpServletRequest request, Model model, CodeChoiceVO codeChoiceVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        codeChoiceVo.setMdfrId(loginVo.getPicId());
        codeChoiceVo.setUpdusrNm(loginVo.getPicNm());

        Integer cnt = opCodeService.deleteCodeChoice(codeChoiceVo);
        if(cnt == 0) {
            return responseJson(model, Boolean.FALSE, codeChoiceVo.getParam("q_cdId"), MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, codeChoiceVo.getParam("q_cdId"), MessageUtil.getMessage("common.deleteOk"));
    }

}
