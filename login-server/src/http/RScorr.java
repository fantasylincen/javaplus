package http;

public class RScorr {
	String code ;
	String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public RScorr( String code, String desc ){
		this.code = code;
		this.desc = desc;
	}
}
