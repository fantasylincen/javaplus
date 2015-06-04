package cn.mxz.bag;


public interface BagRouter {

	GridDao getDAO(int stuffId);

	boolean isSuiPian(int id);

}
