/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsMngr;

import java.util.List;

import zesinc.intra.bbsMngr.domain.BbsMngrVO;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import zesinc.web.support.pager.Pager;

/**
 * 게시판설정 정보 서비스 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-20.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface BbsMngrService {

    /**
     * 게시판 템플릿 목록
     *
     * @param type
     * @return
     */
    List<BbsTmplatVO> selectBbsTmplatList(String tmpltTypeCd);

    /**
     * 게시판 도메인 목록
     * 
     * @param type
     * @return
     */
    List<BbsMngrVO> selectBbsDomnList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판환경설정 목록 조회
     *
     * @param bbsSetupVo
     * @return
     */
    List<BbsMngrVO> selectBbsSetupList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 상세 조회
     *
     * @param bbsMngrVo
     * @return
     */
    BbsMngrVO selectBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 목록 조회
     *
     * @param bbsMngrVo
     * @return
     */
    Pager<BbsMngrVO> selectBbsMngrList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 등록
     *
     * @param bbsMngrVo
     * @return
     */
    Integer insertBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 수정
     *
     * @param bbsMngrVo
     * @return
     */
    Integer updateBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정목록 삭제
     *
     * @param bbsMngrVo
     * @return
     * @throws Exception 
     */
    Integer deleteBbsMngrList(BbsMngrVO bbsMngrVo) throws Exception;

    /**
     * 게시판설정 삭제
     *
     * @param bbsMngrVo
     * @return
     */
    Integer deleteBbsMngr(BbsMngrVO bbsMngrVo) throws Exception;

    /**
     * 게시판 옵션별 사용여부 수정
     *
     * @param bbsMngrVo
     * @return
     */
    Integer updateUseYn(BbsMngrVO bbsMngrVo);

    /**
     * q_createType에 따라 완전 재생성 또는 빈 값만 생성을 지원하며,
     * 생성 오류 결과를 List에 문자열로 담아 전달한다.
     *
     * @param bbsMngrVo
     * @return
     */
    List<String> createThumbNail(BbsMngrVO bbsMngrVo);

    /**
     * 캐시리로드
     *
     * @return
     */
    Boolean reloadCache();
}
