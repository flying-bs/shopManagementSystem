package com.as.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	/**
	 * 实现MD5加密数据
	 */
	public static final String getMd5(String s){
		char hexDigits[]={
	            '5', '0', '5', '6', '2', '9', '6', '2', '5', 'q',
	            'b', 'l', 'e', 's', 's', 'y'
		};
		try {
			char str[];
			byte strtmp[] = s.getBytes();
			MessageDigest mdtmp = MessageDigest.getInstance("MD5");
			mdtmp.update(strtmp);
			byte md[]=mdtmp.digest();
			int j = md.length;
			str = new char[j*2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>>4 &0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
	}
	public static void main(String[] args) {
		System.out.println("admin="+MD5.getMd5("admin"));
	}
}
