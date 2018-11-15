package example.json;

import java.io.Serializable;

public class VoiceReqData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String callee; // 被叫号码。外呼验证码时只能有一个号码，其它外呼可以有多个号码

	private String text; // 语音文本，验证码

	private String medianame; // 语音文件名称，格式 wav，播放多个文件用英文分号隔开。与text不能同时为空。

	private String msgid; // 消息ID

	private int calltype; // 外呼类型。0,"普通文本呼叫"；1, "验证码呼叫"；2, "语音文件呼叫" ；3,"混合呼叫"
							// 默认为0

	private int playmode; // 是否播放文本或语音文件，或同时播放文本与语音文件时的先后播放顺序 。
							// 0-只播放文本；1-只播放语音文件；2-先播放文本再播放语音文件；3-先播放语音文件再播放文本
							// 。默认为0
	private int trytimes;	//重呼次数，默认0，不重呼
	
	private int playtimes;  //播放次数，默认1次

	public VoiceReqData(String callee, String text, String medianame, String msgid, int calltype, int playmode,int trytimes,int playtimes) {
		super();
		this.callee = callee;
		this.text = text;
		this.medianame = medianame;
		this.msgid = msgid;
		this.calltype = calltype;
		this.playmode = playmode;
		this.trytimes=trytimes;
		this.playtimes=playtimes;
	}

	public VoiceReqData() {
		super();
	}

	public String getCallee() {
		return callee;
	}

	public void setCallee(String callee) {
		this.callee = callee;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMedianame() {
		return medianame;
	}

	public void setMedianame(String medianame) {
		this.medianame = medianame;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public int getCalltype() {
		return calltype;
	}

	public void setCalltype(int calltype) {
		this.calltype = calltype;
	}

	public int getPlaymode() {
		return playmode;
	}

	public void setPlaymode(int playmode) {
		this.playmode = playmode;
	}
	public int getTrytimes(){
		return trytimes;
	}
	public void setTrytimes(int trytimes){
		this.trytimes=trytimes;
	}
	public int getPlaytimes(){
		return playtimes;
	}
	public void setPlaytimes(int playtimes){
		this.playtimes=playtimes;
	}
}
