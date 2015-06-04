import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.type.ClassOrInterfaceType;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.javaplus.file.FileCamera;
import cn.javaplus.generator.protocol.ImplementConfig;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.ProtocolClassGenerator;
import cn.javaplus.generator.protocol.generator.ProtocolHandlerDotASGenerator;
import cn.javaplus.generator.protocol.generator.ProtocolHandlerDotJavaGenerator;
import cn.javaplus.generator.protocol.generator.ProtocolInterfaceGenerator;
import cn.javaplus.generator.protocol.generator.ResponsesDotJavaGenerator;
import cn.javaplus.generator.protocol.generator.ServerDotASGenerator;
import cn.javaplus.generator.protocol.generator.XXXActionDotJavaGenerator;
import cn.javaplus.generator.protocol.generator.XXXEventDotASGenerator;
import cn.javaplus.generator.protocol.generator.XXXObjectASClassGenerator;
import cn.javaplus.generator.protocol.generator.XXXObjectASInterfaceDuplicator;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.java.JavaFileImpl;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * ActionScript - Java 通信协议生成器
 * @author 	林岑
 * @since	2014年2月18日 14:58:26
 */
public class App {

	public static void main(String[] args) throws ParseException, IOException {

		FileCamera fm = new FileCamera(D.JAVA_SRC_PATH);
		List<File> all = fm.getFiles();
		List<JavaFile> cs = loadClasss(all);

		new Checker().check(cs);

		new ServerDotASGenerator().generate(cs);			//AS 	生成Server.as文件
		new XXXObjectASInterfaceDuplicator().copy(cs);	//AS 	拷贝Java端 通信接口到ActionScript目录
		new XXXObjectASClassGenerator().generate(cs);		//AS 	生成 通信接口的实现到AS目录
		new ProtocolInterfaceGenerator().generate(cs);		//AS 	生成 通信接口
		new ProtocolClassGenerator().generate(cs);			//AS 	生成 通信接口实现
		new ProtocolHandlerDotASGenerator().generate(cs);	//AS 	生成ProtocolHandler.as
		new XXXEventDotASGenerator().generate(cs);				//AS 	生成CCCyyyEvent.as
		new ResponsesDotJavaGenerator().generate(cs);		//Java 	生成Responses.java
		new ProtocolHandlerDotJavaGenerator().generate(cs);		//Java 	生成ProtocolHandler.java
		new XXXActionDotJavaGenerator().generate(cs);		// Java 生成XXXAction.java
	}

	private static List<JavaFile> loadClasss(List<File> all) {

		List<JavaFile> ls = Lists.newArrayList();
		for (File file : all) {
			CompilationUnit parse;
			try {
					parse = JavaParser.parse(file);

			} catch (japa.parser.ParseException e) {
				System.err.println("发现报错的Java文件 " + file);
				continue;
			} catch (Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}
			ls.add(new JavaFileImpl(parse));
		}
		initImpls(ls);
		return ls;
	}

	private static void initImpls(List<JavaFile> allclass) {
		ProtocolClassFilter ft = new ProtocolClassFilter();
		List<JavaFile> interfaces = ft.filter(allclass);
		for (JavaFile interfacee : interfaces) {
			findAndPutImpl(interfacee, allclass);
		}
	}

	private static void findAndPutImpl(JavaFile interfacee, List<JavaFile> allclass) {
		for (JavaFile im : allclass) {
			TypeDeclaration type = im.getType();

			if (!(type instanceof ClassOrInterfaceDeclaration)) {
				continue;
			}

			ClassOrInterfaceDeclaration d = (ClassOrInterfaceDeclaration) type;
			List<ClassOrInterfaceType> implements1 = d.getImplements();

			if (implements1 == null) {
				continue;
			}

			for (ClassOrInterfaceType c : implements1) {
				if (!c.toString().equals(interfacee.toString())) {
					continue;
				}

				JavaFile f = ImplementConfig.get(interfacee);
				if (f != null) {
					throw new RuntimeException("只能有一个实现！" + f.getClassFullName());
				}
				ImplementConfig.put(interfacee, im);
			}
		}
	}
}
