/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.web.utils;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 금직어 포함여부 UTIL Test
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 29.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class PrhibtTest extends TestCase {

    private List<String> prhibtWrdList;
    private String prhibtWord = "바보,뵹신,쪼다";
    private String contents;
    private String defaultMaskedContents;
    private String assignMaskedContents;

    public PrhibtTest() {
        this.prhibtWrdList = Arrays.asList(prhibtWord.split(","));
        this.contents = "새누리당 김무성 대표는 28일 저녁 동아일보 기자와 만난 자리에서 뵹신"
            + "'예전에 나와 유승민, (대통령까지) 셋이 제일 가까웠는데…'라며 연신 한숨을 내쉬었다. 바보"
            + "유 원내대표가 박근혜 대통령의 사실상 불신임 통보에 거듭 사과했지만 청와대의 싸늘한 시선은 여전하다."
            + "중재역을 자임하고 있지만 유 원내대표가 완강히 버틸 경우 별 뾰족한 해법이 없다는 점에 대한 "
            + "답답함을 토로한 것이기도 하다. 쪼다";
        this.defaultMaskedContents = "새누리당 김무성 대표는 28일 저녁 동아일보 기자와 만난 자리에서 **"
            + "'예전에 나와 유승민, (대통령까지) 셋이 제일 가까웠는데…'라며 연신 한숨을 내쉬었다. **"
            + "유 원내대표가 박근혜 대통령의 사실상 불신임 통보에 거듭 사과했지만 청와대의 싸늘한 시선은 여전하다."
            + "중재역을 자임하고 있지만 유 원내대표가 완강히 버틸 경우 별 뾰족한 해법이 없다는 점에 대한 "
            + "답답함을 토로한 것이기도 하다. **";
        this.assignMaskedContents = "새누리당 김무성 대표는 28일 저녁 동아일보 기자와 만난 자리에서 $$"
            + "'예전에 나와 유승민, (대통령까지) 셋이 제일 가까웠는데…'라며 연신 한숨을 내쉬었다. $$"
            + "유 원내대표가 박근혜 대통령의 사실상 불신임 통보에 거듭 사과했지만 청와대의 싸늘한 시선은 여전하다."
            + "중재역을 자임하고 있지만 유 원내대표가 완강히 버틸 경우 별 뾰족한 해법이 없다는 점에 대한 "
            + "답답함을 토로한 것이기도 하다. $$";
    }

    /**
     * 금지단어 포함 확인
     */
    @Test
    public void testContainsPrhibtWrd() {
        Boolean containsWrd = PrhibtUtil.containsPrhibtWrd(prhibtWrdList, contents);
        assertTrue(containsWrd);
    }

    /**
     * 금지단어를 기본 문자열로 치환 (*) 5자리
     */
    @Test
    public void testDefaultMaskWrd() {
        String maskedWrd = PrhibtUtil.maskPrhibtWrd(prhibtWrdList, contents);
        assertEquals(maskedWrd, defaultMaskedContents);
    }

    /**
     * 금지단어를 지정된 문자열로 치환
     */
    @Test
    public void testAssignMaskWrd() {
        String maskedWrd = PrhibtUtil.maskPrhibtWrd(prhibtWrdList, contents, '$');
        assertEquals(maskedWrd, assignMaskedContents);
    }
}
