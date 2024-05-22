/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.domn;

import java.util.List;

import zesinc.intra.domn.domain.DomnGroupVO;
import zesinc.intra.domn.domain.DomnOrgVO;
import zesinc.intra.domn.domain.DomnVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 도메인 정보 DAO 클레스
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
@Mapper("opDomnDao")
public interface DomnMapper {

    /**
     * 레이아웃 사용여부 체크
     * 
     * @param domnVo
     * @return
     */
    Integer selectLayoutUseChck(DomnVO domnVo);

    /**
     * 사용자메뉴 사용여부 체크
     * 
     * @param domnVo
     * @return
     */
    Integer selectUserMenuUseChck(DomnVO domnVo);

    /**
     * 도메인 중복 여부 확인
     * 
     * @param domnVo
     * @return
     */
    Integer selectDplctChckDomn(DomnVO domnVo);

    /**
     * 담당자목록 조회(Auto Complete 용)
     * 
     * @param domnOrgVo
     * @return
     */
    List<DomnOrgVO> selectMngrList(DomnOrgVO domnOrgVo);

    /**
     * 도메인 상세 조회
     * 
     * @param domnVo
     * @return
     */
    DomnVO selectDomn(DomnVO domnVo);

    /**
     * 도메인 목록 조회
     * 
     * @param domnVo
     * @return
     */
    List<DomnVO> selectDomnList(DomnVO domnVo);

    /**
     * 도메인 등록
     * 
     * @param domnVo
     * @return
     */
    Integer insertDomn(DomnVO domnVo);

    /**
     * 도메인 수정
     * 
     * @param domnVo
     * @return
     */
    Integer updateDomn(DomnVO domnVo);

    /**
     * 도메인 삭제
     * 
     * @param domnVo
     * @return
     */
    Integer deleteDomn(DomnVO domnVo);

    /**
     * 도메인그룹 상세 조회
     * 
     * @param domnGroupVo
     * @return
     */
    DomnGroupVO selectDomnGroup(DomnGroupVO domnGroupVo);

    /**
     * 도메인그룹 목록 조회
     * 
     * @param domnGroupVo
     * @return
     */
    List<DomnGroupVO> selectDomnGroupList(DomnGroupVO domnGroupVo);

    /**
     * 도메인그룹 등록
     * 
     * @param domnGroupVo
     * @return
     */
    Integer insertDomnGroup(DomnGroupVO domnGroupVo);

    /**
     * 도메인그룹 수정
     * 
     * @param domnGroupVo
     * @return
     */
    Integer updateDomnGroup(DomnGroupVO domnGroupVo);

    /**
     * 도메인그룹 삭제
     * 
     * @param domnGroupVo
     * @return
     */
    Integer deleteDomnGroup(DomnGroupVO domnGroupVo);

}
