package zesinc.core.compress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import zesinc.core.compress.CompressType;
import zesinc.web.utils.CompressUtil;

public class CompressorTest extends TestCase {

    // ZIP 압축해제
    private String zip1 = "src/test/resources/zesinc/compress/blackbirdjs.zip";
    private String zipuncompressDir = "src/test/resources/zesinc/compress/zip-uncompress-result";
    private String zipcompressDir = "src/test/resources/zesinc/compress/zip-compress-result";
    private String zipFileName = "compress_test1.zip";

    // TAR 압축해제
    private String tar1 = "src/test/resources/zesinc/compress/compress.tar";
    private String taruncompressDir = "src/test/resources/zesinc/compress/tar-uncompress-result";
    private String tarcompressDir = "src/test/resources/zesinc/compress/tar-compress-result";
    private String tarFileName = "compress_test1.tar";

    // 압축시 제외 경로
    private String removePath = "src/test/resources/";

    // 압축대상 파일
    private String img1 = "src/test/resources/zesinc/compress/Chrysanthemum.jpg";
    private String img2 = "src/test/resources/zesinc/compress/Desert.jpg";
    private String img3 = "src/test/resources/zesinc/compress/Hydrangeas.jpg";
    private String img4 = "src/test/resources/zesinc/compress/Jellyfish.jpg";
    private String img5 = "src/test/resources/zesinc/compress/Koala.jpg";
    private String img6 = "src/test/resources/zesinc/compress/Lighthouse.jpg";

    private List<File> files = new ArrayList<File>();

    @Override
    protected void setUp() throws Exception {

        files.add(new File(img1));
        files.add(new File(img2));
        files.add(new File(img3));
        files.add(new File(img4));
        files.add(new File(img5));
        files.add(new File(img6));

        super.setUp();
    }

    @Test
    public void testCompressZip() throws Exception {

        File compFile = CompressUtil.compress(CompressType.zip, files, removePath, zipcompressDir, zipFileName);
        File compareFile = new File(zipcompressDir, zipFileName);

        assertEquals(true, compFile.isFile());
        assertEquals(compFile.getAbsolutePath(), compareFile.getAbsolutePath());
    }

    @Test
    public void testUncompressZip() throws Exception {
        File compFile = new File(zip1);
        List<File> fileList = CompressUtil.uncompress(CompressType.zip, compFile, zipuncompressDir);
        assertEquals(6, fileList.size());
    }

    @Test
    public void testCompressTar() throws Exception {

        File compFile = CompressUtil.compress(CompressType.tar, files, removePath, tarcompressDir, tarFileName);
        File compareFile = new File(tarcompressDir, tarFileName);

        assertEquals(true, compFile.isFile());
        assertEquals(compFile.getAbsolutePath(), compareFile.getAbsolutePath());
    }

    @Test
    public void testUncompressTar() throws Exception {
        File compFile = new File(tar1);
        List<File> fileList = CompressUtil.uncompress(CompressType.tar, compFile, taruncompressDir);
        assertEquals(5, fileList.size());
    }
}
