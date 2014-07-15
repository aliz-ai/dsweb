package com.doctusoft.dsw.sample.client.person;

import java.util.List;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonListActivity extends AbstractActivity {

	private ClientFactory clientFactory;

	public PersonListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<PersonListActivity> view = clientFactory.getPersonListView();
		view.setPresenter(this);
		panel.setWidget(view);
		loadList();
	}

	private void loadList() {
		clientFactory.getPersonRemoteServiceAsync().getPersonDtos(new AbstractCallback<List<PersonDto>>(clientFactory) {
			public void onSuccess(List<PersonDto> result) {
				personList.clear();
				personList.addAll(result);
			};
		});
	}

	@MethodRef
	public void addPerson() {
		final PersonDto dto = new PersonDto();
		dto.setName("New person");
		clientFactory.getPersonRemoteServiceAsync().save(dto, new AbstractCallback<Long>(clientFactory) {
			@Override
			public void onSuccess(Long result) {
				dto.setId(result);
				personList.add(dto);
			}
		});
	}

	@MethodRef
	public void deletePerson(final PersonDto dto) {
		clientFactory.getPersonRemoteServiceAsync().delete(dto.getId(), new AbstractCallback<Void>(clientFactory) {
			@Override
			public void onSuccess(Void result) {
				personList.remove(dto);
			}
		});
	}

}