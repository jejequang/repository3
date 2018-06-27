package test;

import java.util.Date;


import com.bfd.facade.MerchantServer;

import net.sf.json.JSONObject;

/**
* @author wjq
* @version 创建时间：2018年2月8日 上午11:28:16
* 类描述
*/
public class testApi {
public static void main(String[] args) throws Exception {

	MerchantServer ms=new MerchantServer();
	//登陆
	String login_result=ms.login("newunionAPI","newunionAPI");
	System.out.println(login_result);
	JSONObject json=JSONObject.fromObject(login_result);
	
	String tokenid= null;
	String login_res_str = ms.login("newunionAPI","newunionAPI", "LoginApi", "3001028");
		JSONObject loginJson=JSONObject.fromObject(login_res_str);
		if(loginJson.containsKey("tokenid")){
			tokenid = loginJson.getString("tokenid");
		}else{
			System.out.println("返回结果异常，无tokenid！结果为："+login_res_str);
		}
	
	
	JSONObject jso = new JSONObject();
	JSONObject reqData = new JSONObject();
	jso.put("apiName", "HainaApi");
	reqData.put("meal", "EduLevel");//不传默认调用所有打包产品。
	reqData.put("id","432502199510258312");//610602199202191221  //606021199202191221
	reqData.put("cell","");
	reqData.put("name","李伟雄");
	jso.put("tokenid", tokenid);
 	jso.put("reqData",reqData);
	long begin=new Date().getTime();
	String portrait_result=ms.getApiData(jso.toString());
	long end=new Date().getTime();

	
	
	System.out.println("time:"+(end-begin)+" ms");
	System.out.println("parameter:"+jso.toString());
	System.out.println("result:"+portrait_result);
	System.out.println("------------------------------------------");
}
}
