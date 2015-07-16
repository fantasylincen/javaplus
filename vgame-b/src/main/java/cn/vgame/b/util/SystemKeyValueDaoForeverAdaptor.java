package cn.vgame.b.util;

import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.SystemKeyValueDao;
import cn.vgame.b.gen.dto.MongoGen.SystemKeyValueDto;
import cn.vgame.share.ISystemKeyValueDao;
import cn.vgame.share.ISystemKeyValueDto;

public class SystemKeyValueDaoForeverAdaptor implements ISystemKeyValueDao {

	public class SystemKeyValueDtoAdaptor implements ISystemKeyValueDto {

		private final SystemKeyValueDto dto;

		public SystemKeyValueDtoAdaptor(SystemKeyValueDto dto) {
			this.dto = dto;
		}

		public String getKey() {
			return dto.getKey();
		}

		public String getValue() {
			return dto.getValue();
		}

		public void setKey(String key) {
			dto.setKey(key);
		}

		public void setValue(String value) {
			dto.setValue(value);
		}

	}

	private SystemKeyValueDao dao;

	public SystemKeyValueDaoForeverAdaptor() {
		dao = Daos.getSystemKeyValueDao();
	}

	@Override
	public ISystemKeyValueDto createDTO() {
		SystemKeyValueDto dto = dao.createDTO();
		return new SystemKeyValueDtoAdaptor(dto);
	}

	@Override
	public void save(ISystemKeyValueDto dto) {
		SystemKeyValueDto d = new SystemKeyValueDto();
		d.setKey(dto.getKey());
		d.setValue(dto.getValue());
		dao.save(d);
	}

	@Override
	public ISystemKeyValueDto get(String key) {
		SystemKeyValueDto dto = dao.get(key);
		if (dto == null)
			return null;
		return new SystemKeyValueDtoAdaptor(dto);
	}

}
