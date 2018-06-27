package test;

import java.util.Date;

import com.bfd.facade.MerchantServer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class HainaApiTest {
	public static void main(String[] args) throws Exception{
	
		
		//登陆
		MerchantServer ms = new MerchantServer();			
	    String login_result=ms.login("newunion","newunion","LoginApi","3001028");			
	    JSONObject json=JSONObject.fromObject(login_result);			
	    String tokenid=json.getString("tokenid");
	   
	    JSONObject jso = new JSONObject();
		JSONObject reqData = new JSONObject();
		jso.put("apiName", "TrinityForceAPI");
		jso.put("tokenid", tokenid);
		reqData.put("meal", "BankFourPro");//不传默认调用所有打包产品。
		reqData.put("id","441481199005271736");//610602199202191221  //606021199202191221
	    reqData.put("cell","15626187425");
       reqData.put("bank_id","6212263602106459043");
		reqData.put("name","廖胡凯");
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
