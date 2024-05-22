/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.prhibt.impl;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.core.utils.StringUtil;
import zesinc.intra.prhibt.PrhibtWrdMapper;
import zesinc.intra.prhibt.PrhibtWrdService;
import zesinc.intra.prhibt.domain.PrhibtWrdHistoryVO;
import zesinc.intra.prhibt.domain.PrhibtWrdVO;
import zesinc.intra.prhibt.support.LOG_TYPE;
import zesinc.web.support.helper.CacheHelper;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 금지단어 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-17.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opPrhibtWrdService")
public class PrhibtWrdServiceImpl extends EgovAbstractServiceImpl implements PrhibtWrdService {

    @Resource(name = "opPrhibtWrdDao")
    private PrhibtWrdMapper opPrhibtWrdDao;

    @Override
    public Integer selectDplctChckCode(PrhibtWrdVO prhibtWrdVo) {

        return opPrhibtWrdDao.selectDplctChckCode(prhibtWrdVo);
    }

    @Override
    public PrhibtWrdVO selectPrhibtWrd(PrhibtWrdVO prhibtWrdVo) {

        PrhibtWrdVO dataVo = opPrhibtWrdDao.selectPrhibtWrd(prhibtWrdVo);

        return dataVo;
    }

    @Override
    public Pager<PrhibtWrdVO> selectPrhibtWrdPageList(PrhibtWrdVO prhibtWrdVo) {

        List<PrhibtWrdVO> dataList = opPrhibtWrdDao.selectPrhibtWrdList(prhibtWrdVo);
        Integer totalNum = opPrhibtWrdDao.selectPrhibtWrdListCount(prhibtWrdVo);

        return new Pager<PrhibtWrdVO>(dataList, prhibtWrdVo, totalNum);
    }

    @Override
    public Integer insertPrhibtWrd(PrhibtWrdVO prhibtWrdVo) {

        // 중복 단어 필터링
        if(Validate.isNotEmpty(prhibtWrdVo.getPhbwdCn())) {
            setFilterPprhibtWrd(prhibtWrdVo);
        }
        Integer insertCnt = opPrhibtWrdDao.insertPrhibtWrd(prhibtWrdVo);

        // 등록 이력
        prhibtWrdVo.setLogType(LOG_TYPE.INSERT.getCdId());
        prhibtWrdVo.setMdfrId(prhibtWrdVo.getRgtrId());
        opPrhibtWrdDao.insertPrhibtWrdHistory(prhibtWrdVo);

        return insertCnt;
    }

    @Override
    public Integer updatePrhibtWrd(PrhibtWrdVO prhibtWrdVo) {

        // 중복 단어 필터링
        if(Validate.isNotEmpty(prhibtWrdVo.getPhbwdCn())) {
            setFilterPprhibtWrd(prhibtWrdVo);
        }
        Integer updateCnt = opPrhibtWrdDao.updatePrhibtWrd(prhibtWrdVo);

        // 수정이력
        prhibtWrdVo.setLogType(LOG_TYPE.UPDATE.getCdId());
        prhibtWrdVo.setPhbwdCdId(prhibtWrdVo.getString("q_phbwdCdId"));
        opPrhibtWrdDao.insertPrhibtWrdHistory(prhibtWrdVo);

        return updateCnt;
    }

    /**
     * 중복 단어 필터링 및 정렬
     * 
     * @param prhibtWrdVo
     */
    private void setFilterPprhibtWrd(PrhibtWrdVO prhibtWrdVo) {
        // 중복 단어 필터링
        if(Validate.isNotEmpty(prhibtWrdVo.getPhbwdCn())) {
            StringBuilder sb = new StringBuilder();
            SortedSet<String> tset = new TreeSet<String>();
            String[] prhibtWrds = StringUtil.split(prhibtWrdVo.getPhbwdCn(), ",");

            for(String prhibtWrd : prhibtWrds) {
                tset.add(prhibtWrd.trim());
            }

            Iterator<String> it = tset.iterator();
            String prhibtWrd;
            while(it.hasNext()) {
                prhibtWrd = it.next().trim();
                if(Validate.isNotEmpty(prhibtWrd)) {
                    sb.append(prhibtWrd).append(",");
                }
            }

            prhibtWrdVo.setPhbwdCn(sb.toString());
            tset.clear();
        }
    }

    @Override
    public Integer deletePrhibtWrd(PrhibtWrdVO prhibtWrdVo) {

        // 원본 삭제
        PrhibtWrdVO dataVo = selectPrhibtWrd(prhibtWrdVo);
        dataVo.setParamMap(prhibtWrdVo.getParamMap());

        // 삭제이력
        dataVo.setLogType(LOG_TYPE.DELETE.getCdId());
        dataVo.setMdfrId(prhibtWrdVo.getMdfrId());
        opPrhibtWrdDao.insertPrhibtWrdHistory(dataVo);

        Integer delCnt = opPrhibtWrdDao.deletePrhibtWrd(dataVo);

        return delCnt;
    }

    @Override
    public PrhibtWrdHistoryVO selectPrhibtWrdHistory(PrhibtWrdHistoryVO prhibtWrdHistoryVo) {

        PrhibtWrdHistoryVO dataVo = opPrhibtWrdDao.selectPrhibtWrdHistory(prhibtWrdHistoryVo);

        return dataVo;
    }

    @Override
    public Pager<PrhibtWrdHistoryVO> selectPrhibtWrdHistoryPageList(PrhibtWrdHistoryVO prhibtWrdHistoryVo) {

        List<PrhibtWrdHistoryVO> dataList = opPrhibtWrdDao.selectPrhibtWrdHistoryList(prhibtWrdHistoryVo);
        Integer totalNum = opPrhibtWrdDao.selectPrhibtWrdHistoryListCount(prhibtWrdHistoryVo);

        return new Pager<PrhibtWrdHistoryVO>(dataList, prhibtWrdHistoryVo, totalNum);
    }

    @Override
    public Boolean reloadCache() {
        CacheHelper.cacheReload("opPrhibtCache");

        return Boolean.TRUE;
    }
}
