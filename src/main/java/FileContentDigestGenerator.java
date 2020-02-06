import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.Base64;

public class FileContentDigestGenerator {

    private static final int BYTE_SIZE_EACH_ITERATION = 8192;

    private static final int INDEX_DATA_READING_FINISH = -1;

    public static void main(String[] args) {
        String fileName=args[0];
        String digestAlgorithm=args[1];
        System.out.println(generateDigest(fileName, digestAlgorithm));
    }

    public static String generateDigest(String fileName, String digestAlgorithm) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = getFileInputStream(fileName);
            MessageDigest digest = MessageDigest.getInstance(digestAlgorithm);
            updateDigest(fileInputStream, digest);
            BigInteger bigInteger = new BigInteger(digest.digest());
            return Base64.getEncoder().encodeToString(bigInteger.toByteArray());
        } catch (Exception e) {
          return "Okok....you fail, slf4j cannot load the class...";
        } finally {
            try {
                fileInputStream.close();
            } catch (Exception e) {}
        }
    }

    public static FileInputStream getFileInputStream(String fileName) throws URISyntaxException, FileNotFoundException {
        File file = new File(FileContentDigestGenerator.class.getClassLoader().getResource(fileName).toURI());
        return new FileInputStream(file);
    }

    private static void updateDigest(FileInputStream file, MessageDigest digest) throws IOException {
        int fileContentLength;
        final int OFFSET = 0;

        byte[] buffer = new byte[BYTE_SIZE_EACH_ITERATION];
        while ((fileContentLength = file.read(buffer)) != INDEX_DATA_READING_FINISH) {
            digest.update(buffer, OFFSET, fileContentLength);
        }
    }
}
