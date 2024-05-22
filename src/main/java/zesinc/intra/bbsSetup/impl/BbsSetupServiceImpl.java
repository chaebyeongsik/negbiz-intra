/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.config.Config;
import zesinc.core.lang.RequestBinder;
import zesinc.core.lang.Validate;
import zesinc.intra.bbsSetup.BbsSetupMapper;
import zesinc.intra.bbsSetup.BbsSetupService;
import zesinc.intra.bbsSetup.domain.BbsSetupItemVO;
import zesinc.intra.bbsSetup.domain.BbsSetupVO;
import zesinc.intra.bbsSetup.support.BbsSetupConstant;
import zesinc.web.support.helper.CacheHelper;
import zesinc.web.support.pager.Pager;

/**
 * 게시판 환경설정 관리 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 30.    woogi   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opBbsSetupService")
public class BbsSetupServiceImpl implements BbsSetupService {

    @Resource(name = "opBbsSetupDao")
    private BbsSetupMapper opBbsSetupDao;

    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public BbsSetupVO selectBbsSetup(BbsSetupVO bbsSetupVo) {

        BbsSetupVO dataVo = null;

        if(BbsSetupConstant.SECTION_CODE_GLOBAL == bbsSetupVo.getSeCdId()) {
            dataVo = opBbsSetupDao.selectBbsSetup(bbsSetupVo);
        } else if(BbsSetupConstant.SECTION_CODE_LIST == bbsSetupVo.getSeCdId()) {
            dataVo = opBbsSetupDao.selectBbsListSetup(bbsSetupVo);
        } else if(BbsSetupConstant.SECTION_CODE_VIEW == bbsSetupVo.getSeCdId()) {
            dataVo = opBbsSetupDao.selectBbsViewSetup(bbsSetupVo);
        } else if(BbsSetupConstant.SECTION_CODE_FORM == bbsSetupVo.getSeCdId()) {
            dataVo = opBbsSetupDao.selectBbsFormSetup(bbsSetupVo);
        } else if(BbsSetupConstant.SECTION_CODE_ITEM == bbsSetupVo.getSeCdId()) {
            List<BbsSetupItemVO> bbsItemList = opBbsSetupDao.selectBbsItemSetup(bbsSetupVo);
            dataVo = opBbsSetupDao.selectBbsSetup(bbsSetupVo);
            dataVo.setBbsItemList(bbsItemList);
        }

        return dataVo;
    }

    @Override
    public Pager<BbsSetupVO> selectBbsSetupPageList(BbsSetupVO bbsSetupVo) {

        List<BbsSetupVO> dataList = opBbsSetupDao.selectBbsSetupList(bbsSetupVo);
        Integer totalNum = opBbsSetupDao.selectBbsSetupListCount(bbsSetupVo);

        return new Pager<BbsSetupVO>(dataList, bbsSetupVo, totalNum);
    }

    @Override
    public Integer insertBbsSetup(BbsSetupVO bbsSetupVo) {

        Integer affected = opBbsSetupDao.insertBbsSetup(bbsSetupVo);

        if(affected == 1) {
            opBbsSetupDao.insertBbsGlobalSetup(bbsSetupVo);
            opBbsSetupDao.insertBbsListSetup(bbsSetupVo);
            opBbsSetupDao.insertBbsViewSetup(bbsSetupVo);
            opBbsSetupDao.insertBbsFormSetup(bbsSetupVo);

            List<BbsSetupItemVO> itemList = makeItemList(bbsSetupVo);
            for(BbsSetupItemVO itemVo : itemList) {
                opBbsSetupDao.insertBbsItemSetup(itemVo);
            }
        }

        return affected;
    }

    @Override
    public Integer updateBbsSetup(BbsSetupVO bbsSetupVo) {

        Integer affected = opBbsSetupDao.updateBbsSetup(bbsSetupVo);

        if(affected == 1) {

            // 워터마크 파일이 변경된 경우 기존 파일 삭제 후 변경
            if(Validate.isNotEmpty(bbsSetupVo.getNewWtmkFileNm())) {
                if(Validate.isNotEmpty(bbsSetupVo.getWtmkFileNm())) {
                    String wtmkFileNmPath = UploadHelper.getRootPath(Boolean.TRUE) + bbsSetupVo.getWtmkFileNm().substring(1);
                    File delFile = new File(wtmkFileNmPath);
                    if(delFile.canWrite()) {
                        delFile.delete();
                    }
                }
                bbsSetupVo.setWtmkFileNm(bbsSetupVo.getNewWtmkFileNm());
            }

            affected = opBbsSetupDao.updateBbsGlobalSetup(bbsSetupVo);
        }

        return affected;
    }

    @Override
    public Integer updateBbsListSetup(BbsSetupVO bbsSetupVo) {

        return opBbsSetupDao.updateBbsListSetup(bbsSetupVo);
    }

    @Override
    public Integer updateBbsViewSetup(BbsSetupVO bbsSetupVo) {

        return opBbsSetupDao.updateBbsViewSetup(bbsSetupVo);
    }

    @Override
    public Integer updateBbsFormSetup(BbsSetupVO bbsSetupVo) {

        return opBbsSetupDao.updateBbsFormSetup(bbsSetupVo);
    }

    @Override
    public Integer updateBbsItemSetup(HttpServletRequest request, BbsSetupItemVO bbsItemVo) {

        Integer affected = 0;

        opBbsSetupDao.deleteBbsItemSetup(bbsItemVo.getBbsStngSn());

        BbsSetupItemVO dataVo;
        String[] colIds = bbsItemVo.getColId().split(",");
        Integer sortSn = 1;
        for(String colId : colIds) {
            dataVo = new BbsSetupItemVO();
            RequestBinder.beanBind(dataVo, request, colId + "_");

            dataVo.setBbsStngSn(bbsItemVo.getBbsStngSn());
            dataVo.setColId(colId);
            dataVo.setSortSn(sortSn++);
            affected += opBbsSetupDao.insertBbsItemSetup(dataVo);
        }

        return affected;
    }

    @Override
    public Integer deleteBbsSetupList(BbsSetupVO bbsSetupVo) {

        String[] bbsStngSns = bbsSetupVo.getBbsStngSns().split(",");

        Integer affected = bbsStngSns.length;
        Integer deleteCnt = 0;

        BbsSetupVO paramVo = new BbsSetupVO();
        for(String cdId : bbsStngSns) {
            paramVo.setBbsStngSn(Integer.parseInt(cdId));
            deleteCnt += deleteBbsSetup(paramVo);
        }

        // 전체 선택건과 실 삭제 건의 차이를 반환
        affected = affected - deleteCnt;

        return affected;
    }

    @Override
    public Integer deleteBbsSetup(BbsSetupVO bbsSetupVo) {

        Integer affected = 0;
        bbsSetupVo.addParam("q_bbsStngSn", bbsSetupVo.getBbsStngSn());
        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_GLOBAL);
        BbsSetupVO dataVo = selectBbsSetup(bbsSetupVo);

        Integer useCnt = opBbsSetupDao.selectBbsSetupUseChck(bbsSetupVo);
        if(useCnt > 0) {
            return 0;
        }

        opBbsSetupDao.deleteBbsGlobalSetup(bbsSetupVo.getBbsStngSn());
        opBbsSetupDao.deleteBbsListSetup(bbsSetupVo.getBbsStngSn());
        opBbsSetupDao.deleteBbsViewSetup(bbsSetupVo.getBbsStngSn());
        opBbsSetupDao.deleteBbsFormSetup(bbsSetupVo.getBbsStngSn());
        opBbsSetupDao.deleteBbsItemSetup(bbsSetupVo.getBbsStngSn());

        affected += opBbsSetupDao.deleteBbsSetup(bbsSetupVo.getBbsStngSn());

        // GlobalSetup의 워터마크 파일 삭제
        if(Validate.isNotEmpty(dataVo.getWtmkFileNm())) {
            String wtmkFileNmPath = UploadHelper.getRootPath(Boolean.TRUE) + dataVo.getWtmkFileNm().substring(1);
            File delFile = new File(wtmkFileNmPath);
            if(delFile.canWrite()) {
                delFile.delete();
            }
            bbsSetupVo.setWtmkFileNm(bbsSetupVo.getNewWtmkFileNm());
        }

        return affected;
    }

    @Override
    public Integer updateUseYn(BbsSetupVO bbsSetupVo) {

        Integer affected = 0;
        if(bbsSetupVo.getSeCdId() == BbsSetupConstant.SECTION_CODE_BASE) {
            bbsSetupVo.addParam("q_bbsStngSn", bbsSetupVo.getBbsStngSn());
            affected = opBbsSetupDao.updateBbsSetup(bbsSetupVo);
        } else if(bbsSetupVo.getSeCdId() == BbsSetupConstant.SECTION_CODE_GLOBAL) {
            affected = opBbsSetupDao.updateBbsGlobalSetupUseYn(bbsSetupVo);
        } else if(bbsSetupVo.getSeCdId() == BbsSetupConstant.SECTION_CODE_VIEW) {
            affected = opBbsSetupDao.updateBbsViewSetupUseYn(bbsSetupVo);
        } else if(bbsSetupVo.getSeCdId() == BbsSetupConstant.SECTION_CODE_FORM) {
            affected = opBbsSetupDao.updateBbsFormSetupUseYn(bbsSetupVo);
        }

        return affected;
    }

    @Override
    public Boolean reloadCache() {
        CacheHelper.cacheReload("opBbsConfigCache");

        return Boolean.TRUE;
    }

    /**
     * 게시판 환경설정에서 item 목록을 생성
     *
     * @param bbsSetupVo
     * @return
     */
    private List<BbsSetupItemVO> makeItemList(BbsSetupVO bbsSetupVo) {

        List<BbsSetupItemVO> itemList = new ArrayList<BbsSetupItemVO>();

        Iterator<?> it = Config.getKeys("bbs-config.defaultColumn");
        String key = "";
        BbsSetupItemVO bbsItemVo;
        int sortSn = 1;

        while(it.hasNext()) {
            bbsItemVo = new BbsSetupItemVO();

            key = String.valueOf(it.next());
            key = key.replaceAll("bbs-config.defaultColumn", "bbs-config.defaultConfig");

            bbsItemVo.setBbsStngSn(bbsSetupVo.getBbsStngSn());

            bbsItemVo.setColId(Config.getString(key + "[@colId]"));
            bbsItemVo.setColNm(Config.getString(key + "[@colNm]"));
            bbsItemVo.setScrnNm(Config.getString(key + "[@colNm]"));
            bbsItemVo.setColTypeNm(Config.getString(key + "[@colTypeNm]"));
            bbsItemVo.setBbsColExpln(Config.getString(key + "[@bbsColExpln]"));
            bbsItemVo.setSrchType(Config.getString(key + "[@srchType]"));
            bbsItemVo.setEsntlYn(Config.getString(key + "[@esntlYn]"));
            bbsItemVo.setLstIndctYn(Config.getString(key + "[@lstIndctYn]"));
            bbsItemVo.setInqIndctYn(Config.getString(key + "[@inqIndctYn]"));
            bbsItemVo.setInptIndctYn(Config.getString(key + "[@inptIndctYn]"));
            bbsItemVo.setSortSn(sortSn++);

            itemList.add(bbsItemVo);
        }

        return itemList;
    }

}
