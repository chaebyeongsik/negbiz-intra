/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.code.domain.CodeChoiceHistoryVO;
import zesinc.intra.code.domain.CodeHistoryVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;

/**
 * 코드이력 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("코드이력 관리")
@RequestMapping(value = { "/intra/code" })
public class CodeHistoryController extends IntraController {

    @Resource(name = "opCodeHistoryService")
    private CodeHistoryService opCodeHistoryService;

    /**
     * 코드이력 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectCodeHistory.do")
    public void selectCodeHistory(HttpServletRequest request, Model model, CodeHistoryVO codeHistoryVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opCodeHistoryService.selectCodeHistory(codeHistoryVo));
    }

    /**
     * 코드이력 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectCodeHistoryList.do")
    public void selectCodeHistoryList(HttpServletRequest request, Model model, CodeHistoryVO codeHistoryVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opCodeHistoryService.selectCodeHistoryPageList(codeHistoryVo));
    }

    /**
     * 코드선택이력 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectCodeChoiceHistory.do")
    public void selectCodeChoiceHistory(HttpServletRequest request, Model model, CodeChoiceHistoryVO codeChoiceHistoryVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opCodeHistoryService.selectCodeChoiceHistory(codeChoiceHistoryVo));
    }

    /**
     * 코드선택이력 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectCodeChoiceHistoryList.do")
    public void selectCodeChoiceHistoryList(HttpServletRequest request, Model model, CodeChoiceHistoryVO codeChoiceHistoryVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opCodeHistoryService.selectCodeChoiceHistoryPageList(codeChoiceHistoryVo));
    }

}
