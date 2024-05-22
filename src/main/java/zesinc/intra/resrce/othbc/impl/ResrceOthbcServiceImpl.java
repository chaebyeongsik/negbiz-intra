/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.othbc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.resrce.cntnts.ResrceCntntsService;
import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.intra.resrce.file.ResrceFileService;
import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.intra.resrce.othbc.ResrceOthbcMapper;
import zesinc.intra.resrce.othbc.ResrceOthbcService;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공개 자원 정보 서비스 구현 클레스
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

@Service("opResrceOthbcService")
public class ResrceOthbcServiceImpl extends EgovAbstractServiceImpl implements ResrceOthbcService {

    @Resource(name = "opResrceOthbcDao")
    private ResrceOthbcMapper opResrceOthbcDao;

    @Resource(name = "opResrceCntntsService")
    private ResrceCntntsService opResrceCntntsService;

    @Resource(name = "opResrceFileService")
    ResrceFileService opResrceFileService;

    @Override
    public ResrceVO selectResrceOthbc(ResrceVO resrceVo) {

        ResrceVO dataVo = opResrceOthbcDao.selectResrceOthbc(resrceVo);
        // 첨부파일
        ResrceFileVO resrceFileVo = new ResrceFileVO();
        resrceFileVo.setDataSn(dataVo.getDataSn());
        resrceFileVo.setChgCycl(dataVo.getChgCycl());
        dataVo.setFileList(opResrceFileService.selectResrceFileList(resrceFileVo));

        return dataVo;
    }

    @Override
    public Pager<ResrceVO> selectResrceOthbcPageList(ResrceVO resrceVo) {

        List<ResrceVO> dataList = opResrceOthbcDao.selectResrceOthbcList(resrceVo);
        Integer totalNum = opResrceOthbcDao.selectResrceOthbcListCount(resrceVo);

        return new Pager<ResrceVO>(dataList, resrceVo, totalNum);
    }

}
