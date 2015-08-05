package cn.javaplus.crazy.pocker;

public class DzRuleException extends RuntimeException {

	private static final long serialVersionUID = -8072668407690284296L;

	@Override
	public String getMessage() {
		return "error rule";
	}
}
