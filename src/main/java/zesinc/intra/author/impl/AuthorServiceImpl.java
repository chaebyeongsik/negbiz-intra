/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.author.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.author.AuthorMapper;
import zesinc.intra.author.AuthorService;
import zesinc.intra.author.domain.AuthorVO;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.pager.Pager;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 권한 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-24.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opAuthorService")
public class AuthorServiceImpl extends EgovAbstractServiceImpl implements AuthorService {

    @Resource(name = "opAuthorDao")
    private AuthorMapper opAuthorDao;

    @Override
    public List<TreeVO> selectAuthorMenuTreeList(AuthorVO authorVo) {

        authorVo.addParam("q_authrtCdId", authorVo.getAuthrtCdId());
        List<TreeVO> dataList = opAuthorDao.selectAuthorMenuTreeList(authorVo);
        // 자식 메뉴 모두 설정
        AuthorVO paramVo = new AuthorVO();
        paramVo.addParam("q_authrtCdId", authorVo.getAuthrtCdId());
        setAuthorMenuTreeListChildren(dataList, paramVo);

        return dataList;
    }

    /**
     * 하위 자식 메뉴트리 목록 설정
     * 
     * @param treeList
     * @return
     */
    private void setAuthorMenuTreeListChildren(List<TreeVO> dataList, AuthorVO paramVo) {

        if(Validate.isNotEmpty(dataList)) {
            List<TreeVO> treeList;
            for(TreeVO treeVo : dataList) {
                paramVo.addParam("q_upMenuSn", treeVo.getKey());
                treeList = opAuthorDao.selectAuthorMenuTreeList(paramVo);
                treeVo.setChildren(treeList);

                setAuthorMenuTreeListChildren(treeList, paramVo);
            }
        }
    }

    @Override
    public AuthorVO selectAuthor(AuthorVO authorVo) {

        AuthorVO dataVo = opAuthorDao.selectAuthor(authorVo);

        return dataVo;
    }

    @Override
    public List<AuthorVO> selectAuthorList(AuthorVO authorVo) {

        List<AuthorVO> dataList = opAuthorDao.selectAuthorList(authorVo);

        return dataList;
    }

    @Override
    public Integer insertAuthor(AuthorVO authorVo) {

        return opAuthorDao.insertAuthor(authorVo);
    }

    @Override
    public Integer updateAuthor(AuthorVO authorVo) {

        int result = 0;
        if(!authorVo.getOriginalAuthrtCdId().equals(authorVo.getAuthrtCdId())) {
            result = opAuthorDao.insertAuthor(authorVo);
            opAuthorDao.updateAuthorMngrAssign(authorVo);
            opAuthorDao.updateAuthorMenuAssign(authorVo);
            opAuthorDao.deleteAuthor(authorVo);
        } else {
            result = opAuthorDao.updateAuthor(authorVo);
            opAuthorDao.updateAuthorMngrAssign(authorVo);
            opAuthorDao.updateAuthorMenuAssign(authorVo);
        }

        return result;
    }

    @Override
    public Integer deleteAuthor(AuthorVO authorVo) {

        opAuthorDao.deleteMenuAsgn(authorVo);
        opAuthorDao.deleteMngrAsgn(authorVo);
        Integer delCnt = opAuthorDao.deleteAuthor(authorVo);

        return delCnt;
    }

    @Override
    public Integer selectDupCheckAuthorCode(AuthorVO authorVo) {
        return opAuthorDao.selectDupCheckAuthorCode(authorVo);
    }

    @Override
    public List<AuthorVO> selectMenuAsgn(AuthorVO authorVo) {
        return opAuthorDao.selectMenuAsgn(authorVo);
    }

    @Override
    public Integer insertMenuAuthorAsgn(AuthorVO authorVo) {
        int result = 0;

        authorVo.setOriginalAuthrtCdId(authorVo.getAuthrtCdId());
        // 삭제
        result += opAuthorDao.deleteMenuAsgn(authorVo);
        if(Validate.isNotEmpty(authorVo.getMenuSn())){
            String[] menuSns = StringUtil.split(authorVo.getMenuSn(), ",");
            String[] authrtGrntCdIds = StringUtil.split(authorVo.getAuthrtGrntCdId(), ",");
            for(int i = 0; i < menuSns.length; i++){
                AuthorVO vo = new AuthorVO();
                vo.setMenuSn(menuSns[i]);
                vo.setAuthrtCdId(authorVo.getAuthrtCdId());
                vo.setAuthrtGrntCdId(authrtGrntCdIds[i]);
                result += opAuthorDao.insertMenuAsgn(vo);
            }
        }

        return result;
    }

    @Override
    public Pager<MngrVO> selectMngrInAuthorPageList(AuthorVO authorVo) {
        List<MngrVO> dataList = opAuthorDao.selectMngrInAuthorList(authorVo);
        Integer totalNum = opAuthorDao.selectMngrInAuthorListCount(authorVo);

        MngrVO mngrVo = new MngrVO();
        mngrVo.setParamMap(authorVo.getParamMap());
        return new Pager<MngrVO>(dataList, mngrVo, totalNum);
    }

}
