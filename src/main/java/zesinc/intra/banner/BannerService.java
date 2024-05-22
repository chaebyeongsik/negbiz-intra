/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.banner;

import java.util.List;

import zesinc.intra.banner.domain.BannerVO;
import zesinc.web.support.pager.Pager;

/**
 * 배너 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface BannerService {

    /**
     * 배너 상세 조회
     * 
     * @param bannerVo
     * @return
     */
    BannerVO selectBanner(BannerVO bannerVo);

    /**
     * 배너 목록 조회
     * 
     * @param bannerVo
     * @return
     */
    Pager<BannerVO> selectBannerPageList(BannerVO bannerVo);

    /**
     * 배너 목록 조회(정렬순서 변경)
     * 
     * @param bannerVo
     * @return
     */
    List<BannerVO> selectBannerSortSnList(BannerVO bannerVo);

    /**
     * 배너 등록
     * 
     * @param bannerVo
     * @return
     */
    Integer insertBanner(BannerVO bannerVo);

    /**
     * 배너 수정
     * 
     * @param bannerVo
     * @return
     */
    Integer updateBanner(BannerVO bannerVo);

    /**
     * 배너 삭제
     * 
     * @param bannerVo
     * @return
     */
    Integer deleteBanner(BannerVO bannerVo);

    /**
     * 배너정렬순서 변경
     * 
     * @param bannerVo
     * @return
     */
    Integer updateBannerSortSn(BannerVO bannerVo);
}
