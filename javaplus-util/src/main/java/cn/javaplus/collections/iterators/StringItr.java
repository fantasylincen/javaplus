package cn.javaplus.collections.iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 一个可以把所有成员分段截取的迭代器
 */
public class StringItr implements Iterator<String[]>{
	
	private final Iterator<String[]> it;
	
	/**
	 * 一个可以把所有成员分段截取的迭代器
	 * @param members		成员列表
	 * @param sizeSection	分段大小
	 */
	public StringItr(Collection<String> members, int sizeSection) {

		List<String[]> all = new ArrayList<String[]>();
		
		int remain = members.size();
		
		Iterator<String> it = members.iterator();
		
		while(true) {
			
			if(remain < sizeSection) { 
				
				all.add(getNext(it, remain));
				
				break;
				
			} else {
				
				all.add(getNext(it, sizeSection));
			}
			
			remain -= sizeSection;
		}
		
		this.it = all.iterator();
	}

	private String[] getNext(Iterator<String> it, int size) {
		
		String[] ls = new String[size];
		
		for (int i = 0; i < size; i++) {
			
			ls[i] = it.next();
		}
		
		return ls;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public String[] next() {
		return it.next();
	}


	@Override
	public void remove() {
		it.remove();
	}
}
