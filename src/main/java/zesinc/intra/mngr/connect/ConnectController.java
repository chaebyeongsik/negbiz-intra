/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.intra.mngr.connect.support.ConnectSupport;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;

/**
 * 관리자접속이력 정보 컨트롤러 클레스
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
@Controller("관리자접속이력 관리")
@RequestMapping(value = { "/intra/mngr/connect" })
public class ConnectController extends IntraController {

    @Resource(name = "opConnectService")
    private ConnectService opConnectService;

    /**
     * 관리자접속이력 상세
     */

    @RequestMapping(value = "PD_selectConnect.do")
    public void selectConnect(HttpServletRequest request, Model model, ConnectVO connectVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opConnectService.selectConnect(connectVo));
    }

    /**
     * 관리자접속이력 페이지 목록
     */
    @RequestMapping(value = "PD_selectConnectList.do")
    public void selectConnectList(HttpServletRequest request, Model model, ConnectVO connectVo) {

        model.addAttribute("RESULT_CODE_LIST", ConnectSupport.getResultCodeList());
        model.addAttribute(BaseConfig.KEY_PAGER, opConnectService.selectConnectPageList(connectVo));
    }

}
