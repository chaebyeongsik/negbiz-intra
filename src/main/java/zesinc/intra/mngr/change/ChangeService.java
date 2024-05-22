/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.change;

import zesinc.intra.mngr.change.domain.ChangeVO;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.pager.Pager;

/**
 * 관리자변경이력 정보 서비스 클레스
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
public interface ChangeService {

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
    Pager<ChangeVO> selectChangePageList(ChangeVO changeVo);

    /**
     * 관리자등록 이력 등록
     *
     * @param mngrVo
     * @return
     */
    Integer insertChange(MngrVO mngrVo);

    /**
     * 관리자변경 이력 등록
     *
     * @param mngrVo
     * @return
     */
    Integer updateChange(MngrVO mngrVo);

    /**
     * 관리자삭제 이력 등록
     *
     * @param mngrVo
     * @return
     */
    Integer deleteChange(MngrVO mngrVo);

}
