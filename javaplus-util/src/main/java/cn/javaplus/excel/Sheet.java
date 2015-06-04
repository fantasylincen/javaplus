package cn.javaplus.excel;

import java.util.List;

public interface Sheet {

	List<Row> getAll();

	Row findFirst(String fieldName, Object value);

	List<Row> find(String fieldName, Object value);
	
	Row get(Object key);

}
