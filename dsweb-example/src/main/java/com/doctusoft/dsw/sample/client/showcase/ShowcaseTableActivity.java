package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;

public class ShowcaseTableActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTableActivity>{
	
	@Getter
	private ViewOf<ShowcaseTableActivity> view;
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();

	@ObservableProperty
	private ObservableList<PersonDto> selection = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private String selectionString = "";
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.Single;
	
	@ObservableProperty
	private String modalContent;
	
	@ObservableProperty
	private boolean modalVisible;
	
	public ShowcaseTableActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTableView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTableActivity> implements Serializable {
		public Place() {
			super("showcasetable", ShowcaseTableActivity.class );
		}
	}
	
	@MethodRef
	public void personClicked(PersonDto personDto) {
		setModalContent("You selected: " + personDto.getName());
		setModalVisible(true);
	}

}
