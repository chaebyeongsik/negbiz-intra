/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.accessHist;

import java.util.List;

import zesinc.intra.user.accessHist.domain.UserAccessHistStatisticsVO;
import zesinc.intra.user.accessHist.domain.UserAccessHistVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-08-01.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opUserAccessHistDao")
public interface UserAccessHistMapper {

    /**
     * 사용자 상세 목록 조회
     * 
     * @param userAccessHistVo
     * @return
     */
    List<UserAccessHistVO> selectUserAccessHist(UserAccessHistVO userAccessHistVo);

    /**
     * 사용자 상세 목록 건수
     * 
     * @param userAccessHistVo
     * @return
     */
    Integer selectUserAccessHistCount(UserAccessHistVO userAccessHistVo);

    /**
     * 사용자 목록 조회
     * 
     * @param userAccessHistVo
     * @return
     */
    List<UserAccessHistVO> selectUserAccessHistList(UserAccessHistVO userAccessHistVo);

    /**
     * 사용자 목록 건수
     * 
     * @param userAccessHistVo
     * @return
     */
    Integer selectUserAccessHistListCount(UserAccessHistVO userAccessHistVo);

    /**
     * 사용자 접속 통계 조회
     * 
     * @param userAccessHistStatisticsVo
     * @return
     */
    List<UserAccessHistStatisticsVO> selectAccessStatistics();

    /**
     * 사용자 로그인 IP 카운트
     * 
     * @param userAccessHistVo
     * @return
     */
    Integer selectUserLoginIpTotal(UserAccessHistVO userAccessHistVo);

    /**
     * 사용자 로그인 아이피별 카운트(상위3개 및 기타)
     * 
     * @param userAccessHistVo
     * @return
     */
    List<UserAccessHistVO> selectUserLoginIp(UserAccessHistVO userAccessHistVo);

}
