/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.file;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;

/**
 * 자원파일 정보 컨트롤러 클레스
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
@Controller("자원파일 관리")
@RequestMapping(value = { "/intra/resrce/file" })
public class ResrceFileController extends IntraController {

    @Resource(name = "opResrceFileService")
    private ResrceFileService opResrceFileService;

    /**
     * 자원파일 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectResrceFile.do")
    public void selectResrceFile(HttpServletRequest request, Model model, ResrceFileVO resrceFileVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opResrceFileService.selectResrceFile(resrceFileVo));
    }

    /**
     * 자원 파일명/설명/순번 변경 폼
     */
    @RequestMapping(value = "PD_updateResrceFileInfoForm.do")
    public void updateResrceFileInfoForm(HttpServletRequest request, Model model, ResrceFileVO resrceFileVo) {

        model.addAttribute(BaseConfig.KEY_DATA_LIST, opResrceFileService.selectResrceFileList(resrceFileVo));
    }

    /**
     * 자원 파일명/설명/순번 변경
     */
    @RequestMapping(value = "ND_updateResrceFileInfo.do")
    public String updateResrceFileInfo(HttpServletRequest request, Model model, ResrceFileVO resrceFileVo) {

        int updateCnt = opResrceFileService.updateResrceFileInfo(resrceFileVo);
        if(updateCnt <= 0) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }
}
