package org.hhhhhh.house.hibernate.dao;

import java.util.Iterator;

import org.hhhhhh.house.hibernate.dto.HouseDto;

public class HouseDtoCursor implements Iterable<HouseDto>{

	private final Iterator<HouseDto> it;

	public HouseDtoCursor(Iterator<HouseDto> it) {
		this.it = it;
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public HouseDto next() {
		return it.next();
	}

	@Override
	public Iterator<HouseDto> iterator() {
		return it;
	}

}