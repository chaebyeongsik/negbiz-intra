/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.authorAsgn.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.authorAsgn.AuthorAsgnMapper;
import zesinc.intra.authorAsgn.AuthorAsgnService;
import zesinc.intra.authorAsgn.domain.AuthorAsgnVO;
import zesinc.intra.menu.MenuService;
import zesinc.intra.menu.domain.MenuVO;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 담당자권한할당 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opAuthorAsgnService")
public class AuthorAsgnServiceImpl extends EgovAbstractServiceImpl implements AuthorAsgnService {

    @Resource(name = "opAuthorAsgnDao")
    private AuthorAsgnMapper opAuthorAsgnDao;
    @Resource(name = "opMenuService")
    private MenuService opMenuService;

    @Override
    public Pager<AuthorAsgnVO> selectAuthorAsgnPageList(AuthorAsgnVO authorAsgnVo) {

        List<AuthorAsgnVO> dataList = opAuthorAsgnDao.selectAuthorAsgnList(authorAsgnVo);
        Integer totalNum = opAuthorAsgnDao.selectAuthorAsgnListCount(authorAsgnVo);

        return new Pager<AuthorAsgnVO>(dataList, authorAsgnVo, totalNum);
    }

    @Override
    public List<AuthorAsgnVO> selectAuthorMngrList(MngrVO mngrVo) {

        mngrVo.setPicIdArray(mngrVo.getPicIds().split(","));
        List<AuthorAsgnVO> mngrList = opAuthorAsgnDao.selectMngrList(mngrVo);

        return mngrList;
    }

    @Override
    public Integer insertAuthorAsgn(AuthorAsgnVO authorAsgnVo) {

        int result = 0;

        String[] authrtCdIds = StringUtil.split(authorAsgnVo.getAuthrtCdIds(), ",");
        String[] picIds = StringUtil.split(authorAsgnVo.getPicIds(), ",");

        // 담당자 수만큼 루프
        for(String picId : picIds) {
            authorAsgnVo.setPicId(picId);

            // 권한 수만큼 루프
            for(String authrtCdId : authrtCdIds) {
                authorAsgnVo.setAuthrtCdId(authrtCdId);

                // 중복권한이 없는경우 권한등록
                if(Validate.isEmpty(opAuthorAsgnDao.selectAuthorAsgn(authorAsgnVo))) {
                    result += opAuthorAsgnDao.insertAuthorAsgn(authorAsgnVo);
                }

            }

        }

        return result;
    }

    @Override
    public List<AuthorAsgnVO> selectAuthorMenuList(AuthorAsgnVO authorAsgnVo) {

        List<AuthorAsgnVO> menuList = opAuthorAsgnDao.selectAuthorMenuList(authorAsgnVo);

        for(AuthorAsgnVO authorAsgnVO : menuList) {
            MenuVO menuVo = new MenuVO();
            menuVo.setMenuSn(authorAsgnVO.getMenuSn());

            authorAsgnVO.setMenuNm(opMenuService.getMenuPath(menuVo));
        }

        return menuList;
    }

    @Override
    public Pager<AuthorAsgnVO> selectAuthorMngrPageList(AuthorAsgnVO authorAsgnVo) {

        // 체크된 목록을 Array에 담는다
        if(Validate.isNotEmpty(authorAsgnVo.getParam("q_authrtCdId"))) {
            String authCodes = (String) authorAsgnVo.getParam("q_authrtCdId");
            authorAsgnVo.setAuthrtCdIdArray(authCodes.split(","));
        }

        List<AuthorAsgnVO> dataList = opAuthorAsgnDao.selectAuthorMngrList(authorAsgnVo);
        Integer totalNum = opAuthorAsgnDao.selectAuthorMngrListCount(authorAsgnVo);

        for(AuthorAsgnVO dataVo : dataList) {
            List<String> authorNmArray = new ArrayList<String>();
            // 권한목록을 authorNmArray에 담는다
            for(AuthorAsgnVO authorVo : dataVo.getAuthorAsgnList()) {
                authorNmArray.add(authorVo.getAuthrtNm());
            }
            // 화면단에서 fn:join()으로 , 처리해주기 위해 배열로 셋팅
            dataVo.setAuthrtCdIdArray(authorNmArray.toArray(new String[authorNmArray.size()]));
        }

        return new Pager<AuthorAsgnVO>(dataList, authorAsgnVo, totalNum);
    }

}
