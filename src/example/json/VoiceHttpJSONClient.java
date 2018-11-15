package example.json;

import java.util.ArrayList;
import java.util.List;


public class VoiceHttpJSONClient {
	private static String account = "dh19999";
	private static String password = "ifjV34L9";
	

	public static void main(String[] args) {
		try {
			//初始化客户端
			HttpJSONClient client = new HttpJSONClient("http://voice.3tong.net");
			
			List<VoiceReqData> list = new ArrayList<VoiceReqData>();
//			list.add(new VoiceReqData(callee,text,medianame,msgid,calltype,playmode,trytimes,playtimes));	//平台默认提示音 验证码短信
			list.add(new VoiceReqData("13XXXXXXX4","您有新订单，请马上查看订单信息，尽快联系用户。","","f3b9af51feba343604d553ff8512e05a",0,0,0,2)); //自定义提示音 验证码短信
			String submitResp = client.sendAuthCodeVoiceSms(account, password, list);
			System.out.println(submitResp);
			
			String reportResp = client.getVoiceReport(account, password);
			
			System.out.println(reportResp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
