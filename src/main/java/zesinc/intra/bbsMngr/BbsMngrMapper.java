/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsMngr;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.intra.bbsMngr.domain.BbsCtgryVO;
import zesinc.intra.bbsMngr.domain.BbsMngrVO;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시판설정 정보 DAO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-20.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opBbsMngrDao")
public interface BbsMngrMapper {

    /**
     * 게시판첨부파일 전체목록
     *
     * @param bbsMngrVo
     * @return
     */
    List<Integer> selectBbsFileList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판 이미지 첨부파일 전체목록
     *
     * @param bbsMngrVo
     * @return
     */
    List<FileVO> selectBbsImgFileList(BbsMngrVO bbsMngrVo);

    /**
     * 썸네일 생성 정보 업데이트
     *
     * @param fileVo
     * @return
     */
    Integer updateBbsImgThumbCours(FileVO fileVo);

    /**
     * 게시판설정 상세 조회
     *
     * @param bbsMngrVo
     * @return
     */
    BbsMngrVO selectBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 목록 조회
     *
     * @param bbsMngrVo
     * @return
     */
    List<BbsMngrVO> selectBbsMngrList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 목록 건수
     *
     * @param bbsMngrVo
     * @return
     */
    Integer selectBbsMngrListCount(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 등록
     *
     * @param bbsMngrVo
     * @return
     */
    Integer insertBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 수정
     *
     * @param bbsMngrVo
     * @return
     */
    Integer updateBbsMngr(BbsMngrVO bbsMngrVo);

    /**
     * 게시판설정 삭제
     *
     * @param bbsMngrVo
     * @return
     */
    Integer deleteBbsMngr(BbsMngrVO bbsMngrVo);

    /*
     * -----------------------------------------------------------
     * TN_BBS_DOMN
     */
    /**
     * 게시판 도메인 목록 조회
     *
     * @param bbsMngrVo
     * @return
     */
    List<BbsMngrVO> selectBbsDomnList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판환경설정 목록 조회
     *
     * @param bbsSetupVo
     * @return
     */
    List<BbsMngrVO> selectBbsSetupList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판 도메인 등록
     *
     * @param bbsMngrVo
     * @return
     */
    Integer insertBbsDomn(BbsMngrVO bbsMngrVo);

    /**
     * 게시판 도메인 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsDomn(Integer bbsSn);

    /*
     * -----------------------------------------------------------
     * TN_BBS_CTGRY
     */
    /**
     * 게시판 카테고리 목록 조회
     *
     * @param bbsMngrVo
     * @return
     */
    List<BbsCtgryVO> selectBbsCtgryList(BbsMngrVO bbsMngrVo);

    /**
     * 게시판 하위카테고리 목록 조회
     *
     * @param bbsMngrVo
     * @return
     */
    List<BbsCtgryVO> selectBbsLwprtCtgryList(BbsCtgryVO bbsCtgryVo);

    /**
     * 게시판 카테고리 등록
     *
     * @param bbsCtgryVo
     * @return
     */
    Integer insertBbsCtgry(BbsCtgryVO bbsCtgryVo);

    /**
     * 게시판 하위카테고리 등록
     *
     * @param bbsCtgryVo
     * @return
     */
    Integer insertBbsLwprtCtgry(BbsCtgryVO bbsCtgryVo);

    /**
     * 게시판 카테고리 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsCtgry(Integer bbsSn);

    /**
     * 게시판 하위카테고리 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsLwprtCtgry(Integer bbsSn);

    /*
     * -----------------------------------------------------------
     * TN_BBS_TMPLAT
     */
    /**
     * 게시판 템플릿 목록
     *
     * @param type
     * @return
     */
    List<BbsTmplatVO> selectBbsTmplatList(String tmpltTypeCd);

    /*
     * -----------------------------------------------------------
     * TN_BBS_TAG, TN_BBS_CMNT, TN_BBS
     */
    /**
     * 게시판 삭제 TAG
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsTag(Integer bbsSn);

    /**
     * 게시판 삭제 CMNT
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsCmnt(Integer bbsSn);

    /**
     * 게시판 삭제 BBS
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbs(Integer bbsSn);

    /**
     * 게시판 옵션별 사용여부 수정
     *
     * @param bbsMngrVo
     * @return
     */
    Integer updateUseYn(BbsMngrVO bbsMngrVo);
}
