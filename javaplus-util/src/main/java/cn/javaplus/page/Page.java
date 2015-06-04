package cn.javaplus.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 分页工具
 * @author 林岑
 *
 */
public class Page<T> implements Iterable<List<T>>{

	/**
	 * 所有记录
	 */
	private List<T> all = new ArrayList<T>();

	/**
	 * 每一页记录条数
	 */
	private final int countOfEveryPage;

	/**
	 * 新建一个分页内容
	 * @param all		所有元素
	 * @param cntEvery	每页数量
	 */
	public Page(List<T> all, int cntEvery) {
		if(cntEvery <= 0) {
			throw new RuntimeException("每页数量必须大于0: " + cntEvery);
		}

		this.all = all;
		this.countOfEveryPage = cntEvery;
	}

	/**
	 * 所有元素数量
	 */
	public int sizeAll() {
		return all.size();
	}

	/**
	 *
	 * 总计页码数量, 如果总数为 0 , 那么最大页数返回1
	 *
	 * @return
	 */
	public int getPageAll() {

		int sizeAll = sizeAll();

		if(sizeAll == 0) {

			return 1;
		}

		boolean hasM = sizeAll % countOfEveryPage != 0;

		return hasM ? sizeAll / countOfEveryPage + 1 : sizeAll / countOfEveryPage;
	}

	/**
	 * 获得指定页所有元素
	 *
	 * @param page 从1开始
	 * @return
	 * @exception	如果页码非法 : NoSuchElementException
	 */
	public List<T> getPage(int page) {

		int pageAll = getPageAll();

		if(page < 1 || page > pageAll) {

			if(pageAll == 0) {

				return new ArrayList<T>();

			}

			throw new NoSuchElementException("未找到指定页的元素, 页码:" + page + " pageAll = " + pageAll);

		} else {

			return PageUtil.get(all, page, countOfEveryPage);
		}
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new PageIterator();
	}

	public class PageIterator implements Iterator<List<T>> {

		public PageIterator() {
			indexEnd = countOfEveryPage - 1;
		}

		/**
		 * 当前页起始索引位置
		 */
		private int indexStart;

		/**
		 * 当前页结束索引位置
		 */
		private int indexEnd;

		@Override
		public boolean hasNext() {
			return isLastPage();
		}

		/**
		 * 跳转到下一页
		 *
		 * @return	同时返回下一页内容
		 * @Exception 如果已经是最后一页! 抛出RuntimeException
		 */
		@Override
		public List<T> next() {
			if(isLastPage()) {
				throw new RuntimeException("已经是最后一页了!");
			}
			indexStart += countOfEveryPage;
			indexEnd += countOfEveryPage;
			if(indexEnd > all.size() - 1) {
				indexEnd = all.size() - 1;
			}
			return all.subList(indexStart, indexEnd);
		}

		/**
		 * @return	当前页数, 从1开始
		 */
		public int pageNow() {
			return indexStart / countOfEveryPage + 1;
		}

		/**
		 * 判断是不是最后一页
		 * @return
		 */
		private boolean isLastPage() {
			return indexEnd == all.size() - 1;
		}

		/**
		 * doNothing
		 */
		@Override
		@Deprecated
		public void remove() {
		}
	}

//	public static void main(String[] args) {
//
//		List<Integer> all = new ArrayList<Integer>();
//
//		for (int i = 0; i < 105; i++) {
//
//			all.add(i + 1);
//		}
//
//		Page<Integer> page = new Page<Integer>(all, 10);
//
//		for (int i = 0; i < 12; i++) {
//
//			System.out.println(page.getPage(i + 1));
//		}
//	}
}
