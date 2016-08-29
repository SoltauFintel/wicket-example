package de.mwvb.wicket.example;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.protocol.http.WebApplication;

public class ExampleApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return NamenPage.class;
	}
	
	@Override
	protected void init() {
		super.init();
		
		mount(new MountedMapper("/namen", NamenPage.class));
		
		System.out.println("wicket-example gestartet");
	}
	
	// TODO 2. Page machen, verlinken
}
