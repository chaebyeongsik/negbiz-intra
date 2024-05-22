/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.core.utils.StringUtil;
import zesinc.intra.schdul.SchdulMapper;
import zesinc.intra.schdul.SchdulService;
import zesinc.intra.schdul.domain.CalendarSchdulVO;
import zesinc.intra.schdul.domain.SchdulDetailVO;
import zesinc.intra.schdul.domain.SchdulVO;
import zesinc.intra.schdul.support.SchdulReptitTy;
import zesinc.intra.schdul.support.SchdulSupport;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.ReptitDateUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 일정 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opSchdulService")
public class SchdulServiceImpl extends EgovAbstractServiceImpl implements SchdulService {

    @Resource(name = "opSchdulDao")
    private SchdulMapper opSchdulDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public SchdulVO selectSchdul(SchdulVO schdulVo) {

        SchdulVO dataVo = opSchdulDao.selectSchdul(schdulVo);
        dataVo.setBgngHr(SchdulSupport.getFormatTime(dataVo.getBgngHr()));
        dataVo.setEndHr(SchdulSupport.getFormatTime(dataVo.getEndHr()));

        // 첨부파일
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
        }

        return dataVo;
    }

    @Override
    public Pager<SchdulVO> selectSchdulPageList(SchdulVO schdulVo) {

        List<SchdulVO> dataList = opSchdulDao.selectSchdulList(schdulVo);
        Integer totalNum = opSchdulDao.selectSchdulListCount(schdulVo);

        for(SchdulVO dataVo : dataList) {
            dataVo.setBgngHr(SchdulSupport.getFormatTime(dataVo.getBgngHr()));
            dataVo.setEndHr(SchdulSupport.getFormatTime(dataVo.getEndHr()));
        }

        return new Pager<SchdulVO>(dataList, schdulVo, totalNum);
    }

    @Override
    public List<List<CalendarSchdulVO>> selectSchdulCalList(SchdulVO schdulVo) {

        String schdulDate = schdulVo.getString("q_schdulDate");
        // Calendar 객체에서 해당 월을 일자를 모두 구한다.
        Calendar cal = Calendar.getInstance();
        String firstMonthDay = ReptitDateUtil.getMinMonthDate(schdulDate, cal);
        String lastMonthDay = ReptitDateUtil.getMaxMonthDate(schdulDate, cal);
        String minWeekDay = ReptitDateUtil.getMinWeekDate(firstMonthDay, cal);
        String maxWeekDay = ReptitDateUtil.getMaxWeekDate(lastMonthDay, cal);

        List<String> calendarDays = ReptitDateUtil.getRangeDateList(minWeekDay, maxWeekDay);

        // 사용자단의 경우 공개인 것만
        // schdulVo.addParam("q_rlsYn", "Y");
        schdulVo.addParam("q_bgngYmd", firstMonthDay);
        schdulVo.addParam("q_endYmd", lastMonthDay);
        List<SchdulVO> schdulDetailList = opSchdulDao.selectSchdulDetailList(schdulVo);

        // 전체 목록
        List<List<CalendarSchdulVO>> calList = new ArrayList<List<CalendarSchdulVO>>();
        // 주간 목록
        List<CalendarSchdulVO> weekList = new ArrayList<CalendarSchdulVO>();
        // 일일 일정 목록
        List<SchdulVO> dayList = null;
        // 날짜와 해당일 일정목록 정보 VO
        CalendarSchdulVO csVo = null;

        int dayCnt = calendarDays.size();
        for(int i = 0 ; i < dayCnt ; i++) {
            String calendarDay = calendarDays.get(i);

            // 일요일 확인자
            boolean isSndayRsvtNoday = ((i % 7) == 0);
            if(isSndayRsvtNoday) {
                // 한주 목록을 전체 목록에 추가 하고, 한주 목록을 새로 시작한다.
                calList.add(weekList);
                weekList = new ArrayList<CalendarSchdulVO>();
            }

            /*
             * 현재 날짜의 일정만 담는다.
             * 성능이유로 쿼리를 매일 날리는 것 보다, 한번에 가져와 비교하며 담는다.
             */
            dayList = new ArrayList<SchdulVO>();
            for(SchdulVO detailVo : schdulDetailList) {
                if(detailVo.getBgngYmd().equals(calendarDay)) {
                    dayList.add(detailVo);
                }
            }

            Boolean isHoliday = (ReptitDateUtil.getDayWeek(calendarDay, cal) == 1 ? Boolean.TRUE : Boolean.FALSE);
            Boolean isSaturDay = (ReptitDateUtil.getDayWeek(calendarDay, cal) == 7 ? Boolean.TRUE : Boolean.FALSE);
            String[] arrDate = calendarDay.split("-");

            csVo = new CalendarSchdulVO();
            csVo.setYear(arrDate[0]);
            csVo.setMonth(arrDate[1]);
            csVo.setDay(arrDate[2]);
            csVo.setIsHoliday(isHoliday);
            csVo.setIsSaturDay(isSaturDay);

            csVo.setSchdulList(dayList);

            weekList.add(csVo);
        }
        calList.add(weekList);

        return calList;
    }

    @Override
    public Integer insertSchdul(SchdulVO schdulVo) {

        // 첨부파일
        if(Validate.isNotEmpty(schdulVo.getFileList())) {
            Integer fileSn = opFileService.insertFileList(schdulVo.getFileList());
            schdulVo.setFileSn(fileSn);
        }

        // 상세 일정을 구하기 위한 일자 값 변수
        String bgngYmd = schdulVo.getBgngYmd();
        String endYmd = schdulVo.getEndYmd();
        String rpttEndYmd = schdulVo.getRpttEndYmd();

        // 미사용 구분자 삭제
        schdulVo.setBgngYmd(StringUtil.replace(schdulVo.getBgngYmd(), "-", ""));
        schdulVo.setEndYmd(StringUtil.replace(schdulVo.getEndYmd(), "-", ""));
        schdulVo.setRpttEndYmd(StringUtil.replace(schdulVo.getRpttEndYmd(), "-", ""));
        schdulVo.setBgngHr(StringUtil.replace(schdulVo.getBgngHr(), ":", ""));
        schdulVo.setEndHr(StringUtil.replace(schdulVo.getEndHr(), ":", ""));

        Integer insertCnt = opSchdulDao.insertSchdul(schdulVo);

        // 상세 일정 등록 파라미터 객체 생성
        SchdulDetailVO detailVo = new SchdulDetailVO();
        detailVo.setParamMap(schdulVo.getParamMap());
        detailVo.setRegSn(schdulVo.getRegSn());
        detailVo.setSeCdId(schdulVo.getSeCdId());
        detailVo.setBgngHr(schdulVo.getBgngHr());
        detailVo.setEndHr(schdulVo.getEndHr());

        List<String> dayList = null;
        // 반복 설정시와 아닌 경우를 구분하여 날짜를 구한다.
        if(schdulVo.getRpttYn().equals("Y")) {
            /*
             * 값이 있는 것만 일요일부터 토요일까지 순서대로 담는다.
             * 처리 순서를 사용하므로 반드시 순서대로 담겨야 함.
             */
            List<Integer> dayOfWeek = new ArrayList<Integer>();
            if(Validate.isNotEmpty(schdulVo.getSndayRsvtNo())) {
                dayOfWeek.add(schdulVo.getSndayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getMndayRsvtNo())) {
                dayOfWeek.add(schdulVo.getMndayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getTsdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getTsdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getWddayRsvtNo())) {
                dayOfWeek.add(schdulVo.getWddayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getTrdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getTrdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getFrdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getFrdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getStdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getStdayRsvtNo());
            }
            dayList = SchdulSupport.getSchdulDateList(bgngYmd, endYmd, rpttEndYmd, dayOfWeek, SchdulReptitTy.valueOf(schdulVo.getRpttTypeCd()));
        } else {
            dayList = SchdulSupport.getSchdulDateList(bgngYmd, null, endYmd, null, null);
        }

        // 위에서 구한 날짜 목록을 상세 테이블에 입력
        for(String day : dayList) {
            detailVo.setBgngYmd(day);
            detailVo.setEndYmd(day);
            opSchdulDao.insertSchdulDetail(detailVo);
        }

        return insertCnt;
    }

    @Override
    public Integer updateSchdul(SchdulVO schdulVo) {

        SchdulVO dataVo = selectSchdul(schdulVo);
        // 첨부파일
        if(Validate.isNotEmpty(schdulVo.getFileIds())) {
            opFileService.deleteFiles(dataVo.getFileSn(), schdulVo.getFileIds());
        }
        if(Validate.isNotEmpty(schdulVo.getFileList())) {
            Integer fileSn = opFileService.insertFileList(dataVo.getFileSn(), schdulVo.getFileList());
            schdulVo.setFileSn(fileSn);
        }

        // 상세 일정을 구하기 위한 일자 값 변수
        String bgngYmd = schdulVo.getBgngYmd();
        String endYmd = schdulVo.getEndYmd();
        String rpttEndYmd = schdulVo.getRpttEndYmd();

        // 미사용 구분자 삭제
        schdulVo.setBgngYmd(StringUtil.replace(schdulVo.getBgngYmd(), "-", ""));
        schdulVo.setEndYmd(StringUtil.replace(schdulVo.getEndYmd(), "-", ""));
        schdulVo.setRpttEndYmd(StringUtil.replace(schdulVo.getRpttEndYmd(), "-", ""));
        schdulVo.setBgngHr(StringUtil.replace(schdulVo.getBgngHr(), ":", ""));
        schdulVo.setEndHr(StringUtil.replace(schdulVo.getEndHr(), ":", ""));

        // 상세 일정 등록 파라미터 객체 생성
        SchdulDetailVO detailVo = new SchdulDetailVO();
        detailVo.setParamMap(schdulVo.getParamMap());
        detailVo.setRegSn(schdulVo.getInteger("q_regSn"));
        detailVo.setSeCdId(schdulVo.getSeCdId());
        detailVo.setBgngHr(schdulVo.getBgngHr());
        detailVo.setEndHr(schdulVo.getEndHr());
        
        
        // 일정구분에 따른 정보 초기화
        if("program".equals(schdulVo.getSeCdId())) { // 일정
            schdulVo.setHldySeCdId("");
        } else if("restde".equals(schdulVo.getSeCdId())) {  // 휴일
            schdulVo.setSchdlSeCdId("");
            schdulVo.setPlcCdId("");
        }

        opSchdulDao.deleteSchdulDetail(detailVo);

        List<String> dayList = null;
        // 반복 설정시와 아닌 경우를 구분하여 날짜를 구한다.
        if(schdulVo.getRpttYn().equals("Y")) {
            /*
             * 값이 있는 것만 일요일부터 토요일까지 순서대로 담는다.
             * 처리 순서를 사용하므로 반드시 순서대로 담겨야 함.
             */
            List<Integer> dayOfWeek = new ArrayList<Integer>();
            if(Validate.isNotEmpty(schdulVo.getSndayRsvtNo())) {
                dayOfWeek.add(schdulVo.getSndayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getMndayRsvtNo())) {
                dayOfWeek.add(schdulVo.getMndayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getTsdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getTsdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getWddayRsvtNo())) {
                dayOfWeek.add(schdulVo.getWddayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getTrdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getTrdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getFrdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getFrdayRsvtNo());
            }
            if(Validate.isNotEmpty(schdulVo.getStdayRsvtNo())) {
                dayOfWeek.add(schdulVo.getStdayRsvtNo());
            }
            dayList = SchdulSupport.getSchdulDateList(bgngYmd, endYmd, rpttEndYmd, dayOfWeek, SchdulReptitTy.valueOf(schdulVo.getRpttTypeCd()));
        } else {
            dayList = SchdulSupport.getSchdulDateList(bgngYmd, null, endYmd, null, null);
        }

        // 위에서 구한 날짜 목록을 상세 테이블에 입력
        for(String day : dayList) {
            detailVo.setBgngYmd(day);
            detailVo.setEndYmd(day);
            opSchdulDao.insertSchdulDetail(detailVo);
        }

        return opSchdulDao.updateSchdul(schdulVo);
    }

    @Override
    public Integer deleteSchdul(SchdulVO schdulVo) throws Exception {

        // 원본 삭제
        SchdulVO dataVo = selectSchdul(schdulVo);
        dataVo.setParamMap(schdulVo.getParamMap());

        SchdulDetailVO schdulDetailVo = new SchdulDetailVO();
        schdulDetailVo.setParamMap(schdulVo.getParamMap());
        opSchdulDao.deleteSchdulDetail(schdulDetailVo);

        Integer delCnt = opSchdulDao.deleteSchdul(dataVo);

        if(delCnt > 1) {
            throw new Exception("삭제 건수가 일치하지 않습니다.");
        }

        // 첨부파일 삭제
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            opFileService.deleteFile(dataVo.getFileSn());
        }

        return delCnt;
    }

    @Override
    public List<SchdulVO> selectSchdulDetailList(SchdulVO schdulVo) {

        return opSchdulDao.selectSchdulDetailList(schdulVo);
    }

}
