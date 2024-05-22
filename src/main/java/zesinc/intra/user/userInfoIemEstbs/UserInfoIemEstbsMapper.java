/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userInfoIemEstbs;

import java.util.List;

import zesinc.intra.user.userInfoIemEstbs.domain.UserInfoIemEstbsVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자정보항목설정 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opUserInfoIemEstbsDao")
public interface UserInfoIemEstbsMapper {

    /**
     * 사용자정보항목설정 목록 조회
     * 
     * @param UserInfoIemEstbsVo
     * @return
     */
     List<UserInfoIemEstbsVO> selectUserInfoIemEstbsList(UserInfoIemEstbsVO userInfoIemEstbsVo);

    /**
     * 사용자정보항목설정 수정
     * 
     * @param UserInfoIemEstbsVo
     * @return
     */
     Integer updateUserInfoIemEstbs(UserInfoIemEstbsVO userInfoIemEstbsVo);

    /**
     * 회원가입 시 회원가입양식의 항목설정 조회 - 개인사용자
     * 
     * @return
     */
    UserInfoIemEstbsVO selectIndvdlUserInfoIemEstbsYn();

    /**
     * 회원가입 시 회원가입양식의 항목설정 조회 - 기업사용자
     * 
     * @return
     */
    UserInfoIemEstbsVO selectEntrprsUserInfoIemEstbsYn();

}
