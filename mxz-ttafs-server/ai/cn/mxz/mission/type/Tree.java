package cn.mxz.mission.type;

/*
 * Copyright 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * @author ycoppel@google.com (Yohann Coppel)
 * 
 * @param <T>
 *            Object's type in the tree.
 */
public class Tree<T> {
	
	

	public Tree() {
		super();
		// TODO 自动生成的构造函数存根
	}

	private T					head;

	private ArrayList<Tree<T>>	leafs	= new ArrayList<Tree<T>>();

	private Tree<T>				parent	= null;

	private HashMap<T, Tree<T>>	locate	= new HashMap<T, Tree<T>>();

	public Tree(T head) {
		this.head = head;
		locate.put(head, this);
	}

	public void addLeaf(T root, T leaf) {
		if (locate.containsKey(root)) {
			locate.get(root).addLeaf(leaf);
		} else {
			addLeaf(root).addLeaf(leaf);
		}
	}

	public Tree<T> addLeaf(T leaf) {
		Tree<T> t = new Tree<T>(leaf);
		leafs.add(t);
		t.parent = this;
		t.locate = this.locate;
		locate.put(leaf, t);
		return t;
	}

	public Tree<T> setAsParent(T parentRoot) {
		Tree<T> t = new Tree<T>(parentRoot);
		t.leafs.add(this);
		this.parent = t;
		t.locate = this.locate;
		t.locate.put(head, this);
		t.locate.put(parentRoot, t);
		return t;
	}

	public T getHead() {
		return head;
	}

	public Tree<T> getTree(T element) {
		return locate.get(element);
	}

	public Tree<T> getParent() {
		return parent;
	}

	public Collection<T> getSuccessors(T root) {
		Collection<T> successors = new ArrayList<T>();
		Tree<T> tree = getTree(root);
		if (null != tree) {
			for (Tree<T> leaf : tree.leafs) {
				successors.add(leaf.head);
			}
		}
		return successors;
	}

	public Collection<Tree<T>> getSubTrees() {
		return leafs;
	}

	public static <T> Collection<T> getSuccessors(T of, Collection<Tree<T>> in) {
		for (Tree<T> tree : in) {
			if (tree.locate.containsKey(of)) {
				return tree.getSuccessors(of);
			}
		}
		return new ArrayList<T>();
	}

	@Override
	public String toString() {
		return printTree(0);
	}

	private static final int	indent	= 2;

	private String printTree(int increment) {
		String s = "";
		String inc = "";
		for (int i = 0; i < increment; ++i) {
			inc = inc + " ";
		}
		s = inc + head;
		for (Tree<T> child : leafs) {
			s += "\n" + child.printTree(increment + indent);
		}
		return s;
	}
	
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>(1);
		
		tree.addLeaf( 2 ).addLeaf(3).addLeaf( 4 ).addLeaf(5);
		tree.getTree( 2 ).addLeaf(10).addLeaf(11).addLeaf(12).addLeaf(13);
		System.out.println( tree );
//		System.out.println( "3的父节点是" + tree.getTree( 3 ).getParent().getHead() );
//		System.out.println( "10的父节点是" + tree.getTree( 10 ).getParent().getHead() );
//		
//		System.out.println( tree.getSuccessors(tree.getTree( 2 ).getHead()) );
		System.out.println();
//		System.out.println( tree.getTree( 2 ).getSubTrees() );
//		
		String jsonString = JSON.toJSONString(tree);
		System.out.println( jsonString );
		
		//Tree<Integer> tree1 = JSON.parseObject(jsonString, Tree.class); 
//		System.out.println( tree1 );
		
		
//		tree.addLeaf( 2 );
	}
}
