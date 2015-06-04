package cn.mxz.base;

import java.util.ArrayList;

import cn.javaplus.util.Util;



public class _code_generator {

	public static void main(String [] args) {
		ArrayList<String> ls = new ArrayList<String>();
		ls.add("hpPar");
		ls.add("attackPar");
		ls.add("mAttackPar");
		ls.add("defendPar");
		ls.add("mDefendPar");
		ls.add("speedPar");
		ls.add("critPar");
		ls.add("dodgePar");
		ls.add("blockPar");
		ls.add("rCritPar");
		ls.add("hitPar");
		ls.add("rBlockPar");
		ls.add("critAdditionPar");

		// Util.Str.firstToUpperCase(src)
		for (String s : ls) {
			// 生成TalentEmpty.java
			System.out.println("private float " + s + ";");
			System.out.println("@Override");
			System.out.println("public float get" + Util.Str.firstToUpperCase(s) + "() {");
			System.out.println("return " + s + ";");
			System.out.println("}");
			System.out.println("@Override");
			System.out.println("public void set" + Util.Str.firstToUpperCase(s) + "(float value) {");
			System.out.println("this. " + s + " = value;");
			System.out.println("}");
			// 生成TalentEmpty.java 结束
		}
	}
}
