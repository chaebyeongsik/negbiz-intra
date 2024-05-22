/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.cntnts;

import java.util.List;

import zesinc.intra.resrce.cntnts.domain.ResrceCntntsVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 자원컨텐츠 정보 DAO 클레스
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
@Mapper("opResrceCntntsDao")
public interface ResrceCntntsMapper {

    /**
     * 자원컨텐츠 상세 조회
     * 
     * @param resrceCntntsVo
     * @return
     */
    ResrceCntntsVO selectResrceCntnts(ResrceCntntsVO resrceCntntsVo);

    /**
     * 자원컨텐츠 목록 조회
     * 
     * @param resrceCntntsVo
     * @return
     */
    List<ResrceCntntsVO> selectResrceCntntsList(ResrceCntntsVO resrceCntntsVo);

    /**
     * 자원컨텐츠 목록 건수
     * 
     * @param resrceCntntsVo
     * @return
     */
    Integer selectResrceCntntsListCount(ResrceCntntsVO resrceCntntsVo);

    /**
     * 자원컨텐츠 등록
     * 
     * @param resrceCntntsVo
     * @return
     */
    Integer insertResrceCntnts(ResrceCntntsVO resrceCntntsVo);

    /**
     * 자원컨텐츠 수정
     * 
     * @param resrceCntntsVo
     * @return
     */
    Integer updateResrceCntnts(ResrceCntntsVO resrceCntntsVo);

    /**
     * 자원컨텐츠 삭제
     * 
     * @param resrceCntntsVo
     * @return
     */
    Integer deleteResrceCntnts(ResrceCntntsVO resrceCntntsVo);

}
