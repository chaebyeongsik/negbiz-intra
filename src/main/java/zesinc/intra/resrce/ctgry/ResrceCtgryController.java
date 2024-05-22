/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.ctgry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.resrce.ctgry.domain.ResrceCtgryVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 자원카테고리 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-15.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("자원카테고리 관리")
@RequestMapping(value = { "/intra/resrce/ctgry" })
public class ResrceCtgryController extends IntraController {

    @Resource(name = "opResrceCtgryService")
    private ResrceCtgryService opResrceCtgryService;

    /**
     * 자원카테고리 메인화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectResrceCtgryList.do")
    public void selectResrceCtgryBDList(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        model.addAttribute("AUTH_CODE", AuthSupport.getAuthCode());
    }

    /**
     * 자원카테고리 관리화면
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectResrceCtgryList.do")
    public void selectResrceCtgryPDList(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        model.addAttribute("AUTH_CODE", AuthSupport.getAuthCode());
    }

    /**
     * 자원카테고리 트리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectResrceCtgryTreeList.do")
    public String selectResrceCtgryTreeList(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        return responseJson(model, opResrceCtgryService.selectResrceCtgryTreeList(resrceCtgryVo));
    }

    /**
     * 자원카테고리 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_insertResrceCtgryForm.do" })
    public void insertResrceCtgryForm(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        ValidateResultHolder holder = ValidateUtil.validate(resrceCtgryVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceCtgryService.selectResrceCtgry(resrceCtgryVo));
    }

    /**
     * 자원카테고리 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertResrceCtgry.do", method = RequestMethod.POST)
    public String insertResrceCtgry(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(resrceCtgryVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            resrceCtgryVo.setRgtrId(loginVo.getPicId());
            resrceCtgryVo.setRgtrNm(loginVo.getPicNm());
            resrceCtgryVo.setIpAddr(getIpAddr());

            // 등록 실행
            key = opResrceCtgryService.insertResrceCtgry(resrceCtgryVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, resrceCtgryVo.getCtgrySn(), MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 자원카테고리 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "INC_updateResrceCtgryForm.do" })
    public void updateResrceCtgryForm(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        ValidateResultHolder holder = ValidateUtil.validate(resrceCtgryVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceCtgryService.selectResrceCtgry(resrceCtgryVo));
    }

    /**
     * 자원카테고리 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateResrceCtgry.do", method = RequestMethod.POST)
    public String updateResrceCtgry(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(resrceCtgryVo);
        if(holder.isValid()) {

            key = opResrceCtgryService.updateResrceCtgry(resrceCtgryVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 자원카테고리 정렬순서 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateResrceCtgrySortOrder.do")
    public String updateResrceCtgrySortOrder(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) {

        Boolean success = opResrceCtgryService.updateResrceCtgrySortOrder(resrceCtgryVo);

        if(!success) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 자원카테고리 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteResrceCtgry.do", method = RequestMethod.POST)
    public String deleteResrceCtgry(HttpServletRequest request, Model model, ResrceCtgryVO resrceCtgryVo) throws Exception {

        Integer cnt = opResrceCtgryService.deleteResrceCtgry(resrceCtgryVo);

        if(cnt < 0) {
            return responseJson(model, Boolean.FALSE, cnt);
        } else if(cnt != 1) {
            return responseJson(model, Boolean.FALSE, resrceCtgryVo.getParam("q_ctgrySn"), MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, resrceCtgryVo.getParam("q_ctgrySn"), MessageUtil.getMessage("common.deleteOk"));
    }

}
