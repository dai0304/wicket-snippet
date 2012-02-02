/*
 * Copyright 2011 Daisuke Miyamoto.
 * Created on 2012/02/02
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.xet.example.ajax;

import java.util.UUID;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * {@link AbstractDefaultAjaxBehavior}のサンプル。
 * 
 * @author daisuke
 */
@MountPath("/ajax")
@SuppressWarnings("serial")
public class AjaxBehaviorPage extends WebPage {
	
	/**
	 * true -- 意図通り
	 * false -- SampleBehavior#respondが呼ばれない
	 */
	private static final boolean STATELESS = false;
	
	private static Logger logger = LoggerFactory.getLogger(AjaxBehaviorPage.class);
	
	String value;
	
	private SampleBehavior behavior;
	
	private Label valueLabel;
	
	
	/**
	 * インスタンスを生成する。
	 */
	public AjaxBehaviorPage() {
		valueLabel = new Label("label", new PropertyModel<String>(this, "value"));
		valueLabel.setMarkupId(valueLabel.getId());
		valueLabel.setOutputMarkupId(true);
		add(valueLabel);
		
		behavior = new SampleBehavior();
		add(behavior);
		
		if (STATELESS) {
			add(new WebComponent("form"));
		} else {
			add(new Form<Void>("form"));
		}
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScript("function callll(){" + behavior.getCallbackScript() + "}", UUID.randomUUID()
			.toString());
	}
	
	
	private class SampleBehavior extends AbstractDefaultAjaxBehavior {
		
		@Override
		public CharSequence getCallbackScript() {
			return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl()
					+ "&value=' + encodeURIComponent('vvaalluuee')");
		}
		
		@Override
		public boolean getStatelessHint(Component component) {
			return STATELESS;
		}
		
		@Override
		protected void respond(AjaxRequestTarget target) {
			value = target.getPageParameters().get("value").toString();
			logger.info("value = {}", value);
			target.add(valueLabel);
		}
	}
}
