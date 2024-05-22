/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.domn.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.domn.DomnMapper;
import zesinc.intra.domn.DomnService;
import zesinc.intra.domn.domain.DomnGroupVO;
import zesinc.intra.domn.domain.DomnOrgVO;
import zesinc.intra.domn.domain.DomnVO;
import zesinc.web.support.helper.CacheHelper;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 도메인 정보 서비스 구현 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-27.    방기배   최초작성
 * </pre>
 * @see
 */
@Service("opDomnService")
public class DomnServiceImpl extends EgovAbstractServiceImpl implements DomnService {

    @Resource(name = "opDomnDao")
    private DomnMapper opDomnDao;

    @Override
    public List<DomnOrgVO> selectMngrList(DomnOrgVO domnOrgVo) {

        return opDomnDao.selectMngrList(domnOrgVo);
    }

    @Override
    public DomnVO selectDomn(DomnVO domnVo) {

        DomnVO dataVo = opDomnDao.selectDomn(domnVo);

        DomnGroupVO domnGroupVo = new DomnGroupVO();
        domnGroupVo.setParamMap(domnVo.getParamMap());

        dataVo.setDomnGroupList(selectDomnGroupList(domnGroupVo));

        return dataVo;
    }

    @Override
    public List<DomnVO> selectDomnList(DomnVO domnVo) {

        List<DomnVO> dataList = opDomnDao.selectDomnList(domnVo);

        return dataList;
    }

    @Override
    public Integer insertDomn(DomnVO domnVo) {
        // 중복 등록 체크
        Integer dplctCnt = opDomnDao.selectDplctChckDomn(domnVo);
        if(dplctCnt > 0) {
            return -1;
        }
        Integer insertCnt = opDomnDao.insertDomn(domnVo);

        insertDomnGroupList(domnVo);

        return insertCnt;
    }

    @Override
    public Integer updateDomn(DomnVO domnVo) {
        // 중복 등록 체크
        Integer dplctCnt = opDomnDao.selectDplctChckDomn(domnVo);
        if(dplctCnt > 0) {
            return -1;
        }
        DomnGroupVO domnGroupVo = new DomnGroupVO();
        domnGroupVo.setParamMap(domnVo.getParamMap());

        deleteDomnGroup(domnGroupVo);
        insertDomnGroupList(domnVo);

        return opDomnDao.updateDomn(domnVo);
    }

    @Override
    public Integer deleteDomn(DomnVO domnVo) {

        // 레이아웃에서 사용중인 도메인 여부 확인
        Integer layoutCnt = opDomnDao.selectLayoutUseChck(domnVo);
        if(layoutCnt > 0) {
            return -1;
        }
        // 사용자 메뉴에서 사용중인 도메인 여부 확인
        Integer userMenuCnt = opDomnDao.selectUserMenuUseChck(domnVo);
        if(userMenuCnt > 0) {
            return -2;
        }

        // 원본 삭제
        DomnVO dataVo = selectDomn(domnVo);
        dataVo.setParamMap(domnVo.getParamMap());

        DomnGroupVO domnGroupVo = new DomnGroupVO();
        domnGroupVo.setParamMap(domnVo.getParamMap());

        // 모든 도메인 그룹 삭제
        deleteDomnGroup(domnGroupVo);

        Integer delCnt = opDomnDao.deleteDomn(dataVo);

        return delCnt;
    }

    @Override
    public DomnGroupVO selectDomnGroup(DomnGroupVO domnGroupVo) {

        DomnGroupVO dataVo = opDomnDao.selectDomnGroup(domnGroupVo);

        return dataVo;
    }

    @Override
    public List<DomnGroupVO> selectDomnGroupList(DomnGroupVO domnGroupVo) {

        List<DomnGroupVO> dataList = opDomnDao.selectDomnGroupList(domnGroupVo);

        return dataList;
    }

    @Override
    public Integer insertDomnGroup(DomnGroupVO domnGroupVo) {

        return opDomnDao.insertDomnGroup(domnGroupVo);
    }

    @Override
    public Integer updateDomnGroup(DomnGroupVO domnGroupVo) {

        return opDomnDao.updateDomnGroup(domnGroupVo);
    }

    @Override
    public Integer deleteDomnGroup(DomnGroupVO domnGroupVo) {

        Integer delCnt = opDomnDao.deleteDomnGroup(domnGroupVo);

        return delCnt;
    }

    @Override
    public Integer insertDomnGroupList(DomnVO domnVo) {
        int insertCnt = 0;

        String[] srvrIpAddr = domnVo.getSrvrIpAddr();
        Integer[] groupPortSn = domnVo.getGroupPortSn();

        if(Validate.isNotEmpty(srvrIpAddr)) {
            int groupCnt = srvrIpAddr.length;
            Integer siteSn = domnVo.getSiteSn();

            DomnGroupVO domnGroupVo = new DomnGroupVO();
            domnGroupVo.setParamMap(domnVo.getParamMap());

            for(int i = 0 ; i < groupCnt ; i++) {
                domnGroupVo.setSiteSn(siteSn);
                domnGroupVo.setSrvrIpAddr(srvrIpAddr[i]);
                domnGroupVo.setGroupPortSn(groupPortSn[i]);

                insertCnt += insertDomnGroup(domnGroupVo);
            }
        }

        return insertCnt;
    }

    @Override
    public Boolean reloadCache() {
        CacheHelper.cacheReload("opDomnCache");

        return Boolean.TRUE;
    }
}
