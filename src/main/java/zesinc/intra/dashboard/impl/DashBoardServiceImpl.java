/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dashboard.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.dashboard.DashBoardMapper;
import zesinc.intra.dashboard.DashBoardService;
import zesinc.intra.dashboard.domain.DashBoardCmsEvlVO;
import zesinc.intra.dashboard.domain.DashBoardCmsVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 DashBoard 정보 서비스 구현 객체
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
@Service("opDashBoardService")
public class DashBoardServiceImpl extends EgovAbstractServiceImpl implements DashBoardService {

    @Resource(name = "opDashBoardDao")
    DashBoardMapper opDashBoardDao;

    @Override
    public Pager<DashBoardCmsVO> selectDashBoardCmsPageList(DashBoardCmsVO dashBoardCmsVo) {

        List<DashBoardCmsVO> dataList = opDashBoardDao.selectDashBoardCmsList(dashBoardCmsVo);
        Integer totalNum = opDashBoardDao.selectDashBoardCmsListCount(dashBoardCmsVo);

        for(DashBoardCmsVO dataVo : dataList) {
            // 메뉴권한담당자목록
            if(Validate.isNotEmpty(dataVo.getAuthrtGroupNm()) && dataVo.getAuthrtGroupNm().equals("CHARGER")) {
                dashBoardCmsVo.addParam("siteSn", dataVo.getSiteSn());
                dashBoardCmsVo.addParam("userMenuEngNm", dataVo.getUserMenuEngNm());
                dataVo.setAuthorChargerList(opDashBoardDao.selectAuthorChargerList(dashBoardCmsVo.getParamMap()));
            }

            /*
             * 화면에서 사용할 사용자메뉴 URL 생성
             */
            StringBuilder userMenuUrlAddr = new StringBuilder();
            if(Validate.isNotEmpty(dataVo.getUserMenuUrlAddr())) {

                // 화면 컨트롤을 위해 제일 마지막 / 문자를 삭제
                String sitePathNm = dataVo.getSitePathNm();
                if(sitePathNm.charAt(sitePathNm.length() - 1) == '/') {
                    sitePathNm = sitePathNm.substring(0, sitePathNm.length() - 1);
                    dataVo.setSitePathNm(sitePathNm);
                }

                userMenuUrlAddr.append("http://").append(dataVo.getSiteNm()).append(":");
                userMenuUrlAddr.append(dataVo.getPortSn()).append(sitePathNm).append(dataVo.getUserMenuUrlAddr());
            }
            dataVo.setUserMenuUrlAddr(userMenuUrlAddr.toString());
        }

        return new Pager<DashBoardCmsVO>(dataList, dashBoardCmsVo, totalNum);
    }

    @Override
    public Pager<DashBoardCmsEvlVO> selectCmsEvlList(DashBoardCmsEvlVO dashBoardCmsEvlVo) {

        List<DashBoardCmsEvlVO> dataList = opDashBoardDao.selectCmsEvlList(dashBoardCmsEvlVo);
        Integer totalNum = opDashBoardDao.selectCmsEvlListCount(dashBoardCmsEvlVo);

        for(DashBoardCmsEvlVO dataVo : dataList) {
            // 메뉴권한담당자목록
            if(Validate.isNotEmpty(dataVo.getAuthrtGroupNm()) && dataVo.getAuthrtGroupNm().equals("CHARGER")) {
                dashBoardCmsEvlVo.addParam("siteSn", dataVo.getSiteSn());
                dashBoardCmsEvlVo.addParam("userMenuEngNm", dataVo.getUserMenuEngNm());
                dataVo.setAuthorChargerList(opDashBoardDao.selectAuthorChargerList(dashBoardCmsEvlVo.getParamMap()));
            }
        }
        return new Pager<DashBoardCmsEvlVO>(dataList, dashBoardCmsEvlVo, totalNum);
    }

}
