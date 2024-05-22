/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce;

import java.util.List;

import zesinc.intra.resrce.domain.ResrceVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 자원 정보 DAO 클레스
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
@Mapper("opResrceDao")
public interface ResrceMapper {

    /**
     * 자원 상세 조회
     * 
     * @param resrceVo
     * @return
     */
    ResrceVO selectResrce(ResrceVO resrceVo);

    /**
     * 자원 목록 조회
     * 
     * @param resrceVo
     * @return
     */
    List<ResrceVO> selectResrceList(ResrceVO resrceVo);

    /**
     * 자원 목록 건수
     * 
     * @param resrceVo
     * @return
     */
    Integer selectResrceListCount(ResrceVO resrceVo);

    /**
     * 자원 등록
     * 
     * @param resrceVo
     * @return
     */
    Integer insertResrce(ResrceVO resrceVo);

    /**
     * 자원 수정
     * 
     * @param resrceVo
     * @return
     */
    Integer updateResrce(ResrceVO resrceVo);

    /**
     * 자원카테고리 이동 수정
     * 
     * @param resrceVo
     * @return
     */
    Integer updateResrceCtgrySn(ResrceVO resrceVo);

    /**
     * 자원 삭제
     * 
     * @param resrceVo
     * @return
     */
    Integer deleteResrce(ResrceVO resrceVo);

}
