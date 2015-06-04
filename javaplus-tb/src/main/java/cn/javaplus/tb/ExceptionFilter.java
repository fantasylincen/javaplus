package cn.javaplus.tb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ExceptionFilter implements Filter {

	protected FilterConfig filterConfig;

	public void doFilter(final ServletRequest req, final ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse hres = (HttpServletResponse) res;
		try {
			chain.doFilter(req, res);
		} catch (Throwable e) {
			hres.sendRedirect("error.jsp?error=" + e.getMessage() + "&type="
					+ e.getClass().getSimpleName());
		}

	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}