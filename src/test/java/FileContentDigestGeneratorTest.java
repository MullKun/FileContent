import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.security.MessageDigest;
import java.util.Base64;


public class FileContentDigestGeneratorTest {
    @Test
    public void testDigestIsGeneratedCorrectly() {
        String digestMD5 = FileContentDigestGenerator.generateDigest("short.txt", "MD5");
        assertEquals(digestMD5, "gKdR/eV3AoZAxBkADjPrpg==");

        String digestSha1 = FileContentDigestGenerator.generateDigest("short.txt", "SHA-1");
        assertEquals(digestSha1, "v7d1mmfa62VBBJC02Yu52n0eos4=");

        String digestSha256 = FileContentDigestGenerator.generateDigest("short.txt", "SHA-256");
        assertEquals(digestSha256, "Xiv1fT9AxLbfadrxk2y3ZvgyN0tPwCWafL/wbi9w8mk=");
    }

    @Test
    public void testDigestAlgorithms() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("lorem ipsum".getBytes());
            assertEquals(Base64.getEncoder().encodeToString(md5.digest()),"gKdR/eV3AoZAxBkADjPrpg==");

            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.update("lorem ipsum".getBytes());
            assertEquals(Base64.getEncoder().encodeToString(sha1.digest()),"v7d1mmfa62VBBJC02Yu52n0eos4=");

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update("lorem ipsum".getBytes());
            assertEquals(Base64.getEncoder().encodeToString(sha256.digest()),"Xiv1fT9AxLbfadrxk2y3ZvgyN0tPwCWafL/wbi9w8mk=");
        } catch (Exception e) {
        }
    }
}
