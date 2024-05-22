/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.othbc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;

/**
 * 공개 자원 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-14.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = { "/intra/resrce/othbc" })
public class ResrceOthbcController extends IntraController {

    @Resource(name = "opResrceOthbcService")
    private ResrceOthbcService opResrceOthbcService;


    /**
     * 자원 페이지 상세
     */
    @RequestMapping(value = "ND_selectResrceOthbc.do")
    public void selectResrceND(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceOthbcService.selectResrceOthbc(resrceVo));
    }

    /**
     * 자원 페이지 상세
     */
    @RequestMapping(value = "PD_selectResrceOthbc.do")
    public void selectResrcePD(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceOthbcService.selectResrceOthbc(resrceVo));
    }

    /**
     * 자원 페이지 목록
     */
    @RequestMapping(value = "PD_selectResrceOthbcList.do")
    public void selectResrceList(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        resrceVo.addParam("q_rgtrId", loginVo.getPicId());
        resrceVo.addParam("q_rlsYn", "Y");

        model.addAttribute(BaseConfig.KEY_PAGER, opResrceOthbcService.selectResrceOthbcPageList(resrceVo));
    }

}
