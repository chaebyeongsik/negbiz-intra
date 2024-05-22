/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.change.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.mngr.change.ChangeMapper;
import zesinc.intra.mngr.change.ChangeService;
import zesinc.intra.mngr.change.domain.ChangeVO;
import zesinc.intra.mngr.change.support.CHANGE_LOG_TYPE;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자변경이력 정보 서비스 구현 클레스
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

@Service("opChangeService")
public class ChangeServiceImpl extends EgovAbstractServiceImpl implements ChangeService {

    /** 관리자 로그 모드가 true인 경우 로그를 기록 */
    private static final Boolean MGR_LOG_MODE = BaseConfig.MGR_LOG_MODE;

    @Resource(name = "opChangeDao")
    private ChangeMapper opChangeDao;

    @Override
    public ChangeVO selectChange(ChangeVO changeVo) {

        ChangeVO dataVo = opChangeDao.selectChange(changeVo);

        return dataVo;
    }

    @Override
    public Pager<ChangeVO> selectChangePageList(ChangeVO changeVo) {

        List<ChangeVO> dataList = opChangeDao.selectChangeList(changeVo);
        Integer totalNum = opChangeDao.selectChangeListCount(changeVo);

        return new Pager<ChangeVO>(dataList, changeVo, totalNum);
    }

    @Override
    public Integer insertChange(MngrVO mngrVo) {
        Integer insertCnt = 0;
        if(MGR_LOG_MODE) {
            String picId = mngrVo.getPicId();
            if(Validate.isEmpty(picId)) {
                picId = mngrVo.getString("q_picId");
            }

            ChangeVO changeVo = new ChangeVO();
            changeVo.setLogType(CHANGE_LOG_TYPE.INSERT.getCdId());
            changeVo.setPicId(picId);

            insertCnt = opChangeDao.insertChange(changeVo);
        }
        return insertCnt;
    }

    @Override
    public Integer updateChange(MngrVO mngrVo) {
        Integer insertCnt = 0;
        if(MGR_LOG_MODE) {
            String picId = mngrVo.getPicId();
            if(Validate.isEmpty(picId)) {
                picId = mngrVo.getString("q_picId");
            }

            ChangeVO changeVo = new ChangeVO();
            changeVo.setLogType(CHANGE_LOG_TYPE.UPDATE.getCdId());
            changeVo.setPicId(picId);

            insertCnt = opChangeDao.insertChange(changeVo);
        }
        return insertCnt;
    }

    @Override
    public Integer deleteChange(MngrVO mngrVo) {
        Integer insertCnt = 0;
        if(MGR_LOG_MODE) {
            String picId = mngrVo.getPicId();
            if(Validate.isEmpty(picId)) {
                picId = mngrVo.getString("q_picId");
            }

            ChangeVO changeVo = new ChangeVO();
            changeVo.setLogType(CHANGE_LOG_TYPE.DELETE.getCdId());
            changeVo.setPicId(picId);

            insertCnt = opChangeDao.insertChange(changeVo);
        }
        return insertCnt;
    }

}
