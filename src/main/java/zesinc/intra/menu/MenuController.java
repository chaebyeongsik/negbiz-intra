/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.menu.domain.MenuVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.CacheHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 메뉴관리 Controller 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 17.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("메뉴관리")
@RequestMapping(value = { "/intra/menu" })
public class MenuController extends IntraController {

    @Resource(name = "opMenuService")
    private MenuService opMenuService;

    /**
     * index페이지
     * 
     * @param request
     * @param model
     * @param menuVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectMenuList.do")
    public void selectMenuBDList(HttpServletRequest request, Model model, MenuVO menuVo) {
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 메뉴관리 목록 for TreeList 화면조회
     * 
     * @param vo
     * @param model
     * @return
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectMenuTreeList.do")
    public String selectMenuTreeList(HttpServletRequest request, Model model, MenuVO menuVo) {
        return responseJson(model, opMenuService.selectMenuTreeList(menuVo));
    }

    /**
     * 메뉴관리 상세-트리오른쪽상세부분(INC) 화면 조회
     * 
     * @param vo
     * @param model
     */
    @RequestMapping(value = "INC_updateMenuForm.do")
    public void updateMenuForm(HttpServletRequest request, Model model, MenuVO menuVo) {
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opMenuService.selectMenu(menuVo));
        model.addAttribute("programInfo", opMenuService.selectProgramInfo());
    }

    /**
     * 프로그램명 선택 시 해당하는 컨트롤러 안의 리퀘스트 메핑 메뉴URL정보를 조회 ajax
     * 
     * @param request
     * @param model
     * @param menuVo
     * @return 해당하는 컨트롤러 안의 리퀘스트 메핑 메뉴URL정보
     */
    @RequestMapping(value = "ND_processRequestMappingInfo.do", method = RequestMethod.POST)
    public String processRequestMappingInfo(HttpServletRequest request, Model model, MenuVO menuVo) {

        return responseJson(model, opMenuService.selectRequestMappingInfo(menuVo));
    }

    /**
     * 메뉴등록 팝업 폼
     * 
     * @param request
     * @param model
     * @param menuVo
     */
    @RequestMapping(value = "PD_insertMenuForm.do")
    public void insertMenuPDForm(HttpServletRequest request, Model model, MenuVO menuVo) {
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 메뉴관리 등록 팝업의 트리 오른쪽 상세부분(INC)
     * 
     * @param vo
     * @param model
     */
    @RequestMapping(value = "INC_insertMenuForm.do")
    public void insertMenuINCForm(HttpServletRequest request, Model model, MenuVO menuVo) {
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 메뉴 등록 처리
     * 
     * @param request
     * @param model
     * @param menuVo
     * @return
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertMenu.do", method = RequestMethod.POST)
    public String insertMenu(HttpServletRequest request, Model model, MenuVO menuVo)
        throws Exception {

        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        if(holder.isValid()) {

            key = opMenuService.insertMenu(menuVo);
            if(key < 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 메뉴 수정 처리
     * 
     * @param request
     * @param model
     * @param menuVo
     * @return
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateMenu.do", method = RequestMethod.POST)
    public String updateMenu(HttpServletRequest request, Model model, MenuVO menuVo) throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        if(holder.isValid()) {

            // 메뉴URL 중복 체크. 동일한것과 하위 패턴의 URL 모두 필터링
            String dplctInfo = opMenuService.checkDplctMenuUrlList(menuVo);
            if(Validate.isNotEmpty(dplctInfo)) {
                return responseJson(model, Boolean.FALSE, dplctInfo);
            }
            Integer key = opMenuService.updateMenu(menuVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 케시리로드
        CacheHelper.cacheReload("opMenuCache");

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 메뉴 삭제
     * 
     * @param request
     * @param model
     * @param menuVo
     * @return
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteMenu.do", method = RequestMethod.POST)
    public String deleteMenu(HttpServletRequest request, Model model, MenuVO menuVo)
        throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(menuVo);
        if(holder.isValid()) {

            Integer key = opMenuService.deleteMenu(menuVo);
            if(key < 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        // 케시리로드
        CacheHelper.cacheReload("opMenuCache");

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 메뉴 순서 변경
     * 
     * @param request
     * @param model
     * @param menuVo
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateMenuSortOrder.do")
    public String updateMenuSortOrder(HttpServletRequest request, Model model, MenuVO menuVo) throws Exception {
        opMenuService.updateMenuSortOrder(menuVo);
        return responseJson(model, Boolean.TRUE);
    }

    /**
     * 메뉴 URL 동기화
     * 
     * @param request
     * @param model
     * @param menuVo
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_processMenuUrlSynch.do")
    public String processMenuUrlSynch(HttpServletRequest request, Model model, MenuVO menuVo) throws Exception {

        int result = opMenuService.processMenuUrlSynch();

        if(result < 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.processOk"));
    }

}
