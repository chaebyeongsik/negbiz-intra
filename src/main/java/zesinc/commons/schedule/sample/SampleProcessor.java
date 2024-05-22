/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.commons.schedule.sample;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zesinc.commons.schedule.Processor;

/**
 * 주기적으로 숫자를 출력하는 셈플
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 28.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class SampleProcessor implements Processor {

    Logger logger = LoggerFactory.getLogger(SampleProcessor.class);

    AtomicInteger atInt = new AtomicInteger();

    /**
     * 설정에 지정된 시간마다 출력
     */
    @Override
    public void process() {
        int cnt = atInt.incrementAndGet();

        logger.debug("{} run is  {} times", this.getClass().getName(), cnt);
    }

}
