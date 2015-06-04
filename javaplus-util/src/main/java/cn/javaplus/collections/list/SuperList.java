package cn.javaplus.collections.list;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * 高级链表, 适用于排行榜快速更新
 * 
 * 该方法提供了adjustPos方法, 能够快速的将元素移动到合适的位置上
 * 
 * 为商盟排行榜量身定做的一个List, 快速, 高效. 哈哈, 也适用于其他排行榜问题
 * 
 * @author 林岑
 *
 * @param <E>
 */
public class SuperList<E> implements Iterable<E> {

	/**
	 * 比较器
	 */
	private final Comparator<E> cop;

	private LinkedList<E> lis = new LinkedList<E>();

	/**
	 * 新生成一个列表, 传入一个比较器
	 * @param cop
	 */
	public SuperList(Comparator<E> cop) {
		this.cop = cop;
	}

	/**
	 * 让e自动调整到一个合适的位置(将e移动到比自己小, 比自己大的元素之间)
	 * @param e
	 * @param cop
	 * @return		如果e不在容器中, 或者e已经在一个合适的位置, 那么就返回false
	 */
	public boolean adjustPos(Object e) {

		ListIterator<E> it = this.listIterator(e);//获得指向e的迭代器
		if(it == null) {
			return false;
		}

		//没有后继节点且大于前驱节点   或者  没有前驱节点且小于后继节点, 或者小于后继节点且大于前驱节点
		while(true) {
			if(changeToNext(it)) {		 //先尝试与后者交换, 如果失败, 试着与前者交换
				continue;
			} else if(changeToPre(it)){ //如果与前者也交换失败了, 那么就证明他在一个正确的位置上的.
				continue;
			} else {
				return true;
			}
		}
	}

	/**
	 * 添加一个元素, 同时校准它的位置
	 */
	public boolean add(E e) {
		boolean b = lis.add(e);
		if(b) this.adjustPos(e);
		return b;
	}

	/**
	 * 将所有元素添加到容器中,添加之后, 是排好序的
	 * @param c
	 * @return
	 */
	public boolean addAll(Collection<? extends E> c) {
		boolean b = this.lis.addAll(c);
		if(b) Collections.sort(this.lis, cop);
		return b;
	}



	/**
	 * 试着与it的前驱节点进行交换, 如果没有前驱节点, 就返回false
	 * 
	 * 如果it所在的节点, 比它的前驱节点小, 才发生交换
	 * 
	 * 交换之后, it指向它原来指向的对象
	 * 
	 * 调用前后, it最后调用的都是next方法
	 * 
	 * @param it	迭代器, 该迭代器指向容器中某个对象
	 * @param cop	比较器
	 * @param isLastNext 
	 * @return		是否发生交换
	 * 
	 * 
	 */
	private boolean changeToPre(ListIterator<E> it) {

		it.previous();
		if(it.hasPrevious()) {

			E p = it.previous();
			E now = it.next();
			now = it.next();

			if(cop.compare(p, now) <= 0) {
				return false;
			}

			it.set(p);
			it.previous();
			it.previous();
			it.set(now);
			it.next();
			return true;

		} else {
			it.next();
			return false;
		}
	}


	/**
	 * 试着与it的后继节点进行交换, 如果没有后继节点, 就返回false
	 * 
	 * 如果it所在的节点, 比它的后继节点大, 才发生交换
	 * 
	 * 交换之后, it指向它原来指向的对象
	 * 
	 * 调用前后, it最后调用的都是next方法
	 * 
	 * @param it	迭代器, 该迭代器指向容器中某个对象
	 * @param cop	比较器
	 * @param isLastNext 
	 * @return		是否发生交换
	 */
	private boolean changeToNext(ListIterator<E> it) {
		if(it.hasNext()) {

			E next = it.next();
			it.previous();
			E now =	it.previous();

			if(cop.compare(next, now) >= 0) {
				it.next();
				return false;
			}

			it.set(next);
			it.next();
			it.next();
			it.set(now);
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 返回指向e的迭代器, 如果该容器不包含e, 那么就返回null, 最后一次一定是调用的next
	 * @param e
	 * @return
	 */
	private ListIterator<E> listIterator(Object e) {
		ListIterator<E> it = lis.listIterator();
		while(it.hasNext()) {
			if(it.next() == e) {
				return it;
			}
		}
		return null;
	}

	//
	private static final Comparator<Integer> copa = new Comparator<Integer>() {

		/**
		 * 如果o1比o2大, 就返回一个正整数
		 * @param o1
		 * @param o2
		 * @return
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	};

	//	
	public static void main(String[] args) {
		SuperList<Integer> lis = new SuperList<Integer>(copa);

		boolean f = true;
		while(f) {
			for (int i = 0; i < new Random().nextInt(10); i++) {
				lis.add(new Random().nextInt(10));
			}
			lis.adjustPos(new Random().nextInt(10));
			
			for (Integer in : lis) {
				System.out.print(in + ", ");
			}
			System.out.println();
			lis.clear();
		}
	}
	//		
	//		for (int i = 0; i < 10; i++) {
	//			lis.add(i);
	//		}
	//
	//		lis.add(5, 50);
	//		lis.add(5, 49);
	//		lis.add(5, 48);
	//		lis.add(5, 47);
	//		lis.add(5, 46);
	//		lis.add(0,100);
	//		
	//
	//		System.out.println(lis);
	//		lis.adjustPos(100, cop);
	//		lis.adjustPos(100, cop);
	//		lis.adjustPos(100, cop);
	//		lis.adjustPos(100, cop);
	//		lis.adjustPos(100, cop);
	//		System.out.println(lis);
	//		lis.adjustPos(0, cop);
	//		lis.adjustPos(1, cop);
	//		System.out.println(lis);
	//		lis.adjustPos(47, cop);
	//		System.out.println(lis);
	//		lis.adjustPos(48, cop);
	//		System.out.println(lis);
	//		lis.adjustPos(49, cop);
	//		
	//		
	//		List<Integer> list2 = new ArrayList<Integer>();
	//		
	//		for (int i = 0; i < 10; i++) {
	//			list2.add(new Random().nextInt(500));
	//		}
	//		
	//		System.out.println();
	//		System.out.println(list2);
	//		Collections.sort(list2, cop);
	//		System.out.println(list2);
	//	}

	public void clear() {
		this.lis.clear();
	}

	@Override
	public Iterator<E> iterator() {
		return lis.iterator();
	}

	public int indexOf(E e) {
		return this.lis.indexOf(e);
	}

	public List<E> getList() {
		return this.lis;
	}

}
