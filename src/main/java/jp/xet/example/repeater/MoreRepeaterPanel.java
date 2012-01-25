package jp.xet.example.repeater;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
public final class MoreRepeaterPanel<T> extends Panel {
	
	public MoreRepeaterPanel(String id, IDataProvider<T> dataProvider, int itemsParPage, PopulationProcessor<T> p) {
		this(id, dataProvider, itemsParPage, p, 0);
	}
	
	MoreRepeaterPanel(String id, final IDataProvider<T> dataProvider, final int itemsParPage,
			final PopulationProcessor<T> p, final int index) {
		super(id);
		
		DataView<T> view = new DataView<T>("repeatingList", dataProvider, itemsParPage) {
			
			@Override
			protected void populateItem(Item<T> item) {
				p.populateItem("content", item);
			}
		};
		view.setCurrentPage(index);
		add(view);
		
		final WebMarkupContainer next = new WebMarkupContainer("next");
		next.setOutputMarkupId(true);
		next.setOutputMarkupPlaceholderTag(true);
		next.setVisible(false);
		add(next);
		
		final WebMarkupContainer container = new WebMarkupContainer("container");
		container.setOutputMarkupId(true);
		add(container);
		
		AjaxLink<Void> button = new AjaxLink<Void>("repeatingButton") {
			
			private static final long serialVersionUID = 1L;
			
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				Panel panel = new MoreRepeaterPanel<T>(next.getId(), dataProvider, itemsParPage, p, index + 1);
				next.replaceWith(panel);
				container.setVisible(false);
				target.add(container, panel);
			}
		};
		container.add(button);
	}
	
	
	public interface PopulationProcessor<T> extends Serializable {
		
		void populateItem(String id, Item<T> item);
		
	}
}
