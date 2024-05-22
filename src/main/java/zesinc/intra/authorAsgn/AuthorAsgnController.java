/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.authorAsgn;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.authorAsgn.domain.AuthorAsgnVO;
import zesinc.intra.mngr.MngrService;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;

/**
 * 담당자권한할당 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("담당자권한할당 관리")
@RequestMapping(value = { "/intra/authorAsgn" })
public class AuthorAsgnController extends IntraController {

    @Resource(name = "opAuthorAsgnService")
    private AuthorAsgnService opAuthorAsgnService;
    @Resource(name = "opMngrService")
    private MngrService opMngrService;

    /**
     * 담당자 권한할당 관리 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectAuthorAsgnList.do")
    public void selectAuthorAsgnTreeList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opMngrService.selectAuthorList(mngrVo));
    }

    /**
     * 부서별 담당자 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_selectMngrList.do")
    public void selectMngrList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opMngrService.selectMngrPageList(mngrVo));
    }

    /**
     * 권한담당자 목록
     * (선택된 부서별 담당자의 목록)
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectAuthorMngrList.do")
    public String selectAuthorMngrList(HttpServletRequest request, Model model, MngrVO mngrVo) {

        List<AuthorAsgnVO> dataList = opAuthorAsgnService.selectAuthorMngrList(mngrVo);

        return responseJson(model, Boolean.TRUE, dataList, "");
    }

    /**
     * 담당자권한할당 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertAuthorAsgn.do", method = RequestMethod.POST)
    public String insertAuthorAsgn(HttpServletRequest request, Model model, AuthorAsgnVO authorAsgnVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        authorAsgnVo.setRgtrId(loginVo.getPicId());
        authorAsgnVo.setIpAddr(getIpAddr());

        Integer key = 0;
        key = opAuthorAsgnService.insertAuthorAsgn(authorAsgnVo);
        if(key < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), "BD_selectAuthorAsgnList.do");
    }

    /**
     * 지정권한 메뉴 목록
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectAuthorMenuList.do")
    public void selectAuthorMenuList(HttpServletRequest request, Model model, AuthorAsgnVO authorAsgnVo) {

        // 권한코드 목록
        model.addAttribute(BaseConfig.KEY_AUTH_LIST, AuthSupport.getAuthCodeList());

        List<AuthorAsgnVO> dataList = opAuthorAsgnService.selectAuthorMenuList(authorAsgnVo);
        model.addAttribute(BaseConfig.KEY_DATA_LIST, dataList);
    }

    /**
     * 권한별 담당자 목록
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectAuthorMngrList.do")
    public void selectAuthorMngrPDList(HttpServletRequest request, Model model, AuthorAsgnVO authorAsgnVo, MngrVO mngrVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opMngrService.selectAuthorList(mngrVo));
        model.addAttribute(BaseConfig.KEY_PAGER, opAuthorAsgnService.selectAuthorMngrPageList(authorAsgnVo));
    }

}
