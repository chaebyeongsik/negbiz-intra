/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.bbs.BbsCmntMapper;
import zesinc.intra.bbs.BbsCmntService;
import zesinc.intra.bbs.domain.BbsCmntVO;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.XssUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시판커멘트 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-11-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opBbsCmntService")
public class BbsCmntServiceImpl extends EgovAbstractServiceImpl implements BbsCmntService {

    @Resource(name = "opBbsCmntDao")
    private BbsCmntMapper opBbsCmntDao;

    @Override
    public BbsCmntVO selectBbsCmnt(BbsCmntVO bbsCmntVo) {

        BbsCmntVO dataVo = opBbsCmntDao.selectBbsCmnt(bbsCmntVo);

        return dataVo;
    }

    @Override
    public Pager<BbsCmntVO> selectBbsCmntPageList(BbsCmntVO bbsCmntVo) {

        List<BbsCmntVO> dataList = opBbsCmntDao.selectBbsCmntList(bbsCmntVo);
        Integer totalNum = opBbsCmntDao.selectBbsCmntListCount(bbsCmntVo);

        return new Pager<BbsCmntVO>(dataList, bbsCmntVo, totalNum);
    }

    @Override
    public Integer insertBbsCmnt(BbsCmntVO bbsCmntVo) {

        // html 태그 치환
        bbsCmntVo.setOpnnCn(XssUtil.cleanTag(bbsCmntVo.getOpnnCn(), XssUtil.TYPE.ALL));

        if(Validate.isNotEmpty(bbsCmntVo.getOpnnSn())) {
            // 댓글의 댓글 작성시
            BbsCmntVO dataVo = selectBbsCmnt(bbsCmntVo);
            bbsCmntVo.setOpnnRfrncSn(dataVo.getOpnnRfrncSn());
            bbsCmntVo.setSortSn(dataVo.getSortSn() + 1);
            bbsCmntVo.setOpnnGrdSn(dataVo.getOpnnGrdSn() + 1);

            // 정렬순 증가
            opBbsCmntDao.updateBbsCmntSortSn(bbsCmntVo);
        } else {
            // 신규 등록시
            bbsCmntVo.setSortSn(0);
            bbsCmntVo.setOpnnGrdSn(0);
        }

        return opBbsCmntDao.insertBbsCmnt(bbsCmntVo);
    }

    @Override
    public Integer updateBbsCmnt(BbsCmntVO bbsCmntVo) {

        return opBbsCmntDao.updateBbsCmnt(bbsCmntVo);
    }

    @Override
    public Integer updateBbsDisplayCmnt(BbsCmntVO bbsCmntVo) {

        return opBbsCmntDao.updateBbsDisplayCmnt(bbsCmntVo);
    }

    @Override
    public Integer deleteBbsCmnt(BbsCmntVO bbsCmntVo) throws Exception {
        Integer delCnt = 0;

        Integer refrnCnt = opBbsCmntDao.selectCmntRefrnCnt(bbsCmntVo);
        if(refrnCnt > 0) {
            bbsCmntVo.setDelYn("Y");
            delCnt = opBbsCmntDao.updateDeleteBbsCmnt(bbsCmntVo);
        } else {
            delCnt = opBbsCmntDao.deleteBbsCmnt(bbsCmntVo);
        }

        if(delCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }

        return delCnt;
    }

    @Override
    public Integer deleteBbsCmntAll(BbsCmntVO bbsCmntVo) throws Exception {

        Integer delCnt = opBbsCmntDao.deleteBbsCmntAll(bbsCmntVo);

        return delCnt;
    }

}
