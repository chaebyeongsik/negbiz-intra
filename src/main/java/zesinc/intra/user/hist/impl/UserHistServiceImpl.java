package zesinc.intra.user.hist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.user.hist.UserHistDao;
import zesinc.intra.user.hist.UserHistService;
import zesinc.intra.user.hist.domain.UserHistCheckResultVO;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.intra.user.support.UserConsts;
import zesinc.login.LoginMapper;
import zesinc.login.domain.LoginVO;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자이력 정보 서비스 구현 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 22.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserHistService")
public class UserHistServiceImpl extends EgovAbstractServiceImpl implements UserHistService {

    @Resource
    private UserHistDao opUserHistDao;

    @Resource(name = "opLoginDao")
    LoginMapper opLoginDao;

    @Override
    public Integer insertUserLog(UserHistVO userHistVo) {
        return opUserHistDao.insertUserLog(userHistVo);
    }

    @Override
    public boolean selectCheckMngrPasswordMatch(LoginVO loginVo, UserHistVO userHistVo) {
        if(Validate.isNotEmpty(loginVo) && Validate.isNotEmpty(loginVo.getPicId())) {
            LoginVO dataVo = opLoginDao.selectLogin(loginVo);
            String passwd = userHistVo.getPicPswd();// 화면에서 입력받은 비밀번호
            //Boolean matcheAt = PasswdUtil.matche(passwd, dataVo.getPicPswd());
            Boolean matcheAt = PasswdUtil.matches(passwd, dataVo.getPicPswd());
            if(matcheAt) {
                return true;
            }
        }
        return false;
    }

    /**
     * 메뉴 접근 유효성 체크
     * (사용자정보 보호와 관련하여 사유를 입력한 사람에게만 접근권한을 부여)
     *
     * @param request
     * @param userManageVo
     * @param menuNm
     * @return
     */
    @Override
    public boolean accessValidate(HttpServletRequest request) {
        String sessionValidate = (String) OpHelper.getSession(request, UserConsts.USER_INFO_LOG_VALIDATE);

        if(Validate.isNull(sessionValidate)) {
            return false;
        }

        return true;
    }

    @Override
    public void removeValidate(HttpServletRequest request) {
        OpHelper.removeSession(request, UserConsts.USER_INFO_LOG_VALIDATE);
    }

    // public boolean accessValidate(HttpServletRequest request, UserManageVO userManageVo, String
    // menuNm) {
    // String sessionValidate = (String) OpHelper.getSession(request,
    // UserConsts.USER_INFO_LOG_VALIDATE);
    //
    // if(Validate.isNull(sessionValidate)) {
    // OpHelper.setSession(request, UserConsts.SELECT_USER_ID, userManageVo.getUserId());
    // OpHelper.setSession(request, UserConsts.USER_INFO_ACCESS_MENU_URL, request.getRequestURI());
    // OpHelper.setSession(request, UserConsts.SELECT_MENU_NM, menuNm);
    // return false;
    // }
    //
    // String userId = (String) OpHelper.getSession(request, UserConsts.SELECT_USER_ID);
    // userManageVo.setUserId(userId);
    // OpHelper.removeSession(request, UserConsts.USER_INFO_LOG_VALIDATE);
    //
    // return true;
    // }

    @Override
    public Integer selectListCount(String queryId, UserHistVO userHistVo) {

        return opUserHistDao.selectListCount(queryId, userHistVo);
    }

    @Override
    public String selectUserId(String queryId, UserHistVO userHistVo) {

        return opUserHistDao.selectUserId(queryId, userHistVo);
    }

    @Override
    public UserHistCheckResultVO checkSearchCnd(HttpServletRequest request, Map<String, Object> nowSearchCnd) {

        UserHistCheckResultVO userHistCheckResultVo = new UserHistCheckResultVO();
        boolean checkSearchCnd = true;

        // 여러 메뉴일 경우 세션 앞쪽에 메뉴명까지 붙여야 함..
        @SuppressWarnings("unchecked")
        Map<String, Object> beforeSearchCnd = (HashMap<String, Object>) OpHelper.getSession(request, UserConsts.USER_INFO_SEARCH_CND);

        Set<String> keys = nowSearchCnd.keySet();

        // 검색조건이 완전하게 비어있을 경우-유효하지않다
        boolean totallyEmpty = true;

        for(String key : keys) {
            if(key.contains("q_currPage") || key.contains("q_pagePerPage") || key.contains("q_pagingEndNum")
                || key.contains("q_pagingStartNum") || key.contains("q_rowPerPage")) {

            } else {
                String nowKey = (String) nowSearchCnd.get(key);

                if(Validate.isNotEmpty(nowKey)) {
                    totallyEmpty = false;

                    if(beforeSearchCnd != null) {
                        String beforeKey = (String) beforeSearchCnd.get(key);

                        nowKey = (nowKey == null) ? "" : nowKey;
                        beforeKey = (beforeKey == null) ? "" : beforeKey;

                        if(!nowKey.equals(beforeKey)) {
                            checkSearchCnd = false;
                        }
                    }
                }
            }
        }

        userHistCheckResultVo.setEquals(checkSearchCnd);
        userHistCheckResultVo.setEmpty(totallyEmpty);

        return userHistCheckResultVo;
    }

    @Override
    public Pager<UserHistVO> selectUserLogList(UserHistVO userHistVo) {

        List<UserHistVO> dataList = opUserHistDao.selectUserLogList(userHistVo);
        Integer totalNum = opUserHistDao.selectUserLogListCount(userHistVo);

        Pager<UserHistVO> pageVo = new Pager<UserHistVO>(dataList, userHistVo, totalNum);

        return pageVo;
    }

    @Override
    public List<UserHistVO> selectMenuNmList() {

        return opUserHistDao.selectMenuNmList();
    }

}
