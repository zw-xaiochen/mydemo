package com.cjy.cloud.technology.aes;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * aes7 加密
 * @author create by zhouzongbo on 2019/10/25
 */
@Slf4j
public class Aes7PaddingUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 偏移量，只有CBC模式才需要
     */
    private static final String IV_PARAMETER = "0000000000000000";

    /**
     * AES要求密钥长度为128位或192位或256位，java默认限制AES密钥长度最多128位
     */
    public static String sKey = "";

    /**
     * 秘钥
     */
    public static final String ENCODE_KEY = "E15S3A4F5R6E7G8S";

    static {
        //如果是PKCS7Padding填充方式，则必须加上下面这行
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES加密
     * @param source 源字符串
     * @param key    密钥
     * @return 加密后的密文
     * @throws Exception
     */
    public static String encrypt(String source, String key) throws Exception {
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), iv);
        byte[] decrypted = cipher.doFinal(sourceBytes);
        return Base64.encodeBase64String(decrypted);
    }

    /**
     * AES解密
     * @param encryptStr 加密后的密文
     * @param key        密钥
     * @return 源字符串
     * @throws Exception ex
     */
    public static String decrypt(String encryptStr, String key) {
        try {
            byte[] sourceBytes = Base64.decodeBase64(encryptStr);
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), iv);
            byte[] decoded = cipher.doFinal(sourceBytes);
            return new String(decoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解密异常>>>>>>>>>>>>>>");
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        String key = ENCODE_KEY;

        // 加密
        long lastTime = System.currentTimeMillis();
        String enString = Aes7PaddingUtil.encrypt("1234567", key);
        System.out.println("加密后的字串是：" + enString);

        long endUseTime = System.currentTimeMillis() - lastTime;
        System.out.println("加密耗时：" + endUseTime + "毫秒");

        // 解密
        lastTime = System.currentTimeMillis();
        String decodeString = Aes7PaddingUtil.decrypt(enString, key);
        System.out.println("解密后的字串是：" + decodeString);
        endUseTime = System.currentTimeMillis() - lastTime;
        System.out.println("解密耗时：" + endUseTime + "毫秒");
    }

}