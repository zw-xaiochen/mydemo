package com.cjy.cloud.technology.aes;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * PKCS5Padding
 */
@Slf4j
public class Aes5PaddingUtil {
    private static final String KEY_ALGORITHM = "AES";

    /**
     * AES/CBC算法
     */
    private static String AES_CBC_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 秘钥
     */
    private static final String ENCODE_KEY = "E15S3A4F5R6E7G8S";

    /**
     * 将对象进行AES加密
     * @param encryptData : 加密对象
     * @return encodePassword
     */
    private static String encrypt(Object encryptData) {
        String encryptResultStr = null;
        try {
            /*ObjectMapper om = new ObjectMapper();
            String encryptDataStr = om.writeValueAsString(encryptData);*/
            String encryptDataStr = JSON.toJSONString(encryptData);
            encryptResultStr = encryptToString(encryptDataStr);
        } catch (Exception e) {
            log.error("加密异常>>>>>>>>>");
        }
        return encryptResultStr;
    }

    /**
     * 加密AES
     * @param content ： 加密内容
     * @return encodePassword
     */
    private static String encryptToString(String content) {
        byte[] encryptResult = null;
        try {
            // 密钥
            SecretKeySpec secretKey = new SecretKeySpec(ENCODE_KEY.getBytes(), KEY_ALGORITHM);
            // 算法/模式/填充
            Cipher cipher = Cipher.getInstance(AES_CBC_ALGORITHM);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化向量,在密钥相同的前提下，加上初始化向量，相同内容加密后相同
            IvParameterSpec zeroIv = new IvParameterSpec(ENCODE_KEY.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, zeroIv);
            encryptResult = cipher.doFinal(byteContent);

        } catch (Exception e) {
            log.error("加密异常>>>>>>>>>");
        }
        return Base64.getEncoder().encodeToString(encryptResult);
    }

    /**
     * 解密AES
     * @param content ： 解密内容
     * @return decodePassword
     */
    public static String decrypt(String content) {
        byte[] decryptResult;
        try {
            // 密钥
            SecretKeySpec secretKey = new SecretKeySpec(ENCODE_KEY.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CBC_ALGORITHM);
            // base64转换
            byte[] byteContent = Base64.getDecoder().decode(content);
            IvParameterSpec zeroIv = new IvParameterSpec(ENCODE_KEY.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, zeroIv);
            decryptResult = cipher.doFinal(byteContent);

            return new String(decryptResult, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解密异常>>>>>>>>> 解密内容: {}", content);
        }
        return null;
    }

    /**
     * main:(这里用一句话描述这个方法的作用)
     * @param args args
     */
    public static void main(String[] args) {
        try {
            // String password = System.currentTimeMillis()+"000";
            String key = "1234567812345678";
            String content = "123456";
            System.out.println("加密前：" + content);
            String encryptString = encrypt(content);
            System.out.println("加密后：" + encryptString);

            System.out.println("解密前：" + encryptString);
            String decrypyResult = decrypt("P/hRujzmdgtpCFNqii1WdQ==");
            System.out.println("解密后：" + decrypyResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}