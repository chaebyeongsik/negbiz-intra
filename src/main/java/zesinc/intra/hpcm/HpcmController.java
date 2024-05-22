/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.hpcm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.hpcm.domain.HpcmVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 도움말 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("도움말 관리")
@RequestMapping(value = { "/intra/hpcm" })
public class HpcmController extends IntraController {

    @Resource(name = "opHpcmService")
    private HpcmService opHpcmService;

    /**
     * 도움말 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectHpcmList.do")
    public void selectHpcmList(HttpServletRequest request, Model model, HpcmVO hpcmVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opHpcmService.selectHpcmPageList(hpcmVo));
    }

    /**
     * 도움말 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "PD_insertHpcmForm.do" })
    public void insertHpcmForm(HttpServletRequest request, Model model, HpcmVO hpcmVo) {

        ValidateResultHolder holder = ValidateUtil.validate(hpcmVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 도움말 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertHpcm.do", method = RequestMethod.POST)
    public String insertHpcm(HttpServletRequest request, Model model, HpcmVO hpcmVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(hpcmVo);
        if(holder.isValid()) {

            Integer key = opHpcmService.insertHpcm(hpcmVo);
            if(key < 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 도움말 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "PD_updateHpcmForm.do" })
    public void updateHpcmForm(HttpServletRequest request, Model model, HpcmVO hpcmVo) {

        ValidateResultHolder holder = ValidateUtil.validate(hpcmVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opHpcmService.selectHpcm(hpcmVo));
    }

    /**
     * 도움말 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateHpcm.do", method = RequestMethod.POST)
    public String updateHpcm(HttpServletRequest request, Model model, HpcmVO hpcmVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(hpcmVo);
        if(holder.isValid()) {

            // 수정 실행
            Integer key = opHpcmService.updateHpcm(hpcmVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 도움말 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteHpcm.do", method = RequestMethod.POST)
    public String deleteHpcm(HttpServletRequest request, Model model, HpcmVO hpcmVo) {
        Integer cnt = opHpcmService.deleteHpcm(hpcmVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }
        String url = "BD_selectHpcmList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 도움말 DB에 등록되어있는지 조회
     * 
     * @param request
     * @param model
     * @param hpcmVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectHpcmExistsAt.do", method = RequestMethod.POST)
    public String hpcmExistsAt(HttpServletRequest request, Model model, HpcmVO hpcmVo) {
        Integer cnt = opHpcmService.selectHpcmExistsAt(hpcmVo);
        if(cnt > 0) {
            return responseJson(model, Boolean.FALSE);
        }
        return responseJson(model, Boolean.TRUE);
    }

}
