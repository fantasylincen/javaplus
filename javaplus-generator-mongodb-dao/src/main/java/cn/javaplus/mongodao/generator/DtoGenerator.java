package cn.javaplus.mongodao.generator;

import java.util.List;
import java.util.Set;

import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Sets;

public class DtoGenerator {

	public void generate(StringPrinter code, String dtoPath) {
		List<Dto> dtos = Config.getDtos(dtoPath);
		for (Dto dto : dtos) {
			writeToFile(dto, code);
		}
	}

	private void writeToFile(Dto dto, StringPrinter code) {
		Templet temp = Config.getTemplet("DTO");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		temp.set("TO_DBOBJECT", generateToDBObject(dto));
		temp.set("IMPLEMENTS", generateImplements(dto));
		temp.set("FROM_DBOBJECT", generateFromDBObject(dto));
		temp.set("COPY_MONGO_MAP_METHODS", generateCopyMongoMapMethods(dto));
		temp.set("COPY_LIST_METHODS", generateCopyListMethods(dto));

		List<Field> fields = dto.getFields();

		for (Field field : fields) {
			temp.append("GETTER", generateGetter(field));
			temp.append("SETTER", generateSetter(field));
			temp.append("COPY_FS", generatCopyFields(field));
			temp.append("FIELDS", generateFields(field));
			temp.append("MAP_LOADERS", generateMapLoaders(field));
			temp.append("LIST_LOADERS", generateListLoaders(field));
		}

		code.println(temp);
	}

	private String generateCopyListMethods(Dto dto) {
		Templet temp = Config.getTemplet("CP_LIST_METHOD");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		return temp.toString();
	}

	private String generateCopyMongoMapMethods(Dto dto) {
		Templet temp = Config.getTemplet("CP_MONGO_M_METHOD");
		temp.set("CLASS_NAME", dto.getDtoClassName());
		return temp.toString();
	}

	private String generatCopyFields(Field field) {

		if (field.isBaseType()) {
			return buildTemplet(field, "COPY_XXX");

		} else if (field.isList() || field.isMongoMap()) {

			if (Util.JavaType.isBaseType(field.getElementType())) {
				return buildTemplet(field, "COPY_XXX");
			} else {
				return buildTemplet(field, "COPY_CONLLECTION");
			}

		} else {
			return buildTemplet(field, "COPY_XXX");
		}
	}

	private String generateListLoaders(Field field) {
		if (field.isList()) {
			if (Util.JavaType.isBaseType(field.getElementType())) {
				return buildTemplet(field, "LOAD_BASE_TYPE_LIST");
			} else {
				return buildTemplet(field, "LOAD_LIST");
			}
		}
		return "";
	}

	private String generateMapLoaders(Field field) {
		if (field.isMongoMap()) {
			if (Util.JavaType.isBaseType(field.getElementType())) {
				return buildTemplet(field, "LOAD_BASE_TYPE_MAP");
			} else {
				return buildTemplet(field, "LOAD_MAP");
			}
		}
		return "";
	}

	private String generateImplements(Dto dto) {
		List<Annotation> annotations = dto.getAnnotations(DtoInterface.class
				.getSimpleName());

		StringBuilder sb = new StringBuilder();

		for (Annotation annotation : annotations) {
			sb.append("," + annotation.getValue());
		}

		return sb.toString();
	}

	private String generateFromDBObject(Dto dto) {
		List<Field> fs = dto.getFields();
		StringPrinter out = new StringPrinter();
		for (Field field : fs) {
			if (field.isBaseType()) {
				printFromBaseType(out, field);
			} else if (field.isMongoMap() || field.isList()) {
				printFromMapDto(out, field);
			} else {
				printFromDto(out, field);
			}
		}
		return out.toString();
	}

	private void printFromMapDto(StringPrinter out, Field field) {
		String str = buildTemplet(field, "LOAD_DTO");
		out.println(str);
	}

	private void printFromDto(StringPrinter out, Field field) {
		Templet temp = Config.getTemplet("FROM_DTO");
		temp.set("FILED_NAME", field.getName());
		temp.set("TYPE", field.getType());
		out.println(temp.toString());
	}

	private void printFromBaseType(StringPrinter out, Field field) {
		String name = field.getName();
		out.println("			" + name + " = " + field.getTypeGetterName() + "(o, \""
				+ name + "\");");
	}

	private String generateToDBObject(Dto dto) {
		List<Field> fs = dto.getFields();
		StringPrinter out = new StringPrinter();

		String s = new KeyPutter().generateKeysPutts(dto);

		out.println(s);

		for (Field field : fs) {
			if(field.isMongoMap()) {
				String et = field.getElementType();
				if("String".equals(et)) {
					out.println(buildTemplet(field, "TO_MONGO_MAP_STRING_DTO"));
				} else {
					out.println(buildTemplet(field, "TO_MONGO_MAP_DTO"));
				}
			} else {
				out.println(buildTemplet(field, "TO_DTO"));
			}
		}
		return out.toString();
	}

	private String generateFields(Field field) {
		if (field.isList()) {
			return buildTemplet(field, "LIST_DTO_FIELDS");
		} else if (field.isMongoMap()) {
			return buildTemplet(field, "MAP_DTO_FIELDS");
		} else {
			return buildTemplet(field, "DTO_FIELDS");
		}
	}

	private String generateGetter(Field field) {

		return buildTemplet(field, "DTO_GETTER");
	}

	private String generateSetter(Field field) {
		return buildTemplet(field, "DTO_SETTER");
	}

	private String buildTemplet(Field field, String templetName) {
		Templet templet = Config.getTemplet(templetName);

		String elementType = getElementType(field);
		templet.set("ELEMENT_TYPE", elementType);
		templet.set("UPPER_FNAME", Util.Str.firstToUpperCase(field.getName()));
		templet.set("FIELD_NAME", field.getName());
		templet.set("TYPE", field.getType());
		templet.set("DEFAULT_VALUE", buildDefaultValue(field.getType()));

		return templet.toString();
	}

	private String getElementType(Field field) {
		if (field.isMongoMap() || field.isList()) {
			return field.getElementType();
		}
		return "";
	}

	private String buildDefaultValue(String type) {
		if ("String".equals(type)) {
			return "\"\"";
		}
		if ("boolean".equals(type)) {
			return "false";
		}
		if ("Boolean".equals(type)) {
			return "false";
		}
		Set<String> set = Sets.newHashSet("int", "float", "double", "long",
				"char", "byte");

		if (set.contains(type)) {
			return "0";
		}
		return "null";
	}

}
