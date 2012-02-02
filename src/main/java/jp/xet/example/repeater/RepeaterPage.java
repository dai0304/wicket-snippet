package jp.xet.example.repeater;

import jp.xet.example.DataProviderImpl;
import jp.xet.uncommons.wicket.gp.MoreRepeaterPanel;
import jp.xet.uncommons.wicket.gp.MoreRepeaterPanel.PopulationProcessor;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/repeater")
@SuppressWarnings("serial")
public class RepeaterPage extends WebPage {
	
	public RepeaterPage() {
		add(new MoreRepeaterPanel<String>("repeater", new DataProviderImpl(10), 2, new PopulationProcessor<String>() {
			
			public void populateItem(String id, Item<String> item) {
				item.add(new Label(id, item.getModelObject().toString()));
			}
		}));
	}
}
