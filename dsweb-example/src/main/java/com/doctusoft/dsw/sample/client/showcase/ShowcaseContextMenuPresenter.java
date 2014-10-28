package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.client.mvp.AbstractPresenter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;

public class ShowcaseContextMenuPresenter extends AbstractPresenter<ShowcaseContextMenuPresenter> {

	@Getter
	private ViewOf<ShowcaseContextMenuPresenter> view;

	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.Single;
	
	@ObservableProperty
	private ObservableList<PersonDto> selection = new ObservableList<PersonDto>();
	
	private ClientFactory clientFactory;
	
	public ShowcaseContextMenuPresenter(Place place, ClientFactory clientFactory) {
		view = clientFactory.getShowcaseContextMenuView();
		this.clientFactory = clientFactory;
		personList.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu", new Date(7, 10, 18)));
		personList.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu", new Date(30, 9, 29)));
		personList.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu", new Date(6, 7, 27)));
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseContextMenuPresenter> implements Serializable {
		public Place() {
			super("showcasecontextmenu", ShowcaseContextMenuPresenter.class );
		}
	}
	
	@MethodRef
	public void removeSelection() {
		personList.removeAll(selection);
	}

}
