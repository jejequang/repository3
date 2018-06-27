package test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

/**
* @author wjq
* @version 创建时间：2017年12月22日 下午2:35:37
* 类描述
*/
public class dsd {
	
	public static List<dsd> list = new ArrayList<dsd>();
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
	/*	while(true){
			dsd d = new dsd();
			list.add(d);
		}*/
		/*System.out.println(100/0);*/
		System.out.println(""+null+"");
	}
	
	
	/**
	 * 对银行卡进行打马赛克
	 * @param cardNo
	 * @return<br>
	 * @author wjq, 2018年2月24日.<br>
	 */
	public static String mosaicCardNo(String cardNo){
		if(StringUtils.isEmpty(cardNo)){
			return "";
		}
		int len = cardNo.length();
		if(len > 12){
			cardNo = cardNo.substring(0, 6)+"******"+cardNo.substring(len - 3);
		}else if(len <= 3){
			cardNo = cardNo.substring(0, 1)+"**";
		}else{
			 String regEx = "1([0-9]{10})";
			// 编译正则表达式
			 Pattern pattern = Pattern.compile(regEx);
			 Matcher matcher = pattern.matcher(cardNo);
			 // 字符串是否与正则表达式相匹配
			 boolean rs = matcher.matches();
			if(len == 11 && rs){
				cardNo = cardNo.substring(0, 3)+"****"+cardNo.substring(7);
			}else{
				cardNo = cardNo.substring(0, 2)+"****";
			}
		}
		return cardNo;
	}
}
