/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.hpcm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.hpcm.HpcmMapper;
import zesinc.intra.hpcm.HpcmService;
import zesinc.intra.hpcm.domain.HpcmVO;
import zesinc.intra.menu.MenuMapper;
import zesinc.intra.menu.MenuService;
import zesinc.intra.menu.domain.MenuVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 도움말 정보 서비스 구현 클레스
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

@Service("opHpcmService")
public class HpcmServiceImpl extends EgovAbstractServiceImpl implements HpcmService {

    @Resource(name = "opHpcmDao")
    private HpcmMapper opHpcmDao;

    @Resource(name = "opMenuDao")
    private MenuMapper opMenuDao;

    @Resource(name = "opMenuService")
    private MenuService opMenuService;

    @Override
    public HpcmVO selectHpcm(HpcmVO hpcmVo) {

        // 도움말 조회
        HpcmVO dataVo = opHpcmDao.selectHpcm(hpcmVo);
        MenuVO menuVo = new MenuVO();
        menuVo.setMenuSn(dataVo.getMenuSn());
        menuVo.setHomeStr("메뉴목록");
        dataVo.setMenuPath(opMenuService.getMenuPath(menuVo));
        // 메뉴상위정보 조회[]
        List<MenuVO> menuUpperInfo = opMenuService.selectMenuUpperInfo(dataVo.getMenuSn());
        String menuUpperInfoStr = "";
        for(MenuVO vo : menuUpperInfo) {
            if(Validate.isNotEmpty(menuUpperInfoStr)) menuUpperInfoStr += ",";
            menuUpperInfoStr += vo.getMenuSn();
        }
        dataVo.setMenuUpperInfo(menuUpperInfoStr);

        return dataVo;
    }

    @Override
    public Pager<HpcmVO> selectHpcmPageList(HpcmVO hpcmVo) {
        List<HpcmVO> dataList = opHpcmDao.selectHpcmList(hpcmVo);
        for(HpcmVO dataVo : dataList) {
            MenuVO menuVo = new MenuVO();
            menuVo.setMenuSn(dataVo.getMenuSn());
            dataVo.setMenuPath(opMenuService.getMenuPath(menuVo));
        }
        Integer totalNum = opHpcmDao.selectHpcmListCount(hpcmVo);

        return new Pager<HpcmVO>(dataList, hpcmVo, totalNum);
    }

    @Override
    public Integer insertHpcm(HpcmVO hpcmVo) {

        return opHpcmDao.insertHpcm(hpcmVo);
    }

    @Override
    public Integer updateHpcm(HpcmVO hpcmVo) {

        return opHpcmDao.updateHpcm(hpcmVo);
    }

    @Override
    public Integer deleteHpcm(HpcmVO hpcmVo) {

        HpcmVO dataVo = selectHpcm(hpcmVo);
        Integer delCnt = opHpcmDao.deleteHpcm(dataVo);

        return delCnt;
    }

    @Override
    public Integer selectHpcmExistsAt(HpcmVO hpcmVo) {
        return opHpcmDao.selectHpcmByMenuSnCount(hpcmVo);
    }

}
