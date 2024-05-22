/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userManage;

import java.util.List;

import zesinc.intra.user.userManage.domain.UserManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 27.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opUserManageDao")
public interface UserManageMapper {

    /**
     * 사용자 상세 조회(BY USER_IDNTF_NM)
     * 
     * @param userManageVo
     * @return
     */
    UserManageVO selectUserByUserKey(UserManageVO userManageVo);

    /**
     * 사용자 목록 조회
     * 
     * @param userManageVo
     * @return
     */
    List<UserManageVO> selectUserList(UserManageVO userManageVo);

    /**
     * 사용자 목록 건수
     * 
     * @param userManageVo
     * @return
     */
    Integer selectUserListCount(UserManageVO userManageVo);

    /**
     * 사용자 등급 조회
     * 
     * @return
     */
    List<UserManageVO> selectUserGradList();

    /**
     * 사용자 아이디 중복 체크
     * 
     * @param userManageVo
     * @return
     */
    Integer selectDupCheckUserId(UserManageVO userManageVo);

    /**
     * 사용자 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertIndvdlUser(UserManageVO userManageVo);

    /**
     * 사용자 기타 정보 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertIndvdlUserEtcInfo(UserManageVO userManageVo);

    /**
     * 개인 사용자 기타 정보 수정 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer updateIndvdlUserEtcInfo(UserManageVO userManageVo);

    /**
     * 개인 사용자 정보 조회
     * 
     * @param userManageVo
     * @return
     */
    UserManageVO selectIndvdlUserInfo(UserManageVO userManageVo);

    /**
     * 개인 사용자 정보 수정 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer updateIndvdlUser(UserManageVO userManageVo);

    /**
     * 기업 사용자 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertEntrprsUser(UserManageVO userManageVo);

    /**
     * 기업사용자 기타정보 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertEntrprsUserEtcInfo(UserManageVO userManageVo);

    /**
     * 기업사용자 정보 조회
     * 
     * @param userManageVo
     * @return
     */
    UserManageVO selectEntrprsUserInfo(UserManageVO userManageVo);

    /**
     * 기업사용자 기타정보 수정 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer updateEntrprsUserEtcInfo(UserManageVO userManageVo);

    /**
     * 기업사용자 정보 수정 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer updateEntrprsUser(UserManageVO userManageVo);

    /**
     * 사용자 삭제 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer deleteUser(UserManageVO userManageVo);

    /**
     * 개인사용자 기타정보 삭제 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer deleteIndvdlUserEtcInfo(UserManageVO userManageVo);

    /**
     * 기업사용자 기타정보 삭제 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer deleteEntrprsUserEtcInfo(UserManageVO userManageVo);

    /**
     * 사용자 등급 코드 목록 조회
     * 
     * @param userManageVo
     * @return
     */
    List<UserManageVO> selectUserGrdCdDsctn(UserManageVO userManageVo);

}
