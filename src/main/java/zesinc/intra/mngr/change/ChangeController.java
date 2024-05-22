/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.change;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.mngr.change.domain.ChangeVO;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;

/**
 * 관리자변경이력 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-05-16.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("관리자변경이력 관리")
@RequestMapping(value = { "/intra/mngr/change" })
public class ChangeController extends IntraController {

    @Resource(name = "opChangeService")
    private ChangeService opChangeService;

    /**
     * 관리자변경이력 상세
     */
    @RequestMapping(value = "PD_selectChange.do")
    public void selectChange(HttpServletRequest request, Model model, ChangeVO changeVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opChangeService.selectChange(changeVo));
    }

    /**
     * 관리자변경이력 목록
     */
    @RequestMapping(value = "PD_selectChangeList.do")
    public void selectChangeList(HttpServletRequest request, Model model, ChangeVO changeVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opChangeService.selectChangePageList(changeVo));
    }

}
