package com.gt.debugconsole;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.gt.debugconsole.module.dashboard.DashboardPage;

public class ConsoleApplication extends WebApplication {

	public static ConsoleApplication get() {
		return (ConsoleApplication) Application.get();
	}

	@Override
	protected void init() {
		super.init();

		mountPage("/", getHomePage());

	}

	@Override
	public Class<? extends Page> getHomePage() {
		return DashboardPage.class;
	}

}
