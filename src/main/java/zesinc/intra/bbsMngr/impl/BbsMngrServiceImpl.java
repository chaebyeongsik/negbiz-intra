/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsMngr.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.lang.Validate;
import zesinc.intra.bbsMngr.BbsMngrMapper;
import zesinc.intra.bbsMngr.BbsMngrService;
import zesinc.intra.bbsMngr.domain.BbsCtgryVO;
import zesinc.intra.bbsMngr.domain.BbsMngrVO;
import zesinc.intra.bbsSetup.BbsSetupService;
import zesinc.intra.bbsSetup.domain.BbsSetupVO;
import zesinc.intra.bbsSetup.support.BbsSetupConstant;
import zesinc.intra.bbstmplat.domain.BbsTmplatVO;
import zesinc.web.support.helper.CacheHelper;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시판설정 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-20.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opBbsMngrService")
public class BbsMngrServiceImpl extends EgovAbstractServiceImpl implements BbsMngrService {

    @Resource(name = "opBbsSetupService")
    private BbsSetupService opBbsSetupService;

    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Resource(name = "opBbsMngrDao")
    private BbsMngrMapper opBbsMngrDao;

    @Override
    public List<BbsTmplatVO> selectBbsTmplatList(String tmpltTypeCd) {

        return opBbsMngrDao.selectBbsTmplatList(tmpltTypeCd);
    }

    @Override
    public List<BbsMngrVO> selectBbsDomnList(BbsMngrVO bbsMngrVo) {

        return opBbsMngrDao.selectBbsDomnList(bbsMngrVo);
    }

    @Override
    public List<BbsMngrVO> selectBbsSetupList(BbsMngrVO bbsMngrVo) {

        return opBbsMngrDao.selectBbsSetupList(bbsMngrVo);
    }

    @Override
    public BbsMngrVO selectBbsMngr(BbsMngrVO bbsMngrVo) {

        BbsMngrVO dataVo = opBbsMngrDao.selectBbsMngr(bbsMngrVo);

        StringBuilder sb;
        List<BbsCtgryVO> lwprtCtgryList;

        List<BbsCtgryVO> bbsCtgryList = opBbsMngrDao.selectBbsCtgryList(bbsMngrVo);
        for(BbsCtgryVO ctgryVo : bbsCtgryList) {
            lwprtCtgryList = opBbsMngrDao.selectBbsLwprtCtgryList(ctgryVo);
            if(Validate.isNotEmpty(lwprtCtgryList)) {
                sb = new StringBuilder();
                for(BbsCtgryVO lwprtCtgryVo : lwprtCtgryList) {
                    sb.append(lwprtCtgryVo.getLwrkClsfNm()).append(",");
                }
                ctgryVo.setLwrkClsfNm(sb.substring(0, sb.length() - 1));
            }
        }
        dataVo.setClNmList(bbsCtgryList);

        return dataVo;
    }

    @Override
    public Pager<BbsMngrVO> selectBbsMngrList(BbsMngrVO bbsMngrVo) {

        List<BbsMngrVO> dataList = opBbsMngrDao.selectBbsMngrList(bbsMngrVo);
        Integer totalNum = opBbsMngrDao.selectBbsMngrListCount(bbsMngrVo);

        return new Pager<BbsMngrVO>(dataList, bbsMngrVo, totalNum);
    }

    @Override
    public Integer insertBbsMngr(BbsMngrVO bbsMngrVo) {

        Integer insertCnt = opBbsMngrDao.insertBbsMngr(bbsMngrVo);

        // 도메인 선택시 도메인별 게시판스킨정보 등록
        if(Validate.isNotEmpty(bbsMngrVo.getSiteSns())) {
            insertBbsDomn(bbsMngrVo);
        }
        if("Y".equals(bbsMngrVo.getClsfUseYn())) {
            insertBbsCtgry(bbsMngrVo);
        }

        return insertCnt;
    }

    @Override
    public Integer updateBbsMngr(BbsMngrVO bbsMngrVo) {
        Integer affected = opBbsMngrDao.updateBbsMngr(bbsMngrVo);
        if(affected == 1) {
            if(Validate.isNotEmpty(bbsMngrVo.getSiteSns())) {
                insertBbsDomn(bbsMngrVo);
            }
            if(bbsMngrVo.getClsfUseYn().equals("Y")) {
                insertBbsCtgry(bbsMngrVo);
            }
        }

        return affected;
    }

