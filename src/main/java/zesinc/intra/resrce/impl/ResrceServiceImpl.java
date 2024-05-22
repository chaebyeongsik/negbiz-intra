/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.intra.resrce.ResrceMapper;
import zesinc.intra.resrce.ResrceService;
import zesinc.intra.resrce.cntnts.ResrceCntntsService;
import zesinc.intra.resrce.cntnts.domain.ResrceCntntsVO;
import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.intra.resrce.file.ResrceFileService;
import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 자원 정보 서비스 구현 클레스
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

@Service("opResrceService")
public class ResrceServiceImpl extends EgovAbstractServiceImpl implements ResrceService {

    @Resource(name = "opResrceDao")
    private ResrceMapper opResrceDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Resource(name = "opResrceCntntsService")
    private ResrceCntntsService opResrceCntntsService;

    @Resource(name = "opResrceFileService")
    ResrceFileService opResrceFileService;

    @Override
    public ResrceVO selectResrce(ResrceVO resrceVo) {

        ResrceVO dataVo = opResrceDao.selectResrce(resrceVo);
        // 첨부파일
        ResrceFileVO resrceFileVo = new ResrceFileVO();
        resrceFileVo.setDataSn(dataVo.getDataSn());
        resrceFileVo.setChgCycl(dataVo.getChgCycl());
        dataVo.setFileList(opResrceFileService.selectResrceFileList(resrceFileVo));

        return dataVo;
    }

    @Override
    public Pager<ResrceVO> selectResrcePageList(ResrceVO resrceVo) {

        List<ResrceVO> dataList = opResrceDao.selectResrceList(resrceVo);
        Integer totalNum = opResrceDao.selectResrceListCount(resrceVo);

        return new Pager<ResrceVO>(dataList, resrceVo, totalNum);
    }

    @Override
    public Integer insertResrce(ResrceVO resrceVo) {

        Integer insertCnt = opResrceDao.insertResrce(resrceVo);
        resrceVo.setChgCycl(1);

        // 파일 자원인 경우와 아닌 경우로 구분
        String dataTypeNo = resrceVo.getDataTypeNo();
        if(dataTypeNo.equals("1010")) {
            List<ResrceFileVO> fileList = resrceVo.getFileList();
            if(Validate.isNotEmpty(fileList)) {
                ResrceFileVO resrceFileVo = resrceVo.getFileList().get(0);
                resrceFileVo.setDataSn(resrceVo.getDataSn());
                resrceFileVo.setChgCycl(resrceVo.getChgCycl());

                opResrceFileService.insertResrceFile(resrceFileVo);
            }
        } else {
            List<ResrceFileVO> fileList = resrceVo.getFileList();
            for(ResrceFileVO resrceFileVo : fileList) {
                resrceFileVo.setDataSn(resrceVo.getDataSn());
                resrceFileVo.setChgCycl(resrceVo.getChgCycl());

                opResrceFileService.insertResrceFile(resrceFileVo);
            }
        }

        // 파일 자원이 아닌경우
        if(!dataTypeNo.equals("1010")) {
            ResrceCntntsVO resrceCntntsVo = new ResrceCntntsVO();

            resrceCntntsVo.setDataSn(resrceVo.getDataSn());
            resrceCntntsVo.setChgCycl(resrceVo.getChgCycl());
            resrceCntntsVo.setContsCn(resrceVo.getContsCn());
            resrceCntntsVo.setWrtTypeNm(resrceVo.getWrtTypeNm());
            resrceCntntsVo.setRgtrId(resrceVo.getRgtrId());

            opResrceCntntsService.insertResrceCntnts(resrceCntntsVo);
        }

        return insertCnt;
    }

