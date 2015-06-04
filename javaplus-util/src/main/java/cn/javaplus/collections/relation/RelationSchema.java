package cn.javaplus.collections.relation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 这是一个关系图解<br>
 * 
 * 表示了多个 对象之间的关系<br>
 * 
 * 比如现在有多个人(A B C D E F G),  他们之间有多种关系<br>
 * 
 * AB:   	友好<br>
 * BC:   	敌对<br>
 * CA:   	朋友<br>
 * EF:    	同学<br>
 * ......<br>
 * ......<br>
 * 
 * 那么你用这个类, 就能完全记录这些关系 , 并且 表现出来.<br>
 * 
 * 你可以生成一个该类的实例当作一个容器, 用来装入(A B C D E F G), 并可以设置 或者取消 两两之间的关系. link方法<br>
 * 而且该类能够快速的遍历每个节点(每个人)  与 他 有关系的所有节点(人).<br>
 * 
 * @author 林岑
 * @since    2012年4月10日 22:33:06
 *
 */
public interface RelationSchema<O, R extends Enum<?>> {
	
	/**
	 * 在关系图当中放入一个对象, 如果已经存在, 那么就不会执行任何操作
	 * @param o   被放入对象
	 * @return 	是否放入成功
	 */
	boolean put(O o);
	
	/**
	 * 移除关系图中的某个对象, 移除的同时, 移除它所有的关系
	 * @param o
	 * @return 	是否移除成功
	 */
	boolean remove(O o);
	
	/**
	 * 在o1,o2有关系的前提下, 断绝o1, o2的关系. 并返回他们的关系类型
	 * 
	 * 在断绝之前, 可能3种情况: 没关系, 双向关系, 单向关系....
	 * 
	 * 如果他们之前没有关系, 那么就返回null
	 * 
	 * 如果o1, o2任何一个不在关系图中, 那么就会抛出NotInRelationSchemaException
	 * 
	 * @param o1         对象1
	 * @param o2         对象2
	 * @return      之前的关系
	 * @exception NotInRelationSchemaException
	 */
	R split(O o1, O o2);
	
	/**
	 * 列出指定对象周围的所有关系节点(用Map表示),通过这些节点, 客户端可以找到所有与它有关系的对象, 和他们有什么关系
	 * @param o  被指定的对象
	 * @return 所有关系节点, 键为关系类型, 值为所有与之有关系的节点
	 */
	Map<O, R> getAllRelations(O o);

	/**
	 * 将容器中的所有对象, 全部放到关系图中
	 * @param coll
	 */
	void putAll(Collection<O> coll);

	/**
	 * 获得两者的关系类型
	 * 
	 * 单项关系:
	 * 	如果之前调用过 #linkOneWay(o1, o2)
	 * 	该方法会有返回值
	 * 
	 * 	如果之前调用过 #linkOneWay(o2, o1)
	 * 	该方法会返回null
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	R getRelation(O o1, O o2);
	
	/**
	 * 获得与o有r关系的所有节点
	 * 
	 * 
	 * 如果包含单项关系:
	 * 	之前调用过 #linkOneWay(o, XX)
	 * 	该方法返回值中包含o --> XX的关系
	 * 
	 * 	之前调用过 #linkOneWay(XX, o)
	 * 	该方法返回值中, 不包含 o ---> XX的关系
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	Set<O> getAllRelations(O o, R r);

	/**
	 * 给两个对象建立某种关系relation, 前提是两者都在关系图中, 否则就会抛出NotInRelationSchemaException
	 * 如果两者之前有关系, 那么该方法就会改变两者之前的关系
	 * 
	 * 注意: 该方法执行前, 会解除两者之前的任何关系
	 * 
	 * @param o1         对象1
	 * @param o2         对象2
	 * @param relation  关系
	 * @exception NotInRelationSchemaException
	 */
	void link(O o1, O o2, R relation);

	/**
	 * 在关系图没有元素的时候, 自动将元素加到关系图中, 同时建立两者关系
	 * 
	 * 注意: 该方法执行前, 会解除两者之前的任何关系
	 * 
	 * @param o1
	 * @param o2
	 * @param rt
	 */
	void putAndLink(O o1, O o2, R rt);

	/**
	 * 单向连接, o1指向o2, 
	 * 
	 * 该方法调用后  (可以通过o1, 看到他和o2的关系, 但是o2看不到他和o1的关系)
	 * 
	 * 	#getRelation(o1, o2) 会有返回值
	 * 	#getRelation(o2, o1) 会返回空
	 * 
	 * 注意: 该方法执行前, 会解除两者之前的任何关系
	 * 
	 * @param o1
	 * @param o2
	 * @param relation
	 */
	void linkOneWay(O o1, O o2, R relation);
	
	/**
	 * 单向连接, o1指向o2
	 * 
	 * 如果o1或者o2不存在于容器中, 那么会先将其放入后, 然后连接
	 * 如果已经存在于容器中, 那么直接连接
	 * 
	 * 
	 * 该方法调用后	(可以通过o1, 看到他和o2的关系, 但是o2看不到他和o1的关系)
	 * 
	 * 	#getRelation(o1, o2) 会有返回值
	 * 	#getRelation(o2, o1) 会返回空
	 * 
	 * 注意: 该方法执行前, 会解除两者之前的任何关系
	 * 
	 * @param o1
	 * @param o2
	 * @param relation
	 */
	void putAndLinkOneWay(O o1, O o2, R relation);

	/**
	 * 判断o1, o2是否有双向连接的关系(前提是两者都在关系图中. 否则一定返回false)
	 * @param o1
	 * @param o2
	 * @return
	 */
	boolean isDoubleWayLink(O o1, O o2);
}