/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.hist;

import java.util.List;

import org.springframework.stereotype.Repository;

import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.web.commons.dao.CommonMapper;

/**
 * 사용자이력 정보 DAO 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 22.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Repository
public class UserHistDao extends CommonMapper {

    /**
     * 사용자 로그 등록
     * 
     * @param userHistVo
     * @return
     */
    public Integer insertUserLog(UserHistVO userHistVo) {

        return insert("zesinc.intra.user.hist.UserHistMapper.insertUserLog", userHistVo);
    }

    /**
     * selectData
     * 
     * @param queryId
     * @param userHistVo
     * @return
     */
    public Integer selectListCount(String queryId, UserHistVO userHistVo) {

        return (Integer) selectOne(queryId, userHistVo);
    }

    /**
     * selectListData
     * 
     * @param queryId
     * @param userHistVo
     * @return
     */
    public String selectUserId(String queryId, UserHistVO userHistVo) {

        return (String) selectOne(queryId, userHistVo);
    }

    /**
     * 사용자 로그 목록 조회
     * 
     * @param userHistVo
     * @return
     */
    public List<UserHistVO> selectUserLogList(UserHistVO userHistVo) {

        return selectList("zesinc.intra.user.hist.UserHistMapper.selectUserLogList", userHistVo);
    }

    /**
     * 사용자 로그 목록 조회 건수
     * 
     * @param userHistVo
     * @return
     */
    public Integer selectUserLogListCount(UserHistVO userHistVo) {

        return (Integer) selectOne("zesinc.intra.user.hist.UserHistMapper.selectUserLogListCount", userHistVo);
    }

    /**
     * 메뉴명목록 조회
     * 
     * @return
     */
    public List<UserHistVO> selectMenuNmList() {

        return selectList("zesinc.intra.user.hist.UserHistMapper.selectMenuNmList");
    }
}
