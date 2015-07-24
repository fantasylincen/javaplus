package org.hhhhhh.guess.util;

public interface ISystemKeyValueDao {

	ISystemKeyValueDto createDTO();

	void save(ISystemKeyValueDto dto);

	ISystemKeyValueDto get(String key);

}
