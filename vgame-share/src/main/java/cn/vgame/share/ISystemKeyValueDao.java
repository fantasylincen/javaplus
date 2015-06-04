package cn.vgame.share;

public interface ISystemKeyValueDao {

	ISystemKeyValueDto createDTO();

	void save(ISystemKeyValueDto dto);

	ISystemKeyValueDto get(String key);

}
