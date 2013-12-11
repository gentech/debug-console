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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.settings.IExceptionSettings.AjaxErrorStrategy;
import org.apache.wicket.util.crypt.CharEncoding;

import com.gt.debugconsole.module.Module;
import com.gt.debugconsole.module.dashboard.DashboardModule;
import com.gt.debugconsole.module.dashboard.DashboardPage;
import com.gt.debugconsole.module.scripting.ScriptingModule;

/**
 * 
 * @author Gábor Horváth
 */
class ConsoleApplicationImpl extends ConsoleApplication {

	private final List<Module> modules = new CopyOnWriteArrayList<Module>();

	@Override
	protected void init() {

		super.init();

		// markup settings

		getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);

		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

		// error setting

		if (isDevelopmentConfiguraton()) {
			getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_EXCEPTION_PAGE);
		} else {
			getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		}
		getExceptionSettings().setAjaxErrorHandlingStrategy(AjaxErrorStrategy.INVOKE_FAILURE_HANDLER);

		registerBuiltInModules();

	}

	@Override
	public Class<? extends Page> getHomePage() {
		return DashboardPage.class;
	}

	@Override
	public void registerModule(final Module module) {
		modules.add(module);
		module.initialize(this);
	}

	protected void registerBuiltInModules() {

		registerModule(new DashboardModule());
		registerModule(new ScriptingModule());

	}

	public boolean isDevelopmentConfiguraton() {
		return RuntimeConfigurationType.DEPLOYMENT.equals(getConfigurationType());
	}

}
