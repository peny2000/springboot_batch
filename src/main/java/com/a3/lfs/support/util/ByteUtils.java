/**
 * COPYRIGHT(c) A3 Security co.,Ltd 2011
 * This software is the proprietary information of A3 Security.
 *
 * 업무내용 : Byte 관련 함수 제공
 * 상세설명 : Byte 관련 함수를 제공한다.
 */

package com.a3.lfs.support.util;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * 업무명 : Byte 관련 함수 제공
 * <p>
 * 업무구분 : 공통 > 유틸리티
 * <p>
 * 설명 : Byte 관련 함수를 제공한다.
 * 
 * @author shyun
 * @version 0.1
 */
public class ByteUtils {

    /**
     * <p>
     * 두 배열의 값이 동일한지 비교한다.
     * </p>
     *
     * @param array1 the array1
     * @param array2 the array2
     * @return 동일하면 <code>true</code>, 아니면 <code>false</code>
     */
    public static boolean equals(byte[] array1, byte[] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * <p>
     * unsigned byte(바이트)를 16진수 문자열로 바꾼다.
     * </p>
     * 
     * @param b unsigned byte
     * @return the string
     */
    public static String byte2hex (byte []b) {
        String hs ="";
        String stmp ="";
        for (int n =0 ;n <b.length ;n ++) {
            stmp =(java.lang.Integer.toHexString (b [n ]&0XFF ));
            if (stmp.length ()==1 )hs =hs +"0"+stmp ;
            else hs =hs +stmp ;
            if (n <b.length -1 )hs = hs;
        }
        return hs;
    }

     /**
      * 16진수 문자열을 바이트 배열로 변환한다.
      *
      * @param hex the hex
      * @return the byte[]
      */
     public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }
}