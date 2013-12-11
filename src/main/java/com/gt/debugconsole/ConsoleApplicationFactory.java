package com.gt.debugconsole;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

public class ConsoleApplicationFactory implements IWebApplicationFactory {

	@Override
	public WebApplication createApplication(final WicketFilter filter) {
		return new ConsoleApplication();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy(final WicketFilter filter) {
	}

}
