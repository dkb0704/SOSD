package cn.edu.fzu.sosd.web.shared.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * class name: PasswdUtil
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/22 16:37
 */
public class PasswdUtil {


    // 可逆的加密算法
    private static final String AES = "AES";
    // 生成 AES 密钥时使用的种子
    private static final String SECRET_KEY = "web@sosd";
    // 生成随机数的算法
    private static final String ALGORITHM = "SHA1PRNG";

    private PasswdUtil() {
    }

    /**
     * 对明文密码进行编码。
     *
     * @param rawPassword 明文密码
     * @return 编码后的密码
     */
    public static String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }

        try {
            SecretKeySpec key = generateKey();
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(rawPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("密码编码失败", e);
        }
    }

    /**
     * 生成 AES 密钥。
     *
     * @return 密钥对象
     */
    private static SecretKeySpec generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance(ALGORITHM);
        secureRandom.setSeed(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), AES);
    }
}