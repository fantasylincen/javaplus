package com.cnbizmedia.recharge;

import com.cnbizmedia.config.ServerNode;
import com.cnbizmedia.gen.dto.MongoGen.OrderDto;
import com.ning.http.client.RequestBuilder;

public interface ParameterSetter {

	void addParameters(RequestBuilder b, OrderDto dto, ServerNode node);
}
