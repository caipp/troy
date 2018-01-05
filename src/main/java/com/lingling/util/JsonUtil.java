package com.lingling.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static final String REQUEST_PARAM = "requestParam";
	public static final String RESPONSE_RESULT = "responseResult";
	public static final String STATUS_CODE = "statusCode";
	public static final String STATUS_CODE_SUCCESS = "1";
	public static final String STATUS_CODE_ERROR = "0";
	public static final String HEADER = "header";
	public static final String SIGNATURE = "signature";
	public static final String TOKEN = "token";
	
	/**
	 * 生成发送消息
	 * @param requestParam
	 * @return
	 */
	public static String makeMessage(JSONObject requestParam,String signature,String token){
		
		JSONObject message = new JSONObject();
		message.put(REQUEST_PARAM, requestParam);
		// 生成消息头
		JSONObject header = new JSONObject();
		header.put(SIGNATURE,signature);
		header.put(TOKEN, token);
		message.put(HEADER, header);
		System.out.println(message.toString());
		return message.toString();
	}


}
