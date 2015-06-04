package cn.mxz.invite;

public class CodeUIImpl implements CodeUI {

	private String code;

	public CodeUIImpl(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

}
