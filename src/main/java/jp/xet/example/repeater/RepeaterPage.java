package jp.xet.example.repeater;

import jp.xet.example.DataProviderImpl;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/repeater")
@SuppressWarnings("serial")
public class RepeaterPage extends WebPage {
	
	public RepeaterPage() {
		add(new MoreRepeaterPanel1<String>("repeater", new DataProviderImpl(1000), 10));
	}
}
