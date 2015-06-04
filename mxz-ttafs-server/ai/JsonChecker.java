import java.util.List;

import com.alibaba.fastjson.JSON;

import cn.mxz.RolesTemplet;
import cn.mxz.RolesTempletConfig;


public class JsonChecker {

	public static void check() {
		List<RolesTemplet> all = RolesTempletConfig.getAll();
		for (RolesTemplet rt : all) {
			String text = getRoleText(rt);
			JSON.parse(text);
		}
	}
	
	private static String getRoleText(RolesTemplet temp) {
		String role = temp.getRole();
		return role.replaceAll("\\[newline\\]", "\r");
	}

}
