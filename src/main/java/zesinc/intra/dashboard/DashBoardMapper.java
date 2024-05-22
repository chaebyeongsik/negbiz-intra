/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dashboard;

import java.util.List;
import java.util.Map;

import zesinc.intra.dashboard.domain.DashBoardCmsEvlVO;
import zesinc.intra.dashboard.domain.DashBoardCmsOrgVO;
import zesinc.intra.dashboard.domain.DashBoardCmsVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자 DashBoard 정보 Dao 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opDashBoardDao")
public interface DashBoardMapper {

    /**
     * 데시보드용 담당컨텐츠 목록
     * 
     * @param dashBoardCmsVo
     * @return
     */
    List<DashBoardCmsVO> selectDashBoardCmsList(DashBoardCmsVO dashBoardCmsVo);

    /**
     * 데시보드용 담당컨텐츠 목록 건수
     * 
     * @param dashBoardCmsVo
     * @return
     */
    Integer selectDashBoardCmsListCount(DashBoardCmsVO dashBoardCmsVo);

    /**
     * 컨텐츠별 담당자 목록
     * 
     * @param paramMap
     * @return
     */
    List<DashBoardCmsOrgVO> selectAuthorChargerList(Map<String, Object> paramMap);

    /**
     * 컨텐츠별 만족도 조사 통계목록
     * 
     * @param dashBoardCmsEvlVo
     * @return
     */
    List<DashBoardCmsEvlVO> selectCmsEvlList(DashBoardCmsEvlVO dashBoardCmsEvlVo);

    /**
     * 컨텐츠별 만족도 조사 통계목록 건수
     * 
     * @param dashBoardCmsEvlVo
     * @return
     */
    Integer selectCmsEvlListCount(DashBoardCmsEvlVO dashBoardCmsEvlVo);

}
