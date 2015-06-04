package cn.javaplus.mongodao.generator;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;

public class DaosGenerator {

	public void generate(StringPrinter code, String dtoPath) {
		Templet temp = Config.getTemplet("DAOS");

		List<Dto> dtos = Config.getDtos(dtoPath);

		for (Dto dto : dtos) {
			if(dto.hasAnnotation(Dao.class.getSimpleName())) {
				temp.append("DAO_GETTER", generateDaoGetterMethod(dto));
			}
		}

		code.println(temp);
	}

	private String generateDaoGetterMethod(Dto dto) {
		Templet temp = Config.getTemplet("DAO_GETTER_METHOD");
		temp.set("DAO_NAME", dto.getDaoClassName());
		temp.set("COLLECTION_NAME", "\"" + dto.getSimpleClassName() + "\"");

		return temp.toString();
	}

}
