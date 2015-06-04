package cn.javaplus.build.protocol.generator;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.TypeDeclaration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.comunication.annotations.CommunicationDto;
import cn.javaplus.file.Templet;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * 通信协议生成器
 * 
 * @author 林岑
 * @since 2014年2月18日 14:58:26
 */
public class ProtocolGenerator {

	public void generate(String javaSrcPath, String userType, String serverProtocolsPath, String clientProtocolsPath) throws ParseException, IOException {

		List<File> all = Util.File.getFiles(javaSrcPath);

		List<JavaFile> cs = loadClasss(all, Communication.class,
				CommunicationDto.class);

		new Checker().check(cs);
		generateClient(cs, clientProtocolsPath);
		generateServer(cs, serverProtocolsPath, userType);
	}

	private void generateServer(List<JavaFile> cs, String serverProtocolsPath, String userType) {
		String temp = "ProtocolsServer.temp";
		URL url = ProtocolGenerator.class.getResource(temp);
		Templet t = Templet.createByContent(Util.File.getContent(url));

		t.set("ACTION_CLASSES", new ServerActionsGenerator().generate(cs));
		t.set("PUT_ACTIONS", new PutActionsGenerator().generate(cs));
		t.set("MESSAGE_SENDERS",
				new ServerMessageSenderGenerator().generate(cs));
		t.set("USER_TYPE", userType);

		t.writeToFile(serverProtocolsPath);
	}

	private void generateClient(List<JavaFile> cs, String clientProtocolsPath) {
		String temp = "ProtocolsClient.temp";
		URL url = ProtocolGenerator.class.getResource(temp);
		Templet t = Templet.createByContent(Util.File.getContent(url));

		t.set("HANDLERS", new HandlersGenerator().generate(cs));
		t.set("VALUE_OBJECT", new ValueObjectJavaClassGenerator().generate(cs));
		t.set("PROTOCOL_CLASS", new ProtocolClassGenerator().generate(cs));
		t.set("CALLBACK_CLASS", new CallBackClassGenerator().generate(cs));
		t.set("CALLBACK_IMPORTS", new CallBackImportsGenerator().generate(cs));
		t.set("CHECK_TIME_METHOD", new CheckTimeMethodGenerator().generate(cs));
		t.set("PROTOCOL_HANDLER", new ProtocolHandlerGenerator().generate(cs));
		t.set("EVENTS", new EventDotJavaGenerator().generate(cs));
		t.set("ACTIONS", new ActionGenerator().generate(cs));

		t.writeToFile(clientProtocolsPath);
	}

	private static List<JavaFile> loadClasss(List<File> all, Class<?>... c1) {

		List<JavaFile> ls = Lists.newArrayList();
		for (File file : all) {
			if (!file.getName().endsWith(".java")) {
				continue;
			}
			CompilationUnit parse;
			try {
				parse = JavaParser.parse(file);

			} catch (japa.parser.ParseException e) {
				continue;
			} catch (Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}

			List<TypeDeclaration> types = parse.getTypes();
			types = Util.Collection.nullToEmpty(types);

			if (types.size() == 1) {
				JavaFile ff = new JavaFile(parse);

				for (Class<?> c : c1) {
					if (ff.containsAnnotation(c)) {
						ls.add(ff);
					}
				}
			}
		}
		return ls;
	}

}
