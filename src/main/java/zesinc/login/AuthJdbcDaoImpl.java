/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import zesinc.core.config.Config;
import zesinc.core.crypto.Crypto;
import zesinc.core.crypto.CryptoFactory;
import zesinc.login.domain.LoginVO;
import zesinc.web.utils.CryptoUtil;

/**
 * Springframework security 권한 조회용 Dao 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 24.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class AuthJdbcDaoImpl extends JdbcDaoImpl implements UserDetailsService {

    private static String CHANGE_PSWD_TERM = Config.getString("passwd-config.changePasswordTerm", "90");

    private String authorGroupCodeQuery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            username = CryptoUtil.decrypt(String.valueOf(username));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if(username.startsWith("SERIAL:")) {
            Crypto cry = CryptoFactory.getInstance();
            String acntNm = username.substring(7);

            try {
                acntNm = URLDecoder.decode(acntNm, "utf-8");
            } catch (UnsupportedEncodingException e) {
                acntNm = "";
            }
            String deCrtfcAcnt = cry.decrypt(acntNm);
            username = "SERIAL:" + deCrtfcAcnt;
        }
        List<UserDetails> users = loadUsersByUsername(username);

        if(users.size() == 0) {
            logger.debug("Query returned no results for user '" + username + "'");

            UsernameNotFoundException ue =
                new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }, "Username {0} not found"));

            throw ue;
        }

        LoginVO user = (LoginVO) users.get(0);

        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

        if(getEnableAuthorities()) {
            dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
        }
        if(getEnableGroups()) {
            dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
        }

        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
        user.setAuthorities(dbAuths);

//        if(dbAuths.size() == 0) {
//            UsernameNotFoundException ue =
//                new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username },
//                    "User {0} has no GrantedAuthority"));
//
//            throw ue;
//        }
        // 권한 그룹코드 목록 설정
        user.setAuthrtCdIdList(loadGroupAuthorCodeList(user.getUsername()));

        return user;
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        String param1, param2;
        if(username.startsWith("SERIAL:")) {
            param1 = "SERIAL";
            param2 = username.substring(7);
        } else {
            param1 = "NOT";
            param2 = username;
        }
        return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { param1, param1, CHANGE_PSWD_TERM, param2, param2}, new RowMapper<UserDetails>() {

            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                LoginVO loginVo = new LoginVO();
                loginVo.setUpDeptCdId(rs.getString("UP_DEPT_CD_ID"));
                loginVo.setUpperDeptNm(rs.getString("UPPER_DEPT_NM"));
                loginVo.setDeptCdId(rs.getString("DEPT_CD_ID"));
                loginVo.setDeptNm(rs.getString("DEPT_NM"));
                loginVo.setPicId(rs.getString("PIC_ID"));
                loginVo.setPicNm(rs.getString("PIC_NM"));
                loginVo.setRgnTelno(rs.getString("RGN_TELNO"));
                loginVo.setTelofcTelno(rs.getString("TELOFC_TELNO"));
                loginVo.setIndivTelno(rs.getString("INDIV_TELNO"));
                loginVo.setPicPswd(rs.getString("PIC_PSWD"));
                loginVo.setSttsSn(rs.getInt("STTS_SN"));
                loginVo.setUseYn(rs.getString("USE_YN"));
                loginVo.setMatcheAt(Boolean.TRUE);
                loginVo.setPswdChgDt(rs.getDate("PSWD_CHG_DT"));
                loginVo.setPasswordEndAt(rs.getString("PSWD_END_AT"));
                loginVo.setLgnDt(rs.getDate("LGN_DT"));
                loginVo.setAcntNm(rs.getString("ACNT_NM"));
                loginVo.setAuthorities(AuthorityUtils.NO_AUTHORITIES);

                return loginVo;
            }

        });
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] { username }, new RowMapper<GrantedAuthority>() {

            public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);

                return new SimpleGrantedAuthority(roleName);
            }
        });
    }

    @Override
    protected List<GrantedAuthority> loadGroupAuthorities(String username) {
        return super.loadGroupAuthorities(username);
    }

    /**
     * 할당된 권한그룹코드 목록
     *
     * @param username
     * @return
     */
    private List<String> loadGroupAuthorCodeList(String username) {
        return getJdbcTemplate().query(getAuthorGroupCodeQuery(), new String[] { username }, new RowMapper<String>() {

            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("AUTHRT_CD_ID");
            }
        });

    }

    /**
     * 권한그룹코드 목록 Query을 반환
     *
     * @return String authorGroupCodeQuery
     */
    public String getAuthorGroupCodeQuery() {
        return authorGroupCodeQuery;
    }

    /**
     * 권한그룹코드 목록 Query을 설정
     *
     * @param authorGroupCodeQuery 을(를) String authorGroupCodeQuery로 설정
     */
    public void setAuthorGroupCodeQuery(String authorGroupCodeQuery) {
        this.authorGroupCodeQuery = authorGroupCodeQuery;
    }

}
