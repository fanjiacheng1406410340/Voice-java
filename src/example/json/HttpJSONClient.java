package example.json;

import java.io.IOException;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import com.dahantc.api.commons.EncryptUtil;

/**
 * 
 * 类描述：大汉语音短信 HTTP JSON客户端
 * 
 * @version: 1.0
 * @author: 8516
 * @date: 2016年6月15日 下午2:23:49
 */
public class HttpJSONClient {

	private static final int CON_TIMEOUT = 60000;

	private static final int READ_TIMEOUT = 60000;

	private static final String DEFAULT_RESPONSE = "{\"result\":\"DH:1021\",\"desc\":\"数据包/消息内容为空\"}";

	/** http服务端地址 */
	private String serverUrlBase = null;

	/** 提交语音验证码短信相对地址 */
	private static final String SUBMIT_URL = "/json/voiceSms/SubmitVoc";

	/** 获取语音验证码短信状态报告相对地址 */
	private static final String GET_REPORT_URL = "/json/voiceSms/GetReport";

	public HttpJSONClient(String serverUrlBase) {
		this.serverUrlBase = getSchemaHost(serverUrlBase);
	}

	private String getSchemaHost(String host) {
		if (host != null) {
			if (host.indexOf("http://") != -1 || host.indexOf("https://") != -1) {
				return host;
			} else {
				return "http://" + host;
			}
		}
		return null;
	}

	/**
	 * 
	  * 方法描述：获取状态报告
	  * @param account
	  * @param password
	  * @return
	  * @throws Exception
	  * @author: 8516
	  * @date: 2016年6月15日 下午2:22:32
	 */
	public String getVoiceReport(String account, String password) throws Exception {
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", EncryptUtil.MD5Encode(password));
		String requestData = param.toString();
		
		return doPost(this.serverUrlBase + GET_REPORT_URL, requestData);
	}

	/**
	 * 
	 * 方法描述：提交语音验证码短信
	 * 
	 * @param account
	 * @param password
	 * @param list
	 * @return
	 * @throws Exception
	 * @author: 8516
	 * @date: 2016年6月15日 下午2:23:04
	 */
	public String sendAuthCodeVoiceSms(String account, String password, List<VoiceReqData> list) throws Exception {
		String resp = DEFAULT_RESPONSE;
		if (list != null && !list.isEmpty()) {
			JSONObject param = new JSONObject();
			param.put("account", account);
			param.put("password", EncryptUtil.MD5Encode(password));
			JSONArray array = new JSONArray();
			for (VoiceReqData data : list) {
				JSONObject paramData = new JSONObject();
				paramData.put("callee", data.getCallee());
				paramData.put("text", data.getText());
				paramData.put("medianame", data.getMedianame());
				paramData.put("msgid", data.getMsgid());
				paramData.put("calltype", data.getCalltype());
				paramData.put("playmode", data.getPlaymode());
				paramData.put("trytimes", data.getTrytimes());
				array.add(paramData);
			}
			param.put("data", array);
			String requestData = param.toString();
			resp = doPost(this.serverUrlBase + SUBMIT_URL, requestData);
		}

		return resp;
	}

	private String doPost(String url, String data) throws HttpException, IOException {
		String response = null;
		PostMethod postMethod = null;
		try {
			HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Connection", "close");
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			byte[] byteData = data.getBytes("utf-8");
			RequestEntity requestEntity = new ByteArrayRequestEntity(byteData);
			postMethod.setRequestEntity(requestEntity);
			HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(CON_TIMEOUT);
			managerParams.setSoTimeout(READ_TIMEOUT);
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} finally {
			if (postMethod != null)
				postMethod.releaseConnection();
		}
		return response;
	}
}