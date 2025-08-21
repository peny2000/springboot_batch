/**
 * COPYRIGHT(c) A3 Security co.,Ltd 2011
 * This software is the proprietary information of A3 Security.
 *
 * 업무내용 : AES 암호화 
 * 상세설명 : AES 알고리즘을 이용한 암호화/복호화
 */

package com.a3.lfs.support.util;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * <p>
 * 업무명 : AES 암호화
 * <p>
 * 업무구분 : 공통 > 암호화
 * <p>
 * 설명 : AES 알고리즘을 이용한 암호화
 * 
 * @author shyun
 * @version 0.1
 */
public class AES {

    /** log. */
    private Logger log = Logger.getLogger(this.getClass());
    
    /** The conf. */
    
    private static ConfigSet    conf;

    /** The key data. */
    private static String       keyData;
    
    /** The iv data. */
    private static String       ivData;
    
    private static String       transformation;
    
    /**
     * <p>
     * AES Constructor
     * <p>
     * AES Constructor
     * 
     * @param propertyName the property name
     * @throws Exception the exception
     */
    public AES() throws Exception {
        
        String propertyName = "default";
        transformation = "AES/CBC/PKCS5Padding";
            
        try {
            conf = new ConfigSet();
            
            keyData = conf.getProperty(propertyName+".aes.key_data");
            ivData = conf.getProperty(propertyName+".aes.iv_data");
            
        } catch(NumberFormatException e){
            log.error(this.getClass().getName() + "::AES : Error : Property Name Check : " + e.toString());
        } catch (Exception e) {
            log.error(this.getClass().getName() + "::AES : Error : " + e.toString());
        }
    }
    
    /**
     * <p>
     * AES 초기화
     * <p>
     * AES 초기화
     * 
     * @param propertyName the property name
     * @throws Exception the exception
     */
    public AES(String propertyName) throws Exception {
        
        if(propertyName == null || "".equals(propertyName)){
            propertyName = "default";
        }
            
        transformation = "AES/CBC/PKCS5Padding";
        
        try {
            conf = new ConfigSet();
            
            keyData = conf.getProperty(propertyName+".aes.key_data");
            ivData = conf.getProperty(propertyName+".aes.iv_data");
            
        } catch(NumberFormatException e){
            log.error(this.getClass().getName() + "::AES : Error : Property Name Check : " + e.toString());
        } catch (Exception e) {
            log.error(this.getClass().getName() + "::AES : Error : " + e.toString());
        }
    }
    
    /**
     * <p>
     * 암호화
     * <p>
     * 입력받은 메시지를 암호화 한다.
     *
     * @param message the message
     * @return the string
     * @throws Exception the exception
     */
    public String encode(String message) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getName() + "::encrypt : start");
        }
        
        String returnString = "";
        try {
            //Key secureKey = CipherKeyGen.generateKey("AES");
            Key secureKey = CipherKeyGen.generateKey("AES", keyData.getBytes());
            
            byte[] iv = ivData.getBytes();
   
            Cipher cipher = Cipher.getInstance(transformation);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); 
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, ivParameterSpec);
            
            byte[] plain = message.getBytes();  
            byte[] encryptedData = cipher.doFinal(plain);
            returnString = ByteUtils.byte2hex(encryptedData); 

            if(log.isDebugEnabled()) {
                log.debug(this.getClass().getName() + "::encrypt : plainData : " + message);
                log.debug(this.getClass().getName() + "::encrypt : encryptedData : " + returnString);
            }
            
        }  catch (Exception e) {
            log.error(this.getClass().getName() + "::encrypt : Error : " + e.toString());
        }
        
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getName() + "::encrypt : end");
        }
        
        return returnString;
    }
}