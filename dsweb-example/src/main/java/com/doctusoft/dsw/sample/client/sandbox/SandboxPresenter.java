package com.doctusoft.dsw.sample.client.sandbox;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.AbstractPresenter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.common.collect.Lists;

public class SandboxPresenter extends AbstractPresenter<SandboxPresenter> {
	
	@Getter
	private ViewOf<SandboxPresenter> view;
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();
	
	@ObservableProperty
	private String queryString;
	
	@ObservableProperty
	private List<String> suggestions = null;
	
	@ObservableProperty
	private String valuesLabel;

	public SandboxPresenter(Place place, ClientFactory clientFactory) {
		view = clientFactory.getSandboxView();
		tags.add("preset value");
		SandboxPresenter_._queryString.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				setSuggestions(Lists.newArrayList(newValue + "1", newValue + "2", newValue + "3"));
			}
		});
		new ListChangeListener(Bindings.obs(this).get(SandboxPresenter_._tags)) {
			@Override
			protected void changed() {
				setValuesLabel("" + tags);
			}
		};
	}
	
	public static class Place extends AbstractPlace<SandboxPresenter> implements Serializable {
		public Place() {
			super("sandbox", SandboxPresenter.class );
		}
	}


}
