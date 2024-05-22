/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.hpcm;

import java.util.List;

import zesinc.intra.hpcm.domain.HpcmVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 도움말 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opHpcmDao")
public interface HpcmMapper {

    /**
     * 도움말 상세 조회
     * 
     * @param hpcmVo
     * @return
     */
    HpcmVO selectHpcm(HpcmVO hpcmVo);

    /**
     * 도움말 목록 조회
     * 
     * @param hpcmVo
     * @return
     */
    List<HpcmVO> selectHpcmList(HpcmVO hpcmVo);

    /**
     * 도움말 목록 건수
     * 
     * @param hpcmVo
     * @return
     */
    Integer selectHpcmListCount(HpcmVO hpcmVo);

    /**
     * 도움말 등록
     * 
     * @param hpcmVo
     * @return
     */
    Integer insertHpcm(HpcmVO hpcmVo);

    /**
     * 도움말 수정
     * 
     * @param hpcmVo
     * @return
     */
    Integer updateHpcm(HpcmVO hpcmVo);

    /**
     * 도움말 삭제
     * 
     * @param hpcmVo
     * @return
     */
    Integer deleteHpcm(HpcmVO hpcmVo);

    /**
     * 도움말 DB에 등록되어있는지 조회
     * @param hpcmVo
     * @return
     */
    Integer selectHpcmByMenuSnCount(HpcmVO hpcmVo);

}
