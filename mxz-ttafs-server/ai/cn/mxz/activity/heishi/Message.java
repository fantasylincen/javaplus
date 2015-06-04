package cn.mxz.activity.heishi;

public class Message {
	private int 		propId;
	private String 	nickName;
	
	
	public Message() {
		// TODO 自动生成的构造函数存根
	}
	public Message(int propId, String nick) {
		this.setPropId(propId);
		this.setNickName(nick);
	}
	public int getPropId() {
		return propId;
	}
	public String getNickName() {
		return nickName;
	}
	@Override
	public String toString() {
		return "Message [propId=" + getPropId() + ", nickName=" + getNickName() + "]";
	}
	/**
	 * @param nickName 要设置的 nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @param propId 要设置的 propId
	 */
	public void setPropId(int propId) {
		this.propId = propId;
	}
	
}
