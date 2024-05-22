/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import zesinc.component.file.support.DevFileUtil;
import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.FileUtil;
import zesinc.web.utils.PathUtil;
import zesinc.web.utils.XssUtil;
import zesinc.web.utils.image.ThumbnailUtil;
import zesinc.web.vo.ISessVO;

/**
 * File upload 지원 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 * </pre>
 * @see
 */
public class ResrceUploadHelper {

    /** 로깅 */
    private static Logger logger = LoggerFactory.getLogger(ResrceUploadHelper.class);

    /** 컨텐츠 파일 저장 경로(웹서버에서 직접 접근) */
    private static final String RESOURCE_FOLDER_PATH = BaseConfig.RESOURCE_FOLDER_PATH;
    /** File.separator */
    public static final String separator = "/";
    /** 썸네일문자열 */
    private static final String THUMB_STR = "thumb";
    /** 정적컨텐츠파일 ROOT 경로 */
    public static final String RESOURCE_ROOT = BaseConfig.RESOURCE_ROOT;

    /** 썸네일 가로설정 */
    private static final int THUMB_SIZE1W = Config.getInt("resrce-config.thumbSize1W");
    private static final int THUMB_SIZE2W = Config.getInt("resrce-config.thumbSize2W");
    private static final int THUMB_SIZE3W = Config.getInt("resrce-config.thumbSize3W");

    /** 썸네일 세로설정 */
    private static final int THUMB_SIZE1H = Config.getInt("resrce-config.thumbSize1H");
    private static final int THUMB_SIZE2H = Config.getInt("resrce-config.thumbSize2H");
    private static final int THUMB_SIZE3H = Config.getInt("resrce-config.thumbSize3H");

    /** 섬네일 생성가능 이미지 확장자 목록 */
    public static final List<String> THUMB_NAIL_EXTS;
    static {
        String[] exts = Config.getString("system-config.imageFileExts", "bmp,gif,jpe,jpeg,jfif,pcx,png,tiff,wbmp").split(",");
        THUMB_NAIL_EXTS = new ArrayList<String>();
        for(String ext : exts) {
            THUMB_NAIL_EXTS.add(ext);
        }
    }

    /**
     * upload 설명
     * 
     * @param request Http 요청 객체
     * @param folderName 파일저장 폴더명
     * @return 저장 파일 목록
     * @throws Exception
     */
    public static List<ResrceFileVO> upload(HttpServletRequest request, String folderName) {

        return upload(request, folderName, null);
    }

    /**
     * upload 설명
     * 
     * @param request Http 요청 객체
     * @param folderName 파일저장 폴더명
     * @param overwriteFile 덮어쓸 파일경로(파일컨텐츠는 하나의 파일만 사용하기 때문에 이전 파일을 변겨한다.)
     * @return 저장 파일 목록
     * @throws Exception
     */
    public static List<ResrceFileVO> upload(HttpServletRequest request, String folderName, String overwriteFile) {

        if(!MultipartHttpServletRequest.class.isAssignableFrom(request.getClass())) {
            return new ArrayList<ResrceFileVO>();
        }

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multiList = new ArrayList<MultipartFile>();
        Iterator<String> fileNames = multiRequest.getFileNames();
        while(fileNames.hasNext()) {
            multiList.addAll(multiRequest.getFiles(fileNames.next()));
        }

        List<ResrceFileVO> fileList = new ArrayList<ResrceFileVO>();

        int fileCnt = multiList.size();
        if(fileCnt > 0) {

            ISessVO sessVo = (ISessVO) OpHelper.getMgrSession();
            String deptCdId = sessVo.getDeptCdId();
            String chagerId = sessVo.getUsername();

            try {
                String rootPath = getRootPath();
                String filePath = getFilePath();
                String overwriteFilePath = RESOURCE_ROOT + overwriteFile;

                String webFolderPath = separator + filePath + folderName + separator + deptCdId + separator + getDatePath();
                String concludeFolderPath = rootPath + filePath + folderName + separator + deptCdId + separator + getDatePath();
                FileUtil.mkdirs(concludeFolderPath);

                String[] fileDescs = request.getParameterValues("fileExpln");

                ResrceFileVO fileVo;
                List<String> extList = Arrays.asList(BaseConfig.NOT_ALLOW_FILE_EXTS);

                for(int i = 0 ; i < fileCnt ; i++) {

                    MultipartFile multiFile = multiList.get(i);
                    String fileDesc = "";
                    if(fileDescs != null && fileDescs.length > i && fileDescs[i] != null) {
                        fileDesc = XssUtil.cleanTag(fileDescs[i], XssUtil.TYPE.ALL);
                    }

                    if(!multiFile.isEmpty()) {

                        String uuid = UUID.randomUUID().toString();
                        String ext = extension(multiFile.getOriginalFilename());

                        // 허용불가 첨부파일 유형인 경우 업로드 된 파일을 무시하며, 결과에도 포함하지 않는다.
                        if(extList.contains(ext)) {
                            multiFile.getInputStream().close();
                            continue;
                        }

                        String newFileName = uuid + "." + ext;
                        File newFileObject;

                        // 덮어쓰기 옵션이 있으면 이전 경로와 이전 파일명을 그대로 사용한다.
                        if(Validate.isNotEmpty(overwriteFile)) {
                            int pos = overwriteFile.lastIndexOf("/");
                            newFileName = overwriteFile.substring(pos + 1);

                            newFileObject = new File(overwriteFilePath);
                            if(newFileObject.exists()) {
                                newFileObject.delete();
                            } else {
                                // 기존파일정보의 디렉토리가 없을경우 물리적인 디렉토리를 생성한다.
                                if (!newFileObject.getParentFile().isDirectory()) {
                                    newFileObject.getParentFile().mkdirs();
                                }
                            }
                        } else {
                            newFileObject = new File(concludeFolderPath, newFileName);
                        }
                        String fileType = multiFile.getContentType();

                        multiFile.transferTo(newFileObject);

                        fileVo = new ResrceFileVO();

                        fileVo.setFile(newFileObject);
                        if(Validate.isNotEmpty(overwriteFile)) {
                            fileVo.setFileUrlAddr(overwriteFile);
                        } else {
                            fileVo.setFileUrlAddr(PathUtil.toUnixPath(webFolderPath) + newFileName);
                        }
                        fileVo.setSrvrFileNm(newFileName);
                        fileVo.setOrgnlFileNm(multiFile.getOriginalFilename());
                        fileVo.setFileTypeNm(fileType);
                        fileVo.setFileSzNm(FileUtil.toDisplaySize(multiFile.getSize()));
                        fileVo.setByteFileSz(multiFile.getSize());
                        fileVo.setFileExtnNm(ext);
                        fileVo.setFileExpln(fileDesc);
                        fileVo.setRgtrId(chagerId);

                        fileList.add(fileVo);

                        // 개발설정 파일 생성
                        DevFileUtil.makeDevPathFile(newFileObject, fileVo.getFileUrlAddr());
                    }
                }
            } catch (IOException e) {
                logger.error("Upload error", e);
            }
        }
        return fileList;
    }

