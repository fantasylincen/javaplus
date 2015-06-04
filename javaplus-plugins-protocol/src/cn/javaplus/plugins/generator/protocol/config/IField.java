package cn.javaplus.plugins.generator.protocol.config;

public interface IField {

	/**
	 * 类型(int,float,.....boolean.....String)
	 *
	 * @return
	 */
	String getType();

	String getTypeSimple();

	String getName();

	void setType(String string);

	void setDoc(String string);

	void setName(String string);

	String getDoc();

	/**
	 * 包装类型(Integer,FLoat,.....Boolean.....String)
	 *
	 * @return
	 */
	String getPackagingType();

	/**
	 * 包装类型(Number.....String)
	 *
	 * @return
	 */
	String getASPackagingType();
}
