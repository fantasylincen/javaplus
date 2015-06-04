package cn.javaplus.mongodao.generator;

import java.util.List;

import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class MongoGenerator {

	public void generate(String dtoPath, String dstPath, String packageName) {

		Templet temp = Config.getTemplet("MONGO_GEN");

		StringPrinter code = new StringPrinter();

		new DaosGenerator().generate(code, dtoPath); // Daos.java 生成器
		new DaoGenerator().generate(code, dtoPath); // XXXDao.java 生成器
		new DtoGenerator().generate(code, dtoPath); // XXXDto.java 生成器

		temp.set("GEN_CLASSES", code.toString());
		temp.set("PACKAGE_NAME", packageName);

		List<Dto> dtos = Config.getDtos(dtoPath);
		for (Dto dto : dtos) {
			temp.append("COPY_METHODS", generatCopyMethods(dto));
			temp.append("PUT_METHODS", generatPutMethods(dto));
			temp.append("TO_OBJECT_METHODS", generatToObjectMethods(dto));
		}	

		dstPath = dstPath + "/MongoGen.java";
		
		String string = temp.toString();
//		System.out.println(string);
		Util.File.write(dstPath, string);
	}

	private String generatCopyMethods(Dto dto) {
		Templet temp = Config.getTemplet("COPY_MTHD");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		return temp.toString();
	}
	

	private String generatPutMethods(Dto dto) {
		Templet temp = Config.getTemplet("PUT_MTHD");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		return temp.toString();
	}
	
	private String generatToObjectMethods(Dto dto) {
		Templet temp = Config.getTemplet("TOBJ_MTHD");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		return temp.toString();
	}
}
