/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang.domain;

import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 다국어그룹 정보 VO 클레스
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
public class MultilangGrpVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -6380825525111147642L;
    /** 언어코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String langCdId;

    /** 언어코드명 */
    private String langCodeNm;

    /** 다국어코드 */
    @Required
    @RangeLength(min = 2, max = 300)
    private String mtlngCdNm;

    /** 다국어코드명 */
    private String multiLangCodeNm;

    /** 메시지 */
    @Required
    @RangeLength(min = 2, max = 100)
    private String multiLangMsgCn;

    /**
     * 언어코드을 설정
     * 
     * @param langCdId을(를) String langCdId로 설정
     */
    public void setLangCdId(String langCdId) {
        this.langCdId = langCdId;
    }

    /**
     * 언어코드을 반환
     * 
     * @return String langCdId
     */
    public String getLangCdId() {
        return langCdId;
    }

    /**
     * 언어코드명을 설정
     * 
     * @param langCodeNm을(를) String langCodeNm로 설정
     */
    public void setLangCodeNm(String langCodeNm) {
        this.langCodeNm = langCodeNm;
    }

    /**
     * 언어코드명을 반환
     * 
     * @return String langCodeNm
     */
    public String getLangCodeNm() {
        return langCodeNm;
    }

    /**
     * 다국어코드을 설정
     * 
     * @param mtlngCdNm을(를) String mtlngCdNm로 설정
     */
    public void setMtlngCdNm(String mtlngCdNm) {
        this.mtlngCdNm = mtlngCdNm;
    }

    /**
     * 다국어코드을 반환
     * 
     * @return String mtlngCdNm
     */
    public String getMtlngCdNm() {
        return mtlngCdNm;
    }

    /**
     * 다국어코드명을 설정
     * 
     * @param multiLangCodeNm을(를) String multiLangCodeNm로 설정
     */
    public void setMultiLangCodeNm(String multiLangCodeNm) {
        this.multiLangCodeNm = multiLangCodeNm;
    }

    /**
     * 다국어코드명을 반환
     * 
     * @return String multiLangCodeNm
     */
    public String getMultiLangCodeNm() {
        return multiLangCodeNm;
    }

    /**
     * 메시지을 설정
     * 
     * @param multiLangMsgCn을(를) String multiLangMsgCn로 설정
     */
    public void setMultiLangMsgCn(String multiLangMsgCn) {
        this.multiLangMsgCn = multiLangMsgCn;
    }

    /**
     * 메시지을 반환
     * 
     * @return String multiLangMsgCn
     */
    public String getMultiLangMsgCn() {
        return multiLangMsgCn;
    }

}
