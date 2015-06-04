package cn.mxz.util.interceptor;

import cn.mxz.base.config.KeyValueDefine;

interface ServiceMethodRecorder {

	void saveToDB(KeyValueDefine key, String head, String time_ms);

}
