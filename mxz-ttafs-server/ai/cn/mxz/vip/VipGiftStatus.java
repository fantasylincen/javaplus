package cn.mxz.vip;

import java.util.List;

/**
 * 用户礼包购买情况
 *
 * @author 林岑
 *
 */
public interface VipGiftStatus {

	/**
	 * 礼包购买情况列表
	 *
	 * @return
	 */
	List<Gift> getGifts();

}
