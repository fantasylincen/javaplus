public class Result {

	private String format;
	private boolean isMoningMeiDaka;

	public Result(boolean isMoningMeiDaka, String format) {
		this.isMoningMeiDaka = isMoningMeiDaka;
		this.format = format;
	}

	public String getDate() {
		return format;
	}

	public boolean isZaoShangMeiDaKa() {
		return isMoningMeiDaka;
	}

}
