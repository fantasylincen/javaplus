package cn.javaplus.generator.mongodb.dto;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Student {

	@Key
	int getId();

	/**
	 * 学生名字
	 * @return
	 */
	String getName();

	/**
	 * 书包
	 * @return
	 */
	BookBag getBookBag();

	/**
	 * 学生年龄
	 * @return
	 */
	int getAge();
}
