package cn.javaplus.collections.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 二叉树
 * @author 	林岑
 * @since	2012年5月7日 10:17:31
 *
 * @param <T> 二叉树所包含的数据类型
 */
public abstract class AbstractTree<T> implements Iterable<Node<T>>{

	/**
	 * 根节点
	 */
	private Node<T> root;

	public AbstractTree() {
		this.root = new Node<T>(null);
		root.setRoot();
	}
	
	public Node<T> getRoot() {
		return this.root;
	}
	
	/**
	 * 获得 第layer层, 第col列的节点
	 * @param layer	层数 0开始
	 * @param col	列数 0开始
	 * @return
	 */
	public final Node<T> getNode(int layer, int col) {
		return this.getNode(layer, col, this.getRoot());
	}
	
	/**
	 * 定义node节点为 0, 0 ,  获得 第layer层, 第col列的节点
	 * @param layer	层数 0开始
	 * @param col	列数 0开始
	 * @return
	 */
	protected final Node<T> getNode(int layer, int col, Node<T> node) {
		if(layer < 0 || col < 0 || col >= Math.pow(2, layer)) {
			throw new IllegalArgumentException("参数有误");
		}
		if(layer == 0 && col == 0) {
			return node;
		} else {
			int half = (int) (Math.pow(2, layer) / 2);
			node = col >= half ? node.getRightChild() : node.getLeftChild();
			col %= half;	//折半
			layer --;		//前进一层
			return node == null ? node : getNode(layer, col, node);
		}
	}
	
	/**
	 * 获得所有叶子节点
	 * @return
	 */
	public List<Node<T>> getAllLeaf() {
		List<Node<T>> leafs = new ArrayList<Node<T>>();
		Iterator<Node<T>> it = this.iterator();
		while(it.hasNext()) {
			Node<T> node = it.next();
			if(node.isLeaf()) {
				leafs.add(node);
			}
		}
		return leafs;
	}
	
	/**
	 * 获得第layer层的所有节点
	 * @param layer		层数/深度
	 */
	public List<Node<T>> getLayer(int layer){
		List<Node<T>> leafs = new ArrayList<Node<T>>();
		for (Node<T> node : this) {
			if(node.getDepth() == layer) {
				leafs.add(node);
			}
		}
		return leafs;
	}

//	public static void main(String[] args) throws InterruptedException {
//		AbstractTree<Integer> at = new AbstractTree<Integer>() {
//		};
//		at.root.setData(0);
//		
//		Node<Integer> n1 = new Node<Integer>(1);
//		Node<Integer> n2 = new Node<Integer>(2);
//		Node<Integer> n3 = new Node<Integer>(3);
//		Node<Integer> n4 = new Node<Integer>(4);
//		Node<Integer> n5 = new Node<Integer>(5);
//		Node<Integer> n6 = new Node<Integer>(6);
//		Node<Integer> n7 = new Node<Integer>(7);
//		Node<Integer> n8 = new Node<Integer>(8);
//		
//		
//		
//		at.root.addLeftChild(n1);
//		n1.addLeftChild(n2);
//		n1.addRightChild(n3);
//		at.root.addRightChild(n4);
//		n4.addLeftChild(n5);
//		n4.addRightChild(n6);
//		n3.addLeftChild(n7);
//		n3.addRightChild(n8);
//		
//		
//		Iterator<Node<Integer>> it = at.iterator();
//		
//		while (it.hasNext()) {
//			Node<Integer> node = (Node<Integer>) it.next();
//			System.out.println(node.getData());
//			Thread.sleep(500);
//		}
//		for (Node<Integer> node : at) {
//
//			System.out.println(node.getData());
//			Thread.sleep(500);
//		}
//	}
	
	/**
	 * 先序遍历
	 */
	@Override
	public Iterator<Node<T>> iterator() {
		return new Iterator<Node<T>>() {

			private Node<T> now;
			
			/**
			 * 已被遍历过的节点
			 */
			private Set<Node<T>> areadyIterator = new HashSet<Node<T>>();
			
			@Override
			public boolean hasNext() {
				if(this.now == null) {
					return true;
				}
				return getNext(now) != null;
			}

			@Override
			public Node<T> next() {
				if(this.now == null) {
					this.now = AbstractTree.this.root;
					this.areadyIterator.add(this.now);
					return this.now;
				}
				
				
				Node<T> temp = this.getNext(this.now);
				if(temp == null) {
					throw new java.util.NoSuchElementException();
				}
				
				this.now = temp;
				this.areadyIterator.add(this.now);
				return this.now;
			}

			/**
			 * 获得now的下一个节点
			 * @param now
			 * @return
			 */
			private Node<T> getNext(Node<T> now) {
				if(!now.isLeftEmpty() && !isAreadyItr(now.getLeftChild())) { //左节点不为空, 并且左孩子节点没有被遍历过
					return now.getLeftChild();
				}
				
				if(!now.isRightEmpty() && !isAreadyItr(now.getRightChild())) {//右节点不为空
					return now.getRightChild();
				}
				
				//到这里, now既没有左节点也没有右节点
				
				if(now.isRoot()) {
					return null;
				}
				
				now = backAndNext(now);
				return now;
			}

			/**
			 * now不是root的前提下, 回退, 并返回下一个节点(先序遍历)
			 * @param now2
			 * @return
			 */
			private Node<T> backAndNext(Node<T> now) {
				now = now.getParent();//返回上一级
				
				if(now == null) {//已经返回到根节点, 不能再返回
					return null;
				}
				
				if(!isAreadyItr(now.getLeftChild())) {
					now = now.getLeftChild();
					return now;
				}
				
				if(!isAreadyItr(now.getRightChild())) {
					now = now.getRightChild();
					return now;
				}

				if(isAreadyItr(now)) {
					now = backAndNext(now);
				}
				
				return now;
			}

			/**
			 * 判断该节点是否被遍历过
			 * @param node
			 * @return
			 */
			private boolean isAreadyItr(Node<T> node) {
				return this.areadyIterator.contains(node);
			}

			@Override
			public void remove() {
				if(now.isRoot()) {
					AbstractTree.this.root = null;
				} else {
					now.getParent().remove(now);
				}
			}
		};
		
	}
}
