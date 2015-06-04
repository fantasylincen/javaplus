package cn.javaplus.generator.mongodb.dto;

import java.util.List;

/**
 * 书包
 * @author 林岑
 *
 */
interface BookBag {

	/**
	 * 所有的书
	 * @return
	 */
	List<Book> getBooks();

	/**
	 * 颜色
	 * @return
	 */
	String getColor();
}
