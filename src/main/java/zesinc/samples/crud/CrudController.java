/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.samples.crud;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.lang.Validate;
import zesinc.login.domain.LoginVO;
import zesinc.samples.crud.domain.CrudReplyVO;
import zesinc.samples.crud.domain.CrudVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * CRUD 셈플 프로그램 Controller 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 11.    방기배   최초작성
 * </pre>
 * @see
 */
@Controller("CRUD 셈플")
@RequestMapping(value = { "/samples/crud" })
public class CrudController extends IntraController {

    @Resource(name = "opCrudService")
    private CrudService opCrudService;

    /**
     * 상세
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_crud.view.do")
    public void selectCrud(HttpServletRequest request, Model model, CrudVO crudVo,
        CrudReplyVO crudReplyVo) {

        // 원본글 상세
        model.addAttribute(BaseConfig.KEY_BASE_VO, opCrudService.selectCrud(crudVo));

        // 답글 목록
        crudReplyVo.setParamMap(crudVo.getParamMap());
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opCrudService.selectCrudReplyList(crudReplyVo));
    }

    /**
     * 목록
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_crud.list.do")
    public void selectCrudList(HttpServletRequest request, Model model, CrudVO crudVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opCrudService.selectCrudList(crudVo));
    }

    /**
     * 입력 폼
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_crud.insert.form.do", "BD_crud.update.form.do" })
    public void insertCrudForm(HttpServletRequest request, Model model, CrudVO crudVo) {

        // 스크립트단에 동일한 validator를 설정할 수 있도록 한다.
        ValidateResultHolder holder = ValidateUtil.validate(crudVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        if(Validate.isNotEmpty(crudVo.getParamMap().get("q_regSn"))) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opCrudService.selectCrud(crudVo));
        }
    }

    /**
     * 등록
     * 
     * @param request
     * @param model
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.insert.do", method = RequestMethod.POST)
    public String insertCrud(HttpServletRequest request, Model model, CrudVO crudVo)
        throws Exception {

        /*
         * xxxxxxxxx 라는 권한 그룹을 보유하고 있다면 이라는 셈플 코드 예제이나
         * xxxxxxxxx 라는 권한 그룹 코드가 없으리라 생각하고 만든 코드임.
         * 따라서 아래 if 문은 xxxxxxxxx라는 권한그룹을 보유하고 있지 않다면으로 역으로 코딩한 셈플
         * 기본적으로는 ! 을 빼고 보유하고 있어야 하는 권한 그룹 코드를 작성하는 것이 맞음
         */
        if(!AuthSupport.isAuthGroup("xxxxxxxxx")) {

            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(crudVo);
            if(holder.isValid()) {

                LoginVO loginVo = (LoginVO) getMgrSession();

                crudVo.setRgtrId(loginVo.getPicId());
                crudVo.setRgtrNm(loginVo.getPicNm());

                crudVo.setIpAddr(getIpAddr());
                // 파일 업로드 (웹경로를 사용하도록 TRUE 값을 추가)
                List<FileVO> fileList = UploadHelper.upload(request, "crud");
                // 썸네일 생성 뒤 숫자는 가로 세로 값
                UploadHelper.makeThumbNail(fileList, 300, 200);
                // 아래 예는 접두사를 사용할 경우에 별도도 설정이 가능하다는 것을 예시 예 : thumb_
                // UploadHelper.makeThumbNail(fileList, 300, 200, "접두사");

                crudVo.setFileList(fileList);
                // 등록 실행
                Integer key = opCrudService.insertCrud(crudVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }
        }
        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 수정
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.update.do", method = RequestMethod.POST)
    public String updateCrud(HttpServletRequest request, Model model, CrudVO crudVo) {

        // 권한 확인 셈플
        CrudVO dataVo = opCrudService.selectCrud(crudVo);
        // 등록되어 있는 데이터를 작성한 ID와 로그인 사용자의 ID가 같거나, 총괄운영자 권한이 있다면 실행
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {

            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(crudVo);
            if(holder.isValid()) {

                LoginVO loginVo = (LoginVO) getMgrSession();

                crudVo.setMdfrId(loginVo.getPicId());
                crudVo.setUpdusrNm(loginVo.getPicNm());
                crudVo.setIpAddr(getIpAddr());

                // 파일 업로드 input type="file" name="fileInput"
                List<FileVO> fileList = UploadHelper.upload(request, "crud", "fileInput");
                // 파일 업로드 input type="file" name="fileInput1"
                // List<FileVO> fileList1 = UploadHelper.upload(request, "crud", "fileInput1");
                // 썸네일 생성 뒤 숫자는 가로 세로 값
                UploadHelper.makeThumbNail(fileList, 300, 200);
                // UploadHelper.makeThumbNail(fileList, 300, 200, "접두사");
                // UploadHelper.makeWarterMark(fileList1, width, height, waterMark, position,
                // transparent);
                // UploadHelper.makeWarterMark(fileList1, width, height, waterMark, position,
                // transparent, prefix);
                crudVo.setFileList(fileList);

                // 등록 실행
                Integer key = opCrudService.updateCrud(crudVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }
            String url = "BD_crud.view.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.delete.do", method = RequestMethod.POST)
    public String deleteCrud(HttpServletRequest request, Model model, CrudVO crudVo) {

        // 권한 확인 셈플
        CrudVO dataVo = opCrudService.selectCrud(crudVo);
        // 등록되어 있는 데이터를 작성한 ID와 로그인 사용자의 ID가 같거나, 총괄운영자 권한이 있다면 실행
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opCrudService.deleteCrud(crudVo);
            if(cnt != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }

            String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 목록 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.list.delete.do", method = RequestMethod.POST)
    public String deleteCrudList(HttpServletRequest request, Model model, CrudVO crudVo) {

        Integer cnt = opCrudService.deleteCrudList(crudVo);
        if(cnt <= 0) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 답변 폼
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_crud.reply.insert.form.do", "BD_crud.reply.update.form.do" })
    public String insertCrudReplyForm(HttpServletRequest request, Model model, CrudVO crudVo,
        CrudReplyVO crudReplyVo) {

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);

        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_BASE_VO, opCrudService.selectCrud(crudVo));

        if(Validate.isNotEmpty(crudReplyVo.getParamMap().get("q_replySeq"))) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opCrudService.selectCrudReply(crudReplyVo));
        }

        return "samples/crud/BD_crud.reply.form";
    }

    /**
     * 답변 등록
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.reply.insert.do", method = RequestMethod.POST)
    public String insertCrudReply(HttpServletRequest request, Model model, CrudReplyVO crudReplyVo) {

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);
        if(holder.isValid()) {

            LoginVO loginVo = (LoginVO) getMgrSession();

            crudReplyVo.setRgtrId(loginVo.getPicId());
            crudReplyVo.setRgtrNm(loginVo.getPicNm());
            crudReplyVo.setIpAddr(getIpAddr());

            crudReplyVo.setFileList(UploadHelper.upload(request, "crud"));
            // 등록 실행
            Object key = opCrudService.insertCrudReply(crudReplyVo);
            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }
        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 답변 수정
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.reply.update.do", method = RequestMethod.POST)
    public String updateCrudReply(HttpServletRequest request, Model model,
        CrudReplyVO crudReplyVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();

        crudReplyVo.setMdfrId(loginVo.getPicId());
        crudReplyVo.setUpdusrNm(loginVo.getPicNm());
        crudReplyVo.setIpAddr(getIpAddr());

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);
        if(holder.isValid()) {

            crudReplyVo.setFileList(UploadHelper.upload(request, "crud"));
            // 수정 실행
            Integer key = opCrudService.updateCrudReply(crudReplyVo);

            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }
        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 답변 삭제
     * 
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_crud.reply.delete.do", method = RequestMethod.POST)
    public String deleteCrudReply(HttpServletRequest request, Model model,
        CrudReplyVO crudReplyVo) {

        Integer key = opCrudService.deleteCrudReply(crudReplyVo);
        if(Validate.isEmpty(key)) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 엑셀 불러오기 기능 샘플 페이지
     * 
     * @return
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "PD_excelRegistSample.do")
    public void sendForExcelSample(HttpServletRequest request, Model model) {

    }

}
