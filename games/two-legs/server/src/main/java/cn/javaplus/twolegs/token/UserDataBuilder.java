package cn.javaplus.twolegs.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserDataBuilder {

	public class RoleDataImpl implements RoleData {

		private JSONObject obj;

		public RoleDataImpl(JSONObject obj) {
			this.obj = obj;
		}

		@Override
		public int getRoleId() {
			return obj.getInteger("roleId");
		}

		@Override
		public String getUname() {
			return obj.getString("uname");
		}

		@Override
		public String getNick() {
			return obj.getString("nick");
		}

	}

	public String build(RoleData data) {
		int roleId = data.getRoleId();
		String uname = data.getUname();
		JSONObject j = new JSONObject();
		j.put("roleId", roleId);
		j.put("uname", uname);
		j.put("nick", data.getNick());
		return DES.encrypt(j.toJSONString());
	}

	public RoleData resoleve(String roleData) {
		roleData = DES.decrypt(roleData);
		JSONObject j = (JSONObject) JSON.parse(roleData);
		return new RoleDataImpl(j);
	}

}
