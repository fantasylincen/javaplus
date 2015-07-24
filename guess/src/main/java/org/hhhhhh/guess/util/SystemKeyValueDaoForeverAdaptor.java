package org.hhhhhh.guess.util;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dao.KeyValueDao;
import org.hhhhhh.guess.hibernate.dto.KeyValueDto;

public class SystemKeyValueDaoForeverAdaptor implements ISystemKeyValueDao {

	public class SystemKeyValueDtoAdaptor implements ISystemKeyValueDto {

		private final KeyValueDto dto;

		public SystemKeyValueDtoAdaptor(KeyValueDto dto) {
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

	private KeyValueDao dao;

	public SystemKeyValueDaoForeverAdaptor() {
		dao = Daos.getKeyValueDao();
	}
	
	@Override
	public ISystemKeyValueDto createDTO() {
		 KeyValueDto dto = dao.createDTO();
		 return new SystemKeyValueDtoAdaptor(dto);
	}

	@Override
	public void save(ISystemKeyValueDto dto) {
		KeyValueDto d = new KeyValueDto();
		d.setKey(dto.getKey());
		d.setValue(dto.getValue());
		DbUtil.save(d);
	}

	@Override
	public ISystemKeyValueDto get(String key) {
		KeyValueDto dto = dao.get(key);
		if(dto == null)
			return null;
		return new SystemKeyValueDtoAdaptor(dto);
	}

}
