package cn.vgame.b.log;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.GmLogDao;
import cn.vgame.b.gen.dto.MongoGen.GmLogDto;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 管理员操作日志拦截器
 */
public class LogInterceptor implements Interceptor {

	private final class PreResultListenerImplementation implements
			PreResultListener {
		public void beforeResult(ActionInvocation ai, String arg1) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			try {
				String gmUserId = (String) session.getAttribute("gmUserId");

				String className = getClassName(ai);
				String methodName = ai.getInvocationContext().getName();

				GmLogDao dao = Daos.getGmLogDao();
				GmLogDto dto = dao.createDTO();

				dto.setId(Util.ID.createId());
				dto.setDate(Util.Time.getCurrentFormatTime());
				dto.setUser(gmUserId);
				dto.setClassName(className);
				dto.setMethodName(methodName);
				addArgs(ai, dto);
				dto.setResult(ai.getResultCode());

				dao.save(dto);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private void addArgs(ActionInvocation ai, GmLogDto dto) {
			Map<String, Object> map = ai.getInvocationContext()
					.getParameters();
			Set<String> keys = map.keySet();

			for (String key : keys) {
				String arg = key + ":" + ((Object[]) map.get(key))[0];
				dto.getArgs().add(arg);
			}
		}

		private String getClassName(ActionInvocation ai) {
			String className = ai.getAction().toString();
			String[] split = className.split("\\p{Punct}");
			className = split[split.length - 2];
			return className;
		}
	}

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation ai) throws Exception {

		ai.addPreResultListener(new PreResultListenerImplementation());

		return ai.invoke();
	}

	public void destroy() {

	}

	public void init() {

	}
}