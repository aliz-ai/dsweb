package com.doctusoft.dsw.sample.client.sandbox;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.AbstractPresenter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class SandboxPresenter extends AbstractPresenter<SandboxPresenter> {
	
	@Getter
	private ViewOf<SandboxPresenter> view;
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();

	public SandboxPresenter(Place place, ClientFactory clientFactory) {
		view = clientFactory.getSandboxView();
		tags.add("a");
		tags.add("b");
	}
	
	public static class Place extends AbstractPlace<SandboxPresenter> implements Serializable {
		public Place() {
			super("sandbox", SandboxPresenter.class );
		}
	}


}
