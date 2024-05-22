/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.author;

import java.util.List;

import zesinc.intra.author.domain.AuthorVO;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 권한 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-24.    박수정   최초작성
 *  2016-03-10.    방기배   권한그룹 메핑용 메뉴 트리 추가
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opAuthorDao")
public interface AuthorMapper {

    /**
     * 권한메뉴트리 목록
     * 
     * @param authorVo
     * @return
     */
    List<TreeVO> selectAuthorMenuTreeList(AuthorVO authorVo);

    /**
     * 권한 상세 조회
     * 
     * @param authorVo
     * @return
     */
    AuthorVO selectAuthor(AuthorVO authorVo);

    /**
     * 권한 목록 조회
     * 
     * @param authorVo
     * @return
     */
    List<AuthorVO> selectAuthorList(AuthorVO authorVo);

    /**
     * 권한 등록
     * 
     * @param authorVo
     * @return
     */
    Integer insertAuthor(AuthorVO authorVo);

    /**
     * 권한 수정
     * 
     * @param authorVo
     * @return
     */
    Integer updateAuthor(AuthorVO authorVo);

    /**
     * 권한 삭제
     * 
     * @param authorVo
     * @return
     */
    Integer deleteAuthor(AuthorVO authorVo);

    /**
     * 권한 코드 중복체크
     * 
     * @param authorVo
     * @return
     */
    Integer selectDupCheckAuthorCode(AuthorVO authorVo);

    /**
     * 권한-담당자할당수정
     * 
     * @param authorVo
     * @return
     */
    Integer updateAuthorMngrAssign(AuthorVO authorVo);

    /**
     * 권한-메뉴할당수정
     * 
     * @param authorVo
     * @return
     */
    Integer updateAuthorMenuAssign(AuthorVO authorVo);

    /**
     * 권한메뉴할당 조회
     * 
     * @param authorVo
     * @return
     */
    List<AuthorVO> selectMenuAsgn(AuthorVO authorVo);

    /**
     * 권한 할당된 메뉴 삭제
     * 
     * @param authorVo
     * @return
     */
    Integer deleteMenuAsgn(AuthorVO authorVo);

    /**
     * 권한 할당된 담당자 삭제
     * 
     * @param authorVo
     * @return
     */
    Integer deleteMngrAsgn(AuthorVO authorVo);

    /**
     * 권한 할당 메뉴 등록
     * 
     * @param authorVo
     * @return
     */
    Integer insertMenuAsgn(AuthorVO authorVo);

    /**
     * 해당 권한 할당된 관리자 목록 조회
     * 
     * @param authorVo
     * @return
     */
    List<MngrVO> selectMngrInAuthorList(AuthorVO authorVo);

    /**
     * 해당 권한 할당된 관리자 목록 건수 조회
     * 
     * @param authorVo
     * @return
     */
    Integer selectMngrInAuthorListCount(AuthorVO authorVo);
}
