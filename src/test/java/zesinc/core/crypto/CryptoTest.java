package zesinc.core.crypto;

import junit.framework.TestCase;

import org.junit.Test;

import zesinc.core.crypto.Crypto;
import zesinc.core.crypto.CryptoFactory;
import zesinc.core.crypto.CryptoFactory.EncryptType;

public class CryptoTest extends TestCase {

    @Test
    public void testSEEDCrypto() {

        Crypto cry = CryptoFactory.getInstance(EncryptType.SEED);

        // 암호화 키는 기본값이 등록되어 있지만.. 별도의 키(16자리 문자)를 사용하는 경우는 설정
        String str1 = cry.setKey("a234567890123456").encrypt("123456789");
        String str2 = cry.setKey("a234567890123456").decrypt(str1);

        assertEquals("123456789", str2);

    }

    @Test
    public void testSHA256Crypto() {

        // 복호화 불가(비밀 번호등의 개인정보 보호용으로 사용(개인정보 보호법 의거)
        Crypto cry = CryptoFactory.getInstance(EncryptType.SHA256);

        // 복호화가 불가능한 암호화 이므로 키가 존재하지 않음
        String str1 = cry.encrypt("123456789");
        assertNotNull(str1);

        // 복호화 요청시 null 반환(오류임)
        String str2 = cry.decrypt(str1);
        assertNull(str2);

    }

    @Test
    public void testARIACrypto() {

        Crypto cry = CryptoFactory.getInstance(EncryptType.ARIA);

        // 암호화 키는 기본값이 등록되어 있지만.. 별도의 키(32자리 문자)를 사용하는 경우는 설정
        String str1 = cry.setKey("a234567890123456a234567890123456").encrypt("123456789");
        String str2 = cry.setKey("a234567890123456a234567890123456").decrypt(str1);

        assertEquals("123456789", str2);

    }

}
