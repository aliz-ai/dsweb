package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseOnbeforeunloadPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseOnbeforeunloadPresenter>{

	@Getter
	private ViewOf<ShowcaseOnbeforeunloadPresenter> view;

	@ObservableProperty
	private String onbeforeunloadMessage = "Are you sure?";

	@ObservableProperty
	private Boolean onbeforeMessageNull;

	public ShowcaseOnbeforeunloadPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseOnbeforeunloadView();

		Bindings.obs(this).get(ShowcaseOnbeforeunloadPresenter_._onbeforeMessageNull).addValueChangeListener(new ValueChangeListener<Boolean>() {

			@Override
			public void valueChanged(final Boolean newValue) {
				if (newValue) {
					setOnbeforeunloadMessage(null);
					setOnbeforeMessageNull(false);
				}
			}
		});
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseOnbeforeunloadPresenter> implements Serializable {
		public Place() {
			super("showcaseonbeforeunload", ShowcaseOnbeforeunloadPresenter.class );
		}
	}

}
