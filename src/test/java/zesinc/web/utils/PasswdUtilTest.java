package zesinc.web.utils;

import junit.framework.TestCase;

import org.junit.Test;

import zesinc.core.config.Config;

/**
 * 비밀번호 관리 유틸 테스트로 salt 는 보안 권고에 따라 10회 적용된 후 결과를 생성한다.
 * 
 * @since 3.0
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2014. 10. 15.    방기배   최초작성
 * </pre>
 * @see
 */
public class PasswdUtilTest extends TestCase {

    private static int passwdLength = Config.getInt("passwd-config.defaultPasswdLength");

    @Test
    public void testMakePasswd() {

        String passwd = "a123456789";
        String salt1 = "a1234";
        String salt2 = "!@#$%^";

        String encNoSalt = PasswdUtil.encodePasswd(passwd);
        String encSalt = PasswdUtil.encodePasswd(passwd, salt1);
        String encSalt1 = PasswdUtil.encodePasswd(passwd, salt2);

        assertEquals(encNoSalt, encSalt);
        assertFalse(encSalt.equals(encSalt1));

        // 동일한지 비교
        boolean matche = PasswdUtil.matche(passwd, encNoSalt);
        assertEquals(true, matche);
        // 유효성 검증
        boolean isAllow = PasswdUtil.isAllowPasswd(passwd, encSalt1, null);
        assertEquals(true, isAllow);
    }

    @Test
    public void testRamdomPasswd() {

        String passwd = PasswdUtil.generatePasswd();
        assertEquals(passwd.length(), passwdLength);
    }
}
