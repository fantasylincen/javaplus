package cn.javaplus.tb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.tb.gen.dto.MongoGen.Daos;
import cn.javaplus.tb.gen.dto.MongoGen.RecordDao;
import cn.javaplus.tb.gen.dto.MongoGen.RecordDto;
import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class AddAction extends ActionSupport {

	private static final long serialVersionUID = -8965549726279594696L;

	private String sourceId;
	private String id;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = trim(sourceId);

	}

	private String trim(String id) {
		id = id.trim();
		if(id.matches("[0-9]+"))
			return id;
		Pattern c = Pattern.compile("&id=[0-9]+");
		Matcher m = c.matcher(id);
		m.find();
		String a = m.group().replaceAll("&id=", "");
		return a.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = trim(id);
	}

	public String execute() {
		RecordDao dao = Daos.getRecordDao();
		RecordDto dto = dao.createDTO();
		Util.Exception.checkNull(sourceId, "sourceId为null");
		Util.Exception.checkNull(id, "id为null");
		dto.setSourceId(sourceId);
		dto.setMineId(id);
		dao.save(dto);
		return SUCCESS;
	}
}