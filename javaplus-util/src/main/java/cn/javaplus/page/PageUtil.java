package cn.javaplus.page;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页工具
 * @author 	林岑
 * @since	2012年4月10日 10:15:49
 */
public class PageUtil {
	
	
	/**
	 * 获得当前页中的记录的
	 *  开始位置, 和结束位置(从0开始)
	 * 
	 * @param page				当前所在页(从1开始)
	 * @param allSize			总记录条数
	 * @param countOfEveryPage	每页记录条数
	 * 
	 * @return	开始位置放在Point的x变量中, 结束位置放在y变量中
	 */
	private static final Point getArea(int page, int allSize, int countOfEveryPage) {
		Point p = new Point();

		int start = (page - 1) * countOfEveryPage;

		int end = start + countOfEveryPage;

		end = end > allSize ? allSize : end;	//边界限定
		
		p.x = start;
		
		p.y = end;
		
		return p;
	}
	
	/**
	 * 获得当前页的记录条数
	 * @param page
	 * @param allSize
	 * @param countOfEveryPage
	 * @return
	 */
	private static final int getPageCount(int page, int allSize, int countOfEveryPage) {
		Point area = getArea(page, allSize, countOfEveryPage);
		return area.y - area.x;
	}
	
	/**
	 * 获得第page页的List的内容
	 * @param list					所有元素列表
	 * @param page					当前页数 (从1开始)
	 * @param countOfEveryPage		每页元素数量
	 */
	public final static<T> List<T> get(List<T> list, int page, int countOfEveryPage) {
		
		Point area = getArea(page, list.size(), countOfEveryPage);	//获取一个页 所跨的 下标 区域
		
		if(area.getX() > area.getY()) {
			System.err.println("页码超限, 请前端检测页码范围是否算错!");
		}
		
		List<T> subList = list.subList(area.x, area.y);
		
		return new ArrayList<T>(subList);						//分页
	}
	
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 123; i++) {
			list.add(i + "");
		}
		
		for (int i = 1; i <= 13; i++) {
			
			System.out.print("第" + i + "页:" + get(list, i, 10));
			System.out.println("当前页有" + getPageCount(i, 123, 10) + "个元素");
		}
		
		
	}
}
