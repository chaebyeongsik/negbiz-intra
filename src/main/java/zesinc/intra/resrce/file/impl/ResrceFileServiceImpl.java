/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.file.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.component.file.support.DevFileUtil;
import zesinc.core.lang.Validate;
import zesinc.intra.resrce.file.ResrceFileMapper;
import zesinc.intra.resrce.file.ResrceFileService;
import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.intra.resrce.support.ResrceUploadHelper;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 자원파일 정보 서비스 구현 클레스
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

@Service("opResrceFileService")
public class ResrceFileServiceImpl extends EgovAbstractServiceImpl implements ResrceFileService {

    @Resource(name = "opResrceFileDao")
    private ResrceFileMapper opResrceFileDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public ResrceFileVO selectResrceFile(ResrceFileVO resrceFileVo) {

        ResrceFileVO dataVo = opResrceFileDao.selectResrceFile(resrceFileVo);

        return dataVo;
    }

    @Override
    public List<ResrceFileVO> selectResrceFileList(ResrceFileVO resrceFileVo) {

        List<ResrceFileVO> dataList = opResrceFileDao.selectResrceFileList(resrceFileVo);

        return dataList;
    }

    @Override
    public Integer insertResrceFile(ResrceFileVO resrceFileVo) {

        return opResrceFileDao.insertResrceFile(resrceFileVo);
    }

    @Override
    public Integer updateResrceFile(ResrceFileVO resrceFileVo) {
        Integer updateCnt = 0;

        ResrceFileVO dataVo = selectResrceFile(resrceFileVo);
        if(Validate.isNotEmpty(dataVo)) {
            opResrceFileDao.updateResrceFile(resrceFileVo);
        }

        return updateCnt;
    }

    @Override
    public Integer updateResrceFileInfo(ResrceFileVO resrceFileVo) {

        int updateCnt = 0;

        Integer[] dataSns = resrceFileVo.getIntegers("q_dataSn");
        Integer[] chgCycls = resrceFileVo.getIntegers("q_chgCycl");
        Integer[] fileSns = resrceFileVo.getIntegers("q_fileSn");

        Integer[] sortSns = resrceFileVo.getIntegers("q_sortSn");
        String[] orgnlFileNms = resrceFileVo.getStrings("q_orgnlFileNm");
        String[] fileExplns = resrceFileVo.getStrings("q_fileExpln");

        ResrceFileVO dataVo = new ResrceFileVO();
        int fileCnt = fileSns.length;
        for(int i = 0 ; i < fileCnt ; i++) {
            dataVo.setDataSn(dataSns[i]);
            dataVo.setChgCycl(chgCycls[i]);
            dataVo.setFileSn(fileSns[i]);

            dataVo.setSortSn(sortSns[i]);
            dataVo.setOrgnlFileNm(orgnlFileNms[i]);
            dataVo.setFileExpln(fileExplns[i]);

            updateCnt += opResrceFileDao.updateResrceFileInfo(dataVo);
        }

        return updateCnt;
    }

    @Override
    public Integer deleteResrceFile(ResrceFileVO resrceFileVo) throws Exception {

        ResrceFileVO dataVo = opResrceFileDao.selectResrceFile(resrceFileVo);
        deleteFile(dataVo);

        return opResrceFileDao.deleteResrceFile(resrceFileVo);
    }

    @Override
    public Integer deleteResrceFileList(ResrceFileVO resrceFileVo) throws Exception {
        Integer delCnt = 0;
        // 파일목록 삭제
        List<ResrceFileVO> dataList = opResrceFileDao.selectResrceFileList(resrceFileVo);
        for(ResrceFileVO fileVo : dataList) {
            deleteFile(fileVo);
        }

        delCnt = opResrceFileDao.deleteResrceFile(resrceFileVo);

        return delCnt;
    }

    /**
     * 등록된 리소스 파일을 물리적으로 삭제한다.
     * 삭제시 섬네일이 존재하면 섬네일까지 삭제한다.
     * 
     * @param resrceFileVo
     * @return
     * @throws Exception
     */
    private Integer deleteFile(ResrceFileVO resrceFileVo) throws Exception {
        Integer deleteCnt = 0;

        String filePath = null;
        String RESOURCE_ROOT = ResrceUploadHelper.getRootPath();

        List<String> fileList = new ArrayList<String>();
        filePath = resrceFileVo.getFileUrlAddr();
        if(Validate.isNotEmpty(filePath)) {
            fileList.add(filePath);
        }
        filePath = resrceFileVo.getThmbPathNm1();
        if(Validate.isNotEmpty(filePath)) {
            fileList.add(filePath);
        }
        filePath = resrceFileVo.getThmbPathNm2();
        if(Validate.isNotEmpty(filePath)) {
            fileList.add(filePath);
        }
        filePath = resrceFileVo.getThmbPathNm3();
        if(Validate.isNotEmpty(filePath)) {
            fileList.add(filePath);
        }

        File file;
        boolean isDeleted;
        for(String path : fileList) {
            file = new File(RESOURCE_ROOT + path);
            if(file.exists()) {
                isDeleted = file.delete();
                // 개발 설정 삭제
                DevFileUtil.deleteDevPathFile(path);
                if(isDeleted) {
                    deleteCnt++;
                }
            }
        }

        return deleteCnt;
    }
}
