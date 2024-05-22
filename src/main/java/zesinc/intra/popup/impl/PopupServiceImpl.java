/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.intra.popup.PopupMapper;
import zesinc.intra.popup.PopupService;
import zesinc.intra.popup.domain.PopupDomnVO;
import zesinc.intra.popup.domain.PopupVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 팝업 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-20.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opPopupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {

    @Resource(name = "opPopupDao")
    private PopupMapper opPopupDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public PopupVO selectPopup(PopupVO popupVo) {

        PopupVO dataVo = opPopupDao.selectPopup(popupVo);
        if(Validate.isNotEmpty(dataVo)) {
            // 도메인
            dataVo.setDomnList(opPopupDao.selectPopupDomnList(dataVo));

            // 첨부파일
            if(Validate.isNotEmpty(dataVo.getFileSn())) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }
        }

        return dataVo;
    }

    @Override
    public Pager<PopupVO> selectPopupPageList(PopupVO popupVo) {

        List<PopupVO> dataList = opPopupDao.selectPopupList(popupVo);
        Integer totalNum = opPopupDao.selectPopupListCount(popupVo);

        for(PopupVO dataVo : dataList) {
            dataVo.setDomnList(opPopupDao.selectPopupDomnList(dataVo));
        }

        return new Pager<PopupVO>(dataList, popupVo, totalNum);
    }

    @Override
    public Integer insertPopup(PopupVO popupVo) {

        // 첨부파일
        if(Validate.isNotEmpty(popupVo.getFileList())) {
            Integer fileSn = opFileService.insertFileList(popupVo.getFileList());
            popupVo.setFileSn(fileSn);
        }

        if(Validate.isNotEmpty(popupVo)) {
            popupVo.setBgngYmd(popupVo.getBgngYmd().replace("-", ""));
        }
        if(Validate.isNotEmpty(popupVo.getEndYmd())) {
            popupVo.setEndYmd(popupVo.getEndYmd().replace("-", ""));
        }

        Integer insertCnt = opPopupDao.insertPopup(popupVo);

        // 도메인
        if(Validate.isNotEmpty(popupVo.getSiteSns())) {
            PopupDomnVO popupDomnVo = new PopupDomnVO();
            popupDomnVo.setRegSn(popupVo.getRegSn());

            for(Integer siteSn : popupVo.getSiteSns()) {
                popupDomnVo.setSiteSn(siteSn);
                opPopupDao.insertPopupDomn(popupDomnVo);
            }
        }

        return insertCnt;
    }

    @Override
    public Integer updatePopup(PopupVO popupVo) {

        PopupVO dataVo = selectPopup(popupVo);

        // 첨부파일
        if(Validate.isNotEmpty(popupVo.getFileIds())) {
            opFileService.deleteFiles(dataVo.getFileSn(), popupVo.getFileIds());
        }
        if(Validate.isNotEmpty(popupVo.getFileList())) {
            Integer fileSn = opFileService.insertFileList(dataVo.getFileSn(), popupVo.getFileList());
            popupVo.setFileSn(fileSn);
        }

        // 도메인
        if(Validate.isNotEmpty(popupVo.getSiteSns())) {
            // 기존 목록 삭제
            opPopupDao.deletePopupDomn(popupVo);

            PopupDomnVO popupDomnVo = new PopupDomnVO();
            popupDomnVo.setRegSn(popupVo.getInteger("q_regSn"));
            for(Integer siteSn : popupVo.getSiteSns()) {
                popupDomnVo.setSiteSn(siteSn);
                opPopupDao.insertPopupDomn(popupDomnVo);
            }
        }

        if(Validate.isNotEmpty(popupVo.getBgngYmd())) {
            popupVo.setBgngYmd(popupVo.getBgngYmd().replace("-", ""));
        }
        if(Validate.isNotEmpty(popupVo.getEndYmd())) {
            popupVo.setEndYmd(popupVo.getEndYmd().replace("-", ""));
        }

        return opPopupDao.updatePopup(popupVo);
    }

    @Override
    public Integer deletePopup(PopupVO popupVo) {

        // 원본 삭제
        PopupVO dataVo = selectPopup(popupVo);
        dataVo.setParamMap(popupVo.getParamMap());

        // 도메인
        opPopupDao.deletePopupDomn(dataVo);

        Integer deleteCnt = opPopupDao.deletePopup(popupVo);
        // 첨부파일 삭제
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            opFileService.deleteFile(dataVo.getFileSn());
        }

        return deleteCnt;
    }

}
