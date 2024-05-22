/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.menu.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.menu.MenuMapper;
import zesinc.intra.menu.MenuService;
import zesinc.intra.menu.domain.MenuVO;
import zesinc.web.support.helper.SpringHelper;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 메뉴관리 ServiceImpl 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 17.    박수정   최초작성
 *  2016-03-10.    방기배   메뉴URL 중복 체크(권한 설정시 문제 발생됨)
 * @author (주)제스아이엔씨 기술연구소
 * </pre>
 */
@Service("opMenuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

    @Resource(name = "opMenuDao")
    private MenuMapper opMenuDao;

    @Override
    public String checkDplctMenuUrlList(MenuVO menuVo) {

        StringBuilder dplctInfo = new StringBuilder();;
        
        String[] menuUrlAddrs = menuVo.getMenuUrlAddr();

        if(Validate.isNotEmpty(menuUrlAddrs)) {
            MenuVO paramVo = new MenuVO();
            paramVo.setMenuUrlAddrStr(menuUrlAddrs[0]);
            paramVo.setMenuSn(menuVo.getMenuSn());
            List<MenuVO> dplctList = opMenuDao.selectDplctMenuUrlList(paramVo);

            String currUrl;
            String updateUrl = menuUrlAddrs[0] + menuVo.getPrmttNm1() + menuVo.getPrmttNm2() + menuVo.getPrmttNm3();

            for(MenuVO dataVo : dplctList) {
                currUrl = dataVo.getMenuUrlAddrStr() + dataVo.getPrmttNm1() + dataVo.getPrmttNm2() + dataVo.getPrmttNm3();
                if(updateUrl.equals(currUrl)) {
                    dplctInfo.append(dataVo.getMenuNm()).append(" (").append(dataVo.getMenuSn()).append(") 메뉴에 이미 등록되어 있는 [메뉴URL]입니다.<br />");
                    dplctInfo.append("[메뉴URL]은 권한검증에 사용되기 때문에 중복하여 등록할 수 없습니다.");
                    break;
                } else if(updateUrl.startsWith(currUrl)) {
                    dplctInfo.append(dataVo.getMenuNm()).append(" (").append(dataVo.getMenuSn()).append(") 메뉴에 등록되어 있는 [메뉴URL]의 하위URL 입니다.<br />");
                    dplctInfo.append("상위패턴의 [메뉴URL]이 존재하는 경우 등록하는 [메뉴URL]은 접근권한을 확인할 수 없습니다. ");
                    break;
                } else if(currUrl.startsWith(updateUrl)) {
                    dplctInfo.append(dataVo.getMenuNm()).append(" (").append(dataVo.getMenuSn()).append(") 메뉴에 등록되어 있는 [메뉴URL]의 상위URL 입니다.<br />");
                    dplctInfo.append("상위패턴의 [메뉴URL]을 등록하는 경우 기존 하위URL은 접근권한을 확인할 수 없습니다. ");
                    break;
                }
            }
        }

        return dplctInfo.toString();
    }

    @Override
    public MenuVO selectMenu(MenuVO menuVo) {
        MenuVO dataVo = opMenuDao.selectMenu(menuVo);
        if(dataVo != null) {
            // 지정권한목록 조회
            List<String> menuAsgnList = opMenuDao.selectAuthorAsgnList(menuVo);
            String[] authNms = menuAsgnList.toArray(new String[menuAsgnList.size()]);
            dataVo.setAuthNms(authNms);

            // 메뉴URL 조회
            List<MenuVO> menuUrlList = opMenuDao.selectMenuUrlList(menuVo);
            dataVo.setMenuUrlInfo(menuUrlList);
        }
        return dataVo;
    }

    @Override
    public List<TreeVO> selectMenuTreeList(MenuVO menuVo) {

        List<TreeVO> dataList = opMenuDao.selectMenuTreeList(menuVo);

        return dataList;
    }

    @Override
    public Integer insertMenu(MenuVO menuVo) {
        opMenuDao.insertMenu(menuVo);
        return menuVo.getMenuSn();
    }

    @Override
    public Integer updateMenu(MenuVO menuVo) {
        // 메뉴 URL 삭제 후 등록
        opMenuDao.deleteUrl(menuVo);
        String[] menuUrlAddrs = menuVo.getMenuUrlAddr();
        String[] grntCdIds = menuVo.getGrntCdId();

        if(Validate.isNotEmpty(menuUrlAddrs)) {
            Integer mpgUrlYn = Integer.valueOf(menuVo.getMpgUrlYn());
            for(int i = 0 ; i < menuUrlAddrs.length ; i++) {
                if(Validate.isNotEmpty(menuUrlAddrs[i])) {
                    menuVo.setMenuUrlAddrStr(menuUrlAddrs[i]);
                    // 프로그램명이 없을 경우는 URL만 입력한 경우이므로 권한코드가 없다
                    if(Validate.isNotEmpty(menuVo.getPrgrmNm())) {
                        menuVo.setGrntCdIdStr(grntCdIds[i]);
                    }
                    // 화면에서 mpgUrlYn는 숫자값으로 넘어옴(몇번째 URL이 메인인지 알기위해)
                    if(mpgUrlYn.intValue() == (i + 1)) {
                        menuVo.setMpgUrlYn("Y");
                    } else {
                        menuVo.setMpgUrlYn("N");
                    }
                    opMenuDao.insertUrl(menuVo);
                }
            }
        }
        int key = 0;
        key = opMenuDao.updateMenu(menuVo);
        return key;
    }

    @Override
    public Integer deleteMenu(MenuVO menuVo) {

        // 메뉴 URL삭제
        opMenuDao.deleteLowerPartUrl(menuVo);
        // 메뉴 삭제
        Integer delCnt = opMenuDao.deleteLowerPartMenu(menuVo);
        // 권한코드에 할당된 해당 메뉴 삭제
        opMenuDao.deleteMenuInAuthorAsgn(menuVo);

        return delCnt;
    }

    @Override
    public List<MenuVO> selectProgramInfo() {
        List<MenuVO> programList = new ArrayList<MenuVO>();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) SpringHelper.getControllerList();
        for(Map<String, Object> map : list) {
            MenuVO vo = new MenuVO();
            vo.setPrgrmNm((String) map.get("ctrlNm"));
            vo.setProgrmVal((String) map.get("ctrlFullPath"));
            programList.add(vo);
        }
        return programList;
    }

    @Override
    public List<Map<String, String>> selectRequestMappingInfo(MenuVO menuVo) {
        String prgrmNm = menuVo.getPrgrmNm();
        if(Validate.isNotEmpty(prgrmNm)) {
            return SpringHelper.getRequestMappingInController(prgrmNm);
        }

        return null;
    }

    @Override
    public Boolean updateMenuSortOrder(MenuVO menuVo) {

        if(menuVo.getParamMap() == null)
            return Boolean.FALSE;
        Integer upMenuSn = Integer.valueOf((String) menuVo.getParamMap().get("q_upMenuSn"));
        Integer menuSn = Integer.valueOf((String) menuVo.getParamMap().get("q_menuSn"));
        Integer sortSn = Integer.valueOf((String) menuVo.getParamMap().get("q_sortSn"));

        MenuVO currentMenuVO = opMenuDao.selectMenu(menuVo);

        // 현재 해당 메뉴가 속한 그룹의 정렬순서 재정렬
        opMenuDao.updateCurrentGroupAllSortSn(currentMenuVO);

        sortSn++;// 화면에서 정렬순서는 0부터이므로..

        MenuVO tempVO = new MenuVO();
        tempVO.setMenuSn(menuSn);
        tempVO.setUpMenuSn(upMenuSn);
        tempVO.setSortSn(sortSn);

        // 해당 노드 정렬순서 변경 적용
        opMenuDao.updateSimpleSortSn(tempVO);

        // 같은 그룹내에서 이동
        if(currentMenuVO.getUpMenuSn().equals(upMenuSn)) {
            if(currentMenuVO.getSortSn().intValue() > sortSn.intValue()) {
                // 1. 같은 그룹 내에서 위로 올라갔을때 : 바뀔 순번 이하 메뉴들을 오더링 시킴
                opMenuDao.updateSortSn(tempVO);
            } else {
                // 2. 같은 그룹 내에서 아래로 내려갔을때 : 원래 있던 순번 이하의 순번들을 원래 있던 순번부터 오더링
                tempVO.setSortSn(currentMenuVO.getSortSn());// 아직 바뀌지 않은 현재 디비에 있는 값
                tempVO.setSortSn2(sortSn);// 바꿀 위치
                opMenuDao.updateSortSn2(tempVO);
            }
        }
        // 다른 그룹으로 이동했을 때
        else {
            // 1. 원래 있던 그룹 : 그룹 내 순번 전부 오더링
            tempVO.setUpMenuSn(currentMenuVO.getUpMenuSn());
            opMenuDao.updateAllSortSn(tempVO);
            // 2. 편입한 그룹 : 바뀔 순번 이하의 메뉴들을 오더링 시킴
            tempVO.setUpMenuSn(upMenuSn);
            opMenuDao.updateSortSn(tempVO);
        }

        return Boolean.TRUE;

    }

    @Override
    public String getMenuPath(MenuVO menuVo) {
        String delimiter = menuVo.getDelimiter();
        String homeStr = menuVo.getHomeStr();

        MenuVO tempVo = new MenuVO();
        tempVo.setDelimiter(delimiter);
        tempVo.setMenuSn(menuVo.getMenuSn());
        tempVo.setHomeStr(homeStr);

        boolean stop = true;
        StringBuffer menuPath = new StringBuffer();

        do {
            tempVo = opMenuDao.selectMenuNmAndUpperMenuNm(tempVo);
            // '홈>'으로 시작하는 경우를 경로의 처음으로 봄
            if(tempVo.getUpperMenuNm().startsWith(homeStr + delimiter) || tempVo.getUpMenuSn() == 0) {
                if(menuPath.length() > 0) {
                    menuPath.insert(0, tempVo.getUpperMenuNm() + delimiter + tempVo.getMenuNm() + delimiter);
                } else {
                    menuPath.append(tempVo.getUpperMenuNm());
                    if(!tempVo.getUpperMenuNm().equals(homeStr + delimiter)) {
                        menuPath.append(delimiter);
                    }
                    menuPath.append(tempVo.getMenuNm());
                }
                stop = false;
            } else {// 더 돌아야 할 경우
                if(menuPath.length() > 0) {
                    menuPath.insert(0, tempVo.getMenuNm() + delimiter);
                } else {
                    menuPath.append(tempVo.getMenuNm());
                }
                tempVo.setMenuSn(tempVo.getUpMenuSn());
            }
        } while(stop);

        return menuPath.toString();
    }

    @Override
    public List<MenuVO> selectMenuUpperInfo(Integer menuSn) {
        return opMenuDao.selectMenuUpperInfo(menuSn);
    }

    @Override
    public int processMenuUrlSynch() {

        int result = 0;
        List<MenuVO> ctrlList = selectProgramInfo();
        List<MenuVO> menuList = opMenuDao.selectMenuProgramNmIsNotnullList();

        for(MenuVO menuVo : menuList) {
            for(MenuVO ctrlVo : ctrlList) {
                if(ctrlVo.getProgrmVal().equals(menuVo.getPrgrmNm())) {
                    menuVo.setPrgrmNm(ctrlVo.getPrgrmNm());
                    break;
                }
            }

            MenuVO mpgUrlYnYVo = opMenuDao.selectMenuUrlListByMainUrlAt(menuVo);
            String menuUrlAddrStr = "";
            if(Validate.isNotEmpty(mpgUrlYnYVo)) {
                menuUrlAddrStr = mpgUrlYnYVo.getMenuUrlAddrStr();
            }
            opMenuDao.deleteUrl(menuVo);

            List<Map<String, String>> requestMappingInfo = selectRequestMappingInfo(menuVo);
            for(Map<String, String> mapVo : requestMappingInfo) {
                if(menuUrlAddrStr.equals(mapVo.get("pattern"))) {
                    menuVo.setMpgUrlYn("Y");
                } else {
                    menuVo.setMpgUrlYn("N");
                }
                menuVo.setMenuUrlAddrStr(mapVo.get("pattern"));
                menuVo.setGrntCdIdStr(mapVo.get("authCode"));
                result += opMenuDao.insertUrl(menuVo);
            }
        }

        return result;
    }

}
