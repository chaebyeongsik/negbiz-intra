/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbstmplat.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.component.file.support.DevFileUtil;
import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.bbstmplat.BbsTmplatMapper;
import zesinc.intra.bbstmplat.BbsTmplatService;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.pager.Pager;
import zesinc.web.vo.cache.BbsItemCacheVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시판템플릿 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-23.    황신욱   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opBbsTmplatService")
public class BbsTmplatServiceImpl extends EgovAbstractServiceImpl implements BbsTmplatService {

    /** WAS 서버의 Root 경로 */
    public static String WAS_ROOT = BaseConfig.WEBAPP_ROOT;

    @Resource(name = "opBbsTmplatDao")
    private BbsTmplatMapper opBbsTmplatDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public BbsTmplatVO selectBbsTmplat(BbsTmplatVO bbsTmplatVo) {

        BbsTmplatVO dataVo = opBbsTmplatDao.selectBbsTmplat(bbsTmplatVo);
        // 첨부파일
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
        }

        return dataVo;
    }

    @Override
    public Pager<BbsTmplatVO> selectBbsTmplatPageList(BbsTmplatVO bbsTmplatVo) {

        List<BbsTmplatVO> dataList = opBbsTmplatDao.selectBbsTmplatList(bbsTmplatVo);
        Integer totalNum = opBbsTmplatDao.selectBbsTmplatListCount(bbsTmplatVo);

        return new Pager<BbsTmplatVO>(dataList, bbsTmplatVo, totalNum);
    }

    @Override
    public Integer insertBbsTmplat(BbsTmplatVO bbsTmplatVo) {

        if(Validate.isNotEmpty(bbsTmplatVo.getFileList())) {
            // 첨부파일
            Integer fileSn = opFileService.insertFileList(bbsTmplatVo.getFileList());
            bbsTmplatVo.setFileSn(fileSn);
        }

        return opBbsTmplatDao.insertBbsTmplat(bbsTmplatVo);
    }

    @Override
    public Integer updateBbsTmplat(BbsTmplatVO bbsTmplatVo) {

        BbsTmplatVO dataVo = selectBbsTmplat(bbsTmplatVo);

        if(Validate.isNotEmpty(bbsTmplatVo.getFileIds())) {
            opFileService.deleteFiles(dataVo.getFileSn(), bbsTmplatVo.getFileIds());
            bbsTmplatVo.setFileSn(-1);
        }

        if(Validate.isNotEmpty(bbsTmplatVo.getFileList())) {
            // 첨부파일
            if(dataVo.getFileSn() > 0) {
                opFileService.insertFileList(dataVo.getFileSn(), bbsTmplatVo.getFileList());
            } else {
                Integer fileSn = opFileService.insertFileList(bbsTmplatVo.getFileList());
                bbsTmplatVo.setFileSn(fileSn);
            }
        }

        return opBbsTmplatDao.updateBbsTmplat(bbsTmplatVo);
    }

    @Override
    public Integer deleteBbsTmplat(BbsTmplatVO bbsTmplatVo) {

        // 원본 삭제
        BbsTmplatVO dataVo = selectBbsTmplat(bbsTmplatVo);
        dataVo.setParamMap(bbsTmplatVo.getParamMap());

        // 개발 모드인경우 서버 Plugin ROOT 이외에 로컬 소스 경로(Eclipse)에도 파일을 삭제한다.
        String filePathNm = dataVo.getTmpltFilePathNm();
        DevFileUtil.deleteDevPathFile(filePathNm);

        String fullPath = "";
        // WAS Root를 기준으로 파일 삭제
        fullPath = WAS_ROOT;
        if(fullPath.endsWith("/")) {
            fullPath = fullPath.substring(0, fullPath.length() - 1);
        }
        fullPath += filePathNm;
        File file = new File(fullPath);
        if(file.exists() && file.canWrite()) {
            file.delete();
        }

        Integer delCnt = opBbsTmplatDao.deleteBbsTmplat(dataVo);

        // 첨부파일 삭제
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            opFileService.deleteFile(dataVo.getFileSn());
        }

        return delCnt;
    }

    @Override
    public Integer selectDplctChckId(BbsTmplatVO bbsTmplatVo) {

        return opBbsTmplatDao.selectDplctChckId(bbsTmplatVo);
    }

    @Override
    public List<BbsItemCacheVO> selectBbsItemList() {

        Iterator<?> it = Config.getKeys("bbs-config.defaultColumn");
        String key = "";
        BbsItemCacheVO bbsItemVo;
        int sortSn = 0;
        List<BbsItemCacheVO> bbsItemList = new ArrayList<BbsItemCacheVO>();

        while(it.hasNext()) {
            bbsItemVo = new BbsItemCacheVO();

            key = String.valueOf(it.next());
            key = key.replaceAll("bbs-config.defaultColumn", "bbs-config.defaultConfig");

            bbsItemVo.setColId(Config.getString(key + "[@colId]"));
            bbsItemVo.setColNm(Config.getString(key + "[@colNm]"));

            bbsItemList.add(sortSn, bbsItemVo);
            sortSn++;
        }

        return bbsItemList;
    }

}