    /**
     * <pre>
     * 파일목록을 받아서 섬네일 생성 가능 이미지 파일인 경우 섬네일을 생성하고,
     * FileVO.setThmbPathNm(PATH); 메소드를 통하여 설정해 준다.
     * 
     * 섬네일은 용도상 브라우저로 직접 접근가능한 웹경로를 ROOT로 하여 생성한다.
     * 예 : /thumb/이하/원본/경로/파일명
     * 
     * 참조 설정 : /src/main/resources/config/commons/resrce-commons-config.xml
     * </pre>
     * 
     * @param fileList 파일 목록. 확장자가 system-config 설정의 imageFileExts에 정의된 확장자만 생성한다.
     */
    public static void makeThumbNail(List<ResrceFileVO> fileList) {
        String rootPath = getRootPath();
        for(ResrceFileVO fileVo : fileList) {
            String ext = fileVo.getFileExtnNm().toLowerCase();
            if(THUMB_NAIL_EXTS.contains(ext)) {
                String filePath = fileVo.getFileUrlAddr();
                int pos = filePath.lastIndexOf("/");

                String thumbPath = filePath.subSequence(0, pos).toString();
                // 원본과 다른 이름으로 생성
                String thumbName = UUID.randomUUID().toString() + "." + ext;

                FileUtil.mkdirs(rootPath + thumbPath);

                File file = new File(rootPath + fileVo.getFileUrlAddr().substring(1));
                if(file.exists()) {
                    String thumbNm = THUMB_STR + "1_" + thumbName;
                    File thumb = new File(rootPath + thumbPath, thumbNm);
                    if(thumb.exists()) {
                        thumb.delete();
                    }
                    if(ThumbnailUtil.resize(file, thumb, THUMB_SIZE1W, THUMB_SIZE1H)) {
                        fileVo.setThmbPathNm1(thumbPath + "/" + thumbNm);
                        // 개발설정 파일 생성
                        DevFileUtil.makeDevPathFile(thumb, thumbPath, thumbNm);
                    }

                    thumbNm = THUMB_STR + "2_" + thumbName;
                    thumb = new File(rootPath + thumbPath, thumbNm);
                    if(thumb.exists()) {
                        thumb.delete();
                    }
                    if(ThumbnailUtil.resize(file, thumb, THUMB_SIZE2W, THUMB_SIZE2H)) {
                        fileVo.setThmbPathNm2(thumbPath + "/" + thumbNm);
                        // 개발설정 파일 생성
                        DevFileUtil.makeDevPathFile(thumb, thumbPath, thumbNm);
                    }

                    thumbNm = THUMB_STR + "3_" + thumbName;
                    thumb = new File(rootPath + thumbPath, thumbNm);
                    if(thumb.exists()) {
                        thumb.delete();
                    }
                    if(ThumbnailUtil.resize(file, thumb, THUMB_SIZE3W, THUMB_SIZE3H)) {
                        fileVo.setThmbPathNm3(thumbPath + "/" + thumbNm);
                        // 개발설정 파일 생성
                        DevFileUtil.makeDevPathFile(thumb, thumbPath, thumbNm);
                    }
                }
            }
        }
    }

    /**
     * 최상위 ROOT 경로 반환
     * 
     * @return 최상위 저장경로
     */
    public static String getRootPath() {

        String rootPath = RESOURCE_ROOT;

        rootPath += rootPath.endsWith(separator) ? "" : separator;

        return rootPath;
    }

    /**
     * ROOT 경로를 제외한 경로
     * 
     * @return
     */
    public static String getFilePath() {

        String filePath = RESOURCE_FOLDER_PATH;

        filePath += separator;

        return filePath;
    }

    /**
     * 년월일 기반 저장 경로 생성
     * 
     * @param folderPath
     * @return
     */
    public static String getDatePath() {

        Calendar cal = Calendar.getInstance();
        String yearFolderName = Integer.toString(cal.get(Calendar.YEAR));
        String monthFolderName = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String dayFolderName = Integer.toString(cal.get(Calendar.DATE));

        String datePath = yearFolderName + separator + monthFolderName
            + separator + dayFolderName + separator;

        return datePath;
    }

    /**
     * 파일 확장자 추출
     * 
     * @param fileName
     * @return
     */
    private static String extension(String fileName) {

        return FileUtil.extension(fileName).toLowerCase().trim();
    }
}
