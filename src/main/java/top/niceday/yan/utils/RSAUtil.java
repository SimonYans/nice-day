package top.niceday.yan.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RSAUtil {

    public String data = "123456";

    // 公钥
    private String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPNkWW0S3oE/ljTIWV4IZ1JInbNnMlzazQjEE0kABeoyyOYNV4Yg7LKLBp5R/zfaYL/b/9ZzaT8+JglFNSrvbRPAWdxkz9t4L5y5YsfPUciGenTE/b+tJCKhlxT7uuQwRxc9RE6b2WEv7LTuC6RqwRmHxUvACuOYBM2j5d8UwQVwIDAQAB";
    // 私钥
    private String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI82RZbRLegT+WNMhZXghnUkids2cyXNrNCMQTSQAF6jLI5g1XhiDssosGnlH/N9pgv9v/1nNpPz4mCUU1Ku9tE8BZ3GTP23gvnLlix89RyIZ6dMT9v60kIqGXFPu65DBHFz1ETpvZYS/stO4LpGrBGYfFS8AK45gEzaPl3xTBBXAgMBAAECgYA6FcQR0JOy/m73BHFmAaPnXUOAosv/ODVwfIJx5DoSKFKJCONsKTFg2wb6XFEiPAMd9/RnvfYX+iIGFALblVVLmpXwIy1KdKVoi6LccFLpH5aEjN0021pxunsgZpgTOZUDTDgK+4vaJ67eJ/PJO606Vq+g2uSVF3Dl6+nBnHYeaQJBAMOu2qf5OJi9nVMJ1+8IChAh35YviTPv08yBJ2fa3mPZHrct4uT8oSBUIbkGyXGZ90980lxLmRitAxNRKKoppZsCQQC7Wvuy+gXXOVhDAX/kT+3vUvarEApFEPApmU0TuSdOAmmAkaBTN/Wny6Zjvfr4H3Xsoz/RrPkywhzcNFZRzGn1AkAkeNeqTSo+VcEX9BK6K55siVO6E1wYQRAR4tSsZ7/hjltKOxQ6J9gERYoPiXNMaC0PNK2kpDaBweV72TmiFacXAkA2zAWR806CBGOsNWQSgs40kS9UGPzn9mxHvm6sthqo1kvFLtKVhGh1Czs/MPGWYJ08+QJRcSXxkpSeUzBr/D2BAkEAkjKF1jEWpcGrtrMq8pT2gKJNcAmXM565RRqvEZNWrdKnTtW24BjdXHoiAIo9rfAOU8IUp7ig1+I+kzhizvMCuQ==";

    private static RSAUtil ourInstance = new RSAUtil();

    public static RSAUtil getInstance() { return ourInstance; }

    // 生成密钥对
    private void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        publicKeyStr = new String(Base64.getEncoder().encode(publicKey.getEncoded()));

        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        privateKeyStr = new String(Base64.getEncoder().encode(privateKey.getEncoded()));

    }

    // 将base64编码后的公钥字符串转成PublicKey实例
    private PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 将base64编码后的私钥字符串转成PrivateKey实例
    private PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // 公钥加密
    public String encryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = Base64.getEncoder().encodeToString(cipherText);
        return cipherStr;
    }

    // 私钥加密
    public String encryptByPrivateKey(String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = Base64.getEncoder().encodeToString(cipherText);
        return cipherStr;
    }

    // 私钥解密
    public String decryptByPrivateKey(String content) throws Exception {
        return decryptByPrivateKey(privateKeyStr, content);
    }
    public String decryptByPrivateKey(String privateKeyStr, String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherText = Base64.getDecoder().decode(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    // 公钥解密
    public String decryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] cipherText = Base64.getDecoder().decode(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    public static void main(String[] args) {
        RSAUtil rsaUtil = new RSAUtil();
        try {
            rsaUtil.test2();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test2() throws Exception {

        //
        // 公钥加密
        String encryptedBytes = encryptByPublicKey(data);
        System.out.println("公钥加密后：" + encryptedBytes);
        // 私钥解密
        String decryptedBytes = decryptByPrivateKey(encryptedBytes);
        System.out.println("私钥解密后：" + decryptedBytes);


        // 私钥加密
        String encryptedBytes2 = encryptByPrivateKey(data);
        System.out.println("私钥加密后：" + encryptedBytes2);
        // 公钥解密
        String decryptedBytes2 = decryptByPublicKey(encryptedBytes2);
        System.out.println("公钥解密后：" + decryptedBytes2);
    }


}