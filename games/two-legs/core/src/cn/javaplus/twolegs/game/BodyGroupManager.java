package cn.javaplus.twolegs.game;
//package cn.javaplus.shooter.game;
//
//import java.util.Set;
//
//import cn.javaplus.shooter.util.Sets;
//
//
//public class BodyGroupManager {
//
//	
//	public class Collides {
//
//		private String base;
//		private String[] bodys;
//
//		public Collides(String base, String[] bodys) {
//			this.base = base;
//			this.bodys = bodys;
//		}
//		
//		public String getBase() {
//			return base;
//		}
//		
//		public Set<String> getBodys() {
//			return Sets.newHashSet(bodys);
//		}
//
//	}
//
//	private Set<Set<String>> unCollides = Sets.newHashSet();
//
//	private Set<Collides> collides = Sets.newHashSet();
//
//	/**
//	 * 两两不碰撞， 需要调用group方法后， 才分配这个计划
//	 * @param bodys
//	 */
//	public void setUnCollides(String... bodys) {
//		unCollides.add(Sets.newHashSet(bodys));
//	}
//
//	/**
//	 * base 和bodys中的任何一个元素都碰撞， 需要调用group方法后， 才分配这个计划
//	 * @param base
//	 * @param bodys
//	 */
//	public void setCollides(String base, String... bodys) {
//		Collides c = new Collides(base, bodys);
//		collides.add(c);
//	}
//
//	/**
//	 * 正式分配计划
//	 */
//	public void group() {
//		
//	}
//	
////	groupIndex：表示刚体的分组信息。相同groupIndex属性的刚体属性一个组，groupIndex为正数时，刚体只和同组的刚体发生碰撞。groupIndex为负数时，刚体只和同组之外的刚体进行碰撞。
////	categoryBits：表示刚体的分组信息，但不决定要碰撞的分组对象。另外，值得注意的，这个值必须是2的N次方。当然设置成其他值，程序不会报错，但是实际的碰撞分类效果，可能会出现意想不到的差错。
////	maskBits：表示刚体要碰撞的那个刚体分组对象。这个值通常是另外一个FilterData对象的categoryBits属性，表示只与该类刚体发生碰撞。如果要对多组刚体进行碰撞，可以设置maskBits为多个categoryBits的加合。如要和categoryBits分别为2和4的刚体组都进行碰撞，可以设置maskBits属性为6。
////	举个例子，比如，圆形刚体的categoryBits和maskBits分别为2和2，矩形刚体的categoryBits和maskBits分别为4和4。那么圆形与矩形刚体之间不会发生碰撞，只有相同形状刚体之间才会发生碰撞。
//	
//	public int getMaskBits(String name) {
//		
//	}
//
//	public int getCategoryBits(String name) {
//		
//	}
//	
//	public int getGroupIndex(String name) {
//		
//	}
//}
