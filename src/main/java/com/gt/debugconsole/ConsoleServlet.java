/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * 
 * @author Gábor Horváth
 */
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
