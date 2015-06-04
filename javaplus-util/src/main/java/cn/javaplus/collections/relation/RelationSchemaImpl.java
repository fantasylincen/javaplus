package cn.javaplus.collections.relation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class RelationSchemaImpl<O, R extends Enum<?>> implements RelationSchema<O, R> {

	/**
	 * 所有对象节点
	 */
	private Map<O, Map<O, R>> allNode = new HashMap<O, Map<O, R>>();

	@Override
	public boolean put(O o) {
		if(o == null || allNode.containsKey(o)) {
			return false;
		}
		this.allNode.put(o, new HashMap<O,R>());
		return true;
	}

	private void check(O o1, O o2) {
		if(o1 == null || o2 == null ) 
			throw new NullPointerException();

		if(o1.equals( o2 )) 
			throw new IllegalArgumentException("不能将同一个对象用某种关系连接!");

		if(!this.allNode.containsKey(o1) || !this.allNode.containsKey(o2)) 
			throw new NotInRelationSchemaException();//请先将对象添加到关系图中了来.
	}

	@Override
	public R split(O o1, O o2) {

		if(o1 == null || o2 == null ) 
			throw new NullPointerException();

		Map<O, R> node1 = this.allNode.get(o1);		//获得附着在A上的所有节点
		Map<O, R> node2 = this.allNode.get(o2); 	//获得附着在B上的所有节点

		R r1 = null;
		R r2 = null;
		if(node1 != null) 
			r1 = node1.remove(o2);
		if(node2 != null) {
			r2 = node2.remove(o1);
		}
		return r1 != null ? r1 : r2;		//尽量返回不为空的一个, 但是有可能为空
	}

	@Override
	public boolean isDoubleWayLink(O o1, O o2) {
		R r1 = getRelation(o1, o2);
		R r2 = getRelation(o2, o1);
		if(r1 == null || r2 == null) 
			return false;
		else
			return r1 == r2;
	}

	@Override
	public boolean remove(O o) {
		if(o == null )
			return false;

		Map<O, R> nodes = this.allNode.get(o);

		if(nodes == null)
			return false;

		removeAllNode(o, nodes);	//将这个节点所指的所有终端上的关系全部移除

		this.allNode.remove(o); //移除对象
		return true;
	}


	/**
	 * 传入指定对象上附着的所有节点,  将这些节点所指的另一端的所有节点移除
	 * @param src		指定对象	(源对象)
	 * @param nodes		指定对象上的所有关系节点
	 */
	private void removeAllNode(O src, Map<O, R> nodes) {
		Set<O> keySet = nodes.keySet();
		for (O dst : keySet)
			this.allNode.get(dst).remove(src);
	}

	@Override
	public Map<O, R> getAllRelations(O o) {
		Map<O, R> map = this.allNode.get(o);
		if(map != null) {
			return map;
		} else {
			return new HashMap<O, R>();
		}
	}

	@Override
	public void putAll(Collection<O> coll) {
		for (O o : coll)
			this.put(o);
	}

	@Override
	public R getRelation(O o1, O o2) {
		Map<O, R> map = this.allNode.get(o1);
		return map != null ? map.get(o2) : null;
	}

	@Override
	public Set<O> getAllRelations(O o, R r) {

		Set<O> set = new HashSet<O>();

		Map<O, R> allNode = this.allNode.get(o);

		if(allNode != null)
			for (O k : allNode.keySet())
				if(allNode.get(k).equals(r))
					set.add(k);

		return set;
	}

	@Override
	public void link(O o1, O o2, R relation) {
		check(o1, o2);
		split(o1, o2);							//先断绝他们所有原有关系
		this.allNode.get(o1).put(o2, relation);	//单向连接到o1 ---> o2(前面的check方法保证了this.allNode.get(o1)不为空)
		this.allNode.get(o2).put(o1, relation);	//单向连接到o2 ---> o1(前面的check方法保证了this.allNode.get(o2)不为空)
	}

	@Override
	public void linkOneWay(O o1, O o2, R relation) {
		check(o1, o2);
		split(o1, o2);
		this.allNode.get(o1).put(o2, relation);	//单向连接到o1 ---> o2(前面的check方法保证了this.allNode.get(o1)不为空)
	}

	@Override
	public void putAndLink(O o1, O o2, R rt) {
		putIfUnExist(o1, o2);
		link(o1, o2, rt);		
	}


	@Override
	public void putAndLinkOneWay(O o1, O o2, R relation) {
		putIfUnExist(o1, o2);
		linkOneWay(o1, o2, relation);
	}

	/*
	 * 如果不存在, 就放入
	 * @param o1
	 * @param o2
	 */
	private void putIfUnExist(O o1, O o2) {
		if(!this.allNode.containsKey(o1)) 
			put(o1);

		if(!this.allNode.containsKey(o2)) 
			put(o2);
	}

//	public static void main(String[] args) {
//		RelationSchema<String, RelationType> rs = new RelationSchemaImpl<String, RelationType>();
//		//		Random r = new Random();
//		//		String [] persons = new String[26];
//		//		for (int i = 0; i < persons.length; i++) {
//		//			persons[i] = (char) ('A' + i) + "";
//		//		}
//		//
//		//
//		//
//		//				for (int i = 0; i < persons.length; i++) {
//		//					for (int j = 0; j < persons.length; j++) {
//		//						String p1 = persons[r.nextInt(persons.length)];
//		//						String p2 = persons[r.nextInt(persons.length)];
//		//						
//		//						if(p1.equals(p2)) {
//		//							continue;
//		//						}
//		//						rs.putAndLink(p1, p2, getRandomRL());
//		//					}
//		//				}
//		//				
//		//				
//		//				for (int i = 0; i < persons.length; i++) {
//		//					for (int j = 0; j < persons.length; j++) {
//		//						System.out.println(rs.getRelation(persons[i], persons[j]));
//		//					}
//		//				}
//		//
//		//
//
//
//
//		//		rs.putAndLink("A", "B", RelationType.FRIENDLY);
//		//		rs.putAndLink("A", "C", RelationType.FRIENDLY);
//		//		rs.putAndLink("C", "B", RelationType.UN_FRIENDLY);
//		//		rs.putAndLink("D", "B", RelationType.FRIENDLY);
//		//		rs.putAndLink("E", "B", RelationType.FRIENDLY);
//		//		rs.putAndLink("A", "F", RelationType.FRIENDLY);
//		rs.putAndLinkOneWay("D", "E", RelationType.UN_FRIENDLY);
//		rs.putAndLinkOneWay("E", "D", RelationType.UN_FRIENDLY);
//		rs.putAndLinkOneWay("A", "B", RelationType.UN_FRIENDLY);
//
////		rs.split("E", "D");
//		//		System.out.println(rs.getAllRelations("A"));
//		System.out.println(rs.getAllRelations("D"));
//		System.out.println(rs.getAllRelations("E"));
////		rs.split("E", "D");
//		System.out.println(rs.getRelation("E", "D"));
////		rs.split("E", "D");
//		rs.remove("D");
//		System.out.println(rs.getRelation("D", "E"));
////		System.out.println(rs.getRelation("A", "B"));
//		
////		rs.putAndLink("A", "B", RelationType.FRIENDLY);
////		
////		
////		System.out.println(rs.getRelation("B", "A"));
////		System.out.println(rs.getRelation("A", "B"));
////		Set<String> set = new HashSet<String>();
////		set.add("B");
////		set.add("A");
////		set.add("C");
////		rs.putAll(set );
////		rs.remove("B");
////		System.out.println(rs.getRelation("B", "A"));
////		System.out.println(rs.getRelation("A", "B"));
////		System.out.println(rs.isDoubleWayLink("D", "E"));
////		System.out.println(rs.isDoubleWayLink("E", "D"));
////		System.out.println(rs.isDoubleWayLink("A", "D"));
////		System.out.println(rs.isDoubleWayLink("E", "C"));
////		System.out.println(rs.isDoubleWayLink("E", "D"));
////		System.out.println(rs.isDoubleWayLink("E", "D"));
//		
//
//		System.out.println("成功结束");
//	}
//
//	private static RelationType getRandomRL() {
//		Random r = new Random();
//		switch (r.nextInt(2)) {
//		case 0:
//			return RelationType.FRIENDLY;
//		default:
//			return RelationType.UN_FRIENDLY;
//		}
//	}
}
