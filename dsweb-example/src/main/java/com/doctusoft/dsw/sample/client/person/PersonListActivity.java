package com.doctusoft.dsw.sample.client.person;

import java.util.List;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.common.collect.Lists;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonListActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	public PersonListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@ObservableProperty
	private ObservableList<SelectItem<Long>> locationItems = new ObservableList<SelectItem<Long>>();
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private Boolean checked = true;
	
	@ObservableProperty
	private String password;
	
	@ObservableProperty
	private String checkboxLabel = "Check it!";
	
	@ObservableProperty
	private String inplaceTextLabel = "Inplace";
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();
	
	@ObservableProperty
	private int progress = 30;
	
	@ObservableProperty
	private PersonDto person = new PersonDto();
	
	@ObservableProperty
	private String textareaText = "Lorem ipsum textareasum blipblupqweasd";
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<PersonListActivity> view = clientFactory.getPersonListView();
		view.setPresenter(this);
		generateData();
		person.setId(locationItems.get(2).getValue());
		tags.addAll(Lists.newArrayList("egy","ketto","harom"));
		panel.setWidget(view);
		view.viewPresented();
		loadList();
	}
	
	private void loadList() {
		clientFactory.getPersonRemoteServiceAsync().getPersonDtos(new AbstractCallback<List<PersonDto>>(clientFactory) {
			@Override
			public void onSuccess(List<PersonDto> result) {
				personList.clear();
				personList.addAll(result);
			};
		});
	}
	
	private void generateData() {
		SelectItem<Long> item = new SelectItem<Long>();
		item.setCaption("Ehun");
		item.setValue(new Long(42));
		locationItems.add(item);

		SelectItem<Long> item2 = new SelectItem<Long>();
		item2.setCaption("Ehelyt");
		item2.setValue(new Long(43));
		locationItems.add(item2);

		SelectItem<Long> item3 = new SelectItem<Long>();
		item3.setCaption("Ahelyt");
		item3.setValue(new Long(44));
		locationItems.add(item3);
	}

	
	@MethodRef
	public void checkBindings() {
		System.out.println("\nCheckbox: " + checked);
		System.out.println("Textarea: " + textareaText);
		System.out.println("Password: " + password);
		System.out.println("Tags: " + tags);
//		System.out.println("Select value: " + selectValue);
	}
	
	@MethodRef
	public void addTag() {
		tags.addAll(Lists.newArrayList("lofasz","kehely"));
	}
	
	@MethodRef
	public void removeTag() {
//		tags.remove("blip");
		tags.clear();
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
