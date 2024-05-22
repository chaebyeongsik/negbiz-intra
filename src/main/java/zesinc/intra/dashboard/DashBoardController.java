/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dashboard;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.core.cache.CacheService;
import zesinc.core.config.Config;
import zesinc.intra.dashboard.domain.DashBoardCmsEvlVO;
import zesinc.intra.dashboard.domain.DashBoardCmsVO;
import zesinc.intra.mngr.MngrService;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.vo.BaseVO;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 최초 화면인 데시보드 컨트롤러
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 3. 19.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("데시보드")
@RequestMapping(value = { "/intra/dashboard" })
public class DashBoardController extends IntraController {
    
    @Resource(name = "opDashBoardService")
    DashBoardService opDashBoardService;

    @Resource(name = "opMngrService")
    private MngrService opMngrService;

    /**
     * 데시보드 메인 페이지
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_selectDashBoard.do" })
    public void selectDashBoard(HttpServletRequest request, Model model, BaseVO baseVo) {
        MngrVO mngrVo = new MngrVO();
        LoginVO loginVo = (LoginVO) getMgrSession();
        mngrVo.addParam("q_picId", loginVo.getPicId());
        model.addAttribute("INIT_CHECK", opMngrService.selectPasswordChangeDeCheck(mngrVo));
        model.addAttribute("EXPIRED_CHECK", opMngrService.selectPasswordExpiredCheck(mngrVo));
    }

    /**
     * 데시보드 컨텐츠관리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "INC_selectCmsCntnsList.do" })
    public void selectCmsCntnsList(HttpServletRequest request, Model model, DashBoardCmsVO dashBoardCmsVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        // Validate URL
        String css = Config.getString("cms-config.urls.cssValidate");
        String html = Config.getString("cms-config.urls.htmlValidate");
        model.addAttribute("CSS_VALIDATE", css);
        model.addAttribute("HTML_VALIDATE", html);

        String authCode = (String) request.getAttribute(BaseConfig.AUTH_CODE_KEY);
        if(AuthType.MANAGER.getCdId().equals(authCode)) {
            dashBoardCmsVo.addParam("q_isManager", Boolean.TRUE);
        } else {
            LoginVO loginVo = (LoginVO) getMgrSession();
            dashBoardCmsVo.addParam("q_picId", loginVo.getPicId());
            dashBoardCmsVo.addParam("q_deptCdId", loginVo.getDeptCdId());
        }

        model.addAttribute(BaseConfig.KEY_PAGER, opDashBoardService.selectDashBoardCmsPageList(dashBoardCmsVo));
    }

    /**
     * 데시보드 만족도조사 통계 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "INC_selectCmsEvlList.do" })
    public void selectCmsEvlList(HttpServletRequest request, Model model, DashBoardCmsEvlVO dashBoardCmsEvlVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        String authCode = (String) request.getAttribute(BaseConfig.AUTH_CODE_KEY);
        if(AuthType.MANAGER.getCdId().equals(authCode)) {
            dashBoardCmsEvlVo.addParam("q_isManager", Boolean.TRUE);
        } else {
            LoginVO loginVo = (LoginVO) getMgrSession();
            dashBoardCmsEvlVo.addParam("q_picId", loginVo.getPicId());
            dashBoardCmsEvlVo.addParam("q_deptCdId", loginVo.getDeptCdId());
        }

        model.addAttribute(BaseConfig.KEY_PAGER, opDashBoardService.selectCmsEvlList(dashBoardCmsEvlVo));
    }

}
