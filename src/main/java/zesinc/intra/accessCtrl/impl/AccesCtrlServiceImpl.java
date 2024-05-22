/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.accessCtrl.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.accessCtrl.AccesCtrlMapper;
import zesinc.intra.accessCtrl.AccesCtrlService;
import zesinc.intra.accessCtrl.domain.AccesCtrlVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 접근제어 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-02.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opAccesCtrlService")
public class AccesCtrlServiceImpl extends EgovAbstractServiceImpl implements AccesCtrlService {

    @Resource(name = "opAccesCtrlDao")
    private AccesCtrlMapper opAccesCtrlDao;

    @Override
    public List<AccesCtrlVO> selectMngrList(AccesCtrlVO accesCtrlVo) {

        return opAccesCtrlDao.selectMngrList(accesCtrlVo);
    }

    @Override
    public AccesCtrlVO selectAccesCtrl(AccesCtrlVO accesCtrlVo) {

        AccesCtrlVO dataVo = opAccesCtrlDao.selectAccesCtrl(accesCtrlVo);

        return dataVo;
    }

    @Override
    public List<AccesCtrlVO> selectAccesCtrlList(AccesCtrlVO accesCtrlVo) {

        List<AccesCtrlVO> dataList = opAccesCtrlDao.selectAccesCtrlList(accesCtrlVo);

        return dataList;
    }

    @Override
    public Integer insertAccesCtrl(AccesCtrlVO accesCtrlVo) {

        return opAccesCtrlDao.insertAccesCtrl(accesCtrlVo);
    }

    @Override
    public Integer updateAccesCtrl(AccesCtrlVO accesCtrlVo) {

        return opAccesCtrlDao.updateAccesCtrl(accesCtrlVo);
    }

    @Override
    public Integer deleteAccesCtrl(AccesCtrlVO accesCtrlVo) throws Exception {

        // 원본 삭제
        AccesCtrlVO dataVo = selectAccesCtrl(accesCtrlVo);
        dataVo.setParamMap(accesCtrlVo.getParamMap());

        Integer delCnt = opAccesCtrlDao.deleteAccesCtrl(dataVo);

        if(delCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }

        return delCnt;
    }

}
