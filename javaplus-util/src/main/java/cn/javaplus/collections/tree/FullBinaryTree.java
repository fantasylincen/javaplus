package cn.javaplus.collections.tree;

import java.util.Iterator;
import java.util.List;

/**
 * 满二叉树
 * @author 	林岑
 * @since	2012年5月7日 09:39:58
 */
public class FullBinaryTree<T> extends AbstractTree<T>{

	/**
	 * 深度
	 */
	private int depth;

	/**
	 * 新建一个满二叉树, 深度为depth
	 * @param depth
	 */
	public FullBinaryTree(int depth) {
		if(depth < 1) {
			throw new IllegalArgumentException("参数有误, 满二叉树的最小深度为1");
		}

		for (int i = 0; i < depth - 1; i++) {
			addLayer();
			this.depth++;
		}
	}

	/**
	 * 在所有叶子节点中
	 */
	private void addLayer() {
		List<Node<T>> allLeaf = getAllLeaf();
		for (Node<T> leaf : allLeaf) {
			leaf.addLeftChild(new Node<T>(null));
			leaf.addRightChild(new Node<T>(null));
		}
	}


	@Override
	public Iterator<Node<T>> iterator() {
		return new Iterator<Node<T>>() {

			/**
			 * 当前层
			 */
			private int layerNow;

			/**
			 * 当前列
			 */
			private int colNow;

			@Override
			public boolean hasNext() {
				if(this.layerNow == FullBinaryTree.this.depth + 1) {
					return false;
				} else {
					return true;
				}
			}

			@Override
			public Node<T> next() {
				Node<T> node = FullBinaryTree.this.getNode(this.layerNow, this.colNow);
				if(this.colNow == Math.pow(2, this.layerNow) - 1) {
					this.layerNow ++;
					this.colNow = 0;
				} else {
					this.colNow++;
				}
				return node;
			}

			@Override
			public void remove() {
				throw new RuntimeException("不可移除!");
			}
		};
	}

	public static void main(String[] args) {
		FullBinaryTree<Integer> c = new FullBinaryTree<Integer>(5);
		int i = 0;

		for (int j = 0; j < 5; j++) {
			List<Node<Integer>> layer = c.getLayer(j);
			for (Node<Integer> node : layer) {
				node.setData(i++);
			}
		}

		//		System.out.println(c.getRoot());
		//		System.out.println(c.getRoot().getLeftChild());
		//		System.out.println(c.getRoot().getRightChild());
		//		System.out.println(c.getRoot().getLeftChild().getLeftChild());
		//		System.out.println(c.getRoot().getLeftChild().getRightChild());
		//		System.out.println(c.getRoot().getRightChild().getLeftChild());
		//		System.out.println(c.getRoot().getRightChild().getRightChild());

//		System.out.println(c.getNode(0, 0).getData());
//		System.out.println(c.getNode(1, 0).getData());
//		System.out.println(c.getNode(1, 1).getData());
//		System.out.println(c.getNode(2, 0).getData());
//		System.out.println(c.getNode(2, 1).getData());
//		System.out.println(c.getNode(2, 2).getData());
//		System.out.println(c.getNode(2, 3).getData());

		for (Node<Integer> node : c) {
			System.out.println(node);
		}
	}

	/**
	 * 获得树的高度
	 * @return
	 */
	public int getDepth() {
		return this.depth;
	}
}
