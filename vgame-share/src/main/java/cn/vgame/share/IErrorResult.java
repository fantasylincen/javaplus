package cn.vgame.share;

public interface IErrorResult {

	public abstract int getCode();

	public abstract Object[] getArgs();

	public abstract Object getError();

}