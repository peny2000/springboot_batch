package com.a3.lfs.support.util;

import java.net.InetAddress;

public class BatchUtil{

	public static String longToIp(long ip){

		return ((ip >> 24) & 0xFF) +
				"." + ((ip >> 16) & 0xFF) +
				"." + ((ip >> 8) & 0xFF) +
				"." + (ip & 0xFF);
	}

	public static long ipToLong(InetAddress ip){

		byte[] octets = ip.getAddress();
		long result = 0;
		for (byte octet : octets) {
			result <<= 8;
			result |= octet & 0xff;
		}
		return result;
	}
	
	/**
     * AES 암호화
     * @param src - 평문
     * @param encType - 복호화할 프로퍼티타입  : default,dbInfo,fileId 중하나(app.properties 정의)
     * @return
     */
    public static String encAES(String src, String encType){
        String dst = "";
        if(!src.isEmpty()){
            try {
                AES aes = new AES(encType);
                dst = aes.encode(src);
            } catch (Exception e) {}
        }
        return dst;
    }
}