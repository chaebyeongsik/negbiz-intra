/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mymenu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.mymenu.MyMenuMapper;
import zesinc.intra.mymenu.MyMenuService;
import zesinc.intra.mymenu.domain.MyMenuVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 내메뉴 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-21.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */

@Service("opMyMenuService")
public class MyMenuServiceImpl extends EgovAbstractServiceImpl implements MyMenuService {

    @Resource(name = "opMyMenuDao")
    private MyMenuMapper opMyMenuDao;

    @Override
    public List<MyMenuVO> selectMyMenuList(MyMenuVO myMenuVo) {

        List<MyMenuVO> dataList = opMyMenuDao.selectMyMenuList(myMenuVo);

        return dataList;
    }

    @Override
    public List<MyMenuVO> selectAuthMenuList(MyMenuVO myMenuVo) {

        List<MyMenuVO> dataList = opMyMenuDao.selectAuthMenuList(myMenuVo);

        return dataList;
    }

    @Override
    public Integer insertMyMenu(MyMenuVO myMenuVo) {

        Integer insertCnt = 0;
        // 삭제 후 신규 등록
        opMyMenuDao.deleteMyMenu(myMenuVo);

        MyMenuVO paramVo = new MyMenuVO();
        paramVo.setPicId(myMenuVo.getPicId());

        List<Integer> menuSnList = myMenuVo.getMenuSns();
        if(Validate.isNotEmpty(menuSnList)) {
            int menuCnt = menuSnList.size();
            for(int i = 0 ; i < menuCnt ; i++) {
                paramVo.setMenuSn(menuSnList.get(i));
                paramVo.setSortSn(i);
                insertCnt += opMyMenuDao.insertMyMenu(paramVo);
            }
        }

        return insertCnt;
    }

}
