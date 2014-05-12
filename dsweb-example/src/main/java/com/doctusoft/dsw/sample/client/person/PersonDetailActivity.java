package com.doctusoft.dsw.sample.client.person;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonDetailActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	@ObservableProperty @Getter
	private PersonDto personDto;

	private long personId;

	public PersonDetailActivity(ClientFactory clientFactory, long personId) {
		this.clientFactory = clientFactory;
		this.personId = personId;
		loadPersonDto();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<PersonDetailActivity> view = clientFactory.getPersonDetailView();
		view.setPresenter(this);
		panel.setWidget(view);
	}
	
	protected void loadPersonDto() {
		clientFactory.getPersonRemoteServiceAsync().getPerson(personId, new AbstractCallback<PersonDto>(clientFactory) {
			@Override
			public void onSuccess(PersonDto result) {
				setPersonDto(result);
			}
		});
	}

}
