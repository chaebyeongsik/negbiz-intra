/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.multilang.MultilangMapper;
import zesinc.intra.multilang.MultilangService;
import zesinc.intra.multilang.domain.MultilangGrpVO;
import zesinc.intra.multilang.domain.MultilangVO;
import zesinc.web.utils.MessageUtil;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 다국어 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opMultilangService")
public class MultilangServiceImpl extends EgovAbstractServiceImpl implements MultilangService {

    @Resource(name = "opMultilangDao")
    private MultilangMapper opMultilangDao;
    private static String HIGH_MULTILANG_CD = Config.getString("webapp-config.defaultCode.highCommonCd");

    @Override
    public Integer selectDplctChckMultilang(MultilangVO multilangVo) {

        String upMtlngCdNm = multilangVo.getString("q_upMtlngCdNm");
        String mtlngUnqCdNm = multilangVo.getString("q_mtlngUnqCdNm");
        if(Validate.isEmpty(upMtlngCdNm) || Validate.isEmpty(mtlngUnqCdNm)) {
            return -1;
        }
        // 최상위 더미 키 인경우는 제외시킴
        String mtlngCdNm;
        if(upMtlngCdNm.equals(HIGH_MULTILANG_CD)) {
            mtlngCdNm = mtlngUnqCdNm;
        } else {
            mtlngCdNm = upMtlngCdNm + "." + mtlngUnqCdNm;
        }

        multilangVo.addParam("q_mtlngCdNm", mtlngCdNm);

        return opMultilangDao.selectDplctChckMultilang(multilangVo);
    }

    @Override
    public MultilangVO selectMultilang(MultilangVO multilangVo, Boolean isUpdate) {

        MultilangVO dataVo = opMultilangDao.selectMultilang(multilangVo);

        MultilangGrpVO dataGrpVo = new MultilangGrpVO();
        dataGrpVo.setParamMap(multilangVo.getParamMap());

        if(Validate.isNotEmpty(dataVo) && isUpdate) {
            dataVo.setMessageList(opMultilangDao.selectMultilangGrpList(dataGrpVo));
        }

        return dataVo;
    }

    @Override
    public List<TreeVO> selectMultilangTreeList(MultilangVO multilangVo) {

        return opMultilangDao.selectMultilangTreeList(multilangVo);
    }

    @Override
    public String insertMultilang(MultilangVO multilangVo) {
        
        // 공백제거
        String mtlngUnqCdNm = multilangVo.getMtlngUnqCdNm().trim();
        // 부모 코드 정보
        MultilangVO dataVo = selectMultilang(multilangVo, Boolean.FALSE);

        /* 상위 코드가 dummy 최상위 코드인 경우는 키에서 제외 */
        String mtlngCdNm;
        if(dataVo.getMtlngUnqCdNm().equals(HIGH_MULTILANG_CD)) {
            mtlngCdNm = mtlngUnqCdNm;
        } else {
            mtlngCdNm = dataVo.getMtlngCdNm() + "." + mtlngUnqCdNm;
        }
        multilangVo.setUpMtlngCdNm(dataVo.getMtlngCdNm());
        multilangVo.setMtlngCdNm(mtlngCdNm);
        multilangVo.setMtlngUnqCdNm(mtlngUnqCdNm);

        opMultilangDao.insertMultilang(multilangVo);

        // 메시지 그룹 삭제 후 등록
        insertMultilangGrps(multilangVo);

        return mtlngCdNm;
    }

    @Override
    public Integer updateMultilang(MultilangVO multilangVo) throws Exception {

        Integer updateCnt = opMultilangDao.updateMultilang(multilangVo);
        if(updateCnt > 1) {
            throw new Exception(MessageUtil.getMessage("common.processFail"));
        }

        // 메시지 그룹 삭제 후 등록
        insertMultilangGrps(multilangVo);

        return updateCnt;
    }

    @Override
    public Integer deleteMultilang(MultilangVO multilangVo) throws Exception {

        // 메시지 그룹 삭제
        MultilangGrpVO multilangGrpVo = new MultilangGrpVO();
        multilangGrpVo.setParamMap(multilangVo.getParamMap());
        opMultilangDao.deleteMultilangGrp(multilangGrpVo);

        // 메시지 삭제
        Integer delCnt = opMultilangDao.deleteMultilang(multilangVo);
        if(delCnt > 1) {
            throw new Exception(MessageUtil.getMessage("common.processFail"));
        }
        return delCnt;
    }

    @Override
    public Integer insertMultilangGrps(MultilangVO multilangVo) {
        Integer insertCnt = 0;
        String mtlngCdNm = multilangVo.getMtlngCdNm();

        // 등록과 수정을 분리하여 파라미터 조정
        if(Validate.isEmpty(mtlngCdNm)) {
            // 수정화면인 경우
            mtlngCdNm = (String) multilangVo.getParam("q_mtlngCdNm");
        } else {
            // 등록화면인 경우
            multilangVo.addParam("q_mtlngCdNm", mtlngCdNm);
        }
        String[] langCdIds = multilangVo.getLangCdIds();
        String[] multiLangMsgCns = multilangVo.getMultiLangMsgCns();

        // 기존 그룹 삭제
        MultilangGrpVO multilangGrpVo = new MultilangGrpVO();
        multilangGrpVo.setParamMap(multilangVo.getParamMap());
        opMultilangDao.deleteMultilangGrp(multilangGrpVo);

        // 신규 등록
        for(int i = 0 ; i < langCdIds.length ; i++) {
            if(Validate.isNotEmpty(multiLangMsgCns[i])) {
                multilangGrpVo = new MultilangGrpVO();
                multilangGrpVo.setMtlngCdNm(mtlngCdNm);
                multilangGrpVo.setLangCdId(langCdIds[i]);
                multilangGrpVo.setMultiLangMsgCn(multiLangMsgCns[i]);

                insertCnt += opMultilangDao.insertMultilangGrp(multilangGrpVo);
            }
        }

        return insertCnt;
    }

    @Override
    public List<TreeVO> selectConnectMultilangTreeList(MultilangVO multilangVo) {

        return opMultilangDao.selectMultilangTreeList(multilangVo);
    }

}
