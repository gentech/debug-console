package com.gt.debugconsole;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WicketServlet;

import com.gt.debugconsole.util.OverridingServletConfig;

public class ConsoleServlet implements Servlet {

	private OverridingServletConfig servletConfig;
	private WicketServlet wicketServlet;

	@Override
	public void init(final ServletConfig config) throws ServletException {

		servletConfig = new OverridingServletConfig(config);

		servletConfig.setInitParameter(WicketFilter.APP_FACT_PARAM, ConsoleApplicationFactory.class.getName());

		wicketServlet = new WicketServlet();
		wicketServlet.init(servletConfig);
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public void service(final ServletRequest request, final ServletResponse response) throws ServletException,
			IOException {
		wicketServlet.service(request, response);
	}

	@Override
	public String getServletInfo() {
		return "";
	}

	@Override
	public void destroy() {
		wicketServlet.destroy();
		wicketServlet = null;
	}

}
