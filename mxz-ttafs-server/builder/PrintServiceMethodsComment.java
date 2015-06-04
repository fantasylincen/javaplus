import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class PrintServiceMethodsComment {

	public static void print() {
		List<CompilationUnit> files = getJavaServiceFiles();
		for (CompilationUnit c : files) {
			List<TypeDeclaration> ts = c.getTypes();
			if(ts == null) {
				continue;
			}
			
			TypeDeclaration t = ts.get(0);
			List<MethodDeclaration> ms = getMethods(t);
			
			for (MethodDeclaration m : ms) {
				String name = m.getName();
				String a = t.getName() + "." + name;
				Debuger.debug(t.getName());
			}
			
		}
	}
	
	public static List<MethodDeclaration> getMethods(TypeDeclaration t) {
		List<MethodDeclaration> ls = Lists.newArrayList();
		List<BodyDeclaration> all = t.getMembers();
		for (BodyDeclaration b : all) {
			if (b instanceof MethodDeclaration) {
				MethodDeclaration m = (MethodDeclaration) b;
				ls.add(m);
			}
		}
		return ls;
	}

	private static List<CompilationUnit> getJavaServiceFiles() {
		ArrayList<CompilationUnit> ls = Lists.newArrayList();

		List<File> all = Util.File.getFiles(".");
		for (File file : all) {
			if (endWith(file, "Service.java", "Transform.java")) {
				try {
					CompilationUnit c = JavaParser.parse(file);
					ls.add(c);
				} catch (Exception e) {
					throw Util.Exception.toRuntimeException(e);
				}
			}
		}
		return ls;
	}

	private static boolean endWith(File file, String... string) {
		String name = file.getName();
		for (String string2 : string) {
			if (name.endsWith(string2)) {
				return true;
			}
		}
		return false;
	}
}
