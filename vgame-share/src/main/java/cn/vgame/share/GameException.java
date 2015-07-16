package cn.vgame.share;




public class GameException extends RuntimeException {

	private static final long serialVersionUID = 43498513480845461L;

	IErrorResult errorResult;

	public GameException(IErrorResult errorResult) {
		this.errorResult = errorResult;
	}

	public IErrorResult getErrorResult() {
		return errorResult;
	}
	
	@Override
	public String getMessage() {
		return errorResult.getError() + "";
	}

}
