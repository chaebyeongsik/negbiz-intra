/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mssage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.intra.mssage.MssageMapper;
import zesinc.intra.mssage.MssageService;
import zesinc.intra.mssage.domain.MssageVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 메시지 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-09.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opMssageService")
public class MssageServiceImpl extends EgovAbstractServiceImpl implements MssageService {

    @Resource(name = "opMssageDao")
    private MssageMapper opMssageDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public MssageVO selectMssage(MssageVO mssageVo) {

        MssageVO dataVo = opMssageDao.selectMssage(mssageVo);
        if(dataVo == null) return null;

        // 첨부파일
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
        }

        return dataVo;
    }

    @Override
    public Pager<MssageVO> selectMssagePageList(MssageVO mssageVo) {

        List<MssageVO> dataList = opMssageDao.selectMssageList(mssageVo);
        Integer totalNum = opMssageDao.selectMssageListCount(mssageVo);

        Pager<MssageVO> pageVo = new Pager<MssageVO>(dataList, mssageVo, totalNum);
        List<MssageVO> list = pageVo.getList();
        for(MssageVO dataVO : list) {
            // 첨부파일
            if(Validate.isNotEmpty(dataVO.getFileSn())) {
                dataVO.setFileList(opFileService.selectFileList(dataVO.getFileSn()));
            }
        }

        return pageVo;
    }

    @Override
    public Integer insertMssage(MssageVO mssageVo) {

        if("Y".equals(mssageVo.getSndptyDelYn())) {
            mssageVo.setSndptyDelYn("N");
        } else {
            mssageVo.setSndptyDelYn("Y");
        }
        // 첨부파일
        if(Validate.isNotEmpty(mssageVo.getFileList())) {
	        Integer fileSn = opFileService.insertFileList(mssageVo.getFileList());
	        mssageVo.setFileSn(fileSn);
        }
        
        int result = 0;
        
        if(Validate.isNotEmpty(mssageVo.getReceivers())) {
            String datas[] = StringUtil.split(mssageVo.getReceivers(), ",");
            for(int i = 0 ; i < datas.length ; i++) {
                if(Validate.isEmpty(datas[i])) {
                    continue;
                }
                mssageVo.setPicId(datas[i]);
                result += opMssageDao.insertMssage(mssageVo);
            }
        }

        return result > 0 ? 1 : 0;
    }

    @Override
    public Integer updateRcptnDt(MssageVO mssageVo) {

        // 본인 수신 전 && 수신일시 없을 경우 수신일시 업데이트
        int result = 0;
        MssageVO dataVo = selectMssage(mssageVo);
        if(mssageVo.getPicId().equals(dataVo.getPicId()) && Validate.isEmpty(dataVo.getRcptnDt())){
            result = opMssageDao.updateRcptnDt(mssageVo);
        }

        return result;
    }

    @Override
    public Integer updateMssageDeleteAt(MssageVO mssageVo) {

        Integer delCnt = opMssageDao.updateMssageDeleteAt(mssageVo);

        return delCnt;
    }

    @Override
    public Integer updateMssageDeleateAtList(MssageVO mssageVo) {

        int result = 0;
        String[] arraySn = StringUtil.split(mssageVo.getArraySn(), ",");
        for(String regSn : arraySn){
            mssageVo.setRegSn(Integer.valueOf(regSn));
            result += opMssageDao.updateMssageDeleteAt(mssageVo);
        }

        return result;
    }

    @Override
    public List<MssageVO> selectReceptionChargerList(MssageVO mssageVo) {
        return opMssageDao.selectReceptionChargerList(mssageVo);
    }

}
