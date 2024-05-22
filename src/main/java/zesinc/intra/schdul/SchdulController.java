/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.support.UploadHelper;
import zesinc.core.lang.Validate;
import zesinc.intra.cms.support.CmsReference;
import zesinc.intra.cms.support.domain.CmsReferenceVO;
import zesinc.intra.schdul.domain.SchdulVO;
import zesinc.intra.schdul.support.SchdulSupport;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.ReptitDateUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 일정 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("일정 관리")
@RequestMapping(value = { "/intra/schdul" })
@CmsReference(name = "일정휴일", method = "getCmsRefrence")
public class SchdulController extends IntraController {

    @Resource(name = "opSchdulService")
    private SchdulService opSchdulService;

    /**
     * 사용자 메뉴관리에서 본 기능의 링크를 획득하기 위한 함수
     * 
     * @return
     */
    public List<CmsReferenceVO> getCmsRefrence(CmsReferenceVO cmsRVO) {
        List<CmsReferenceVO> reference = new ArrayList<CmsReferenceVO>();
        CmsReferenceVO cmsReferenceVo = new CmsReferenceVO();
        /*
         * 각 구분마다의 키를 할당한다. 게시판은 당연히 게시판 코드
         * 키로 사용할 무언가가 없다면 임의의 문자열을 설정한다.
         * 필수로 key 를 등록해야 한다. 없는 경우 CMS에서 오작동 함
         * 설문은 도메인으로 구분되기 때문에 도메인코드를 설정
         */
        cmsReferenceVo.setKey(String.valueOf(cmsRVO.getSiteSn()));
        cmsReferenceVo.setName("일정(휴일)관리");
        // 대표 URL
        cmsReferenceVo.setUserMainMenuUrl("/user/schdul/BD_selectSchdulList.do?q_seCdId=");
        // 자식 URL
        List<String> lwprtMenuUrlAddr = new ArrayList<String>();
        lwprtMenuUrlAddr.add("/user/schdul/BD_selectSchdulCal.do?q_seCdId=");
        lwprtMenuUrlAddr.add("/user/schdul/PD_selectSchdul.do?q_seCdId=");

        cmsReferenceVo.setLwprtMenuUrlAddr(lwprtMenuUrlAddr);

        cmsReferenceVo.setMngrMenuUrlAddr("/intra/schdul/BD_selectSchdulList.do");

        reference.add(cmsReferenceVo);

        return reference;
    }

    /**
     * 일정 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectSchdul.do")
    public void selectSchdul(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opSchdulService.selectSchdul(schdulVo));
    }

    /**
     * 일정 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectSchdulList.do")
    public void selectSchdulList(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opSchdulService.selectSchdulPageList(schdulVo));
    }

    /**
     * 일정 달력 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectSchdulCal.do")
    public void selectSchdulCal(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        String schdulDate = "";
        String year = schdulVo.getString("q_year");
        String month = schdulVo.getString("q_month");

        if(Validate.isEmpty(year) || Validate.isEmpty(month)) {
            Calendar cal = Calendar.getInstance();
            String date = ReptitDateUtil.getToday();
            year = ReptitDateUtil.getYear(date, cal);
            month = ReptitDateUtil.getMonth(date, cal);
            schdulVo.addParam("q_year", year);
            schdulVo.addParam("q_month", month);
        }

        schdulDate = year + "-" + month + "-01";
        schdulVo.addParam("q_schdulDate", schdulDate);

        model.addAttribute("yearList", ReptitDateUtil.getRangeYearList(schdulDate, 5, 5));
        model.addAttribute("monthList", ReptitDateUtil.getRangeMonthList());
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opSchdulService.selectSchdulCalList(schdulVo));
    }

    /**
     * 일정 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertSchdul.do", method = RequestMethod.POST)
    public String insertSchdul(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(schdulVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            schdulVo.setRgtrId(loginVo.getPicId());
            schdulVo.setRgtrNm(loginVo.getPicNm());
            schdulVo.setIpAddr(getIpAddr());

            schdulVo.setFileList(UploadHelper.upload(request, "schdul", Boolean.TRUE));

            Integer key = opSchdulService.insertSchdul(schdulVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model, Boolean.TRUE, "insertOk", MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 일정 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "PD_updateSchdulForm.do" })
    public void updateSchdulForm(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        ValidateResultHolder holder = ValidateUtil.validate(schdulVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        List<Map<String, String>> schdulReptitTyList = SchdulSupport.getSchdulReptitTyList();
        model.addAttribute("reptitTyList", schdulReptitTyList);

        Integer regSn = schdulVo.getInteger("q_regSn");
        if(Validate.isNotEmpty(regSn)) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opSchdulService.selectSchdul(schdulVo));
        } else {
            // 달력보기에서 특정일자를 찍고 온 경우
            String schdulDate = schdulVo.getString("q_schdulDate");
            if(Validate.isNotEmpty(schdulDate)) {
                SchdulVO dataVo = new SchdulVO();
                dataVo.setBgngYmd(schdulDate);
                dataVo.setEndYmd(schdulDate);
                dataVo.setSeCdId(schdulVo.getString("q_seCdId"));

                model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);
            }
        }
    }

    /**
     * 일정 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateSchdul.do", method = RequestMethod.POST)
    public String updateSchdul(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        SchdulVO dataVo = opSchdulService.selectSchdul(schdulVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(schdulVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                schdulVo.setMdfrId(loginVo.getPicId());
                schdulVo.setUpdusrNm(loginVo.getPicNm());
                schdulVo.setIpAddr(getIpAddr());

                schdulVo.setFileList(UploadHelper.upload(request, "schdul", Boolean.TRUE));

                // 수정 실행
                Integer key = opSchdulService.updateSchdul(schdulVo);
                if(key != 1) {
                    return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
            }
            return responseJson(model, Boolean.TRUE, "updateOk", MessageUtil.getMessage("common.updateOk"));
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.notAllow"));
        }
    }

    /**
     * 일정 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteSchdul.do", method = RequestMethod.POST)
    public String deleteSchdul(HttpServletRequest request, Model model, SchdulVO schdulVo) throws Exception {

        SchdulVO dataVo = opSchdulService.selectSchdul(schdulVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opSchdulService.deleteSchdul(schdulVo);
            if(cnt != 1) {
                return responseJson(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.getMessage("common.notAllow"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 일정관리상세 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectSchdulDetailList.do")
    public String selectSchdulDetailList(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        // model.addAttribute(BaseConfig.KEY_DATA_LIST,
        // opSchdulService.selectSchdulDetailList(schdulVo));

        return responseJson(model, opSchdulService.selectSchdulDetailList(schdulVo));
    }

}
