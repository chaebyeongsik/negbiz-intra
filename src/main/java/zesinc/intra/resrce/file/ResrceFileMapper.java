/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.file;

import java.util.List;

import zesinc.intra.resrce.file.domain.ResrceFileVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 자원파일 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-14.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opResrceFileDao")
public interface ResrceFileMapper {

    /**
     * 자원파일 상세 조회
     * 
     * @param resrceFileVo
     * @return
     */
    ResrceFileVO selectResrceFile(ResrceFileVO resrceFileVo);

    /**
     * 자원파일 목록 조회
     * 
     * @param resrceFileVo
     * @return
     */
    List<ResrceFileVO> selectResrceFileList(ResrceFileVO resrceFileVo);

    /**
     * 자원파일 목록 건수
     * 
     * @param resrceFileVo
     * @return
     */
    Integer selectResrceFileListCount(ResrceFileVO resrceFileVo);

    /**
     * 자원파일 등록
     * 
     * @param resrceFileVo
     * @return
     */
    Integer insertResrceFile(ResrceFileVO resrceFileVo);

    /**
     * 자원파일 수정
     * 
     * @param resrceFileVo
     * @return
     */
    Integer updateResrceFile(ResrceFileVO resrceFileVo);

    /**
     * 자원 파일명/설명/순번 변경
     * 
     * @param resrceFileVo
     * @return
     */
    Integer updateResrceFileInfo(ResrceFileVO resrceFileVo);

    /**
     * 자원파일 삭제
     * 
     * @param resrceFileVo
     * @return
     */
    Integer deleteResrceFile(ResrceFileVO resrceFileVo);
}
