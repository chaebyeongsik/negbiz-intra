/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbstmplat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.FileUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.XssUtil;
import zesinc.web.utils.XssUtil.TYPE;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 게시판템플릿 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-23.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판템플릿 관리")
@RequestMapping(value = { "/intra/bbstmplat" })
public class BbsTmplatController extends IntraController {

    @Resource(name = "opBbsTmplatService")
    private BbsTmplatService opBbsTmplatService;

    /** WAS 서버의 Root 경로 */
    private static String WAS_ROOT = BaseConfig.WEBAPP_ROOT;
    /** 개발환경 소스파일 Root 경로 */
    private static List<String> PROGRAM_PATH = BaseConfig.PROGRAM_PATH_LIST;
    /** 개발환경 소스파일 Root 경로 */
    private static List<String> DEV_PATH = BaseConfig.DEV_PATH_LIST;
    /** 파일인코딩 케릭터셋 */
    private static final String FILE_ENCODING = Config.getString("system-config.defaultCharset");

    /**
     * 게시판템플릿
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsTmplat.do")
    public void selectBbsTmplat(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

    }

    /**
     * 게시판템플릿 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBbsTmplatList.do")
    public void selectBbsTmplatList(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opBbsTmplatService.selectBbsTmplatPageList(bbsTmplatVo));
    }

    /**
     * 게시판템플릿 입력 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_insertBbsTmplatForm.do" })
    public void insertBbsTmplatForm(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        ValidateResultHolder holder = ValidateUtil.validate(bbsTmplatVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute("tagList", opBbsTmplatService.selectBbsItemList());
    }

    /**
     * 게시판템플릿 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertBbsTmplat.do", method = RequestMethod.POST)
    public String insertBbsTmplat(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsTmplatVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsTmplatVo.setRgtrId(loginVo.getPicId());
            bbsTmplatVo.setRgtrNm(loginVo.getPicNm());
            bbsTmplatVo.setIpAddr(getIpAddr());

            // 템플릿ID 중복체크
            if(0 != opBbsTmplatService.selectDplctChckId(bbsTmplatVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.dplctChckFalse"));
            }

            // 파일 업로드
            List<FileVO> fileList = UploadHelper.upload(request, "bbsTmplat");
            bbsTmplatVo.setFileList(fileList);

            // 템플릿스크린샷 파일경로 설정
            for(FileVO fileVo : fileList) {
                bbsTmplatVo.setTmpltScrnPathNm(fileVo.getFileUrlAddr());
            }

            // 컨텐츠내용이 아닌 컬럼들은 SCRIPT방지
            bbsTmplatVo.setTmpltId(XssUtil.cleanTag(bbsTmplatVo.getTmpltId(), TYPE.SCRIPT));
            bbsTmplatVo.setTmpltNm(XssUtil.cleanTag(bbsTmplatVo.getTmpltNm(), TYPE.SCRIPT));

            Integer key = 0;
            // 템플릿 파일 생성
            Boolean success = createTmplatFile(bbsTmplatVo);
            if(success) {
                key = opBbsTmplatService.insertBbsTmplat(bbsTmplatVo);
            }
            if(!success || key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectBbsTmplatList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 게시판템플릿 수정 폼
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_updateBbsTmplatForm.do" })
    public void updateBbsTmplatForm(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        ValidateResultHolder holder = ValidateUtil.validate(bbsTmplatVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opBbsTmplatService.selectBbsTmplat(bbsTmplatVo));
        model.addAttribute("tagList", opBbsTmplatService.selectBbsItemList());
    }

    /**
     * 게시판템플릿 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBbsTmplat.do", method = RequestMethod.POST)
    public String updateBbsTmplat(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsTmplatVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bbsTmplatVo.setMdfrId(loginVo.getPicId());
            bbsTmplatVo.setUpdusrNm(loginVo.getPicNm());
            bbsTmplatVo.setIpAddr(getIpAddr());

            // 파일 업로드
            List<FileVO> fileList = UploadHelper.upload(request, "bbsTmplat");
            bbsTmplatVo.setFileList(fileList);

            // 템플릿스크린샷 파일경로 설정
            for(FileVO fileVo : fileList) {
                bbsTmplatVo.setTmpltScrnPathNm(fileVo.getFileUrlAddr());
            }

            // 컨텐츠내용이 아닌 컬럼들은 SCRIPT방지
            bbsTmplatVo.setTmpltId(XssUtil.cleanTag(bbsTmplatVo.getTmpltId(), TYPE.SCRIPT));
            bbsTmplatVo.setTmpltNm(XssUtil.cleanTag(bbsTmplatVo.getTmpltNm(), TYPE.SCRIPT));

            Integer key = 0;
            // 템플릿 파일 생성
            Boolean success = createTmplatFile(bbsTmplatVo);
            if(success) {
                // 수정 실행
                key = opBbsTmplatService.updateBbsTmplat(bbsTmplatVo);
            }
            if(!success || key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectBbsTmplatList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 게시판템플릿 삭제 (FLAG 처리)
     *
     * @param request
     * @param model
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteBbsTmplat.do", method = RequestMethod.POST)
    public String deleteBbsTmplat(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        bbsTmplatVo.setMdfrId(loginVo.getPicId());
        bbsTmplatVo.setUpdusrNm(loginVo.getPicNm());
        bbsTmplatVo.setIpAddr(getIpAddr());

        Integer cnt = opBbsTmplatService.deleteBbsTmplat(bbsTmplatVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_selectBbsTmplatList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 게시판템플릿 ID 중복체크
     *
     * @param request
     * @param model
     * @param deptVo
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectDplctChckId.do")
    public String selectDplctChckId(HttpServletRequest request, Model model, BbsTmplatVO bbsTmplatVo) {

        Integer cnt = opBbsTmplatService.selectDplctChckId(bbsTmplatVo);

        ValidateResultHolder holder = ValidateUtil.doFiledValidate(bbsTmplatVo, "tmpltId");

        if(cnt == 0 && holder.isValid()) {
            return responseText(model, cnt);
        }
        if(!holder.isValid()) {
            return responseText(model, 2);
        }

        return responseText(model, 1);
    }

    /**
     * 템플릿 html코딩소스로 템플릿파일 생성
     *
     * @param bbsTmplatVo
     */
    private Boolean createTmplatFile(BbsTmplatVO bbsTmplatVo) {

        String fullPath = "";
        String mvnmFileNm = "";
        String filePathNm = "";

        if(bbsTmplatVo.getTmpltTypeCd().equals("L")) {
            mvnmFileNm = "PD_bbs.list.jsp";
        } else if(bbsTmplatVo.getTmpltTypeCd().equals("V")) {
            mvnmFileNm = "PD_bbs.view.jsp";
        } else if(bbsTmplatVo.getTmpltTypeCd().equals("F")) {
            mvnmFileNm = "PD_bbs.form.jsp";
        }

        // 설정에 동시 저장 경로 목록이 있는 경우
        if(Validate.isNotEmpty(PROGRAM_PATH)) {
            for(String programPath : PROGRAM_PATH) {
                fullPath = programPath;
                filePathNm = "WEB-INF/jsp/common/bbsTmplats/" + bbsTmplatVo.getTmpltId() + "/";
                if(!fullPath.endsWith("/")) {
                    fullPath += "/";
                }

                fullPath += filePathNm + mvnmFileNm;
                bbsTmplatVo.setTmpltFilePathNm("/" + filePathNm + mvnmFileNm);

                File file = new File(fullPath);
                publish(file, bbsTmplatVo.getTmpltCn());
            }
        }

        // 개발 모드인경우 서버 Plugin ROOT 이외에 로컬 소스 경로(Eclipse)에도 파일을 생성한다.
        if(BaseConfig.PRO_MODE && Validate.isNotEmpty(DEV_PATH)) {
            for(String devPath : DEV_PATH) {
                fullPath = devPath;
                filePathNm = "WEB-INF/jsp/common/bbsTmplats/" + bbsTmplatVo.getTmpltId() + "/";
                if(!fullPath.endsWith("/")) {
                    fullPath += "/";
                }

                fullPath += filePathNm + mvnmFileNm;
                bbsTmplatVo.setTmpltFilePathNm("/" + filePathNm + mvnmFileNm);

                File file = new File(fullPath);
                publish(file, bbsTmplatVo.getTmpltCn());
            }
        }

        // WAS Root를 기준으로 파일 생성
        fullPath = WAS_ROOT;
        if(!fullPath.endsWith("/")) {
            fullPath += "/";
        }
        fullPath += filePathNm + mvnmFileNm;
        bbsTmplatVo.setTmpltFilePathNm("/" + filePathNm + mvnmFileNm);

        File file = new File(fullPath);
        return publish(file, bbsTmplatVo.getTmpltCn());
    }

    /**
     * 퍼블리싱 대상 내용을 받아서 파일로 생성
     *
     * @return
     */
    private Boolean publish(File file, String content) {
        Boolean published = Boolean.FALSE;

        try {
            File parent = new File(file.getParent());
            if(!parent.isDirectory()) {
                FileUtil.mkdirs(parent);
                parent.setWritable(true);
            }

            FileUtil.writeString(file, content, FILE_ENCODING);
            published = Boolean.TRUE;

            if(logger.isDebugEnabled()) {
                logger.debug("Bbs template Content file create path : {}", file.getAbsolutePath());
            }
        } catch (IOException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("Bbs template file write failed ! path : {}", file.getAbsolutePath(), e);
            }
        }

        return published;
    }
}
