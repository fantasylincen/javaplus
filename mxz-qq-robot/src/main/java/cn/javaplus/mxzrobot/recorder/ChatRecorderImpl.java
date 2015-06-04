package cn.javaplus.mxzrobot.recorder;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.chatrobot.ChatRecorder;
import cn.javaplus.mxzrobot.db.ChatRecordDao;
import cn.javaplus.mxzrobot.db.ChatRecordDto;
import cn.javaplus.mxzrobot.db.Daos;
import cn.javaplus.util.Util;

public class ChatRecorderImpl implements ChatRecorder {

	public void save(String message, String name) {

		ChatRecordDao dao = Daos.getChatRecordDao();
		ChatRecordDto dto = dao.createDTO();
		dto.setContent(message);
		dto.setTalker(name);
		
		long c = Util.Time.getCurrentTimeMillis();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
		String today = df.format(new Date(c));
		
		dto.setTime(today);
		dao.save(dto);
	}

}
