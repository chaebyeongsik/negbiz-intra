/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.code.CodeHistoryService;
import zesinc.intra.code.CodeMapper;
import zesinc.intra.code.CodeService;
import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeVO;
import zesinc.intra.code.support.LOG_TYPE;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 코드 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opCodeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {

    @Resource(name = "opCodeDao")
    private CodeMapper opCodeDao;

    @Resource(name = "opCodeHistoryService")
    private CodeHistoryService opCodeHistoryService;

    /** 최상위 dummy 코드 */
    private static final String HIGH_COMMON_CD = Config.getString("webapp-config.defaultCode.highCommonCd");

    @Override
    public Integer selectDplctChckCode(CodeVO codeVo) {

        return opCodeDao.selectDplctChckCode(codeVo);
    }

    @Override
    public Integer selectDplctChckCodeChoice(CodeChoiceVO codeChoiceVo) {

        return opCodeDao.selectDplctChckCodeChoice(codeChoiceVo);
    }

    @Override
    public CodeVO selectCode(CodeVO codeVo) {

        CodeVO dataVo = opCodeDao.selectCode(codeVo);

        return dataVo;
    }

    @Override
    public List<TreeVO> selectCodeTreeList(CodeVO codeVo) {

        List<TreeVO> dataList = opCodeDao.selectCodeTreeList(codeVo);

        return dataList;
    }

    @Override
    public List<CodeVO> selectHghrkCdIdList(CodeVO codeVo) {

        List<CodeVO> dataList = opCodeDao.selectHghrkCdIdList(codeVo);

        return dataList;
    }

    @Override
    public List<CodeVO> selectCodeList(CodeVO codeVo) {

        List<CodeVO> dataList = opCodeDao.selectCodeList(codeVo);

        return dataList;
    }

    @Override
    public Integer insertCode(CodeVO codeVo) {
        // 최상위 dummy인 경우 쿼리에서 제외할 조건 비교에 사용할 값
        codeVo.addParam("q_dummy", HIGH_COMMON_CD);

        String hghrkCdId = codeVo.getHghrkCdId();
        // 최상위 코드가 더미 인경우 현재 코드와 동일하게 변경한다.
        if(hghrkCdId.equals(HIGH_COMMON_CD)) {
            codeVo.setHghrkCdId(codeVo.getCdId());
        }

        Integer insertCnt = opCodeDao.insertCode(codeVo);

        // 등록이력 저장
        codeVo.setLogType(LOG_TYPE.INSERT.getCdId());
        codeVo.setMdfrId(codeVo.getRgtrId());
        codeVo.addParam("q_hghrkCdId", codeVo.getHghrkCdId());
        codeVo.addParam("q_cdId", codeVo.getCdId());
        opCodeHistoryService.insertCodeHistory(codeVo);

        return insertCnt;
    }

    @Override
    public Integer updateCode(CodeVO codeVo) {

        Integer updateCnt = opCodeDao.updateCode(codeVo);

        // 수정이력 저장
        codeVo.setLogType(LOG_TYPE.UPDATE.getCdId());
        codeVo.setHghrkCdId(codeVo.getString("q_hghrkCdId"));
        codeVo.setCdId(codeVo.getString("q_cdId"));
        codeVo.setUpCdId(codeVo.getString("q_upCdId"));
        opCodeHistoryService.insertCodeHistory(codeVo);

        return updateCnt;
    }

    @Override
    public Boolean updateCodeSortOrder(CodeVO codeVo) {

        String hghrkCdId = codeVo.getString("q_hghrkCdId");
        String upCdId = codeVo.getString("q_upCdId");
        String cdId = codeVo.getString("q_cdId");
        Integer sortSn = codeVo.getInteger("q_sortSn");

        if(hghrkCdId == null || upCdId == null || cdId == null || sortSn == null) {
            return Boolean.FALSE;
        }

        // 현재 코드정보
        CodeVO dataVo = selectCode(codeVo);

        dataVo.addParam("q_upCdId", dataVo.getUpCdId());
        List<CodeVO> childList = opCodeDao.selectChildCodeList(codeVo);

        if(Validate.isEmpty(childList)) {
            childList = new ArrayList<CodeVO>();
            childList.add(dataVo);
        } else {
            childList.add(sortSn, dataVo);
        }

        // 동레벨의 코드목록의 정렬순서를 새로 업데이트한다.
        int childCnt = childList.size();
        CodeVO childVo;
        for(int i = 0 ; i < childCnt ; i++) {
            childVo = childList.get(i);
            childVo.setSortSn(i + 1);

            opCodeDao.updateCodeSortOrder(childVo);
        }

        // 수정이력 저장
        // dataVo.setLogType(LOG_TYPE.UPDATE.getCdId());
        // dataVo.setHghrkCdId(hghrkCdId);
        // dataVo.setCdId(cdId);
        // dataVo.setUpCdId(upCdId);
        // dataVo.setSortSn(sortSn);
        // dataVo.addParam("q_hghrkCdId", hghrkCdId);
        // dataVo.addParam("q_cdId", cdId);
        // opCodeHistoryService.insertCodeHistory(dataVo);

        return Boolean.TRUE;
    }

    @Override
    public Integer deleteCode(CodeVO codeVo) {

        Integer delCnt = 0;

        // 삭제 대상 원본 정보
        CodeVO dataVo = selectCode(codeVo);
        if(Validate.isNotEmpty(dataVo)) {
            // 파라미터 복사
            dataVo.setParamMap(codeVo.getParamMap());

            delCnt = opCodeDao.deleteCode(dataVo);

            // 코드선택목록 삭제
            CodeChoiceVO choiceVo = new CodeChoiceVO();
            choiceVo.addParam("q_cdId", dataVo.getCdId());
            choiceVo.addParam("q_hghrkCdId", dataVo.getHghrkCdId());
            List<CodeChoiceVO> choiceList = opCodeDao.selectCodeAndCodeChoiceList(choiceVo);
            for(CodeChoiceVO codeChoiceVo : choiceList) {
                // 이력 등록
                codeChoiceVo.setLogType(LOG_TYPE.DELETE.getCdId());
                codeChoiceVo.setMdfrId(codeVo.getMdfrId());
                opCodeHistoryService.insertCodeChoiceHistory(codeChoiceVo);
            }
            opCodeDao.deleteCodeAndCodeChoice(choiceVo);

            // 삭제이력 저장
            dataVo.setLogType(LOG_TYPE.DELETE.getCdId());
            dataVo.setMdfrId(codeVo.getMdfrId());
            opCodeHistoryService.insertCodeHistory(dataVo);
        }
        return delCnt;
    }

    @Override
    public CodeChoiceVO selectCodeChoice(CodeChoiceVO codeChoiceVo) {

        CodeChoiceVO dataVo = opCodeDao.selectCodeChoice(codeChoiceVo);

        return dataVo;
    }

    @Override
    public List<CodeChoiceVO> selectCodeChoiceList(CodeChoiceVO codeChoiceVo) {

        List<CodeChoiceVO> dataList = opCodeDao.selectCodeChoiceGroupList(codeChoiceVo);

        return dataList;
    }

    @Override
    public List<CodeChoiceVO> selectCodeChoiceCodeList(CodeChoiceVO codeChoiceVo) {

        List<CodeChoiceVO> dataList = opCodeDao.selectCodeChoiceCodeList(codeChoiceVo);

        return dataList;
    }

    @Override
    public List<TreeVO> selectCodeChoiceInsertTreeList(CodeChoiceVO codeChoiceVo) {

        List<TreeVO> dataList = opCodeDao.selectCodeChoiceInsertTreeList(codeChoiceVo);
        // 자식 메뉴 정보를 모두 추가한다.
        CodeChoiceVO paramVo = new CodeChoiceVO();
        setCdChcIdInsertChildern(dataList, paramVo);

        return dataList;
    }

    /**
     * 등록페이지용 트리 자식 목록을 추가한다.
     * 
     * @param dataList
     */
    private void setCdChcIdInsertChildern(List<TreeVO> dataList, CodeChoiceVO paramVo) {

        if(Validate.isNotEmpty(dataList)) {

            List<TreeVO> treeList;
            for(TreeVO treeVo : dataList) {
                paramVo.addParam("q_hghrkCdId", treeVo.getBaseKey());
                paramVo.addParam("q_cdId", treeVo.getKey());
                treeList = opCodeDao.selectCodeChoiceInsertTreeList(paramVo);
                treeVo.setChildren(treeList);

                setCdChcIdInsertChildern(treeList, paramVo);
            }
        }
    }

    @Override
    public List<TreeVO> selectCodeChoiceUpdateTreeList(CodeChoiceVO codeChoiceVo) {

        List<TreeVO> dataList = opCodeDao.selectCodeChoiceUpdateTreeList(codeChoiceVo);
        // 자식 메뉴 정보를 모두 추가한다.
        setCdChcIdUpdateChildern(dataList, codeChoiceVo.getString("q_cdChcId"));

        return dataList;
    }

    /**
     * 수정페이지용 트리 자식 목록을 추가한다.
     * 
     * @param dataList
     */
    private void setCdChcIdUpdateChildern(List<TreeVO> dataList, String cdChcId) {

        if(Validate.isNotEmpty(dataList)) {
            CodeChoiceVO codeChoiceVo = new CodeChoiceVO();
            List<TreeVO> treeList;
            for(TreeVO treeVo : dataList) {
                codeChoiceVo.addParam("q_hghrkCdId", treeVo.getBaseKey());
                codeChoiceVo.addParam("q_cdId", treeVo.getKey());
                codeChoiceVo.addParam("q_cdChcId", cdChcId);
                treeList = opCodeDao.selectCodeChoiceUpdateTreeList(codeChoiceVo);
                treeVo.setChildren(treeList);

                setCdChcIdUpdateChildern(treeList, cdChcId);
            }
        }
    }

    @Override
    public Integer insertCodeChoice(CodeChoiceVO codeChoiceVo) {

        Integer insertCnt = 0;

        CodeChoiceVO dataVo = new CodeChoiceVO();
        dataVo.setHghrkCdId(codeChoiceVo.getHghrkCdId());
        dataVo.setCdChcId(codeChoiceVo.getCdChcId());
        dataVo.setRgtrId(codeChoiceVo.getRgtrId());
        dataVo.setMdfrId(codeChoiceVo.getRgtrId());

        if(Validate.isNotEmpty(codeChoiceVo.getUpCdIds())) {
            String[] upCdIds = codeChoiceVo.getUpCdIds();
            String[] lwrkCdIds = codeChoiceVo.getLwrkCdIds();

            int codeCnt = upCdIds.length;
            for(int i = 0 ; i < codeCnt ; i++) {
                dataVo.setCdId(upCdIds[i]);
                dataVo.setLwrkCdId(lwrkCdIds[i]);

                insertCnt += opCodeDao.insertCodeChoice(dataVo);

                // 이력 등록
                dataVo.setLogType(LOG_TYPE.INSERT.getCdId());
                opCodeHistoryService.insertCodeChoiceHistory(dataVo);
            }
        }

        return insertCnt;
    }

    @Override
    public Integer updateCodeChoice(CodeChoiceVO codeChoiceVo) {

        int chgCnt = 0;

        // 추가된 것과 삭제 된 것을 구분하여 관리
        CodeChoiceVO dataVo = new CodeChoiceVO();
        dataVo.setHghrkCdId(codeChoiceVo.getString("q_hghrkCdId"));
        dataVo.setCdChcId(codeChoiceVo.getString("q_cdChcId"));
        dataVo.setRgtrId(codeChoiceVo.getRgtrId());
        dataVo.setMdfrId(codeChoiceVo.getRgtrId());

        dataVo.addParam("q_hghrkCdId", codeChoiceVo.getString("q_hghrkCdId"));
        dataVo.addParam("q_cdChcId", codeChoiceVo.getString("q_cdChcId"));

        List<String> lcList = new ArrayList<String>();
        List<String> ucList = new ArrayList<String>();
        List<String> plcList = new ArrayList<String>();
        List<String> pucList = new ArrayList<String>();

        // 등록해야 할 코드목록
        if(Validate.isNotEmpty(codeChoiceVo.getLwrkCdIds())) {
            lcList = Arrays.asList(codeChoiceVo.getLwrkCdIds());
        }
        // 등록해야 할 코드의 부모 코드
        if(Validate.isNotEmpty(codeChoiceVo.getUpCdIds())) {
            ucList = Arrays.asList(codeChoiceVo.getUpCdIds());
        }
        // 이전에 등록되어 있던 코드 목록
        if(Validate.isNotEmpty(codeChoiceVo.getPrvLwrkCdIds())) {
            plcList = Arrays.asList(codeChoiceVo.getPrvLwrkCdIds());
            pucList = Arrays.asList(codeChoiceVo.getPrvUpCdIds());
        }

        // 추가 대상
        if(Validate.isNotEmpty(lcList)) {
            int lcCnt = lcList.size();
            for(int i = 0 ; i < lcCnt ; i++) {
                String lwrkCdId = lcList.get(i);
                String cdId = ucList.get(i);
                if(!plcList.contains(lwrkCdId)) {
                    dataVo.setCdId(cdId);
                    dataVo.setLwrkCdId(lwrkCdId);
                    chgCnt += opCodeDao.insertCodeChoice(dataVo);

                    // 이력 등록
                    dataVo.setLogType(LOG_TYPE.INSERT.getCdId());
                    opCodeHistoryService.insertCodeChoiceHistory(dataVo);
                }
            }
        }
        // 삭제 대상
        if(Validate.isNotEmpty(plcList)) {
            int plcCnt = plcList.size();
            for(int i = 0 ; i < plcCnt ; i++) {
                String lwrkCdId = plcList.get(i);
                String cdId = pucList.get(i);
                if(!lcList.contains(lwrkCdId)) {
                    dataVo.addParam("q_lwrkCdId", lwrkCdId);
                    dataVo.addParam("q_cdId", cdId);
                    chgCnt += opCodeDao.deleteCodeChoice(dataVo);

                    // 이력 등록
                    dataVo.setCdId(cdId);
                    dataVo.setLwrkCdId(lwrkCdId);
                    dataVo.setLogType(LOG_TYPE.DELETE.getCdId());
                    opCodeHistoryService.insertCodeChoiceHistory(dataVo);
                }
            }
        }

        return chgCnt;
    }

    @Override
    public Integer deleteCodeChoice(CodeChoiceVO codeChoiceVo) {

        // 이력등록
        List<CodeChoiceVO> choiceList = opCodeDao.selectCodeChoiceList(codeChoiceVo);
        for(CodeChoiceVO choiceVo : choiceList) {
            // 이력 등록
            choiceVo.setLogType(LOG_TYPE.DELETE.getCdId());
            choiceVo.setMdfrId(codeChoiceVo.getMdfrId());
            opCodeHistoryService.insertCodeChoiceHistory(choiceVo);
        }

        // 코드선택목록 삭제
        Integer delCnt = opCodeDao.deleteCodeChoice(codeChoiceVo);

        return delCnt;
    }

}
