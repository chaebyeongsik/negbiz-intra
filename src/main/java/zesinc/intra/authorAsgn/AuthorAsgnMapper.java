/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.authorAsgn;

import java.util.List;

import zesinc.intra.authorAsgn.domain.AuthorAsgnVO;
import zesinc.intra.mngr.domain.MngrVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 담당자권한할당 정보 DAO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opAuthorAsgnDao")
public interface AuthorAsgnMapper {

    /**
     * 담당자권한할당 상세 조회
     *
     * @param authorAsgnVo
     * @return
     */
    AuthorAsgnVO selectAuthorAsgn(AuthorAsgnVO authorAsgnVo);

    /**
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectMngrList(MngrVO mngrVo);

    /**
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorList(MngrVO mngrVo);

    /**
     * 담당자권한할당 목록 조회
     *
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorAsgnList(AuthorAsgnVO authorAsgnVo);

    /**
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorMngrList(MngrVO mngrVo);

    /**
     * 담당자권한할당 목록 건수
     *
     * @param authorAsgnVo
     * @return
     */
    Integer selectAuthorAsgnListCount(AuthorAsgnVO authorAsgnVo);

    /**
     * 담당자권한할당 등록
     *
     * @param authorAsgnVo
     * @return
     */
    Integer insertAuthorAsgn(AuthorAsgnVO authorAsgnVo);

    /**
     * 지정권한별 메뉴 목록
     *
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorMenuList(AuthorAsgnVO authorAsgnVo);

    /**
     * 권한별 담당자 목록 조회
     *
     * @param authorAsgnVo
     * @return
     */
    List<AuthorAsgnVO> selectAuthorMngrList(AuthorAsgnVO authorAsgnVo);

    /**
     * 권한별 담당자 목록 건수
     *
     * @param authorAsgnVo
     * @return
     */
    Integer selectAuthorMngrListCount(AuthorAsgnVO authorAsgnVo);
}
