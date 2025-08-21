/**
 * COPYRIGHT(c) A3 Security co.,Ltd 2011
 * This software is the proprietary information of A3 Security.
 *
 * 업무내용 : 비밀키 생성
 * 상세설명 : 해당 알고리즘(DES/DESede/TripleDES/AES)에 대한 비밀키를 생성한다.
 */

package com.a3.lfs.support.util;

import org.apache.log4j.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * <p>
 * 업무명 : 비밀키 생성
 * <p>
 * 업무구분 : 공통
 * <p>
 * 설명 : 해당 알고리즘에 사용할 비밀키(SecretKey)를 생성한다.
 * 
 * @author shyun
 * @version 1.0
 */
public class CipherKeyGen {

    /** log. */
    private Logger log = Logger.getLogger(this.getClass());

    /**
     * <P>
     * 해당 알고리즘에 사용할 비밀키(SecretKey)를 생성한다.
     * </P>
     * 
     * @return 비밀키(SecretKey)
     */

    public static Key generateKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        
        if("AES".equals(algorithm)) {
            keyGenerator.init(256); // 128 /192 / 256 bits 
        }

        SecretKey key = keyGenerator.generateKey();
       
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), algorithm);
        
        return keySpec;
    }

    /**
     * <P>
     * 주어진 데이터로, 해당 알고리즘에 사용할 비밀키(SecretKey)를 생성한다.
     * </P>
     * 
     * @param algorithm
     *            DES/DESede/TripleDES/AES
     * @param keyData
     * @return
     */

    public static Key generateKey(String algorithm, byte[] keyData) throws NoSuchAlgorithmException,
            InvalidKeyException, InvalidKeySpecException {

        String upper = algorithm.toUpperCase();

        if ("DES".equals(upper)) {
            KeySpec keySpec = new DESKeySpec(keyData);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            return secretKey;

        } else if ("DESede".equals(upper) || "TripleDES".equals(upper)) {
            KeySpec keySpec = new DESedeKeySpec(keyData);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            return secretKey;

        } else {
            SecretKeySpec keySpec = new SecretKeySpec(keyData, algorithm);
            return keySpec;
        }
    }
}