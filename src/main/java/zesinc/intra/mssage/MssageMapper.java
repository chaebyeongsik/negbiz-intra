/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mssage;

import java.util.List;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import zesinc.intra.mssage.domain.MssageVO;

/**
 * 메시지 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-09.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opMssageDao")
public interface MssageMapper {

    /**
     * 메시지 상세 조회
     * 
     * @param mssageVo
     * @return
     */
    MssageVO selectMssage(MssageVO mssageVo);

    /**
     * 메시지 목록 조회
     * 
     * @param mssageVo
     * @return
     */
    List<MssageVO> selectMssageList(MssageVO mssageVo);

    /**
     * 메시지 목록 건수
     * 
     * @param mssageVo
     * @return
     */
    Integer selectMssageListCount(MssageVO mssageVo);

    /**
     * 메시지 등록
     * 
     * @param mssageVo
     * @return
     */
    Integer insertMssage(MssageVO mssageVo);

    /**
     * 메시지 수신 처리
     * 
     * @param mssageVo
     * @return
     */
    Integer updateRcptnDt(MssageVO mssageVo);

    /**
     * 메시지 삭제여부 수정
     * 
     * @param mssageVo
     * @return
     */
    Integer updateMssageDeleteAt(MssageVO mssageVo);

    /**
     * 수신대상 담당자 검색(자동완성)
     * @param mssageVo
     * @return
     */
    List<MssageVO> selectReceptionChargerList(MssageVO mssageVo);

}
