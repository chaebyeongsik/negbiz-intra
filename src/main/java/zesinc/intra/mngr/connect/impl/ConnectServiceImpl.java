/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.mngr.connect.ConnectMapper;
import zesinc.intra.mngr.connect.ConnectService;
import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.intra.mngr.connect.support.ConnectSupport;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자접속이력 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-05-16.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opConnectService")
public class ConnectServiceImpl extends EgovAbstractServiceImpl implements ConnectService {

    /** 관리자 로그 모드가 true인 경우 로그를 기록 */
    private static final Boolean MGR_LOG_MODE = BaseConfig.MGR_LOG_MODE;

    @Resource(name = "opConnectDao")
    private ConnectMapper opConnectDao;

    @Override
    public ConnectVO selectConnect(ConnectVO connectVo) {

        ConnectVO dataVo = opConnectDao.selectConnect(connectVo);

        return dataVo;
    }

    @Override
    public Pager<ConnectVO> selectConnectPageList(ConnectVO connectVo) {

        List<ConnectVO> dataList = opConnectDao.selectConnectList(connectVo);
        Integer totalNum = opConnectDao.selectConnectListCount(connectVo);
        for(ConnectVO dataVo : dataList) {
            dataVo.setLoginResultNm(ConnectSupport.getResultCodeNm(dataVo.getLgnRsltNo()));
        }

        return new Pager<ConnectVO>(dataList, connectVo, totalNum);
    }

    @Override
    public Integer insertConnect(ConnectVO connectVo) {
        Integer insertCnt = 0;
        if(MGR_LOG_MODE) {
            insertCnt = opConnectDao.insertConnect(connectVo);
        }
        return insertCnt;
    }

    @Override
    public Integer updateConnect(ConnectVO connectVo) {

        return opConnectDao.updateConnect(connectVo);
    }

    @Override
    public Integer deleteConnect(ConnectVO connectVo) throws Exception {

        // 원본 삭제
        ConnectVO dataVo = selectConnect(connectVo);
        dataVo.setParamMap(connectVo.getParamMap());

        Integer delCnt = opConnectDao.deleteConnect(dataVo);

        if(delCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }

        return delCnt;
    }

}
