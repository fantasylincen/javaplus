package cn.javaplus.collections.tree;

/**
 * 二叉树节点
 * @author 	林岑
 * @since	2012年5月7日 09:21:02
 *
 * @param <T> 节点数据类型
 */
public class Node<T> {
	
	/**
	 * 获得父节点
	 */
	private Node<T> parent;

	/**
	 * 节点数据
	 */
	private T data;

	/**
	 * 左孩子节点
	 */
	private Node<T> leftChild;
	
	/**
	 * 右孩子节点
	 */
	private Node<T> rightChild;

	/**
	 * 节点所在行.
	 */
	private int row;
	
	/**
	 * 节点所在列.
	 */
	private int col;
	
	/**
	 * 树的深度, 只有当该节点被加入到某个树上, 才会有深度, 否则它的深度就为-1
	 */
	private int depth = -1;
	
	/**
	 * 新建一个数据为data的节点
	 * @param data
	 */
	public Node(T data) {
		this.setData(data);
	}
	
	/**
	 * 判断是不是根节点
	 * @return
	 */
	public final boolean isRoot() {
		return this.getDepth() == 0;
	}

	/**
	 * 判断是否是叶子节点
	 * @return
	 */
	public final boolean isLeaf() {
		return isLeftEmpty() && isRightEmpty();
	}
	
	/**
	 * 判断做孩子节点是否为空
	 * @return
	 */
	public final boolean isLeftEmpty() {
		return this.getLeftChild() == null;
	}

	/**
	 * 判断右孩子节点是否为空
	 * @return
	 */
	public final boolean isRightEmpty() {
		return this.getRightChild() == null;
	}

	/**
	 * 判断节点本身是不是左孩子节点
	 * @return
	 */
	public final boolean isLeft() {
		Node<T> parent = this.getParent();
		if(parent == null) {
			return false;
		} else {
			return parent.getLeftChild() == this;
		}
	}

	/**
	 * 判断节点本身是不是右孩子节点
	 * @return
	 */
	public final boolean isRight() {
		Node<T> parent = this.getParent();
		if(parent == null) {
			return false;
		} else {
			return parent.getRightChild() == this;
		}
	}

	/**
	 *  树的深度, 只有当该节点被加入到某个树上, 才会有深度, 否则它的深度就为-1
	 * @return
	 */
	public final int getDepth() {
		return depth;
	}

	/**
	 * 获得节点所在行
	 * @return
	 */
	public final int getRow() {
		return this.row;
	}
	
	/**
	 * 获得节点所在列
	 * @return
	 */
	public final int getCol() {
		return this.col;
	}
	
	/**
	 * 获得父节点
	 * @return
	 */
	public final Node<T> getParent() {
		return this.parent;
	}

	/**
	 * 获得左孩子节点
	 * @return
	 */
	public final Node<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * 获得右孩子节点
	 * @return
	 */
	public final Node<T> getRightChild() {
		return rightChild;
	}

	/**
	 * 获得自己的兄弟节点
	 * @return
	 */
	public final Node<T> getBrother() {
		if(this.isLeft()) {
			return this.getParent().getRightChild();
		} else if(this.isRight()) {
			return this.getParent().getLeftChild();
		} else {
			return null;
		}
	}

	/**
	 * 获得数据
	 * @return
	 */
	public final T getData() {
		return data;
	}

	/**
	 * 设置成根节点
	 */
	public void setRoot() {
		this.depth = 0;
	}

	/**
	 * 如果该节点不是根节点, 又没有任何前驱节点, 那么一定返回false
	 * 添加左孩子节点, 如果已经有左孩子节点, 那么就不进行任何操作, 返回false添加失败
	 * 如果添加成功, 返回true
	 * @param lChild
	 * @return		是否添加成功
	 */
	public boolean addLeftChild(Node<T> lChild) {
		if(!this.isRoot() && this.getParent() == null) {
			return false;
		}
		
		if(isLeftEmpty()) {
			this.leftChild = lChild;
			lChild.setParent(this);
			lChild.depth = this.getDepth() + 1;
			lChild.row = this.row + 1;
			lChild.col = this.col * 2;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 如果该节点不是根节点, 又没有任何前驱节点, 那么一定返回false
	 * 添加右孩子节点, 如果已经有右孩子节点, 那么就不进行任何操作, 返回false添加失败
	 * 如果添加成功, 返回true
	 * @param rChild
	 * @return		是否添加成功
	 */
	public boolean addRightChild(Node<T> rChild) {
		if(!this.isRoot() && this.getParent() == null) {
			return false;
		}
		if(isRightEmpty()) {
			this.rightChild = rChild;
			rChild.setParent(this);
			rChild.depth = this.getDepth() + 1;
			rChild.row = this.row + 1;
			rChild.col = this.col * 2 + 1;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 移除左孩子节点
	 * @return
	 */
	public Node<T> removeLeft() {
		Node<T> node = null;
		if(!isLeftEmpty()) {
			node = this.getLeftChild();
			this.leftChild.depth = -1;
			this.leftChild.setParent(null);
			this.leftChild = null;
			node.row = -1;
			node.col = -1;
		}
		return node;
	}
	
	/**
	 * 移除右孩子节点
	 * @return
	 */
	public Node<T> removeRight() {
		Node<T> node = null;
		if(!isRightEmpty()) {
			node = this.getRightChild();
			this.rightChild.depth = -1;
			this.rightChild.setParent(null);
			this.rightChild = null;
			node.row = -1;
			node.col = -1;
		}
		return node;
	}
	
	/**
	 * 移除它的某个节点, 如果没有指定的节点, 那么就返回false
	 * @param node
	 * @return
	 */
	public boolean remove(Node<T> node) {
		if(this.getLeftChild() == node) {
			this.removeLeft();
			return true;
		} else if(this.getRightChild() == node) {
			this.removeRight();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 设置节点的数据
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 设置父节点
	 * @param parent
	 */
	private void setParent(Node<T> parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return this.getData() == null ? "空数据" : this.getData().toString();
	}
}
