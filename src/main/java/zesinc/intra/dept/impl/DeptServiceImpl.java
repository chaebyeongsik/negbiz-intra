/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dept.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.dept.DeptMapper;
import zesinc.intra.dept.DeptService;
import zesinc.intra.dept.domain.DeptVO;
import zesinc.web.vo.TreeVO;

/**
 * @author (주)제스아이엔씨 기술연구소
 *
 *         <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 16.    woogi   최초작성
 * </pre>
 * @see
 */
@Service("opDeptService")
public class DeptServiceImpl implements DeptService {

    @Resource(name = "opDeptDao")
    private DeptMapper opDeptDao;

    /**
     * 부서정보 목록 조회 (계층별 Tree 조회)
     *
     * @param deptVo
     * @return
     */
    @Override
    public List<TreeVO> selectDeptTreeList(DeptVO deptVo) {

        return opDeptDao.selectDeptTreeList(deptVo);
    }

    /**
     * 부서와 부서원 목록 deptCdId 는 따로 속성을 만들지 않고 담당자ID를 설정하여 사용
     * (계층별 Tree 조회)
     *
     * @param deptVo
     * @return
     */
    @Override
    public List<TreeVO> selectDeptUserTreeList(DeptVO deptVo) {

        return opDeptDao.selectDeptUserTreeList(deptVo);
    }

    /**
     * 부서정보 상세 조회
     *
     * @param deptVo
     * @return
     */
    @Override
    public DeptVO selectDept(DeptVO deptVo) {

        return opDeptDao.selectDept(deptVo);
    }

    /**
     * 부서 코드 중복 검사(autocomplete)
     *
     * @param deptVo
     * @return
     */
    @Override
    public Integer selectDplctChckDept(DeptVO deptVo) {

        return opDeptDao.selectDplctChckDept(deptVo);
    }

    /**
     * 부서 코드 중복 검사(일괄등록시)
     *
     * @param deptVo
     * @return
     */
    @Override
    public List<String> selectDplctChckDeptCodes(DeptVO deptVo) {

        return opDeptDao.selectDplctChckDeptCodes(deptVo);
    }

    /**
     * 상위부서코드가 존재하는지 검사(일괄등록시)
     *
     * @param deptVo
     * @return
     */
    @Override
    public DeptVO selectUppderDeptCode(DeptVO deptVo) {

        return opDeptDao.selectUppderDeptCode(deptVo);
    }

    /**
     * 부서 등록
     *
     * @param deptVo
     * @return
     */
    @Override
    public Integer insertDept(DeptVO deptVo) {

        return opDeptDao.insertDept(deptVo);
    }

    /**
     * 부서정보 수정
     *
     * @param deptVo
     * @return
     */
    @Override
    public Integer updateDept(DeptVO deptVo) {

        return opDeptDao.updateDept(deptVo);
    }

    /**
     * 부서 순서 변경
     *
     * @param deptVo
     * @return
     */
    @Override
    public Boolean updateDeptSortOrder(DeptVO deptVo) {

        if(deptVo.getParamMap() == null) {
            return Boolean.FALSE;
        } else {
            String upDeptCdId = (String) deptVo.getParamMap().get("q_upDeptCdId");
            String deptCdId = (String) deptVo.getParamMap().get("q_deptCdId");
            Integer sortSn = Integer.valueOf((String) deptVo.getParamMap().get("q_sortSn"));
            deptVo.setUpDeptCdId(upDeptCdId);
            deptVo.setDeptCdId(deptCdId);
            deptVo.setSortSn(sortSn);

            // 이동할 부서의 동일Depth의 목록을 가져온다
            List<DeptVO> samensslDpList = opDeptDao.selectSamensslDpList(deptVo);
            // 이동할 부서를 들어갈 순서에 넣는다
            samensslDpList.add(sortSn, deptVo);

            for(int i = 0 ; i < samensslDpList.size() ; i++) {
                DeptVO tempVo = samensslDpList.get(i);
                tempVo.setSortSn(i + 1);
                opDeptDao.updateDeptSortOrder(tempVo);
            }

            return Boolean.TRUE;
        }

    }

    /**
     * 부서 삭제(하위부서포함)
     *
     * @param deptVo
     * @return
     * @throws Exception
     */
    @Override
    public Integer deleteDept(DeptVO deptVo) {

        return opDeptDao.deleteLowerPartDept(deptVo);
    }

}
