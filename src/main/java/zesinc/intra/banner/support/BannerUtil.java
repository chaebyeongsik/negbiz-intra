/*
 * Copyright (c) 2012 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.banner.support;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zesinc.component.file.support.DevFileUtil;
import zesinc.core.lang.Validate;
import zesinc.web.support.BaseConfig;

/**
 * 배너 유틸리티
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 19.    방기배
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BannerUtil {

    /** 로깅 */
    private static Logger logger = LoggerFactory.getLogger(BannerUtil.class);

    /** WAS 서버의 Root 경로 */
    public static String RESOURCE_ROOT = BaseConfig.RESOURCE_ROOT;

    /**
     * 업로드 콘트롤을 통해 업로드된 파일을 삭제한다.
     * 
     * @param filePath 문자열
     */
    public static void deleteFile(String filePath) {

        String fullPath = RESOURCE_ROOT;

        File file;
        if(Validate.isNotEmpty(filePath)) {
            String strFile = fullPath + filePath;
            strFile.replace("/", File.separator);

            file = new File(strFile);
            if(file.isFile()) {
                file.delete();
                logger.debug("delete file is {}", file.getAbsolutePath());
            }

            // 개발 모드인경우 서버 Plugin ROOT 이외에 로컬 소스 경로(Eclipse)에도 파일을 삭제한다.
            DevFileUtil.deleteDevPathFile(filePath);
        }
    }

}
