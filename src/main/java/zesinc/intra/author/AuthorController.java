/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.author;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.author.domain.AuthorVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.JsonUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 권한 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-24.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("권한 관리")
@RequestMapping(value = { "/intra/author" })
public class AuthorController extends IntraController {

    @Resource(name = "opAuthorService")
    private AuthorService opAuthorService;

    /**
     * 권한 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_selectAuthor.do")
    public void selectAuthor(HttpServletRequest request, Model model, AuthorVO authorVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opAuthorService.selectAuthor(authorVo));
    }

    /**
     * 권한 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectAuthorList.do")
    public void selectAuthorList(HttpServletRequest request, Model model, AuthorVO authorVo) {
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opAuthorService.selectAuthorList(authorVo));
        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 권한 코드 중복체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectDupCheckAuthrtCdId.do", method = RequestMethod.POST)
    public String selectDupCheckAuthorCode(HttpServletRequest request, Model model, AuthorVO authorVo) {
        return responseJson(model, opAuthorService.selectDupCheckAuthorCode(authorVo));
    }

    /**
     * 권한 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_insertAuthorForm.do" })
    public void insertAuthorForm(HttpServletRequest request, Model model, AuthorVO authorVo) {
        //
        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 권한 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertAuthor.do", method = RequestMethod.POST)
    public String insertAuthor(HttpServletRequest request, Model model, AuthorVO authorVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            authorVo.setRgtrId(loginVo.getPicId());
            // 등록 실행
            key = opAuthorService.insertAuthor(authorVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 권한 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_updateAuthorForm.do" })
    public void updateAuthorForm(HttpServletRequest request, Model model, AuthorVO authorVo) {

        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opAuthorService.selectAuthor(authorVo));
    }

    /**
     * 권한 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateAuthor.do", method = RequestMethod.POST)
    public String updateAuthor(HttpServletRequest request, Model model, AuthorVO authorVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            authorVo.setMdfrId(loginVo.getPicId());
            key = opAuthorService.updateAuthor(authorVo);
            if(key < 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 권한 메뉴할당 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "INC_selectMenuAsgnForm.do" })
    public void selectMenuAsgnForm(HttpServletRequest request, Model model, AuthorVO authorVo) {

        // 권한코드 목록
        model.addAttribute(BaseConfig.KEY_AUTH_LIST, AuthSupport.getAuthCodeList());
        // 권한그룹 목록
        model.addAttribute(BaseConfig.KEY_DATA_VO, opAuthorService.selectMenuAsgn(authorVo));
        // 트리데이터 목록
        model.addAttribute(BaseConfig.KEY_DATA_LIST, JsonUtil.toJson(opAuthorService.selectAuthorMenuTreeList(authorVo)));
    }

    /**
     * 권한 메뉴 권한 할당 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertMenuAuthorAsgn.do", method = RequestMethod.POST)
    public String insertMenuAuthorAsgn(HttpServletRequest request, Model model, AuthorVO authorVo) {
        Integer key = 0;
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(authorVo);
        if(holder.isValid()) {
            key = opAuthorService.insertMenuAuthorAsgn(authorVo);
            if(key < 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, key, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 권한 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteAuthor.do", method = RequestMethod.POST)
    public String deleteAuthor(HttpServletRequest request, Model model, AuthorVO authorVo) {

        Integer cnt = opAuthorService.deleteAuthor(authorVo);
        if(cnt != 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 담당자 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectMngrInAuthorList.do")
    public void selectMngrInAuthorList(HttpServletRequest request, Model model, AuthorVO authorVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opAuthorService.selectMngrInAuthorPageList(authorVo));
    }
}
