/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.cache.CacheService;
import zesinc.intra.multilang.domain.MultilangVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 다국어 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("다국어 관리")
@RequestMapping(value = { "/intra/multilang" })
public class MultilangController extends IntraController {

    @Resource(name = "opMultilangService")
    private MultilangService opMultilangService;

    /**
     * ajax 방식의 다국어 코드 중복 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDplctChckMultilang.do")
    public String selectDplctChckMultilang(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        ValidateResultHolder holder = ValidateUtil.doFiledValidate(multilangVo, "mtlngUnqCdNm");
        Integer cnt = opMultilangService.selectDplctChckMultilang(multilangVo);

        if(cnt == 0 && holder.isValid()) {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.dplctChckTrue"));
        }
        if(!holder.isValid()) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        if(cnt == -1) {
            return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.invalidParam"));
        }

        return responseJson(model, Boolean.FALSE, cnt, MessageUtil.getMessage("common.dplctChckFalse"));
    }

    /**
     * 다국어 메인
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMultilangList.do")
    public void selectMultilangBDList(HttpServletRequest request, Model model, MultilangVO multilangVo) {
    }

    /**
     * 다국어 트리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectMultilangTreeList.do")
    public String selectMultilangTreeList(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        return responseJson(model, opMultilangService.selectMultilangTreeList(multilangVo));
    }

    /**
     * 다국어 등록 메인
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectMultilangList.do")
    public void selectMultilangPDList(HttpServletRequest request, Model model, MultilangVO multilangVo) {
    }

    /**
     * 다국어 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_insertMultilangForm.do" })
    public void insertMultilangForm(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        ValidateResultHolder holder = ValidateUtil.validate(multilangVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);

        List<String> langList = new ArrayList<String>();
        for(DomnCacheVO domnVo : domnList) {
            String langCdId = domnVo.getLangCdId();
            if(!langList.contains(langCdId)) {
                langList.add(langCdId);
            }
        }

        model.addAttribute("langList", langList);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opMultilangService.selectMultilang(multilangVo, Boolean.FALSE));
    }

    /**
     * 다국어 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertMultilang.do", method = RequestMethod.POST)
    public String insertMultilang(HttpServletRequest request, Model model, MultilangVO multilangVo) {
        String key = "";
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(multilangVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            multilangVo.setRgtrId(loginVo.getPicId());
            multilangVo.setRgtrNm(loginVo.getPicNm());
            multilangVo.setIpAddr(getIpAddr());

            // 등록 실행
            key = opMultilangService.insertMultilang(multilangVo);
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 다국어 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_updateMultilangForm.do" })
    public void updateMultilangForm(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        ValidateResultHolder holder = ValidateUtil.validate(multilangVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);

        List<String> langList = new ArrayList<String>();
        for(DomnCacheVO domnVo : domnList) {
            String langCdId = domnVo.getLangCdId();
            if(!langList.contains(langCdId)) {
                langList.add(langCdId);
            }
        }

        model.addAttribute("langList", langList);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opMultilangService.selectMultilang(multilangVo, Boolean.TRUE));
    }

    /**
     * 다국어 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateMultilang.do", method = RequestMethod.POST)
    public String updateMultilang(HttpServletRequest request, Model model, MultilangVO multilangVo) throws Exception {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(multilangVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            multilangVo.setMdfrId(loginVo.getPicId());
            multilangVo.setUpdusrNm(loginVo.getPicNm());
            multilangVo.setIpAddr(getIpAddr());

            opMultilangService.updateMultilang(multilangVo);
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 다국어 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteMultilang.do", method = RequestMethod.POST)
    public String deleteMultilang(HttpServletRequest request, Model model, MultilangVO multilangVo) throws Exception {

        opMultilangService.deleteMultilang(multilangVo);

        return responseJson(model, Boolean.TRUE, multilangVo.getParam("q_mtlngCdNm"), MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 기능별 연계 화면 다국어 트리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectConnectMultilangTreeList.do")
    public String selectConnectMultilangTreeList(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        return responseJson(model, opMultilangService.selectConnectMultilangTreeList(multilangVo));
    }

    /**
     * 기능별 연계 화면 다국어 등록 메인
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectConnectMultilangList.do")
    public void selectConnectMultilangPDList(HttpServletRequest request, Model model, MultilangVO multilangVo) {
    }

    /**
     * 기능별 연계 화면 다국어 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_updateConnectMultilangForm.do" })
    public void updateConnectMultilangForm(HttpServletRequest request, Model model, MultilangVO multilangVo) {

        ValidateResultHolder holder = ValidateUtil.validate(multilangVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);

        List<String> langList = new ArrayList<String>();
        for(DomnCacheVO domnVo : domnList) {
            String langCdId = domnVo.getLangCdId();
            if(!langList.contains(langCdId)) {
                langList.add(langCdId);
            }
        }

        model.addAttribute("langList", langList);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opMultilangService.selectMultilang(multilangVo, Boolean.TRUE));
    }

}
