/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.accessHist;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.user.accessHist.domain.UserAccessHistVO;
import zesinc.intra.user.grad.UserGradService;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;

/**
 * 사용자 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-08-01.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자접속이력 관리")
@RequestMapping(value = { "/intra/user/accessHist" })
public class UserAccessHistController extends IntraController {

    @Resource(name = "opUserAccessHistService")
    private UserAccessHistService opUserAccessHistService;

    @Resource(name = "opUserGradService")
    private UserGradService opUserGradService;

    /**
     * 사용자 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectUserAccessHist.do")
    public void selectUserAccessHist(HttpServletRequest request, Model model, UserAccessHistVO userAccessHistVo) {

        // 페이징 [접속일시/인증방식/결과/아이피]
        model.addAttribute(BaseConfig.KEY_PAGER, opUserAccessHistService.selectUserAccessHist(userAccessHistVo));
        // 해당 사용자 로그인 정보 전체 카운트
        model.addAttribute("loginIpTotalCnt", opUserAccessHistService.selectUserLoginIpTotal(userAccessHistVo));
        // 사용자 로그인 아이피별 카운트(상위3개 및 기타)
        model.addAttribute("loginIpGroupCnt", opUserAccessHistService.selectUserLoginIp(userAccessHistVo));
        
    }

    /**
     * 사용자 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectUserAccessHistList.do")
    public void selectUserAccessHistList(HttpServletRequest request, Model model, UserAccessHistVO userAccessHistVo) {

        model.addAttribute("userGradInUseList", opUserGradService.selectUserGradInUseList());
        model.addAttribute("userGradList", opUserGradService.selectUserGradList());
        model.addAttribute(BaseConfig.KEY_PAGER, opUserAccessHistService.selectUserAccessHistPageList(userAccessHistVo));
        model.addAttribute("accessStatistics", opUserAccessHistService.selectAccessStatistics());
    }

}
