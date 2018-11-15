package example.xml;

import java.util.UUID;

import com.dahantc.api.voice.xml.HttpXMLClient;

public class VoiceHttpXMLClient {
	private static String URL = "http://voice.3tong.net";
	private static String account = "dh12312";
	private static String password = "wFb6tO6z";
	private static String phone = "13628691654";
	private static String code = "03333";
	private static String msgId = UUID.randomUUID().toString();

	public static void main(String[] args) {
		try {
			String send_res = HttpXMLClient.sendAuthCodeVoiceSms(URL, account, password, phone, code, msgId);
			System.out.println(send_res);
			String report_res = HttpXMLClient.getVoiceReport(URL, account, password);
			System.out.println(report_res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
