package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ShowcaseActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	private ShowcasePlace place;
	@ObservableProperty
	private ShowcaseItem item;

	public ShowcaseActivity(ClientFactory clientFactory, ShowcasePlace place) {
		this.clientFactory = clientFactory;
		this.place = place;
		item = place.getItem();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<ShowcaseActivity> view = clientFactory.getShowcaseView();
		view.setPresenter(this);
		panel.setWidget(view);
	}

}
