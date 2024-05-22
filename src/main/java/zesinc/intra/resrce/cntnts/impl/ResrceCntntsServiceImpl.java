/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.cntnts.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.resrce.cntnts.ResrceCntntsMapper;
import zesinc.intra.resrce.cntnts.ResrceCntntsService;
import zesinc.intra.resrce.cntnts.domain.ResrceCntntsVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 자원컨텐츠 정보 서비스 구현 클레스
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

@Service("opResrceCntntsService")
public class ResrceCntntsServiceImpl extends EgovAbstractServiceImpl implements ResrceCntntsService {

    @Resource(name = "opResrceCntntsDao")
    private ResrceCntntsMapper opResrceCntntsDao;

    @Override
    public ResrceCntntsVO selectResrceCntnts(ResrceCntntsVO resrceCntntsVo) {

        ResrceCntntsVO dataVo = opResrceCntntsDao.selectResrceCntnts(resrceCntntsVo);

        return dataVo;
    }

    @Override
    public Pager<ResrceCntntsVO> selectResrceCntntsPageList(ResrceCntntsVO resrceCntntsVo) {

        List<ResrceCntntsVO> dataList = opResrceCntntsDao.selectResrceCntntsList(resrceCntntsVo);
        Integer totalNum = opResrceCntntsDao.selectResrceCntntsListCount(resrceCntntsVo);

        return new Pager<ResrceCntntsVO>(dataList, resrceCntntsVo, totalNum);
    }

    @Override
    public Integer insertResrceCntnts(ResrceCntntsVO resrceCntntsVo) {

        return opResrceCntntsDao.insertResrceCntnts(resrceCntntsVo);
    }

    @Override
    public Integer updateResrceCntnts(ResrceCntntsVO resrceCntntsVo) {

        return opResrceCntntsDao.updateResrceCntnts(resrceCntntsVo);
    }

    @Override
    public Integer deleteResrceCntnts(ResrceCntntsVO resrceCntntsVo) {

        Integer delCnt = 0;
        // 원본 삭제
        ResrceCntntsVO dataVo = selectResrceCntnts(resrceCntntsVo);
        if(Validate.isNotEmpty(dataVo)) {
            dataVo.setParamMap(resrceCntntsVo.getParamMap());
            delCnt = opResrceCntntsDao.deleteResrceCntnts(dataVo);
        }
        return delCnt;
    }

}
