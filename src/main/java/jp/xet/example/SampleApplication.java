package jp.xet.example;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.AutoLabelResolver;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

public class SampleApplication extends WebApplication {
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	
	@Override
	public Class<? extends Page> getHomePage() {
		return TopPage.class;
	}
	
	@Override
	protected void init() {
		super.init();
		
		new AnnotatedMountScanner().scanPackage(SampleApplication.class.getPackage().getName()).mount(this);
		
		getPageSettings().addComponentResolver(new AutoLabelResolver());
		
		getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);
		getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
		getMarkupSettings().setAutomaticLinking(true);
		getMarkupSettings().setStripWicketTags(true);
	}
}
