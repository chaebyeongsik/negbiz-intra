/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userManage;

import zesinc.intra.user.userManage.domain.UserManageVO;
import zesinc.web.support.pager.Pager;

/**
 * 사용자 정보 서비스 클래스
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
public interface UserManageService {

    /**
     * 사용자 목록 조회
     * 
     * @param popupVo
     * @return
     */
    Pager<UserManageVO> selectUserList(UserManageVO userManageVo);

    /**
     * 사용자 아이디 중복 체크
     * 
     * @param userManageVo
     * @return
     */
    Integer selectDupCheckUserId(UserManageVO userManageVo);

    /**
     * 개인 사용자 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertIndvdlUser(UserManageVO userManageVo);

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
     * 기업사용자 등록 처리
     * 
     * @param userManageVo
     * @return
     */
    Integer insertEntrprsUser(UserManageVO userManageVo);

    /**
     * 기업사용자 수정 폼
     * 
     * @param userManageVo
     * @return
     */
    UserManageVO selectEntrprsUserInfo(UserManageVO userManageVo);

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
    Integer deleteEntrprsUser(UserManageVO userManageVo);

    /**
     * 사용자 등급 코드 목록을 등급명으로 반환 (ex: 1001,1002 => 코드명1,코드명2)
     * 
     * @param userGrdCdId
     * @return
     */
    String selectUserGrdCdDsctn(String userGrdCdId);
}
