/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.menu;

import java.util.List;

import zesinc.intra.menu.domain.MenuVO;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 메뉴관리 Mapper 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 17.    박수정   최초작성
 *  2016-03-10.    방기배   메뉴URL 중복 체크(권한 설정시 문제 발생됨)
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
@Mapper("opMenuDao")
public interface MenuMapper {

    /**
     * 메뉴URL 중복 체크
     * 
     * @param menuVo
     * @return
     */
    List<MenuVO> selectDplctMenuUrlList(MenuVO menuVo);

    /**
     * 메뉴 상세 조회
     * 
     * @param menuVo
     * @return
     */
    MenuVO selectMenu(MenuVO menuVo);

    /**
     * 메뉴 목록 조회
     * 
     * @param menuVo
     * @return
     */
    List<MenuVO> selectMenuList(MenuVO menuVo);

    /**
     * 트리 목록 조회
     * 
     * @param menuVo
     * @return
     */
    List<TreeVO> selectMenuTreeList(MenuVO menuVo);

    /**
     * 메뉴정보 등록
     * 
     * @param menuVo
     * @return
     */
    Integer insertMenu(MenuVO menuVo);

    /**
     * 메뉴 URL 등록
     * 
     * @param menuVo
     * @return
     */
    Integer insertUrl(MenuVO menuVo);

    /**
     * 메뉴 수정
     * 
     * @param menuVo
     * @return
     */
    Integer updateMenu(MenuVO menuVo);

    /**
     * 메뉴 삭제
     * 
     * @param menuVo
     * @return
     */
    Integer deleteMenu(MenuVO menuVo);

    /**
     * URL 삭제
     * 
     * @param menuVo
     * @return
     */
    Integer deleteUrl(MenuVO menuVo);

    /**
     * 지정권한목록 조회
     * 
     * @param menuVo
     * @return
     */
    List<String> selectAuthorAsgnList(MenuVO menuVo);

    /**
     * 메뉴URL 조회
     * 
     * @param menuVo
     * @return
     */
    List<MenuVO> selectMenuUrlList(MenuVO menuVo);

    /**
     * 메뉴URL 조회 (BY MPG_URL_YN)
     * 
     * @param menuVo
     * @return
     */
    MenuVO selectMenuUrlListByMainUrlAt(MenuVO menuVo);

    /**
     * 메뉴 정렬 순서 변경
     * 
     * @param menuVo
     * @return
     */
    Integer updateMenuSortOrder(MenuVO menuVo);

    /**
     * 현재 해당 메뉴가 속한 그룹의 정렬순서 재정렬
     * 
     * @param menuVo
     * @return
     */
    int updateCurrentGroupAllSortSn(MenuVO menuVo);

    /**
     * 정렬 번호 수정
     * 
     * @param menuVo
     * @return
     */
    int updateSortSn(MenuVO menuVo);

    /**
     * 정렬 번호 수정2
     * 
     * @param menuVo
     * @return
     */
    int updateSortSn2(MenuVO menuVo);

    /**
     * 정렬 번호 수정(전체)
     * 
     * @param menuVo
     * @return
     */
    int updateAllSortSn(MenuVO menuVo);

    /**
     * 간단 정렬순서 수정 - 해당하는 메뉴코드의 상위메뉴코드,정렬순서 변경
     * 
     * @param menuVo
     * @return
     */
    int updateSimpleSortSn(MenuVO menuVo);

    /**
     * 해당 메뉴코드 포함한 하위 메뉴코드가 키로 있는 메뉴 URL 삭제
     * 
     * @param menuVo
     * @return
     */
    int deleteLowerPartUrl(MenuVO menuVo);

    /**
     * 해당 메뉴를 포함한 하위 메뉴 삭제
     * 
     * @param menuVo
     * @return
     */
    int deleteLowerPartMenu(MenuVO menuVo);

    /**
     * 해당 메뉴명, 상위메뉴명 조회
     * 
     * @param menuVo
     * @return
     */
    MenuVO selectMenuNmAndUpperMenuNm(MenuVO menuVo);

    /**
     * 메뉴상위정보 조회
     * 
     * @param menuVo
     * @return
     */
    List<MenuVO> selectMenuUpperInfo(Integer menuVo);

    /**
     * 권한코드에 할당된 해당 메뉴 삭제
     * 
     * @param menuVo
     * @return
     */
    int deleteMenuInAuthorAsgn(MenuVO menuVo);

    /**
     * 메뉴 프로그램 목록 조회
     * 
     * @return
     */
    List<MenuVO> selectMenuProgramNmIsNotnullList();
}
