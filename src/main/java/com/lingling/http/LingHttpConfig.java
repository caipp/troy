package com.lingling.http;

import com.alibaba.fastjson.JSONObject;
import com.lingling.config.LingConfig;
import com.lingling.util.HttpClientJson;
import com.lingling.util.JsonUtil;
import org.apache.commons.httpclient.NameValuePair;

public class LingHttpConfig {

	public static final String HOST_SERVER = "http://llkmc.izhihuicheng.net:8889/cgi-bin/";

	public static final String HOST_METHOD_ADD_DEVICE = "device/addDevice";//添加设备
	public static final String HOST_METHOD_DEL_DEVICE = "device/delDevice";//删除设备
	public static final String HOST_METHOD_UPDATE_DEVICE = "device/updateDevice";//更新设备
	public static final String HOST_METHOD_ADD_CARD = "openDoorCard/addOpenDoorCard";//添加门卡
	public static final String HOST_METHOD_DEL_CARD = "openDoorCard/delOpenDoorCard";//删除门卡
	public static final String HOST_METHOD_QUERY_CARD = "openDoorCard/queryOpenDoorCard";//查询门卡

	public static final String HOST_METHOD_UPDATE_CARD = "openDoorCard/updateOpenDoorCard";//修改门卡
	public static final String HOST_METHOD_GET_DEVICE_LIST = "device/queryDeviceList";//查询设备列表
	public static final String HOST_METHOD_MAKE_SDK_KEY = "key/makeSdkKey";//生成开门密钥
	public static final String HOST_METHOD_GET_LLID = "qrcode/getLingLingId";//获取令令ID
	public static final String HOST_METHOD_GET_LLID_LIST = "qrcode/getLingLingIds";//批量获取令令ID
	public static final String HOST_METHOD_ADD_OWNER_QRCODE = "qrcode/addOwnerQrCode";//生成业主二维码
	public static final String HOST_METHOD_ADD_VISITOR_QRCODE = "qrcode/addVisitorQrCode";//添加门禁访客二维码
	public static final String HOST_METHOD_DEL_VISITOR_QRCODE = "qrcode/delVisitorQrCode";//删除访客二维码
	public static final String HOST_METHOD_GET_OPENDOORLOG = "openDoorLog/selectOpenDoorLog";//日志查询

	public static final String HOST_METHOD_REMOTE_OPENDOOR = "key/remoteOpenDoor";//远程开门


	/**
	 * POST请求
	 * @param method	对应功能的url
	 * @param requestParam	请求的JSON格式参数
	 * @return	返回json数据
	 */
	public static String doPost(String method, JSONObject requestParam ,LingConfig lingConfig) {
		String url = HOST_SERVER + method +"/"+ lingConfig.getOpenToken();
		System.out.println("url --> "+url);
		HttpClientJson client = new HttpClientJson();
		String param = JsonUtil.makeMessage(requestParam,lingConfig.getSignature(),lingConfig.getToken());
		JSONObject result = client.doPostJson(url,new NameValuePair[] { new NameValuePair("MESSAGE", param)});

		return result.toJSONString();
	}

}