    @Override
    public Integer deleteBbsMngrList(BbsMngrVO bbsMngrV) throws Exception {

        Integer affected = 0;
        String[] bbsSns = bbsMngrV.getBbsSns().split(",");

        BbsMngrVO paramVo = new BbsMngrVO();
        for(String cdId : bbsSns) {
            paramVo.setBbsSn(Integer.parseInt(cdId));
            paramVo.addParam("q_bbsSn", Integer.parseInt(cdId));
            affected += deleteBbsMngr(paramVo);
        }

        return affected;
    }

    @Override
    public Integer deleteBbsMngr(BbsMngrVO bbsMngrVo) throws Exception {

        // 원본 삭제
        BbsMngrVO dataVo = selectBbsMngr(bbsMngrVo);
        dataVo.setParamMap(bbsMngrVo.getParamMap());

        // 모든첨부파일 목록 추출
        List<Integer> bbsFileList = opBbsMngrDao.selectBbsFileList(bbsMngrVo);
        // 도메인, 분류, 댓글, 태그 모두 삭제
        opBbsMngrDao.deleteBbsDomn(bbsMngrVo.getBbsSn());
        opBbsMngrDao.deleteBbsLwprtCtgry(bbsMngrVo.getBbsSn());
        opBbsMngrDao.deleteBbsCtgry(bbsMngrVo.getBbsSn());
        opBbsMngrDao.deleteBbsCmnt(bbsMngrVo.getBbsSn());
        opBbsMngrDao.deleteBbsTag(bbsMngrVo.getBbsSn());
        // 게시물 삭제
        opBbsMngrDao.deleteBbs(bbsMngrVo.getBbsSn());
        // 게시판 삭제
        Integer delCnt = opBbsMngrDao.deleteBbsMngr(dataVo);
        if(delCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }
        // 모든첨부파일 삭제
        for(Integer fileSn : bbsFileList) {
            opFileService.deleteFile(fileSn);
        }

        return delCnt;
    }

    @Override
    public List<String> createThumbNail(BbsMngrVO bbsMngrVo) {

        List<String> errorList = new ArrayList<String>();

        BbsMngrVO dataVo = selectBbsMngr(bbsMngrVo);

        BbsSetupVO bbsSetupVo = new BbsSetupVO();
        bbsSetupVo.addParam("q_bbsStngSn", dataVo.getBbsStngSn());
        bbsSetupVo.setSeCdId(BbsSetupConstant.SECTION_CODE_GLOBAL);
        bbsSetupVo = opBbsSetupService.selectBbsSetup(bbsSetupVo);

        // 썸네일 사용여부
        if(bbsSetupVo.getThmbUseYn().equals("Y")) {
            String createType = bbsMngrVo.getString("q_createType");

            // 모든 이미지 첨부파일 목록
            bbsMngrVo.addParam("extsnList", UploadHelper.THUMB_NAIL_EXTS);
            if(createType.equals("new")) {
                bbsMngrVo.addParam("isNew", "Y");
            } else if(createType.equals("all")) {
                bbsMngrVo.addParam("isNew", "N");
            }
            List<FileVO> bbsImgFileList = opBbsMngrDao.selectBbsImgFileList(bbsMngrVo);

            // 썸네일 사용시
            if(Validate.isNotEmpty(bbsImgFileList)) {
                // 워터마크 사용시
                if(bbsSetupVo.getWtmkUseYn().equals("Y") && Validate.isNotEmpty(bbsSetupVo.getWtmkFileNm())) {
                    UploadHelper.makeWarterMark(bbsImgFileList, bbsSetupVo.getWdthSz(),
                        bbsSetupVo.getVrtcSz(), bbsSetupVo.getWtmkFileNm(), bbsSetupVo.getWtmkPstnNm(),
                        bbsSetupVo.getWtmkTrnspc());
                } else {
                    UploadHelper.makeThumbNail(bbsImgFileList, bbsSetupVo.getWdthSz(), bbsSetupVo.getVrtcSz());
                }
            }
            Integer updateCnt = 0;
            StringBuilder sb = null;
            for(FileVO fileVo : bbsImgFileList) {
                if(Validate.isNotEmpty(fileVo.getThmbPathNm())) {
                    updateCnt += opBbsMngrDao.updateBbsImgThumbCours(fileVo);
                } else {
                    sb = new StringBuilder();
                    sb.append("글번호 : ").append(fileVo.getInptDataNm()).append(" 제목 : ").append(fileVo.getFileExpln()).append("<br />")
                        .append(" 파일경로 : ").append(fileVo.getFileUrlAddr()).append(" 원본파일명 : ").append(fileVo.getOrgnlFileNm());
                    errorList.add(sb.toString());
                }
            }
            sb = new StringBuilder();
            sb.append("총 파일수 : ").append(bbsImgFileList.size()).append(" 성공건수 : ")
                .append(updateCnt).append(" 오류건수 : ").append(errorList.size());

            errorList.add(0, sb.toString());
        }

        return errorList;
    }

