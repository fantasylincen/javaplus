package cn.mxz.invite;

public class CommitResultAdaptor implements CommitResult {

	private boolean hasError;

	public CommitResultAdaptor(boolean hasError) {
		this.hasError = hasError;
	}

	@Override
	public boolean isError() {
		return hasError;
	}

}
