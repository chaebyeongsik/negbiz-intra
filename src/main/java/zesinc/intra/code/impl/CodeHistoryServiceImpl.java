/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.code.CodeHistoryMapper;
import zesinc.intra.code.CodeHistoryService;
import zesinc.intra.code.domain.CodeChoiceHistoryVO;
import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeHistoryVO;
import zesinc.intra.code.domain.CodeVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 코드이력 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opCodeHistoryService")
public class CodeHistoryServiceImpl extends EgovAbstractServiceImpl implements CodeHistoryService {

    @Resource(name = "opCodeHistoryDao")
    private CodeHistoryMapper opCodeHistoryDao;

    @Override
    public CodeHistoryVO selectCodeHistory(CodeHistoryVO codeHistoryVo) {

        CodeHistoryVO dataVo = opCodeHistoryDao.selectCodeHistory(codeHistoryVo);

        return dataVo;
    }

    @Override
    public Pager<CodeHistoryVO> selectCodeHistoryPageList(CodeHistoryVO codeHistoryVo) {

        List<CodeHistoryVO> dataList = opCodeHistoryDao.selectCodeHistoryList(codeHistoryVo);
        Integer totalNum = opCodeHistoryDao.selectCodeHistoryListCount(codeHistoryVo);

        return new Pager<CodeHistoryVO>(dataList, codeHistoryVo, totalNum);
    }

    @Override
    public Integer insertCodeHistory(CodeVO codeVo) {

        return opCodeHistoryDao.insertCodeHistory(codeVo);
    }

    @Override
    public Integer deleteCodeHistory(CodeHistoryVO codeHistoryVo) {

        // 원본 삭제
        CodeHistoryVO dataVo = selectCodeHistory(codeHistoryVo);
        dataVo.setParamMap(codeHistoryVo.getParamMap());

        Integer delCnt = opCodeHistoryDao.deleteCodeHistory(dataVo);

        return delCnt;
    }

    @Override
    public CodeChoiceHistoryVO selectCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo) {

        CodeChoiceHistoryVO dataVo = opCodeHistoryDao.selectCodeChoiceHistory(codeChoiceHistoryVo);

        return dataVo;
    }

    @Override
    public Pager<CodeChoiceHistoryVO> selectCodeChoiceHistoryPageList(CodeChoiceHistoryVO codeChoiceHistoryVo) {

        List<CodeChoiceHistoryVO> dataList = opCodeHistoryDao.selectCodeChoiceHistoryList(codeChoiceHistoryVo);
        Integer totalNum = opCodeHistoryDao.selectCodeChoiceHistoryListCount(codeChoiceHistoryVo);

        return new Pager<CodeChoiceHistoryVO>(dataList, codeChoiceHistoryVo, totalNum);
    }

    @Override
    public Integer insertCodeChoiceHistory(CodeChoiceVO codeChoiceVo) {

        return opCodeHistoryDao.insertCodeChoiceHistory(codeChoiceVo);
    }

    @Override
    public Integer deleteCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo) {

        // 원본 삭제
        CodeChoiceHistoryVO dataVo = selectCodeChoiceHistory(codeChoiceHistoryVo);
        dataVo.setParamMap(codeChoiceHistoryVo.getParamMap());

        Integer delCnt = opCodeHistoryDao.deleteCodeChoiceHistory(dataVo);

        return delCnt;
    }

}
