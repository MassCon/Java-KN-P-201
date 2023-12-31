package step.learning.ioc.services.hash;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.inject.Singleton;

@Singleton
public class ShaHashService implements HashService {

    @Override
    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            StringBuilder sb = new StringBuilder();
            for(int b: digest.digest(input.getBytes(StandardCharsets.UTF_8) ) ) {
                sb.append(String.format("%02x", b & 0xFF ) ) ;
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}