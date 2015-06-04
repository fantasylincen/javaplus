package cn.vgame.gate.user;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignonFilter implements Filter {

	protected FilterConfig filterConfig;

	public void doFilter(final ServletRequest req, final ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) req;
		String uri = hreq.getRequestURI();
		uri = uri.replaceAll(".+/", "");
		if (!uri.equals("login.jsp") && (uri.matches("[a-zA-Z0-9]+") || uri.contains(".jsp"))) {
			filterByLogin(req, res, chain, hreq);
		} else {
			chain.doFilter(req, res);
		}

	}

	private void filterByLogin(final ServletRequest req,
			final ServletResponse res, FilterChain chain,
			HttpServletRequest hreq) {
		HttpServletResponse hres = (HttpServletResponse) res;
		HttpSession session = hreq.getSession();

		try {
			if (session.getAttribute("userId") == null) {
				hres.sendRedirect("login.jsp");
			} else {
				chain.doFilter(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
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