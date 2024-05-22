/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbstmplat;

import java.util.List;

import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시판템플릿 정보 DAO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-23.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opBbsTmplatDao")
public interface BbsTmplatMapper {

    /**
     * 게시판템플릿 상세 조회
     *
     * @param bbsTmplatVo
     * @return
     */
    BbsTmplatVO selectBbsTmplat(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 목록 조회
     *
     * @param bbsTmplatVo
     * @return
     */
    List<BbsTmplatVO> selectBbsTmplatList(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 목록 건수
     *
     * @param bbsTmplatVo
     * @return
     */
    Integer selectBbsTmplatListCount(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 등록
     *
     * @param bbsTmplatVo
     * @return
     */
    Integer insertBbsTmplat(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 수정
     *
     * @param bbsTmplatVo
     * @return
     */
    Integer updateBbsTmplat(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 삭제
     *
     * @param bbsTmplatVo
     * @return
     */
    Integer deleteBbsTmplat(BbsTmplatVO bbsTmplatVo);

    /**
     * 게시판템플릿 ID 중복체크
     *
     * @param bbsTmplatVo
     * @return
     */
    Integer selectDplctChckId(BbsTmplatVO bbsTmplatVo);

}
