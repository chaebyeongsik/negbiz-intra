/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect;

import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.web.support.pager.Pager;

/**
 * 관리자접속이력 정보 서비스 클레스
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
public interface ConnectService {

    /**
     * 관리자접속이력 상세 조회
     *
     * @param connectVo
     * @return
     */
    ConnectVO selectConnect(ConnectVO connectVo);

    /**
     * 관리자접속이력 목록 조회
     *
     * @param connectVo
     * @return
     */
    Pager<ConnectVO> selectConnectPageList(ConnectVO connectVo);

    /**
     * 관리자접속이력 등록
     *
     * @param connectVo
     * @return
     */
    Integer insertConnect(ConnectVO connectVo);

    /**
     * 관리자접속이력 수정
     *
     * @param connectVo
     * @return
     */
    Integer updateConnect(ConnectVO connectVo);

    /**
     * 관리자접속이력 삭제
     *
     * @param connectVo
     * @return
     */
    Integer deleteConnect(ConnectVO connectVo) throws Exception;

}
