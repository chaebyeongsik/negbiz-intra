/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mymenu;

import java.util.List;

import zesinc.intra.mymenu.domain.MyMenuVO;

/**
 * 내메뉴 정보 서비스 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-21.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
public interface MyMenuService {

    /**
     * 내메뉴 목록 조회
     *
     * @param myMenuVo
     * @return
     */
    List<MyMenuVO> selectMyMenuList(MyMenuVO myMenuVo);

    /**
     * 내메뉴 목록 건수
     *
     * @param myMenuVo
     * @return
     */
    List<MyMenuVO> selectAuthMenuList(MyMenuVO myMenuVo);

    /**
     * 내메뉴 등록
     *
     * @param myMenuVo
     * @return
     */
    Integer insertMyMenu(MyMenuVO myMenuVo);

}
