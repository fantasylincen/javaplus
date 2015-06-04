package cn.javaplus.monichaogu;

import com.opensymphony.xwork2.ActionSupport;

public class SellAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472474840041551771L;
	private String code;
	private int count;
	@Override
	public String execute() throws Exception {
		
		User user = ShiChang.getUser();
		user.sell(code, count);
		
		return super.execute();
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
