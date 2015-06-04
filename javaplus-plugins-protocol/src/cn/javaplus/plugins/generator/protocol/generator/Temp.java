package cn.javaplus.plugins.generator.protocol.generator;

//package cn.mxz.generator;
//
//import java.io.File;
//import java.util.Iterator;
//import java.util.List;
//
//import lincen.javase.util.string.StringUtil;
//import cn.mxz.generator.base.GeneratorImpl;
//import cn.mxz.generator.config.FileUtil;
//import cn.mxz.generator.config.GeneratorConfig;
//import cn.mxz.generator.config.IClass;
//import cn.mxz.generator.config.IField;
//import cn.mxz.generator.config.IMethod;
//
//public class ASCallerFactoryGenerator extends GeneratorImpl {
//
//
//	public static final String TAIL = "Factory";
//	
//	
//	
//	public ASCallerFactoryGenerator(GeneratorConfig config) {
//		super(config, "client_src.caller_factory");
//	}
//	
//	@Override
//	public void generator() {
//		List<IClass> all = config.getClazzs();
//		for (IClass c : all) {
//			writeToFile(c, config);
//		}
//	}
//	
//	private void writeToFile(IClass c, GeneratorConfig config) {
//		FileUtil fu = config.getFileUtil(getSrcPath() + File.separator + c.getInterface().getName() + TAIL + ".as");
//		String content = buildContent(c, config);
//		System.out.println(content);
//		fu.writeToFile(content);	
//	}
//
//	private String buildContent(IClass c, GeneratorConfig config) {
//		String t1 = config.getTemplet("ASCallerFactory");
//		t1 = t1.replaceAll("METHODS", buildMethods(c, config));
//		t1 = t1.replaceAll("PACKAGE_NAME", getPackageName());
//		t1 = t1.replaceAll("CLASS_NAME", c.getInterface().getName() + TAIL);
//		
//		String pack = config.getNodeText("client_src.caller_interface.package");
//		t1 = t1.replaceAll("CALLER_INTERFACE_PACKAGE", pack);
//		
//		return t1;
//	}
//	
//	private String buildMethods(IClass c, GeneratorConfig config) {
//
//		StringBuilder sb = new StringBuilder();
//		
//		for (IMethod m : c.getMethods()) {
//			sb.append(buildMethod(m, config) + "\r");
//		}
//		
//		return sb.toString();
//	}
//	
//	private String buildMethod(IMethod m, GeneratorConfig config) {
//		
//		String t2 = config.getTemplet("ASCallerImplMethod");
//		t2 = t2.replaceAll("NAME", m.getName());
//		t2 = t2.replaceAll("FILEDS", buildFields(m.getArgs()));
//		t2 = t2.replaceAll("WRITE_FS", buildWriteFields(m.getArgs()));
//		
//		return t2;
//	}
//	
//	private String buildWriteFields(List<IField> args) {
//		StringBuilder sb = new StringBuilder();
//		for (IField f : args) {
//			String type;
//			if(f.getType().equals("String")) {
//				type = "UTF";
//			} else {
//				type = StringUtil.firstToUpperCase(f.getType());				
//			}
//			sb.append("			socket.write" + type + "(" + f.getName() + ");\r");
//		}
//		return sb.toString();
//	}
//
//	private String buildFields(List<IField> args) {
//		StringBuilder sb = new StringBuilder();
//		Iterator<IField> it = args.iterator();
//		while (it.hasNext()) {
//			IField f = it.next();
//			sb.append("" + f.getName() + ":" + f.getType());
//			if(it.hasNext()) {
//				sb.append(", ");
//			}
//		}
//		return sb.toString();
//	}
//
// }
