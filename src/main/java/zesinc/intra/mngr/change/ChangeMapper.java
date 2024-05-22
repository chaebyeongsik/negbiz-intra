/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.change;

import java.util.List;

import zesinc.intra.mngr.change.domain.ChangeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 관리자변경이력 정보 DAO 클레스
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
@Mapper("opChangeDao")
public interface ChangeMapper {

    /**
     * 관리자변경이력 상세 조회
     *
     * @param changeVo
     * @return
     */
    ChangeVO selectChange(ChangeVO changeVo);

    /**
     * 관리자변경이력 목록 조회
     *
     * @param changeVo
     * @return
     */
    List<ChangeVO> selectChangeList(ChangeVO changeVo);

    /**
     * 관리자변경이력 목록 건수
     *
     * @param changeVo
     * @return
     */
    Integer selectChangeListCount(ChangeVO changeVo);

    /**
     * 관리자변경이력 등록
     *
     * @param changeVo
     * @return
     */
    Integer insertChange(ChangeVO changeVo);

    /**
     * 관리자변경이력 수정
     *
     * @param changeVo
     * @return
     */
    Integer updateChange(ChangeVO changeVo);

    /**
     * 관리자변경이력 삭제
     *
     * @param changeVo
     * @return
     */
    Integer deleteChange(ChangeVO changeVo);

}
