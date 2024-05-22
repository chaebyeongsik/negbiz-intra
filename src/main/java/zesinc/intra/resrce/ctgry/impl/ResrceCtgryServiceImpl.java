/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.ctgry.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.resrce.ctgry.ResrceCtgryMapper;
import zesinc.intra.resrce.ctgry.ResrceCtgryService;
import zesinc.intra.resrce.ctgry.domain.ResrceCtgryVO;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 자원카테고리 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-15.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opResrceCtgryService")
public class ResrceCtgryServiceImpl extends EgovAbstractServiceImpl implements ResrceCtgryService {

    @Resource(name = "opResrceCtgryDao")
    private ResrceCtgryMapper opResrceCtgryDao;

    @Override
    public ResrceCtgryVO selectResrceCtgry(ResrceCtgryVO resrceCtgryVo) {

        ResrceCtgryVO dataVo = opResrceCtgryDao.selectResrceCtgry(resrceCtgryVo);

        return dataVo;
    }

    @Override
    public List<TreeVO> selectResrceCtgryTreeList(ResrceCtgryVO resrceCtgryVo) {

        List<TreeVO> dataList = opResrceCtgryDao.selectResrceCtgryTreeList(resrceCtgryVo);

        return dataList;
    }

    @Override
    public List<ResrceCtgryVO> selectResrceCtgryList(ResrceCtgryVO resrceCtgryVo) {

        List<ResrceCtgryVO> dataList = opResrceCtgryDao.selectResrceCtgryList(resrceCtgryVo);

        return dataList;
    }

    @Override
    public Integer insertResrceCtgry(ResrceCtgryVO resrceCtgryVo) {

        resrceCtgryVo.addParam("q_ctgrySn", resrceCtgryVo.getUpCtgrySn());
        ResrceCtgryVO parentDataVo = opResrceCtgryDao.selectResrceCtgry(resrceCtgryVo);

        if(Validate.isNotEmpty(parentDataVo)) {
            String ctgryPathNm = "";
            if(parentDataVo.getCtgrySn() > 0) {
                ctgryPathNm = parentDataVo.getCtgryPathNm() + " &gt; " + resrceCtgryVo.getCtgryNm();
            } else {
                ctgryPathNm = resrceCtgryVo.getCtgryNm();
            }
            resrceCtgryVo.setCtgryPathNm(ctgryPathNm);

            return opResrceCtgryDao.insertResrceCtgry(resrceCtgryVo);
        }
        return 0;
    }

    @Override
    public Integer updateResrceCtgry(ResrceCtgryVO resrceCtgryVo) {

        Integer upCtgrySn = resrceCtgryVo.getInteger("q_upCtgrySn");

        ResrceCtgryVO paramVo = new ResrceCtgryVO();
        paramVo.addParam("q_ctgrySn", upCtgrySn);
        ResrceCtgryVO parentDataVo = opResrceCtgryDao.selectResrceCtgry(paramVo);

        String ctgryPathNm = "";
        if(parentDataVo.getCtgrySn() > 0) {
            ctgryPathNm = parentDataVo.getCtgryPathNm() + " &gt; " + resrceCtgryVo.getCtgryNm();
        } else {
            ctgryPathNm = resrceCtgryVo.getCtgryNm();
        }
        resrceCtgryVo.setCtgryPathNm(ctgryPathNm);

        return opResrceCtgryDao.updateResrceCtgry(resrceCtgryVo);
    }

    @Override
    public Boolean updateResrceCtgrySortOrder(ResrceCtgryVO resrceCtgryVo) {

        Integer upCtgrySn = resrceCtgryVo.getInteger("q_upCtgrySn");
        Integer ctgrySn = resrceCtgryVo.getInteger("q_ctgrySn");

        if(Validate.isEmpty(upCtgrySn) || Validate.isEmpty(ctgrySn)) {
            return Boolean.FALSE;
        }

        ResrceCtgryVO paramVo = new ResrceCtgryVO();
        paramVo.addParam("q_ctgrySn", upCtgrySn);
        ResrceCtgryVO parentDataVo = opResrceCtgryDao.selectResrceCtgry(paramVo);

        ResrceCtgryVO dataVo = opResrceCtgryDao.selectResrceCtgry(resrceCtgryVo);

        if(Validate.isNotEmpty(parentDataVo) && Validate.isNotEmpty(dataVo)) {
            String ctgryPathNm = "";
            if(parentDataVo.getCtgrySn() > 0) {
                ctgryPathNm = parentDataVo.getCtgryPathNm() + " &gt; " + dataVo.getCtgryNm();
            } else {
                ctgryPathNm = dataVo.getCtgryNm();
            }
            resrceCtgryVo.setCtgryPathNm(ctgryPathNm);
            resrceCtgryVo.setUpCtgrySn(upCtgrySn);

            opResrceCtgryDao.updateResrceCtgrySortOrder(resrceCtgryVo);

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public Integer deleteResrceCtgry(ResrceCtgryVO resrceCtgryVo) throws Exception {

        Integer useCnt = opResrceCtgryDao.selectResrceUseCount(resrceCtgryVo);
        if(useCnt > 0) {
            return -1;
        }

        Integer deleteCnt = opResrceCtgryDao.deleteResrceCtgry(resrceCtgryVo);
        if(deleteCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }

        return deleteCnt;
    }

}
