/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dept;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.dept.domain.DeptVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * @author (주)제스아이엔씨 기술연구소
 *
 *         <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-23.    황신욱   최초작성
 * </pre>
 * @see
 */
@Controller("부서관리")
@RequestMapping(value = { "/intra/dept" })
public class DeptController extends IntraController {

    @Resource(name = "opDeptService")
    private DeptService opDeptService;

    /**
     * 부서관리 메인
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectDeptList.do")
    public void selectDeptList() {

    }

    /**
     * 부서관리 목록 for TreeList
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @RequestMapping(value = "ND_selectDeptTreeList.do")
    public String selectDeptTreeList(HttpServletRequest request, Model model, DeptVO deptVo) {

        return responseJson(model, opDeptService.selectDeptTreeList(deptVo));
    }

    /**
     * 부서와 부서원 목록 deptCdId 는 따로 속성을 만들지 않고 담당자ID를 설정하여 사용
     * (계층별 Tree 조회)
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @RequestMapping(value = "ND_selectDeptUserTreeList.do")
    public String selectDeptUserTreeList(HttpServletRequest request, Model model, DeptVO deptVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        deptVo.addParam("q_picId", loginVo.getPicId());

        return responseJson(model, opDeptService.selectDeptUserTreeList(deptVo));
    }

    /**
     * 부서정보 상세 조회 및 수정폼
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_updateDeptForm.do")
    public void updateDeptForm(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opDeptService.selectDept(deptVo));
    }

    /**
     * 부서 코드 중복 검사
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectDplctChckDept.do")
    public String selectDplctChckDept(HttpServletRequest request, Model model, DeptVO deptVo) {

        return responseText(model, opDeptService.selectDplctChckDept(deptVo));
    }

    /**
     * 부서 입력 폼
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "PD_insertDeptForm.do")
    public void insertDeptPDForm(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 부서 등록 팝업의 트리 오른쪽 상세부분(INC)
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "INC_insertDeptForm.do")
    public void insertDeptINCForm(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 부서 등록
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertDept.do", method = RequestMethod.POST)
    public String insertDept(HttpServletRequest request, Model model, DeptVO deptVo)
        throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        if(holder.isValid()) {

            // 중복코드검사
            if(0 < opDeptService.selectDplctChckDept(deptVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 등록 실행
            Integer key = opDeptService.insertDept(deptVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }

        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 부서정보 수정
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateDept.do", method = RequestMethod.POST)
    public String updateDept(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        if(holder.isValid()) {

            // 중복코드검사
            if(0 < opDeptService.selectDplctChckDept(deptVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 수정
            Integer key = opDeptService.updateDept(deptVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 부서 삭제
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteDept.do", method = RequestMethod.POST)
    public String deleteDept(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 해당부서에 배정된 담당자 조회
        // if (0 < service.method) {
        // }

        // 삭제 실행
        Integer cnt = opDeptService.deleteDept(deptVo);
        if(cnt < 1) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }
        /*
         * } else {
         * return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
         * }
         */
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 부서 순서 변경
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateDeptSortOrder.do")
    public String updateDeptSortOrder(HttpServletRequest request, Model model, DeptVO deptVo) throws Exception {

        Boolean success = opDeptService.updateDeptSortOrder(deptVo);
        if(!success) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 엑셀 불러오기 기능 샘플 페이지
     *
     * @return
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_insertExcelForm.do")
    public void insertExcelForm(HttpServletRequest request, Model model, DeptVO deptVo) {

        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

    }

    /**
     * 부서 등록(엑셀일괄등록)
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertDeptExcel.do", method = RequestMethod.POST)
    public String insertDeptExcel(HttpServletRequest request, Model model, DeptVO deptVo)
        throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(deptVo);
        if(holder.isValid()) {

            List<String> dplctCode = new ArrayList<String>();
            if(Validate.isNotEmpty(deptVo.getDeptCdIds())) {
                dplctCode = opDeptService.selectDplctChckDeptCodes(deptVo);
            }

            // 중복코드검사
            if(0 < dplctCode.size()) {
                return responseJson(model, MessageUtil.FALSE, dplctCode, MessageUtil.getMessage("common.overlapCode"));
            } else {
                // 상위부서코드가 존재하는지 검사
                for(int i = 0 ; i < deptVo.getUpDeptCdIds().length ; i++) {
                    DeptVO paramVo = new DeptVO();
                    paramVo.setUpDeptCdId(deptVo.getUpDeptCdIds()[i]);
                    if(Validate.isEmpty(opDeptService.selectUppderDeptCode(paramVo))) {
                        return responseJson(model, MessageUtil.FALSE, deptVo.getUpDeptCdIds()[i], MessageUtil.getMessage("common.noUpperCode"));
                    }
                }

                Integer key = 0;
                for(int i = 0 ; i < deptVo.getDeptCdIds().length ; i++) {
                    DeptVO excelVo = new DeptVO();
                    excelVo.setUpDeptCdId(deptVo.getUpDeptCdIds()[i]);
                    excelVo.setDeptNm(deptVo.getDeptNms()[i]);
                    excelVo.setDeptCdId(deptVo.getDeptCdIds()[i]);
                    excelVo.setRgnTelno(deptVo.getRgnTelnos()[i]);
                    excelVo.setTelofcTelno(deptVo.getTelofcTelnos()[i]);
                    excelVo.setIndivTelno(deptVo.getIndivTelnos()[i]);
                    excelVo.setRgnFxno(deptVo.getRgnFxnos()[i]);
                    excelVo.setTelofcFxno(deptVo.getTelofcFxnos()[i]);
                    excelVo.setIndivFxno(deptVo.getIndivFxnos()[i]);
                    excelVo.setTkcgTaskNm(deptVo.getTkcgTaskNms()[i]);
                    excelVo.setUseYn(deptVo.getUseYns()[i]);
                    // 등록 실행
                    key += opDeptService.insertDept(excelVo);
                }
                if(key != deptVo.getDeptCdIds().length) {
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
                }
            }

        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

}
