package com.cnbizmedia.user;

public interface IOrderDao {

	IOrderDtoCursor findByUserId(String id);

}
