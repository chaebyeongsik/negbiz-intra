/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.hist;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.excel.download.ExcelDownloadService;
import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.core.config.Config;
import zesinc.core.excel.CreateExcel;
import zesinc.core.lang.Validate;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.intra.user.support.UserConsts;
import zesinc.intra.user.userManage.UserManageService;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자이력 정보 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 22.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자 이력 관리")
@RequestMapping(value = { "/intra/user/hist" })
public class UserHistController extends IntraController {

    @Resource(name = "opUserHistService")
    private UserHistService opUserHistService;

    @Resource(name = "opUserManageService")
    private UserManageService opUserManageService;

    @Resource(name = "opExcelDownloadService")
    private ExcelDownloadService service;

    /**
     * 사용자 로그 목록 조회
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectUserLogList.do")
    public String selectUserLogList(HttpServletRequest request, Model model, UserHistVO userHistVo) {

        String menuSeCd = userHistVo.getString("q_menuSeCd");
        if(Validate.isEmpty(userHistVo.getString("q_menuSeCd"))) {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        model.addAttribute(BaseConfig.KEY_PAGER, opUserHistService.selectUserLogList(userHistVo));

        if(menuSeCd.equals("R")) {
            return "intra/user/hist/BD_selectUserLogListR";
        } else if(menuSeCd.equals("O")) {
            model.addAttribute("menuNmList", opUserHistService.selectMenuNmList());
            return "intra/user/hist/BD_selectUserLogListO";
        } else {
            return "intra/user/hist/BD_selectUserLogListU";
        }
    }

    /**
     * 사용자 로그 입력 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_insertUserLogForm.do")
    public void insertUserLogForm(HttpServletRequest request, Model model, UserHistVO userHistVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userHistVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 사용자 로그 등록 처리(검색로그)
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertUserSearchLog.do", method = RequestMethod.POST)
    public String insertUserSearchLog(HttpServletRequest request, Model model, UserHistVO userHistVo) {

        try {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(userHistVo);
            if(holder.isValid()) {

                LoginVO loginVo = (LoginVO) getMgrSession();

                if(UserConsts.LOG_TYPE.equals("searchLog") && UserConsts.LOG_USE_YN.equals("Y")) {

                    // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
                    if (Validate.isNotEmpty(userHistVo.getPicPswd())) {
                        userHistVo.setPicPswd(CryptoUtil.decrypt(String.valueOf(userHistVo.getPicPswd())));
                    }
                    // 비밀번호 일치 여부 판단
                    boolean match = opUserHistService.selectCheckMngrPasswordMatch(loginVo, userHistVo);
                    if(!match) {
                        // 비밀번호 불일치
                        return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
                    }
                }else{
                    userHistVo.setLogCn(UserConsts.LOG_AUTO_RESN);
                }

                userHistVo.setPicId(loginVo.getPicId());

                String userIdQueryId = Config.getString("user-config.userHist.searchLog." + userHistVo.getSearchId() + ".userId-query");
                String countQueryId = Config.getString("user-config.userHist.searchLog." + userHistVo.getSearchId() + ".count-query");
                String oneOfTheIdInSearchList = opUserHistService.selectUserId(userIdQueryId, userHistVo);
                Integer listCnt = opUserHistService.selectListCount(countQueryId, userHistVo);
                userHistVo.setRsltNocs(listCnt);
                userHistVo.setUserId(oneOfTheIdInSearchList);
                userHistVo.setSrchSeCd("S");

                Integer key = opUserHistService.insertUserLog(userHistVo);

                if(key != 1) {
                    // 등록 실패
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
                } else {
                    if(UserConsts.LOG_TYPE.equals("searchLog") && UserConsts.LOG_USE_YN.equals("Y")) {
                        // 성공을 알리는 벨리데이션 값 셋팅
                        OpHelper.setSession(request, UserConsts.USER_INFO_LOG_VALIDATE, "Y");
                    }
                    OpHelper.setSession(request, UserConsts.USER_INFO_SEARCH_CND, userHistVo.getParamMap());

                    return responseJson(model, MessageUtil.TRUE);
                }
            }

        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
    }

    /**
     * 사용자 로그 등록 처리(개별로그)
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertPersonalUserInfoLog.do", method = RequestMethod.POST)
    public String insertPersonalUserInfoLog(HttpServletRequest request, Model model, UserHistVO userHistVo) throws Exception {

        LoginVO loginVo = (LoginVO) getMgrSession();
        userHistVo.setPicId(loginVo.getPicId());
        userHistVo.setSrchSeCd("R");
        userHistVo.setMenuSeCd("R");

        if(UserConsts.LOG_TYPE.equals("personalLog") && UserConsts.LOG_USE_YN.equals("Y")) {

            String userId = (String) OpHelper.getSession(request, UserConsts.SELECT_USER_ID);
            OpHelper.removeSession(request, UserConsts.SELECT_USER_ID);
            userHistVo.setUserId(userId);

            ValidateResultHolder holder = ValidateUtil.validate(userHistVo);
            if(holder.isValid()) {
                // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
                if (Validate.isNotEmpty(userHistVo.getPicPswd())) {
                    userHistVo.setPicPswd(CryptoUtil.decrypt(String.valueOf(userHistVo.getPicPswd())));
                }
                // 비밀번호 일치 여부 판단
                boolean match = opUserHistService.selectCheckMngrPasswordMatch(loginVo, userHistVo);
                if(!match) {
                    // 비밀번호 불일치
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
                }

                Integer key = opUserHistService.insertUserLog(userHistVo);

                if(key != 1) {
                    // 등록 실패
                    return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
                } else {
                    OpHelper.setSession(request, UserConsts.USER_INFO_LOG_VALIDATE, "Y");
                    String menuUrlAddr = (String) OpHelper.getSession(request, UserConsts.USER_INFO_ACCESS_MENU_URL);
                    if (!menuUrlAddr.contains("userId=")) {
                        if (menuUrlAddr.endsWith(".do")) {
                            menuUrlAddr = menuUrlAddr + "?userId=" + userId;
                        } else if (menuUrlAddr.endsWith("&")) {
                            menuUrlAddr = menuUrlAddr + "userId=" + userId;
                        } else {
                            menuUrlAddr = menuUrlAddr + "&userId=" + userId;
                        }
                    }
                    return responseJson(model, MessageUtil.TRUE, menuUrlAddr);
                }
            }

            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        } else {

            userHistVo.setLogCn(UserConsts.LOG_AUTO_RESN);
            opUserHistService.insertUserLog(userHistVo);
            OpHelper.setSession(request, UserConsts.USER_INFO_LOG_VALIDATE, "Y");

            return responseJson(model, MessageUtil.TRUE);
        }

    }

    /**
     * 출력용 사용자 로그 입력 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_insertUserLogOutputForm.do")
    public void insertUserLogOutputForm(HttpServletRequest request, Model model, UserHistVO userHistVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userHistVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        if(Validate.isNotEmpty(userHistVo.getUserId()))
            OpHelper.setSession(request, UserConsts.SELECT_USER_ID, userHistVo.getUserId());
    }

    /**
     * 출력용 사용자 로그 등록 처리
     * @throws Exception
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertUserOutputLog.do", method = RequestMethod.POST)
    public String insertUserOutputLog(HttpServletRequest request, Model model, UserHistVO userHistVo) throws Exception {

        String logUseYn = UserConsts.LOG_USE_YN;
        LoginVO loginVo = (LoginVO) getMgrSession();

        if(logUseYn.equals("Y")){
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(userHistVo);
            if(holder.isValid()) {
                // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
                if (Validate.isNotEmpty(userHistVo.getPicPswd())) {
                    userHistVo.setPicPswd(CryptoUtil.decrypt(String.valueOf(userHistVo.getPicPswd())));
                }
                // 비밀번호 일치 여부 판단
                boolean match = opUserHistService.selectCheckMngrPasswordMatch(loginVo, userHistVo);

                if(match) {
                    String userId = (String) OpHelper.getSession(request, UserConsts.SELECT_USER_ID);
                    if(Validate.isNotEmpty(userId)) OpHelper.removeSession(request, UserConsts.SELECT_USER_ID);
                    userHistVo.setUserId(userId);
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("login.notMatchPwd"));
                }
            }else{
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }
        }else{
            userHistVo.setLogCn(UserConsts.LOG_AUTO_RESN);
        }

        userHistVo.setPicId(loginVo.getPicId());

        String menuNm = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".menuNm");
        String menuSn = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".menuSn");
        String fileNm = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".fileNm");
        String listType = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".listType");
        userHistVo.setMenuNm(menuNm);
        userHistVo.setMenuSn(Integer.parseInt(menuSn));
        userHistVo.setOtptFileNm(fileNm + ".xls");
        userHistVo.setOtptNm(listType);

        // 로그 등록
        Integer key = opUserHistService.insertUserLog(userHistVo);

        if(key != 1) {
            // 등록 실패
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        } else {
            // 엑셀 출력 옵션
            String queryId = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".queryId");
            String[] headerId = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".headerId").split(",");
            String[] headerNm = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".headerNm").split(",");
            String excelFileName = Config.getString("userExcelDown-config." + userHistVo.getExcelKey() + ".fileNm");

            model.addAttribute(BaseConfig.EXCEL_FILE_NAME, excelFileName);

            ExcelDownloadVO excelVo = new ExcelDownloadVO();
            excelVo.setExcelKey(userHistVo.getExcelKey());
            excelVo.setParamMap(userHistVo.getParamMap());

            ArrayList<List<String>> excelList = service.selectExcelList(queryId, headerId, headerNm, excelVo);
            for(int i = 0; i < excelList.size(); i++){
                List<String> excelData = excelList.get(i);
                for(int j = 0; j < excelData.size(); j++){
                    String excelCol = excelData.get(j);
                    if(excelCol.endsWith("_USER_GRD_CD_DSCTN")){
                        String userGrdCdId = excelCol.substring(0, excelCol.indexOf("_USER_GRD_CD_DSCTN"));
                        String userGradListStr = opUserManageService.selectUserGrdCdDsctn(userGrdCdId);
                        excelData.set(j, userGradListStr.substring(1));
                    }
                }
            }
            HSSFWorkbook workbook = CreateExcel.excelCreate(excelList);

            return responseExcel(model, workbook);
        }
    }

    /**
     * 사용자 비밀번호 일치 여부 체크
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_selectCheckChargerPasswordMatch.do", method = RequestMethod.POST)
    public String selectCheckUserPasswordMatch(HttpServletRequest request, Model model, UserHistVO userHistVo) {
        LoginVO loginVo = (LoginVO) getMgrSession();
        return responseJson(model, opUserHistService.selectCheckMngrPasswordMatch(loginVo, userHistVo));
    }

}
