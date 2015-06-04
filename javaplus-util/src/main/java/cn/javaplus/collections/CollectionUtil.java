package cn.javaplus.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CollectionUtil {
	
	/**
	 * 获得指定个数的List
	 * @param winer 	
	 * @param winerNum
	 */
	public static<T> List<T> getRandomNumList(Collection<? extends T> list,
			int num) {
		Random r = new Random();
		List<T> l1 = new ArrayList<T>();
		for (T s : list) // 遍历Collection
			l1.add(s);
		if(l1.size() <= num) // 如果小于指定num 就取list
			return l1;
		List<T> l2 = new ArrayList<T>();
		for (int i = 0; i < num; i++){ // 从Collection中随机取num个
			int j = r.nextInt(l1.size() - i);
			l2.add(l1.get(j));
			l1.remove(j);
		}
		return l2;
	}
}
