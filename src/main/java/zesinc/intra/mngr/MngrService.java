/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr;

import java.util.List;

import zesinc.intra.author.domain.AuthorVO;
import zesinc.intra.authorAsgn.domain.AuthorAsgnVO;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.pager.Pager;

/**
 * 관리자 정보 서비스 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface MngrService {

    /**
     * 관리자 상세 조회
     *
     * @param mngrVo
     * @return
     */
    MngrVO selectMngr(MngrVO mngrVo);

    /**
     * 관리자 인증서 초기화
     *
     * @param mngrVo
     * @return
     */
    Integer updateCrtfcAcnt(MngrVO mngrVo);

    /**
     * 관리자 목록 조회
     *
     * @param mngrVo
     * @return
     */
    Pager<MngrVO> selectMngrPageList(MngrVO mngrVo);

    /**
     * 관리자 등록
     *
     * @param mngrVo
     * @return
     */
    Integer insertMngr(MngrVO mngrVo);

    /**
     * 관리자 수정
     *
     * @param mngrVo
     * @return
     */
    Integer updateMngr(MngrVO mngrVo);

    /**
     * 담당자본인 수정
     *
     * @param mngrVo
     * @return
     */
    Integer updateCharger(MngrVO mngrVo);

    /**
     * 인증서 등록 수정
     *
     * @param mngrVo
     * @return
     */
    Integer updateMngrCrtfcAcnt(MngrVO mngrVo);

    /**
     * 관리자 삭제
     *
     * @param mngrVo
     * @return
     */
    Integer deleteMngr(MngrVO mngrVo);

    /**
     * 담당자 삭제(목록에서 다중삭제)
     *
     * @param mngrVo
     * @return
     */
    Integer deleteListMngr(MngrVO mngrVo);

    /**
     * 담당자 사용여부 수정
     *
     * @param mngrVo
     * @return
     */
    Integer updateUseYn(MngrVO mngrVo);

    /**
     * 담당자 아이디 중복 검사(autocomplete)
     *
     * @param mngrVo
     * @return
     */
    Integer selectDplctChckId(MngrVO mngrVo);

    /**
     * 부서이동 담당자 정보
     *
     * @param mngrVo
     * @return
     */
    MngrVO selectDeptTrnsferList(MngrVO mngrVo);

    /**
     * 담당자 부서이동
     *
     * @param mngrVo
     * @return
     */
    Integer updateDeptTrnsfer(MngrVO mngrVo);

    /**
     * 권한지정 목록
     *
     * @param mngrVo
     * @return
     */
    List<AuthorVO> selectAuthorList(MngrVO mngrVo);

    /**
     * 담당자 권한할당 목록
     *
     * @param mngrVo
     * @return
     */
    List<AuthorVO> selectAuthorAsgnList(MngrVO mngrVo);

    /**
     * 담당자 권한할당된 메뉴목록
     *
     * @param mngrVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorAsgnMenuList(MngrVO mngrVo);

    /**
     * 담당자 권한할당
     *
     * @param mngrVo
     * @return
     */
    Integer updateAuthorAsgn(MngrVO mngrVo);

    /**
     * 담당자 비밀번호변경일 체크
     *
     * @param mngrVo
     * @return
     */
    Boolean selectPasswordChangeDeCheck(MngrVO mngrVo);

    /**
     * 담당자 비밀번호 만료 체크
     *
     * @param mngrVo
     * @return
     */
    Boolean selectPasswordExpiredCheck(MngrVO mngrVo);

}
