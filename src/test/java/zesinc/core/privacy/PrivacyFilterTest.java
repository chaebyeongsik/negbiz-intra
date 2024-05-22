package zesinc.core.privacy;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import zesinc.core.privacy.PrivacyFilter;
import zesinc.core.privacy.PrivacyFilterFactory;
import zesinc.core.privacy.PrivacyResultVO;

public class PrivacyFilterTest extends TestCase {

    @Test
    public void testPrivacyFilter() throws IOException, Exception {

        String content = "";
        File file = null;

        // 워드파일 테스트
        content = "src/test/resources/zesinc/privacy/doc_sample.doc";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/doc_sample.doc");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        content = "src/test/resources/zesinc/privacy/doc_sample.docx";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/doc_sample.docx");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        // 파워포인트 파일 테스트
        content = "src/test/resources/zesinc/privacy/ppt_sample.ppt";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/ppt_sample.ppt");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        content = "src/test/resources/zesinc/privacy/ppt_sample.pptx";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/ppt_sample.pptx");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        // 엑셀파일 테스트
        content = "src/test/resources/zesinc/privacy/xls_sample.xls";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/xls_sample.xls");
        PrivacyFilter pf = PrivacyFilterFactory.getInstance(file);
        assertEquals(true, pf.doFilter().isResult());

        // 엑셀파일 테스트
        content = "src/test/resources/zesinc/privacy/xls_sample.xlsx";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/xls_sample.xlsx");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        // 텍스트파일 테스트
        content = "src/test/resources/zesinc/privacy/txt_sample.txt";
        assertEquals(true, PrivacyFilterFactory.getInstance(content).doFilter().isResult());
        file = new File("src/test/resources/zesinc/privacy/txt_sample.txt");
        assertEquals(true, PrivacyFilterFactory.getInstance(file).doFilter().isResult());

        // 문자열 테스트
        content = "sadfsadfsadfsadfsa 4333 0333 8333 1333 ";
        PrivacyResultVO vo = PrivacyFilterFactory.getInstance(content).doFilter();
        assertEquals(true, vo.isResult());
        assertEquals(true, vo.getFilterList().get(0).equals("4333 0333 8333 1333"));

    }

}
