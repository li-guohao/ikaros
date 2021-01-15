package cn.liguohao.ikaros.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**RSA加密工具类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/9
 */
public class RSAEncrptUtils {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 默认的密钥长度
     */
    private static final int DEFAULT_KEY_SIZE = 1024;

    /**
     * 生成密钥对
     * @param keySize 生成尺寸 默认1024
     */
    public static KeyPair generateKey(int keySize){
        try {
            // 获取Key生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

            // 初始化Key生成器
            keyPairGenerator.initialize(keySize);

            // 生成密钥对
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成1024位密钥对
     * @see #generateKey(int) 
     */
    public static KeyPair generateKey(){
        return generateKey(DEFAULT_KEY_SIZE);
    }

    /**
     * 生成密钥文件
     * @see #generateKeyFile(int, File, File) 
     */
    public static void generateKeyFile(File publicKeyFile, File privateKeyFile) throws IOException {
        generateKeyFile(DEFAULT_KEY_SIZE,publicKeyFile,privateKeyFile);
    }

    /**
     * 生成密钥对文件
     * @param keySize 密钥尺寸
     * @param publicKeyFile 公钥文件
     * @param privateKeyFile 私钥文件
     * @throws IOException 文件读写异常
     * @see #generateKey(int)
     */
    public static void generateKeyFile(int keySize, File publicKeyFile, File privateKeyFile) throws IOException {
        // 父目录不存在创建
        if(!publicKeyFile.getParentFile().exists()) publicKeyFile.getParentFile().mkdirs();
        if(!privateKeyFile.getParentFile().exists()) privateKeyFile.getParentFile().mkdirs();

        // 生成密钥对
        KeyPair keyPair = generateKey(keySize);

        // 写入到文件中
        FileOutputStream publicKeyFileOutputStream = new FileOutputStream(publicKeyFile);
        FileOutputStream privateKeyFileOutputStream = new FileOutputStream(privateKeyFile);
        publicKeyFileOutputStream.write(keyPair.getPublic().getEncoded());
        privateKeyFileOutputStream.write(keyPair.getPrivate().getEncoded());

        // 释放流
        publicKeyFileOutputStream.close();
        privateKeyFileOutputStream.close();
    }

    /**
     * 用公钥加密文件
     * @param publicKeyFile 公钥文件
     * @param originalFile 源头文件
     * @param encryptedFile 加密后文件
     * @throws IOException 文件读取异常
     */
    public static void encrptFileByPublicKeyFile(File publicKeyFile,File originalFile, File encryptedFile) throws IOException {
        // 文件存在判断
        if(!originalFile.exists()) throw new FileNotFoundException(originalFile.getPath());

        // 加密文件父目录不存在创建
        if(!encryptedFile.getParentFile().exists()) encryptedFile.getParentFile().mkdirs();

        // 获取公钥字节数组
        byte[] publicKeyBytes = FileUtils.toByteArray(publicKeyFile);


        FileOutputStream fileOutputStream = null;
        try {
            // 获取公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(keySpec);

            // 获取加密器
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);

            // 加密数据
            byte[] encrptedBytes = cipher.doFinal(FileUtils.toByteArray(originalFile));

            // 写入到文件
            fileOutputStream = new FileOutputStream(encryptedFile);
            fileOutputStream.write(encrptedBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            // 释放流
            fileOutputStream.close();
        }

    }


    /**
     * 根据私钥解密文件
     * @param privateKeyFile 私钥文件
     * @param encryptedFile 已经加密的文件
     * @param decryptedFile 解密后的文件
     * @throws IOException 文件读写异常
     */
    public static void decrptFileByPrivateKeyFile(File privateKeyFile, File encryptedFile, File decryptedFile) throws IOException {
        // 文件存在判断
        if(!encryptedFile.exists()) throw new FileNotFoundException(encryptedFile.getPath());

        // 加密文件父目录不存在创建
        if(!decryptedFile.getParentFile().exists()) decryptedFile.getParentFile().mkdirs();

        FileOutputStream fileOutputStream = null;
        try {
            // 构建私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(FileUtils.toByteArray(privateKeyFile));
            PrivateKey privateKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(keySpec);

            // 解密数据
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] decrptedBytes = cipher.doFinal(FileUtils.toByteArray(encryptedFile));

            // 构建流写到文件
            fileOutputStream = new FileOutputStream(decryptedFile);
            fileOutputStream.write(decrptedBytes);

        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            // 释放流
            fileOutputStream.close();
        }


    }

}
