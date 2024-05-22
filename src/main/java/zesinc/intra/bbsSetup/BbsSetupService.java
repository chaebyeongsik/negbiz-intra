/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup;

import javax.servlet.http.HttpServletRequest;

import zesinc.intra.bbsSetup.domain.BbsSetupItemVO;
import zesinc.intra.bbsSetup.domain.BbsSetupVO;
import zesinc.web.support.pager.Pager;

/**
 * 게시판 환경설정 관리 서비스 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 30.    woogi   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface BbsSetupService {

    /**
     * 게시판환경설정 상세 조회
     *
     * @param bbsSetupVo
     * @return
     */
    BbsSetupVO selectBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 목록 조회
     *
     * @param bbsSetupVo
     * @return
     */
    Pager<BbsSetupVO> selectBbsSetupPageList(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 목록설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsListSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 상세조회설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsViewSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 입력폼설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsFormSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 항목설정 수정
     *
     * @param request
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsItemSetup(HttpServletRequest request, BbsSetupItemVO bbsItemVo);

    /**
     * 게시판환경설정 목록에서 삭제
     *
     * @param bbsSetupVo
     * @return
     */
    Integer deleteBbsSetupList(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 삭제
     *
     * @param bbsSetupVo
     * @return
     */
    Integer deleteBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 옵션별 사용여부 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateUseYn(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 정보 변경시 캐시정보를 리로드한다.
     *
     * @return
     */
    Boolean reloadCache();

}
