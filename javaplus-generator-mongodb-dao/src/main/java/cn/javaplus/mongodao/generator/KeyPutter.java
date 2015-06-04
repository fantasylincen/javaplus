package cn.javaplus.mongodao.generator;

import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class KeyPutter {
	public String generateKeysPutts(Dto dto) {

		StringPrinter s = new StringPrinter();

		List<Field> fields = dto.getKeys();

		if (fields.size() == 1) {
			s.print("		o.put(\"_id\", " + fields.get(0).getName() + ");");
		} else if (fields.size() > 1) {

			Fetcher<Field, String> fetcher = new Fetcher<Field, String>() {

				public String get(Field t) {
					return t.getName();
				}
			};

			String text = Util.Collection.linkWith(" + \":\" + ", fields, fetcher);

			s.print("		o.put(\"_id\", " + text + ");");
		}

		return s.toString();
	}

}
