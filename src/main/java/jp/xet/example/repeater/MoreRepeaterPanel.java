package jp.xet.example.repeater;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * TODO for daisuke
 * 
 * @since 1.0.0
 * @version $Id$
 * @author daisuke
 */
@SuppressWarnings("serial")
public class MoreRepeaterPanel<T> extends Panel {
	
	public MoreRepeaterPanel(String id, IDataProvider<T> dataProvider, int itemsParPage) {
		this(id, dataProvider, itemsParPage, 0);
	}
	
	MoreRepeaterPanel(String id, final IDataProvider<T> dataProvider, final int itemsParPage, final int index) {
		super(id);
		
		DataView<T> view = new DataView<T>("dataView", dataProvider, itemsParPage) {
			
			@Override
			protected void populateItem(Item<T> item) {
				item.add(new Label("label", item.getModelObject().toString()));
			}
		};
		view.setCurrentPage(index);
		add(view);
		
		final WebMarkupContainer moreContent = new WebMarkupContainer("moreContent");
		moreContent.setOutputMarkupId(true);
		AjaxLink<Void> moreLink = new AjaxLink<Void>("moreLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				WebMarkupContainer replaceMoreContent =
						new MoreRepeaterPanel<T>(moreContent.getId(), dataProvider, itemsParPage, index + 1);
				moreContent.replaceWith(replaceMoreContent);
				setVisible(false);
				
				target.add(replaceMoreContent, this);
			}
		};
		moreLink.setOutputMarkupId(true);
		moreContent.add(moreLink);
		add(moreContent);
		
		setRenderBodyOnly(true);
	}
}
