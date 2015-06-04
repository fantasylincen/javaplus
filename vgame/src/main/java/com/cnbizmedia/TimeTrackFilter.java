package com.cnbizmedia;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TimeTrackFilter implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Date startTime, endTime;
		double totalTime;
		startTime = new Date();
		// Forward the request to the next resource in the chain
		chain.doFilter(request, response);
		// -- Process the response -- \\
		// Calculate the difference between the start time and end time
		endTime = new Date();
		totalTime = endTime.getTime() - startTime.getTime();
		totalTime = totalTime / 1000; // Convert from milliseconds to seconds
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);
		writer.println();
		writer.println("===============");
		writer.println("Total elapsed time is: " + totalTime + " seconds.");
		writer.println("===============");
		// Log the resulting string
		writer.flush();
		filterConfig.getServletContext().log(sw.getBuffer().toString());
	}

}
