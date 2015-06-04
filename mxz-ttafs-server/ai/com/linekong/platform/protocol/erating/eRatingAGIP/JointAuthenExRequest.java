package com.linekong.platform.protocol.erating.eRatingAGIP;

import org.apache.mina.core.buffer.IoBuffer;

import cn.mxz.util.debuger.Debuger;

public class JointAuthenExRequest extends eRatingProtocol {
	private final int	bodyMaxLength	= 1000;
	private String		data			= "";

	public int setParamNameAndValue(String ParamName, Object ParamValue) {
		Debuger.debug("JointAuthenExRequest.setParamNameAndValue() " + ParamName + ":" + ParamValue);
		if (ParamName.isEmpty() || ParamValue.toString().isEmpty())
			return -1;
		String tmpData = "<" + ParamName + ">" + ParamValue;
		if (data.length() + tmpData.length() > bodyMaxLength)
			return -1;
		data = data + tmpData;
		return 0;
	}

	public String getParamValue(String ParamName) {
		if (ParamName.isEmpty())
			return "";
		String tmpParam = "<" + ParamName + ">";
		String ParamValue;
		int pos = 0, end = 0;
		if ((pos = data.indexOf(tmpParam)) < 0)
			return "";

		end = data.indexOf('<', pos + tmpParam.length());
		if (end < 0)
			ParamValue = data.substring(pos);
		else
			ParamValue = data.substring(pos, end);

		if (ParamValue.isEmpty())
			return "";
		return ParamValue;
	}

	public byte[] getBody() {
		if (0 == data.length())
			return null;
		IoBuffer b = IoBuffer.allocate(data.length());

		b.put(fixLength(data, data.length())).flip();

		return b.array();
	}

	public int analyzeBody(byte[] JointAuthenExRequestBody) {
		if (JointAuthenExRequestBody.length > bodyMaxLength || 0 == JointAuthenExRequestBody.length)
			return -1;

		byte[] body = JointAuthenExRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();

		data = getStringByLength(b, body.length);

		return 0;
	}
}
