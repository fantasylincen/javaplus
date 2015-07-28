package org.javaplus.clickscreen.excel;

import java.util.List;

public interface Sheet {

	List<Row> getAll();

	Row find(String fieldName, Object value);

	Row get(Object key);

}
