/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.excelDown;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.component.excel.download.ExcelDownloadService;
import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.core.config.Config;
import zesinc.core.excel.CreateExcel;
import zesinc.core.lang.Validate;
import zesinc.intra.user.support.UserConsts;
import zesinc.web.spring.controller.BaseController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;

/**
 * 사용자 엑셀 다운로드 컨트롤러
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 6.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = "/intra/user/excelDown")
public class UserExcelDownloadController extends BaseController {

    @Resource(name = "opExcelDownloadService")
    private ExcelDownloadService service;

    /**
     * 사용자 엑셀 다운로드
     * 
     * @param request
     * @param model
     * @param excelDownloadVo
     * @return
     */
    @RequestMapping(value = "ND_userExcelDownload.do")
    public String userExcelDownload(HttpServletRequest request, Model model, ExcelDownloadVO excelDownloadVo) {

        String sessionValidate = (String) OpHelper.getSession(request, UserConsts.USER_INFO_LOG_VALIDATE);
        logger.debug("sessionValidate ----> " + sessionValidate);
        if(!Validate.isNull(sessionValidate)) {
            return alertAndBack(model, "Excel Down Fail");
//             return responseJson(model, MessageUtil.FALSE);
        }

        // queryId는 해당메뉴의 sql.xml에 쿼리 생성 resultType은 hmap으로 리턴
        String queryId = Config.getString("userExcelDown-config." + excelDownloadVo.getExcelKey() + ".queryId");
        String[] headerId = Config.getString("userExcelDown-config." + excelDownloadVo.getExcelKey() + ".headerId").split(",");
        String[] headerNm = Config.getString("userExcelDown-config." + excelDownloadVo.getExcelKey() + ".headerNm").split(",");
        String excelFileName = Config.getString("userExcelDown-config." + excelDownloadVo.getExcelKey() + ".fileNm");

        model.addAttribute(BaseConfig.EXCEL_FILE_NAME, excelFileName);

        ArrayList<List<String>> excelList = service.selectExcelList(queryId, headerId, headerNm, excelDownloadVo);

        HSSFWorkbook workbook = CreateExcel.excelCreate(excelList);

        OpHelper.removeSession(request, UserConsts.USER_INFO_LOG_VALIDATE);

        return responseExcel(model, workbook);
    }
}
