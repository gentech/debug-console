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
package com.gt.debugconsole.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * 
 * @author Gábor Horváth
 */
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