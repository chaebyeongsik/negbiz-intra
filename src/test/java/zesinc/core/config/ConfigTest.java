package zesinc.core.config;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.configuration2.Configuration;
import org.junit.Test;

import zesinc.core.config.ConfigLoader;

public class ConfigTest extends TestCase {

    private static String configPattern1 = "classpath:zesinc/config/commons/test-commons-config.xml";
    private static String configPattern2 = "classpath:zesinc/config/commons/test-*-commons-config.xml";

    @Test
    public void testLoadConfig() throws IOException, Exception {

        Configuration conf = ConfigLoader.getConfig(configPattern1);
        assertEquals(conf.getString("system.appNm"), "openworks");
        assertEquals(conf.getString("webapp-config.pagging.pageNums"), "5, 10, 15, 30, 50, 100, 200, 300");

    }

    @Test
    public void testMultiFileConfig() throws IOException, Exception {

        Configuration conf = ConfigLoader.getConfig(configPattern2);

        assertEquals(conf.getString("system-config.test"), "test");
        assertEquals(conf.getString("host-config.kr..localhost"), "ko");

    }

}
