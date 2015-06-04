package cn.mxz.regist;

import java.util.List;

interface RegisterDto {

	List<RegistRecord> getRecords();

	/**
	 * 创建事件
	 * @return
	 */
	long getCreateTime();

}
