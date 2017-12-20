package com.yjlcmsc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.yjlcmsc.entity.SmsVariableRequest;
import com.yjlcmsc.entity.SmsVariableResponse;
import com.yjlcmsc.util.ChuangLanSmsUtil;
import com.yjlcmsc.util.PropertiesUtil;




@Controller
@RequestMapping("/send")
public class SendController {
	
	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)

	private static String account=PropertiesUtil.getProperties("account");	
	// 用户平台API密码(非登录密码)
	private static String pswd=PropertiesUtil.getProperties("pswd");
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public SmsVariableResponse sendMessage(){
		System.out.println("进来了");
		String smsVariableRequestUrl = "http://smssh1.253.com/msg/variable/json";
		// 短信内容
		 String msg = "【253云通讯】您的验证码是{$var},{$var}分钟内有效";
		// 参数组
		 String params = "18665259865,123456,10";
		// 状态报告
		String report = "true";

		SmsVariableRequest smsVariableRequest = new SmsVariableRequest(account, pswd, msg, params, report);

		String requestJson = JSON.toJSONString(smsVariableRequest);

		String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);

		SmsVariableResponse smsVariableResponse = JSON.parseObject(response, SmsVariableResponse.class);

		return smsVariableResponse;
	}

}
