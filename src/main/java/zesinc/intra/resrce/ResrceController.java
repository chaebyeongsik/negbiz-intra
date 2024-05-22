/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.intra.resrce.support.ResrceUploadHelper;
import zesinc.login.domain.LoginVO;
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
 * 자원 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-14.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("자원 관리")
@RequestMapping(value = { "/intra/resrce" })
public class ResrceController extends IntraController {

    @Resource(name = "opResrceService")
    private ResrceService opResrceService;

    /**
     * 자원 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectResrceList.do")
    public void selectResrceBDList(HttpServletRequest request, Model model, ResrceVO resrceVo) {

    }

    /**
     * 자원 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_selectResrceList.do")
    public void selectResrceINCList(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        // 현재 메뉴에 대한 총괄권한 소유여부를 확인한다.
        AuthType authType = AuthSupport.getAuthType();
        if(AuthType.MANAGER.equals(authType)) {
            resrceVo.addParam("isSuper", "YES");
        } else {
            LoginVO loginVo = (LoginVO) getMgrSession();
            resrceVo.addParam("q_rgtrId", loginVo.getPicId());
        }

        model.addAttribute(BaseConfig.KEY_PAGER, opResrceService.selectResrcePageList(resrceVo));
    }

    /**
     * 자원 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "PD_insertResrceForm.do" })
    public void insertResrceForm(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        ValidateResultHolder holder = ValidateUtil.validate(resrceVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 자원 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertResrce.do", method = RequestMethod.POST)
    public String insertResrce(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(resrceVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            resrceVo.setRgtrId(loginVo.getPicId());
            resrceVo.setRgtrNm(loginVo.getPicNm());
            resrceVo.setIpAddr(getIpAddr());

            List<ResrceFileVO> fileList = ResrceUploadHelper.upload(request, "resrce");
            // 썸네일 생성
            ResrceUploadHelper.makeThumbNail(fileList);

            resrceVo.setFileList(fileList);

            Integer key = opResrceService.insertResrce(resrceVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        // 파라미터에 키값을 추가하여 수정 화면으로 이동
        String url = "PD_updateResrceForm.do?" + OpHelper.getSearchQueryString(request);
        url += "&q_dataSn=" + resrceVo.getDataSn() + "&q_chgCycl=" + resrceVo.getChgCycl();

        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 자원 수정 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "PD_updateResrceForm.do" })
    public void updateResrceForm(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        ValidateResultHolder holder = ValidateUtil.validate(resrceVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        ResrceVO dataVo = opResrceService.selectResrce(resrceVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceService.selectResrce(resrceVo));
        }

    }

    /**
     * 자원 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateResrce.do", method = RequestMethod.POST)
    public String updateResrce(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        ResrceVO dataVo = opResrceService.selectResrce(resrceVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(resrceVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                resrceVo.setMdfrId(loginVo.getPicId());
                resrceVo.setUpdusrNm(loginVo.getPicNm());
                resrceVo.setIpAddr(getIpAddr());

                /*
                 * 자원유형이 파일인경우는 단일 파일만 존재하기도 하고,
                 * 이미 사용중인 곳이 있을 수 있으므로 이전 파일 정보를 구하여 덮어쓴다.
                 */
                String prvFileUrlAddr = null;
                if(dataVo.getDataTypeNo().equals("1010")) {
                    ResrceFileVO dataFileVo = dataVo.getFileList().get(0);
                    prvFileUrlAddr = dataFileVo.getFileUrlAddr();
                }
                List<ResrceFileVO> fileList = ResrceUploadHelper.upload(request, "resrce", prvFileUrlAddr);
                // 썸네일 생성
                ResrceUploadHelper.makeThumbNail(fileList);

                resrceVo.setFileList(fileList);

                // 수정 실행
                Integer key = opResrceService.updateResrce(resrceVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }

            String url = "PD_updateResrceForm.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 카테고리 이동
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateResrceCtgrySn.do", method = RequestMethod.POST)
    public String updateResrceCtgrySn(HttpServletRequest request, Model model, ResrceVO resrceVo) {

        ResrceVO dataVo = opResrceService.selectResrce(resrceVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(resrceVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                resrceVo.setMdfrId(loginVo.getPicId());
                resrceVo.setUpdusrNm(loginVo.getPicNm());
                resrceVo.setIpAddr(getIpAddr());
                resrceVo.setFileList(ResrceUploadHelper.upload(request, "resrce"));

                // 수정 실행
                Integer key = opResrceService.updateResrceCtgrySn(resrceVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }

            String url = "BD_selectResrce.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
    }

    /**
     * 자원 삭제
     * 
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteResrce.do", method = RequestMethod.POST)
    public String deleteResrce(HttpServletRequest request, Model model, ResrceVO resrceVo) throws Exception {

        ResrceVO dataVo = opResrceService.selectResrce(resrceVo);

        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opResrceService.deleteResrce(resrceVo);
            if(cnt != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
        }

        return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.notAllow"));
    }

}
