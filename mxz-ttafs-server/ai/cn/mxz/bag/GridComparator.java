package cn.mxz.bag;
import java.util.Comparator;


/**
 * 格子比较器
 *
 * @author 	林岑
 * @time	2012年10月14日 23:26:40
 */

class GridComparator implements Comparator<Grid> {

	@Override
	public int compare ( Grid g1, Grid g2 ) {

		final int temp = g2.getTempletId() - g1.getTempletId();

		if(temp != 0) {

			return temp;
		}

		return g2.getCount() - g1.getCount();
	}
}