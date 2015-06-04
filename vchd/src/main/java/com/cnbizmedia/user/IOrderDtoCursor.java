package com.cnbizmedia.user;

import java.util.Iterator;

import com.cnbizmedia.recharge.IOrderDto;

public interface IOrderDtoCursor extends Iterator<IOrderDto>, Iterable<IOrderDto> {

	int getCount();

}