    @Override
    public Integer updateResrce(ResrceVO resrceVo) {
        Integer updateCnt = 0;

        ResrceVO dataVo = selectResrce(resrceVo);
        if(Validate.isNotEmpty(dataVo)) {
            String dataTypeNo = dataVo.getDataTypeNo();

            Integer[] fileSns = resrceVo.getFileSns();
            if(Validate.isNotEmpty(fileSns)) {
                ResrceFileVO resrceFileVo = new ResrceFileVO();
                for(Integer fileSn : fileSns) {
                    try {
                        // 자원파일삭제
                        resrceFileVo.setDataSn(resrceVo.getDataSn());
                        resrceFileVo.setChgCycl(resrceVo.getChgCycl());
                        resrceFileVo.setFileSn(fileSn);

                        opResrceFileService.deleteResrceFile(resrceFileVo);
                    } catch (Exception e) {
                        // do nothing
                    }
                }
            }

            if(dataTypeNo.equals("1010")) {
                List<ResrceFileVO> fileList = resrceVo.getFileList();
                if(Validate.isNotEmpty(fileList)) {
                    ResrceFileVO resrceFileVo = resrceVo.getFileList().get(0);
                    resrceFileVo.setDataSn(resrceVo.getDataSn());
                    resrceFileVo.setChgCycl(resrceVo.getChgCycl());
                    resrceFileVo.setFileSn(1);

                    opResrceFileService.updateResrceFile(resrceFileVo);
                }
            } else {
                List<ResrceFileVO> fileList = resrceVo.getFileList();
                for(ResrceFileVO resrceFileVo : fileList) {
                    resrceFileVo.setDataSn(resrceVo.getDataSn());
                    resrceFileVo.setChgCycl(resrceVo.getChgCycl());

                    opResrceFileService.insertResrceFile(resrceFileVo);
                }
            }

            // 파일 자원이 아닌경우
            if(!dataTypeNo.equals("1010")) {
                ResrceCntntsVO resrceCntntsVo = new ResrceCntntsVO();

                resrceCntntsVo.setDataSn(resrceVo.getDataSn());
                resrceCntntsVo.setChgCycl(resrceVo.getChgCycl());
                resrceCntntsVo.setContsCn(resrceVo.getContsCn());
                resrceCntntsVo.setWrtTypeNm(resrceVo.getWrtTypeNm());
                resrceCntntsVo.setRgtrId(resrceVo.getRgtrId());

                opResrceCntntsService.updateResrceCntnts(resrceCntntsVo);
            }

            // 공개여부에 변경이 없다면 쿼리를 무시
            String rlsYn = dataVo.getRlsYn();
            if(rlsYn.equals(resrceVo.getRlsYn())) {
                resrceVo.setRlsYn(null);
            }
            updateCnt = opResrceDao.updateResrce(resrceVo);
        }

        return updateCnt;
    }

    @Override
    public Integer updateResrceCtgrySn(ResrceVO resrceVo) {

        return opResrceDao.updateResrceCtgrySn(resrceVo);
    }

    @Override
    public Integer deleteResrce(ResrceVO resrceVo) throws Exception {

        Integer deleteCnt = 0;
        // 원본 삭제
        ResrceVO dataVo = opResrceDao.selectResrce(resrceVo);
        if(Validate.isNotEmpty(dataVo)) {
            dataVo.setParamMap(resrceVo.getParamMap());

            // 자원파일삭제
            ResrceFileVO resrceFileVo = new ResrceFileVO();
            resrceFileVo.setDataSn(resrceVo.getDataSn());
            resrceFileVo.setChgCycl(resrceVo.getChgCycl());

            opResrceFileService.deleteResrceFileList(resrceFileVo);

            // 컨텐츠삭제
            ResrceCntntsVO resrceCntntsVo = new ResrceCntntsVO();
            resrceCntntsVo.setDataSn(resrceVo.getDataSn());
            resrceCntntsVo.setChgCycl(resrceVo.getChgCycl());

            opResrceCntntsService.deleteResrceCntnts(resrceCntntsVo);

            // 마스터 삭제
            deleteCnt = opResrceDao.deleteResrce(dataVo);

            if(deleteCnt > 1) {
                throw new Exception("삭제중 오류가 발생되었습니다.");
            }
        }

        return deleteCnt;
    }

}
