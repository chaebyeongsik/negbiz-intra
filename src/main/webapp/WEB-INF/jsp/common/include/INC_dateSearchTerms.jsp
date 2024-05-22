<%@ page contentType="text/html;charset=UTF-8"%>
                <input type="hidden" id="q_selectDateColor" name="q_selectDateColor" value="${paramMap.q_selectDateColor}"/>
                <input type="hidden" id="q_dateSetYn" name="q_dateSetYn" value="${paramMap.q_dateSetYn}"/>
                <div class="block">
                    <div id="dateSet1" class="btn-group btn-group-xxs">
                        <button type="button" id="0"   class="btn btn-default dateIem">당일</button>
                        <button type="button" id="1D"  class="btn btn-default dateIem">전일</button>
                        <button type="button" id="7D"  class="btn btn-default dateIem">7일</button>
                        <button type="button" id="10D" class="btn btn-default dateIem">10일</button>
                        <button type="button" id="15D" class="btn btn-default dateIem">15일</button>
                        <button type="button" id="1M"  class="btn btn-default dateIem">1개월</button>
                        <button type="button" id="3M"  class="btn btn-default dateIem">3개월</button>
                        <button type="button" id="6M"  class="btn btn-default dateIem">6개월</button>
                        <button type="button" id="ALL" class="btn btn-default dateIem">전체</button>
                        <button type="button" id="DATESET" onclick="opDateSet()" class="btn btn-primary">기간지정</button>
                    </div>
                </div>
                <div class="block">
                    <div class="form-group" id="dateSet">
                        <label class="control-label"> 검색일자</label>
                        <label for="q_startDt" class="sr-only"> 시작일</label>
                        <input type="text" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" onkeydown="opDateAuto(this);" class="datepicker form-control input-sm from-date" placeholder="시작일" />
                        ~
                        <label for="q_endDt" class="sr-only"> 종료일</label>
                        <input type="text" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" onkeydown="opDateAuto(this);" class="datepicker form-control input-sm to-date" placeholder="종료일" />
                    </div>
                </div>