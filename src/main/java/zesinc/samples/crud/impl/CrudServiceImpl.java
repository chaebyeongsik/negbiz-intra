/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.samples.crud.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.samples.crud.CrudMapper;
import zesinc.samples.crud.CrudService;
import zesinc.samples.crud.domain.CrudReplyVO;
import zesinc.samples.crud.domain.CrudVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * CRUD 셈플 프로그램 Service 구현 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 11.    방기배   최초작성
 * </pre>
 * @see
 */
@Service("opCrudService")
public class CrudServiceImpl extends EgovAbstractServiceImpl implements CrudService {

    @Resource(name = "opCrudDao")
    private CrudMapper opCrudDao;
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public CrudVO selectCrud(CrudVO crudVo) {

        CrudVO dataVo = opCrudDao.selectCrud(crudVo);
        // 조회수 증가
        opCrudDao.increase(crudVo);

        // 첨부파일
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
        }

        return dataVo;
    }

    @Override
    public Pager<CrudVO> selectCrudList(CrudVO crudVo) {

        List<CrudVO> dataList = opCrudDao.selectCrudList(crudVo);
        Integer totalNum = opCrudDao.selectCrudListCount(crudVo);

        return new Pager<CrudVO>(dataList, crudVo, totalNum);
    }

    @Override
    public Integer insertCrud(CrudVO crudVo) {
        Integer fileSn = opFileService.insertFileList(crudVo.getFileList());
        crudVo.setFileSn(fileSn);

        return opCrudDao.insertCrud(crudVo);
    }

    @Override
    public Integer updateCrud(CrudVO crudVo) {

        CrudVO dataVo = selectCrud(crudVo);

        if(Validate.isNotEmpty(crudVo.getFileIds())) {
            opFileService.deleteFiles(dataVo.getFileSn(), crudVo.getFileIds());
        }

        Integer fileSn = opFileService.insertFileList(dataVo.getFileSn(), crudVo.getFileList());
        crudVo.setFileSn(fileSn);

        return opCrudDao.updateCrud(crudVo);
    }

    @Override
    public Integer deleteCrud(CrudVO crudVo) {

        // 원글 삭제
        CrudVO delCrudVo = selectCrud(crudVo);
        delCrudVo.setParamMap(crudVo.getParamMap());

        Integer delCnt = opCrudDao.deleteCrud(delCrudVo);

        // 답변 삭제
        CrudReplyVO crudReplyVo = new CrudReplyVO();
        crudReplyVo.setParamMap(crudVo.getParamMap());

        List<CrudReplyVO> replyList = opCrudDao.selectCrudReplyList(crudReplyVo);
        for(CrudReplyVO replyDataVo : replyList) {
            deleteCrudReply(replyDataVo);
        }

        // 첨부파일 삭제
        if(delCrudVo.getFileSn() > 0) {
            opFileService.deleteFile(delCrudVo.getFileSn());
        }

        return delCnt;
    }

    @Override
    public Integer deleteCrudList(CrudVO crudVo) {

        Integer delCnt = 0;
        Integer[] regSns = crudVo.getRegSns();

        for(Integer regSn : regSns) {
            crudVo.getParamMap().put("q_regSn", regSn);

            delCnt += deleteCrud(crudVo);
        }

        return delCnt;
    }

    @Override
    public CrudReplyVO selectCrudReply(CrudReplyVO crudReplyVo) {

        CrudReplyVO replyVo = opCrudDao.selectCrudReply(crudReplyVo);

        return replyVo;
    }

    @Override
    public List<CrudReplyVO> selectCrudReplyList(CrudReplyVO crudReplyVo) {

        List<CrudReplyVO> dataList = opCrudDao.selectCrudReplyList(crudReplyVo);
        // 첨부파일
        for(CrudReplyVO replyVo : dataList) {
            if(Validate.isNotEmpty(replyVo.getFileSn())) {
                replyVo.setFileList(opFileService.selectFileList(replyVo.getFileSn()));
            }
        }

        return dataList;
    }

    @Override
    public Object insertCrudReply(CrudReplyVO crudReplyVo) {

        Integer fileSn = opFileService.insertFileList(crudReplyVo.getFileList());
        crudReplyVo.setFileSn(fileSn);

        opCrudDao.insertCrudReply(crudReplyVo);
        opCrudDao.updateCrudReplyYn(crudReplyVo);

        return crudReplyVo.getAnswerNo();
    }

    @Override
    public Integer updateCrudReply(CrudReplyVO crudReplyVo) {

        CrudReplyVO dataVo = selectCrudReply(crudReplyVo);

        if(Validate.isNotEmpty(crudReplyVo.getFileIds())) {
            opFileService.deleteFiles(crudReplyVo.getFileSn(), crudReplyVo.getFileIds());
        }

        Integer fileSn = opFileService.insertFileList(dataVo.getFileSn(), crudReplyVo.getFileList());

        crudReplyVo.setFileSn(fileSn);

        Integer updateCnt = opCrudDao.updateCrudReply(crudReplyVo);

        return updateCnt;
    }

    @Override
    public Integer deleteCrudReply(CrudReplyVO crudReplyVo) {

        CrudReplyVO replyVo = selectCrudReply(crudReplyVo);
        replyVo.setParamMap(crudReplyVo.getParamMap());

        Integer deleteCnt = opCrudDao.deleteCrudReply(replyVo);
        opCrudDao.updateCrudReplyYn(replyVo);

        // 첨부파일 삭제
        if(Validate.isNotEmpty(replyVo.getFileSn())) {
            opFileService.deleteFile(replyVo.getFileSn());
        }

        return deleteCnt;
    }

}
