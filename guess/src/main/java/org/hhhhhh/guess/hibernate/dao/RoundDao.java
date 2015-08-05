package org.hhhhhh.guess.hibernate.dao;

import java.util.List;

import org.hhhhhh.guess.hibernate.dto.RoundDto;

@SuppressWarnings("unchecked")
public class RoundDao {

	public List<RoundDto> find(String field, String v) {
		return DbUtil.find("RoundDto", field, v);
	}

	public List<RoundDto> find() {
		return DbUtil.find("RoundDto");
	}
}
