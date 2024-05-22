/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.accessCtrl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.accessCtrl.domain.AccesCtrlVO;
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
 * 접근제어 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-02.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("접근제어 관리")
@RequestMapping(value = { "/intra/accessCtrl" })
public class AccesCtrlController extends IntraController {

    @Resource(name = "opAccesCtrlService")
    private AccesCtrlService opAccesCtrlService;

    /**
     * 담당자목록 조회(Auto Complete 용)
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectMngrList.do")
    public String selectMngrList(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {

        List<AccesCtrlVO> orgList = opAccesCtrlService.selectMngrList(accesCtrlVo);

        return responseJson(model, orgList);
    }

    /**
     * 접근제어 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectAccesCtrlList.do")
    public void selectAccesCtrlList(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opAccesCtrlService.selectAccesCtrlList(accesCtrlVo));
    }

    /**
     * 접근제어 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_insertAccesCtrlForm.do" })
    public void insertAccesCtrlForm(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {

        ValidateResultHolder holder = ValidateUtil.validate(accesCtrlVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 접근제어 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertAccesCtrl.do", method = RequestMethod.POST)
    public String insertAccesCtrl(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(accesCtrlVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            accesCtrlVo.setRgtrId(loginVo.getPicId());
            accesCtrlVo.setRgtrNm(loginVo.getPicNm());
            accesCtrlVo.setIpAddr(getIpAddr());

            Integer key = opAccesCtrlService.insertAccesCtrl(accesCtrlVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectAccesCtrlList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 접근제어 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_updateAccesCtrlForm.do" })
    public void updateAccesCtrlForm(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {

        ValidateResultHolder holder = ValidateUtil.validate(accesCtrlVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opAccesCtrlService.selectAccesCtrl(accesCtrlVo));
    }

    /**
     * 접근제어 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateAccesCtrl.do", method = RequestMethod.POST)
    public String updateAccesCtrl(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(accesCtrlVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            accesCtrlVo.setMdfrId(loginVo.getPicId());
            accesCtrlVo.setUpdusrNm(loginVo.getPicNm());
            accesCtrlVo.setIpAddr(getIpAddr());

            // 수정 실행
            Integer key = opAccesCtrlService.updateAccesCtrl(accesCtrlVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectAccesCtrlList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 접근제어 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteAccesCtrl.do", method = RequestMethod.POST)
    public String deleteAccesCtrl(HttpServletRequest request, Model model, AccesCtrlVO accesCtrlVo) throws Exception {

        Integer cnt = opAccesCtrlService.deleteAccesCtrl(accesCtrlVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_selectAccesCtrlList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

}
