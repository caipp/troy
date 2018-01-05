package com.lingling.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lingling.config.LingConfig;
import com.lingling.util.JsonUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author caipiaoping
 */
public class LingService {

	private LingConfig lingConfig;

	public void setLingConfig(LingConfig lingConfig) {
		this.lingConfig = lingConfig;
	}


	public Boolean remoteOpenDoor(String sdkKey) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("sdkKey", sdkKey); // 控制设备的sdkKey
		String result = LingHttpConfig.doPost(
				LingHttpConfig.HOST_METHOD_REMOTE_OPENDOOR, requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		String resultCode = (String)resultMap.get(JsonUtil.STATUS_CODE);
		if(JsonUtil.STATUS_CODE_SUCCESS.equals(resultCode)){
			return true;
		}else{
			return false;
		}
	}

	public String getLingLingId() {
		JSONObject requestParam = new JSONObject();
		String result = LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_GET_LLID,
				requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
		return responseMap.get("lingLingId").toString();
	}

	public String addDevice(String deviceName, String deviceCode) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("deviceName", deviceName);// 设备名称
		requestParam.put("deviceCode", deviceCode);// 设备的SN码
		String result = LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_ADD_DEVICE,
				requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
		return responseMap.get("deviceId").toString();
	}

	// 删除设备
	public void delDevice(String deviceId) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("deviceId", deviceId);// 设备ID
		LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_DEL_DEVICE,
				requestParam,lingConfig);
	}

	// 更新设备
	public void updateDevice(String deviceId,String deviceName, String deviceCode) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("deviceId", deviceId);// 设备ID
		requestParam.put("deviceName", deviceName);// 设备名称
		requestParam.put("deviceCode", deviceCode);// 设备的SN码
		LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_UPDATE_DEVICE,requestParam,lingConfig);
	}

	// 生成开门密钥Key
	public String makeSdkKey(String deviceId) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("deviceIds", new String[] { deviceId });// 设备ID数组
		String result = LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_MAKE_SDK_KEY,
				requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
		return responseMap.get(deviceId).toString();
	}


	// 生成业主二维码
	public String addOwnerQrCode(String linglingId,String[] sdkKeys) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("lingLingId", linglingId); // 令令ID
		requestParam.put("sdkKeys", sdkKeys);// 控制设备的sdkKey
		requestParam.put("endTime", 4095); // 结果时间（单位：分钟；最大支持4095分钟）
		requestParam.put("effecNumber", 0);
		requestParam.put("strKey", "123456AA"); // 自定义密钥（二维码加密用的key，8位16进制的字符串）
		String result = LingHttpConfig.doPost(
				LingHttpConfig.HOST_METHOD_ADD_OWNER_QRCODE, requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
		return responseMap.get("qrcodeKey").toString();
	}

	public Map<String,Object> addVisitorQrCode(String linglingId, String[] sdkKeys, Integer count, Date startTime, Date endTime) {
		Long time = endTime.getTime() - startTime.getTime();
		int minutes = 0;
		if(time >0){
			minutes =(int) (time/(1000 * 60));
		}
		if(minutes >= 4095){
			minutes = 4095;
		}
		JSONObject requestParam = new JSONObject();
		requestParam.put("lingLingId", linglingId); // 令令ID
		requestParam.put("sdkKeys", sdkKeys);// 控制设备的sdkKey
		requestParam.put("startTime", startTime);// 开始时间（时间戳）
		requestParam.put("endTime", minutes);// 结果时间（单位：分钟；最大支持4095分钟）
		requestParam.put("effecNumber", count);// 有效次数（取值0~255）
		requestParam.put("strKey", "123456AA");// 自定义密钥（二维码加密用的key，8位16进制的字符串）
		String result = LingHttpConfig.doPost(
				LingHttpConfig.HOST_METHOD_ADD_VISITOR_QRCODE, requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
		return responseMap;
	}

	public void delVisitorQrCode(Long code) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("codeId",code);// 二维码ID
		LingHttpConfig.doPost(
				LingHttpConfig.HOST_METHOD_DEL_VISITOR_QRCODE, requestParam,lingConfig);
	}



	public void addCard(String cardCode, String[] deviceIds) {
		if(deviceIds.length >0){
			JSONObject requestParam = new JSONObject();
			requestParam.put("cardUids", new String[] { cardCode });// 卡的UID
			requestParam.put("deviceIds", deviceIds);// 设备的ID
			requestParam.put("endTime", 65535);// 过期时间
			LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_ADD_CARD,
					requestParam,lingConfig);
		}
	}

	public void delCard(String cardCode) {
		String[] deviceCodes = queryCard(cardCode);
		if(deviceCodes != null && deviceCodes.length >0){
			JSONObject requestParam = new JSONObject();
			requestParam.put("cardUids", new String[] { cardCode });// 卡的UID
			LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_DEL_CARD,
					requestParam,lingConfig);
		}
	}

	public void updateCard(String cardCode, String[] deviceIds) {
		if(deviceIds.length >0){
			String[] deviceCodes = queryCard(cardCode);
			if(deviceCodes == null || deviceCodes.length ==0){
				addCard(cardCode,deviceIds);
			}else{
				JSONObject requestParam = new JSONObject();
				requestParam.put("cardUids", new String[] { cardCode });// 卡的UID
				requestParam.put("newDeviceIds", deviceIds);// 设备的ID
				requestParam.put("newEndTime", 65535);// 过期时间
				LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_UPDATE_CARD,
						requestParam,lingConfig);
			}
		}else{
			delCard(cardCode);
		}
	}

	public String[] queryCard(String cardCode) {
		JSONObject requestParam = new JSONObject();
		requestParam.put("cardUids", new String[] { cardCode });// 卡的UID
		String result =  LingHttpConfig.doPost(LingHttpConfig.HOST_METHOD_QUERY_CARD,
				requestParam,lingConfig);
		Map resultMap = JSON.parseObject(result);
		String resultCode = (String)resultMap.get(JsonUtil.STATUS_CODE);
		if(JsonUtil.STATUS_CODE_SUCCESS.equals(resultCode)){
			Map responseMap = (Map)resultMap.get(JsonUtil.RESPONSE_RESULT);
			JSONObject devicesMap = (JSONObject)responseMap.get(cardCode);
			JSONArray jsonArray = (JSONArray) devicesMap.get("deviceCodes");
			return jsonArray.toArray(new String[]{});
		}else{
			return new String[] {};
		}

	}







}
