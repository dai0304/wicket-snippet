/*
 * Copyright 2011 Daisuke Miyamoto.
 * Created on 2012/01/24
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
package jp.xet.example.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public final class DataProviderImpl implements IDataProvider<String> {
	
	private final int size;
	
	private final List<String> list;
	
	
	/**
	 * インスタンスを生成する。
	 */
	public DataProviderImpl(int size) {
		this.size = size;
		list = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			list.add(String.valueOf(i));
		}
	}
	
	public void detach() {
	}
	
	public Iterator<? extends String> iterator(final int first, final int count) {
		return list.subList(first, first + count).iterator();
	}
	
	public IModel<String> model(String object) {
		return Model.of(object);
	}
	
	public int size() {
		return size;
	}
}
