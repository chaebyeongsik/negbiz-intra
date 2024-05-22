/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dept;

import java.util.List;

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
public interface DeptService {

    /**
     * 부서정보 목록 조회 (계층별 Tree 조회)
     *
     * @param deptVo
     * @return
     */
    List<TreeVO> selectDeptTreeList(DeptVO deptVo);

    /**
     * 부서와 부서원 목록 deptCdId 는 따로 속성을 만들지 않고 담당자ID를 설정하여 사용
     * (계층별 Tree 조회)
     *
     * @param deptVo
     * @return
     */
    List<TreeVO> selectDeptUserTreeList(DeptVO deptVo);

    /**
     * 부서정보 상세 조회
     *
     * @param deptVo
     * @return
     */
    DeptVO selectDept(DeptVO deptVo);

    /**
     * 부서 코드 중복 검사(autocomplete)
     *
     * @param deptVo
     * @return
     */
    Integer selectDplctChckDept(DeptVO deptVo);

    /**
     * 부서 코드 중복 검사(일괄등록시)
     *
     * @param deptVo
     * @return
     */
    List<String> selectDplctChckDeptCodes(DeptVO deptVo);

    /**
     * 상위부서코드가 존재하는지 검사(일괄등록시)
     *
     * @param deptVo
     * @return
     */
    DeptVO selectUppderDeptCode(DeptVO deptVo);

    /**
     * 부서 등록
     *
     * @param deptVo
     * @return
     */
    Integer insertDept(DeptVO deptVo);

    /**
     * 부서정보 수정
     *
     * @param deptVo
     * @return
     */
    Integer updateDept(DeptVO deptVo);

    /**
     * 부서 순서 변경
     *
     * @param deptVo
     * @return
     */
    Boolean updateDeptSortOrder(DeptVO deptVo);

    /**
     * 부서 삭제
     *
     * @param deptVo
     * @return
     */
    Integer deleteDept(DeptVO deptVo);

}
