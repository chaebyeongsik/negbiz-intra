/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.intra.bbsSetup.domain.BbsSetupItemVO;
import zesinc.intra.bbsSetup.domain.BbsSetupVO;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시판 환경설정 관리 DAO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 30.    woogi   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opBbsSetupDao")
public interface BbsSetupMapper {

    /**
     * 사용중인 게시판 여부
     * 
     * @param bbsSetupVo
     * @return
     */
    Integer selectBbsSetupUseChck(BbsSetupVO bbsSetupVo);

    /**
     * 게시판첨부파일 전체목록
     *
     * @param bbsSetupVo
     * @return
     */
    List<Integer> selectBbsFileList(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 이미지 첨부파일 전체목록
     *
     * @param bbsSetupVo
     * @return
     */
    List<FileVO> selectBbsImgFileList(BbsSetupVO bbsSetupVo);

    /**
     * 썸네일 생성 정보 업데이트
     *
     * @param fileVo
     * @return
     */
    Integer updateBbsImgThumbCours(FileVO fileVo);

    /**
     * 게시판환경설정 목록 조회
     *
     * @param bbsSetupVo
     * @return
     */
    List<BbsSetupVO> selectBbsSetupList(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 목록 건수
     *
     * @param bbsSetupVo
     * @return
     */
    Integer selectBbsSetupListCount(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 상세 조회
     *
     * @param bbsSetupVo
     * @return
     */
    BbsSetupVO selectBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsSetup(Integer bbsSn);

    /*
     * -----------------------------------------------------------
     * TN_BBS_GLOBAL_SETUP
     */
    /**
     * 게시판 환경설정 기본정보 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsGlobalSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 환경설정 기본정보 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsGlobalSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판 환경설정 기본정보 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsGlobalSetup(Integer bbsSn);

    /**
     * 게시판 환경설정 기본정보 사용여부 수정
     *
     * @param bbsSn
     * @return
     */
    Integer updateBbsGlobalSetupUseYn(BbsSetupVO bbsSetupVo);

    /*
     * -----------------------------------------------------------
     * TN_BBS_LIST_SETUP
     */
    /**
     * 게시판환경설정 목록설정 상세조회
     *
     * @param bbsSetupVo
     * @return
     */
    BbsSetupVO selectBbsListSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 목록설정 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsListSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 목록설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsListSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 목록설정 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsListSetup(Integer bbsSn);

    /**
     * 게시판환경설정 목록설정 사용여부 수정
     *
     * @param bbsSn
     * @return
     */
    Integer updateBbsListSetupUseYn(BbsSetupVO bbsSetupVo);

    /*
     * -----------------------------------------------------------
     * TN_BBS_VIEW_SETUP
     */
    /**
     * 게시판환경설정 목록설정 상세조회
     *
     * @param bbsSetupVo
     * @return
     */
    BbsSetupVO selectBbsViewSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 상세조회설정 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsViewSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 상세조회설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsViewSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 상세조회설정 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsViewSetup(Integer bbsSn);

    /**
     * 게시판환경설정 상세조회설정 사용여부 수정
     *
     * @param bbsSn
     * @return
     */
    Integer updateBbsViewSetupUseYn(BbsSetupVO bbsSetupVo);

    /*
     * -----------------------------------------------------------
     * TN_BBS_FORM_SETUP
     */
    /**
     * 게시판환경설정 목록설정 상세조회
     *
     * @param bbsSetupVo
     * @return
     */
    BbsSetupVO selectBbsFormSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 입력폼설정 생성
     *
     * @param bbsSetupVo
     * @return
     */
    Integer insertBbsFormSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 입력폼설정 수정
     *
     * @param bbsSetupVo
     * @return
     */
    Integer updateBbsFormSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 입력폼설정 삭제
     *
     * @param bbsSn
     * @return
     */
    Integer deleteBbsFormSetup(Integer bbsSn);

    /**
     * 게시판환경설정 입력폼설정 사용여부 수정
     *
     * @param bbsSn
     * @return
     */
    Integer updateBbsFormSetupUseYn(BbsSetupVO bbsSetupVo);

    /*
     * -----------------------------------------------------------
     * TN_BBS_ITEM_SETUP
     */
    /**
     * 게시판환경설정 항목설정 상세조회
     *
     * @param bbsSetupVo
     * @return
     */
    List<BbsSetupItemVO> selectBbsItemSetup(BbsSetupVO bbsSetupVo);

    /**
     * 게시판환경설정 항목설정 생성
     *
     * @param bbsItemVo
     * @return
     */
    Integer insertBbsItemSetup(BbsSetupItemVO bbsItemVo);

    /**
     * 게시판환경설정 항목설정 수정
     *
     * @param bbsItemVo
     * @return
     */
    Integer updateBbsItemSetup(BbsSetupItemVO bbsItemVo);

    /**
     * 게시판환경설정 항목설정 삭제
     *
     * @param bbsItemVo
     * @return
     */
    Integer deleteBbsItemSetup(Integer bbsSn);

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

}
