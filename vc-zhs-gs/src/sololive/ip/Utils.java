package sololive.ip;

import java.io.UnsupportedEncodingException;

public class Utils {

	/**
	 * 从ip的字符串形式得到字节数组形式
	 * 
	 * @param ip 字符串形式的ip
	 *            
	 * @return 字节数组形式的ip
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	public static void main(String args[]) {
		
		byte[] a = getIpByteArrayFromString( "122.4.79.65" );
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		System.out.println(getIpStringFromBytes(a));
	}

	/**
	 * 对原始字符串进行编码转换，如果失败，返回原始的字符串
	 * 
	 * @param s 原始字符串
	 *            
	 * @param srcEncoding 源编码方式
	 *           
	 * @param destEncoding 目标编码方式
	 *            
	 * @return 转换编码后的字符串，失败返回原始字符串
	 */
	public static String getString(String s, String srcEncoding,
			String destEncoding) {
		try {
			return new String(s.getBytes(srcEncoding), destEncoding);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b 字节数组
	 *            
	 * @param encoding 编码方式
	 *            
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, String encoding) {
		try {
			return new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b 字节数组
	 *            
	 * @param offset  要转换的起始位置
	 *           
	 * @param len 要转换的长度
	 *            
	 * @param encoding 编码方式
	 *            
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len,
			String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * @param ip
	 *            ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuffer sb = new StringBuffer();
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}
}

// 三、IPSeeker.java
//
// /*
// * LumaQQ - Java QQ Client
// *
// * Copyright (C) 2004 luma < stubma@163.com>
// *
// * This program is free software; you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation; either version 2 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program; if not, write to the Free Software
// * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// */
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.RandomAccessFile;
// import java.nio.ByteOrder;
// import java.nio.MappedByteBuffer;
// import java.nio.channels.FileChannel;
// import java.util.ArrayList;
// import java.util.Hashtable;
// import java.util.List;
// /**
// * * 用来读取QQwry.dat文件，以根据ip获得好友位置，QQwry.dat的格式是
// * 一. 文件头，共8字节
// * 1. 第一个起始IP的绝对偏移， 4字节
// * 2. 最后一个起始IP的绝对偏移， 4字节
// * 二. "结束地址/国家/区域"记录区
// * 四字节ip地址后跟的每一条记录分成两个部分
// * 1. 国家记录
// * 2. 地区记录
// * 但是地区记录是不一定有的。而且国家记录和地区记录都有两种形式
// * 1. 以0结束的字符串
// * 2. 4个字节，一个字节可能为0x1或0x2
// * a. 为0x1时，表示在绝对偏移后还跟着一个区域的记录，注意是绝对偏移之后，而不是这四个字节之后
// * b. 为0x2时，表示在绝对偏移后没有区域记录
// * 不管为0x1还是0x2，后三个字节都是实际国家名的文件内绝对偏移
// * 如果是地区记录，0x1和0x2的含义不明，但是如果出现这两个字节，也肯定是跟着3个字节偏移，如果不是
// * 则为0结尾字符串
// * 三. "起始地址/结束地址偏移"记录区
// * 1. 每条记录7字节，按照起始地址从小到大排列
// * a. 起始IP地址，4字节
// * b. 结束ip地址的绝对偏移，3字节
// *
// * 注意，这个文件里的ip地址和所有的偏移量均采用little-endian格式，而java是采用
// * big-endian格式的，要注意转换
// *
// *
// * @author 马若劼
// */
// public class IPSeeker {
// /**
// * * 用来封装ip相关信息，目前只有两个字段，ip所在的国家和地区
// *
// *
// * @author 马若劼
// */
// private class IPLocation {
// public String country;
// public String area;
// public IPLocation() {
// country = area = "";
// }
// public IPLocation getCopy() {
// IPLocation ret = new IPLocation();
// ret.country = country;
// ret.area = area;
// return ret;
// }
// }
// private static final String IP_FILE =
// IPSeeker.class.getResource("/QQWry.dat").toString().substring(5);
// // 一些固定常量，比如记录长度等等
// private static final int IP_RECORD_LENGTH = 7;
// private static final byte AREA_FOLLOWED = 0x01;
// private static final byte NO_AREA = 0x2;
// // 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
// private Hashtable ipCache;
// // 随机文件访问类
// private RandomAccessFile ipFile;
// // 内存映射文件
// private MappedByteBuffer mbb;
// // 单一模式实例
// private static IPSeeker instance = new IPSeeker();
// // 起始地区的开始和结束的绝对偏移
// private long ipBegin, ipEnd;
// // 为提高效率而采用的临时变量
// private IPLocation loc;
// private byte[] buf;
// private byte[] b4;
// private byte[] b3;
// /**
// * 私有构造函数
// */
// private IPSeeker() {
// ipCache = new Hashtable();
// loc = new IPLocation();
// buf = new byte[100];
// b4 = new byte[4];
// b3 = new byte[3];
// try {
// ipFile = new RandomAccessFile(IP_FILE, "r");
// } catch (FileNotFoundException e) {
// System.out.println(IPSeeker.class.getResource("/QQWry.dat").toString());
// System.out.println(IP_FILE);
// System.out.println("IP地址信息文件没有找到，IP显示功能将无法使用");
// ipFile = null;
// }
// // 如果打开文件成功，读取文件头信息
// if(ipFile != null) {
// try {
// ipBegin = readLong4(0);
// ipEnd = readLong4(4);
// if(ipBegin == -1 || ipEnd == -1) {
// ipFile.close();
// ipFile = null;
// }
// } catch (IOException e) {
// System.out.println("IP地址信息文件格式有错误，IP显示功能将无法使用");
// ipFile = null;
// }
// }
// }
