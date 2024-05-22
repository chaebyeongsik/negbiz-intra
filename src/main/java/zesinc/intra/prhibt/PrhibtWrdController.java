/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.prhibt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.prhibt.domain.PrhibtWrdHistoryVO;
import zesinc.intra.prhibt.domain.PrhibtWrdVO;
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
 * 금지단어 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-17.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("금지단어 관리")
@RequestMapping(value = { "/intra/prhibt" })
public class PrhibtWrdController extends IntraController {

    @Resource(name = "opPrhibtWrdService")
    private PrhibtWrdService opPrhibtWrdService;

    /**
     * ajax 방식의 코드 중복 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDplctChckCode.do")
    public String selectDplctChckCode(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {

        Integer cnt = opPrhibtWrdService.selectDplctChckCode(prhibtWrdVo);
        ValidateResultHolder holder = ValidateUtil.doFiledValidate(prhibtWrdVo, "phbwdCdId");

        if(cnt == 0 && holder.isValid()) {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
        }
        if(!holder.isValid()) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.dplctChckFalse"));
    }

    /**
     * 금지단어 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectPrhibtWrdList.do")
    public void selectPrhibtWrdList(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opPrhibtWrdService.selectPrhibtWrdPageList(prhibtWrdVo));
    }

    /**
     * 금지단어 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_insertPrhibtWrdForm.do" })
    public void insertPrhibtWrdForm(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {

        ValidateResultHolder holder = ValidateUtil.validate(prhibtWrdVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 금지단어 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertPrhibtWrd.do", method = RequestMethod.POST)
    public String insertPrhibtWrd(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(prhibtWrdVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            prhibtWrdVo.setRgtrId(loginVo.getPicId());
            prhibtWrdVo.setRgtrNm(loginVo.getPicNm());
            prhibtWrdVo.setIpAddr(getIpAddr());

            key = opPrhibtWrdService.insertPrhibtWrd(prhibtWrdVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 캐시 리로드
        opPrhibtWrdService.reloadCache();

        String url = "BD_selectPrhibtWrdList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 금지단어 수정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_updatePrhibtWrdForm.do" })
    public void updatePrhibtWrdForm(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {

        ValidateResultHolder holder = ValidateUtil.validate(prhibtWrdVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opPrhibtWrdService.selectPrhibtWrd(prhibtWrdVo));
    }

    /**
     * 금지단어 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updatePrhibtWrd.do", method = RequestMethod.POST)
    public String updatePrhibtWrd(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(prhibtWrdVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            prhibtWrdVo.setMdfrId(loginVo.getPicId());
            prhibtWrdVo.setUpdusrNm(loginVo.getPicNm());
            prhibtWrdVo.setIpAddr(getIpAddr());

            key = opPrhibtWrdService.updatePrhibtWrd(prhibtWrdVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 캐시 리로드
        opPrhibtWrdService.reloadCache();

        String url = "BD_updatePrhibtWrdForm.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 금지단어 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deletePrhibtWrd.do", method = RequestMethod.POST)
    public String deletePrhibtWrd(HttpServletRequest request, Model model, PrhibtWrdVO prhibtWrdVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        prhibtWrdVo.setMdfrId(loginVo.getPicId());
        prhibtWrdVo.setUpdusrNm(loginVo.getPicNm());

        Integer cnt = opPrhibtWrdService.deletePrhibtWrd(prhibtWrdVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_selectPrhibtWrdList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 금지단어이력 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectPrhibtWrdHistory.do")
    public void selectPrhibtWrdHistory(HttpServletRequest request, Model model, PrhibtWrdHistoryVO prhibtWrdHistoryVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opPrhibtWrdService.selectPrhibtWrdHistory(prhibtWrdHistoryVo));
    }

    /**
     * 금지단어이력 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectPrhibtWrdHistoryList.do")
    public void selectPrhibtWrdHistoryList(HttpServletRequest request, Model model, PrhibtWrdHistoryVO prhibtWrdHistoryVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opPrhibtWrdService.selectPrhibtWrdHistoryPageList(prhibtWrdHistoryVo));
    }

}
