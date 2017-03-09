package com.lp;

import java.net.URLEncoder;

import com.lp.utils.EncryptUtils;
import com.lp.utils.HttpRequest;


public class TestMain {
	public static void main(String[] args) {
		String pro = null;
		try {
			pro = "fields=" + URLEncoder.encode(EncryptUtils.aesEncrypt("/helloController/hello?id=1"), "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(HttpRequest.sendPost("http://127.0.0.1:8081/mainController/mainForward", pro));
	}
}
