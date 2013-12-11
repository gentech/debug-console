package com.gt.debugconsole;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ConsoleApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return null;
	}

}