    private void insertBbsDomn(BbsMngrVO bbsMngrVo) {
        String[] siteSns = bbsMngrVo.getSiteSns().split(",");
        String[] lstTmpltNms = bbsMngrVo.getLstTmpltNm().split(",");
        String[] viewSkins = bbsMngrVo.getPstTmpltNm().split(",");
        String[] inptTmpltNms = bbsMngrVo.getInptTmpltNm().split(",");

        if(Validate.isNotEmpty(bbsMngrVo.getSiteSns())) {
            opBbsMngrDao.deleteBbsDomn(bbsMngrVo.getBbsSn());

            for(int i = 0 ; i < siteSns.length ; i++) {
                String lstTmpltNm = "";
                String viewSkin = "";
                String inptTmpltNm = "";

                // 해당도메인코드의 스킨을 찾아낸다
                for (String skin : lstTmpltNms) {
                    if (skin.startsWith(siteSns[i] + "_")) lstTmpltNm = skin.replace(siteSns[i] + "_", "");
                }
                for (String skin : viewSkins) {
                    if (skin.startsWith(siteSns[i] + "_")) viewSkin = skin.replace(siteSns[i] + "_", "");
                }
                for (String skin : inptTmpltNms) {
                    if (skin.startsWith(siteSns[i] + "_")) inptTmpltNm = skin.replace(siteSns[i] + "_", "");
                }

                BbsMngrVO dataVo = new BbsMngrVO();
                dataVo = bbsMngrVo;
                dataVo.setSiteSn(Integer.parseInt(siteSns[i]));
                dataVo.setLstTmpltNm(lstTmpltNm);
                dataVo.setPstTmpltNm(viewSkin);
                dataVo.setInptTmpltNm(inptTmpltNm);
                opBbsMngrDao.insertBbsDomn(dataVo);
            }
        }

    }

    private void insertBbsCtgry(BbsMngrVO bbsMngrVo) {
        String[] clsfNms = bbsMngrVo.getClsfNms();
        String[] lwrkClsfNms = bbsMngrVo.getLwrkClsfNms();

        String[] lwrkClsfNmss;

        if(Validate.isNotEmpty(clsfNms) && (clsfNms[0].length() != 0)) {
            BbsCtgryVO bbsCtgryVo = new BbsCtgryVO();
            bbsCtgryVo.setBbsSn(bbsMngrVo.getBbsSn());

            if("Y".equals(bbsMngrVo.getClsfUseYn())) {
                opBbsMngrDao.deleteBbsLwprtCtgry(bbsCtgryVo.getBbsSn());
                opBbsMngrDao.deleteBbsCtgry(bbsCtgryVo.getBbsSn());

                int sortSn = 1;
                int lwprtSortSn = 1;
                for(int i = 0 ; i < clsfNms.length ; i++) {
                    String clsfNm = clsfNms[i];
                    if(Validate.isEmpty(clsfNm.trim())) {
                        return;
                    }
                    bbsCtgryVo.setClsfNm(StringUtils.trim(clsfNm));
                    bbsCtgryVo.setSortSn(sortSn++);
                    opBbsMngrDao.insertBbsCtgry(bbsCtgryVo);

                    String lwrkClsfNm = lwrkClsfNms[i];
                    if(Validate.isNotEmpty(lwrkClsfNm)) {
                        lwprtSortSn = 1;
                        lwrkClsfNmss = lwrkClsfNm.split(",");
                        for(String lwprtClNm : lwrkClsfNmss) {
                            bbsCtgryVo.setLwrkClsfNm(lwprtClNm);
                            bbsCtgryVo.setSortSn(lwprtSortSn++);
                            opBbsMngrDao.insertBbsLwprtCtgry(bbsCtgryVo);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Integer updateUseYn(BbsMngrVO bbsMngrVo) {

        return opBbsMngrDao.updateUseYn(bbsMngrVo);
    }

    @Override
    public Boolean reloadCache() {
        CacheHelper.cacheReload("opBbsConfigCache");

        return Boolean.TRUE;
    }
}
