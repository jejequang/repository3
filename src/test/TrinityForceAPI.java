package test;

import java.util.Date;

import com.bfd.facade.MerchantServer;

import net.sf.json.JSONObject;


public class TrinityForceAPI {
	public static void main(String[] args) throws Exception{
		
		MerchantServer ms=new MerchantServer();
		//��½
		String login_result=ms.login("newunionAPI","newunionAPI","LoginApi","3001028");
		System.out.println(login_result);
		JSONObject json=JSONObject.fromObject(login_result);
		
		String tokenid=json.getString("tokenid");
		/*
		 ��������API
		 ������ʽ:
		 	{
			    "apiName":"HainaApi",
			    "tokenid":"wangyu_dx84ovsui3ps1eslz5k7oklc6",
			    "reqData":{
			        "meal":"BankFour",
			        "id":"140502198811102244",
			        "cell":"13986671110",
			        "bank_id":"6217002270008198914",
			        "name":"����",
			    }
			}
			
			
			*/
		JSONObject jso = new JSONObject();
		JSONObject reqData = new JSONObject();
		jso.put("apiName", "TrinityForceAPI");
		jso.put("tokenid", tokenid);
		reqData.put("meal", "IdTwo_photo");//����Ĭ�ϵ������д����Ʒ��
		reqData.put("id","445122199211202210");//610602199202191221  //606021199202191221
		reqData.put("name","������");
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
