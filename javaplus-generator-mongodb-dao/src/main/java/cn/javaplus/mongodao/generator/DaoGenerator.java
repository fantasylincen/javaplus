package cn.javaplus.mongodao.generator;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DaoInterface;
import cn.javaplus.file.Templet;
import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class DaoGenerator {

	public void generate(StringPrinter code, String dtoPath) {
		List<Dto> dtos = Config.getDtos(dtoPath);
		for (Dto dto : dtos) {
			if (dto.hasAnnotation(Dao.class.getSimpleName())) {
				writeToFile(dto, code);
			}
		}
	}

	private void writeToFile(Dto dto, StringPrinter code) {
		Templet temp = Config.getTemplet("DAO");
		temp.set("CLASS_NAME", dto.getDaoClassName());
		temp.set("DTO_CNAME", dto.getDtoClassName());
		temp.set("KEYS_DEFINE", generateKeysDefines(dto));
		temp.set("KEYS_VALUES", generateKeysValues(dto));
		temp.set("KEYS_GETTERS", generateKeysGetters(dto));
		temp.set("IMPLEMENTS", generateImplements(dto));
		temp.set("KEYS_PUTTS", new KeyPutter().generateKeysPutts(dto));

		List<Field> fs = dto.getFields();
		for (Field f : fs) {
			if (Util.JavaType.isBaseType(f.getType())) {
				temp.append("FINDERS", generateFinder(f, dto));
			}
			if (Util.JavaType.isDigitType(f.getType())) {
				temp.append("FINDERS", generateScopeFinder(f, dto));
			}
			if ("String".equals(f.getType())) {
				temp.append("FINDERS", generateFuzzyFinder(f, dto));
			}
		}

		try {
			code.println(temp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String generateScopeFinder(Field f, Dto dto) {
		Templet temp = Config.getTemplet("DAO_SCOPE_FINDER");
		temp.set("UPPER_FNAME", Util.Str.firstToUpperCase(f.getName()));
		temp.set("TYPE", f.getType());
		temp.set("FIELD_NAME", f.getName());
		temp.set("DTO_CNAME", dto.getDtoClassName());
		temp.set("ENSURE_INDEX",
				f.hasIndex() ? "collection.ensureIndex(\"" + f.getName() + "\""
						+ ");" : "");
		return temp.toString();
	}

	private String generateFuzzyFinder(Field f, Dto dto) {
		Templet temp = Config.getTemplet("DAO_FUZZY_FINDER");
		temp.set("UPPER_FNAME", Util.Str.firstToUpperCase(f.getName()));
		temp.set("TYPE", f.getType());
		temp.set("FIELD_NAME", f.getName());
		temp.set("DTO_CNAME", dto.getDtoClassName());
		temp.set("ENSURE_INDEX",
				f.hasIndex() ? "collection.ensureIndex(\"" + f.getName() + "\""
						+ ");" : "");
		return temp.toString();
	}

	private String generateKeysGetters(Dto dto) {
		List<Field> fields = dto.getKeys();
		Fetcher<Field, String> fetcher = new Fetcher<Field, String>() {

			public String get(Field t) {
				return "u.get" + Util.Str.firstToUpperCase(t.getName()) + "()";
			}
		};

		return Util.Collection.linkWith(", ", fields, fetcher);
	}

	private String generateImplements(Dto dto) {
		List<Annotation> annotations = dto.getAnnotations(DaoInterface.class
				.getSimpleName());
		if (annotations.isEmpty()) {
			return "";
		}

		Fetcher<Annotation, String> fetcher = new Fetcher<Annotation, String>() {

			public String get(Annotation t) {
				return t.getValue();
			}
		};

		return "implements "
				+ Util.Collection.linkWith(",", annotations, fetcher);
	}

	private String generateKeysValues(Dto dto) {
		List<Field> fields = dto.getKeys();
		Fetcher<Field, String> fetcher = new Fetcher<Field, String>() {

			public String get(Field t) {
				return t.getName();
			}
		};

		return Util.Collection.linkWith(", ", fields, fetcher);
	}

	private String generateKeysDefines(Dto dto) {
		List<Field> fields = dto.getKeys();
		Fetcher<Field, String> fetcher = new Fetcher<Field, String>() {

			public String get(Field t) {
				return t.getType() + " " + t.getName();
			}
		};

		return Util.Collection.linkWith(", ", fields, fetcher);
	}

	private String generateFinder(Field f, Dto dto) {
		Templet temp = Config.getTemplet("DAO_FINDER");
		temp.set("UPPER_FNAME", Util.Str.firstToUpperCase(f.getName()));
		temp.set("TYPE", f.getType());
		temp.set("FIELD_NAME", f.getName());
		temp.set("DTO_CNAME", dto.getDtoClassName());
		temp.set("ENSURE_INDEX",
				f.hasIndex() ? "collection.ensureIndex(\"" + f.getName() + "\""
						+ ");" : "");
		return temp.toString();
	}
}