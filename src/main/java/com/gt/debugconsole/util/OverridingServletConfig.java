package com.gt.debugconsole.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class OverridingServletConfig implements ServletConfig {

	private final ServletConfig delegate;
	private final Map<String, String> initParameters = new HashMap<String, String>();

	public OverridingServletConfig(final ServletConfig delegate) {
		super();

		this.delegate = delegate;

		// copy original init parameters

		final Enumeration<?> parameterNames = delegate.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			final String parameterName = (String) parameterNames.nextElement();
			initParameters.put(parameterName, delegate.getInitParameter(parameterName));
		}

	}

	@Override
	public String getServletName() {
		return delegate.getServletName();
	}

	@Override
	public ServletContext getServletContext() {
		return delegate.getServletContext();
	}

	@Override
	public String getInitParameter(final String name) {
		return initParameters.get(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return new IteratorEnumeration<String>(initParameters.keySet().iterator());
	}

	public void setInitParameter(final String name, final String value) {
		initParameters.put(name, value);
	}

}