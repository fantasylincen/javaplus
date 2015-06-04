package cn.javaplus.generator.protocol.util;

import java.util.List;

import cn.javaplus.java.JavaFile;

public interface ClassFilter {

	List<JavaFile> filter(List<JavaFile> cs);
}