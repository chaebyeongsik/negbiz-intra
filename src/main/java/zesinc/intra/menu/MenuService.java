/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.menu;

import java.util.List;
import java.util.Map;

import zesinc.intra.menu.domain.MenuVO;
import zesinc.web.vo.TreeVO;

/**
 * 메뉴관리 Service 클레스
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
public interface MenuService {

    /**
     * 메뉴URL 중복 체크
     * 
     * @param menuVo
     * @return
     */
    String checkDplctMenuUrlList(MenuVO menuVo);

    /**
     * 메뉴 상세 조회
     * 
     * @param menuVo
     * @return
     */
    MenuVO selectMenu(MenuVO menuVo);

    /**
     * 트리 메뉴 목록 조회
     * 
     * @param menuVo
     * @return
     */
    List<TreeVO> selectMenuTreeList(MenuVO menuVo);

    /**
     * 메뉴 등록
     * 
     * @param menuVo
     * @return
     */
    Integer insertMenu(MenuVO menuVo);

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
     * 프로그램 정보 조회
     * 
     * @param menuVo
     * @return
     */
    List<MenuVO> selectProgramInfo();

    /**
     * 리퀘스트매핑과 권한코드 정보 조회
     * 
     * @param menuVo
     * @return
     */
    List<Map<String, String>> selectRequestMappingInfo(MenuVO menuVo);

    /**
     * 메뉴 정렬 순서 변경
     * 
     * @param menuVo
     * @return Boolean
     */
    Boolean updateMenuSortOrder(MenuVO menuVo);

    /**
     * <pre>
     *  메뉴경로 얻기... 
     *   MenuVO
     *      <b>menuSn  : 메뉴코드 (필수)</b>
     *      delimiter : 구분자로써 [기본값 : ' > ']
     *      homeStr   : 경로의 가장 처음 문자 즉 루트값의 글자로 [기본값 : '홈']
     * @param MenuVO
     * @return 홈 > 경로~~~~ > 해당메뉴명
     * </pre>
     */
    String getMenuPath(MenuVO menuVo);

    /**
     * 메뉴상위정보 조회
     * @param menuSn
     * @return
     */
    List<MenuVO> selectMenuUpperInfo(Integer menuSn);

    int processMenuUrlSynch();
}
