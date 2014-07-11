package com.doctusoft.dsw.sample.client.showcase;

import java.util.Date;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.BaseActivity;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;

public class ShowcaseActivity extends BaseActivity<ShowcaseActivity, ShowcasePlace> {
	
	@ObservableProperty
	private ShowcaseItem item;
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private String modalContent;
	
	@ObservableProperty
	private boolean modalVisible;
	
	public ShowcaseActivity(ClientFactory clientFactory, ShowcasePlace showcasePlace) {
		super(clientFactory, showcasePlace);
		setItem(place.getItem());
		personList.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu", new Date(7, 10, 18)));
		personList.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu", new Date(30, 9, 29)));
		personList.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu", new Date(6, 7, 27)));
	}
	
	@Override
	protected ViewOf<ShowcaseActivity> createView() {
		return clientFactory.getShowcaseView();
	}

	@MethodRef
	public void dangerousMethod() {
		throw new RuntimeException("unexpected exception");
	}
	
	@MethodRef
	public void personClicked(PersonDto personDto) {
		setModalContent("You selected: " + personDto.getName());
		setModalVisible(true);
	}
}
