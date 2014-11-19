package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.Properties;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.datatable.OrderingDirection;
import com.doctusoft.dsw.client.comp.datatable.SingleDataTableOrdering;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ShowcaseTablePresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTablePresenter>{
	
	@Getter
	private ViewOf<ShowcaseTablePresenter> view;
	
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
	
	//--- ordering
	@ObservableProperty
	private SingleDataTableOrdering ordering;
	
	@ObservableProperty
	private String orderingInfo = "";
	
	public ShowcaseTablePresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTableView();
		personList.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu", new Date(7, 10, 18)));
		personList.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu", new Date(30, 9, 29)));
		personList.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu", new Date(6, 7, 27)));
		new ListChangeListener(Bindings.obs(selection)) {
			@Override
			protected void changed() {
				List<String> names = Lists.transform(selection, Properties.functionOf(PersonDto_._name));
				setSelectionString("Your selection is: " + Joiner.on(", ").join(names));
			}
		};
		ShowcaseTablePresenter_._ordering.addChangeListener(this, new ValueChangeListener<SingleDataTableOrdering>() {
			@Override
			public void valueChanged(SingleDataTableOrdering newValue) {
				setOrderingInfo("Ordering: " + newValue);
			}
		});
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTablePresenter> implements Serializable {
		public Place() {
			super("showcasetables", ShowcaseTablePresenter.class );
		}
	}
	
	@MethodRef
	public void personClicked(PersonDto personDto) {
		setModalContent("You selected: " + personDto.getName());
		setModalVisible(true);
	}
	
	@MethodRef
	public void clearOrdering() {
		setOrdering(null);
	}

	@MethodRef
	public void orderByName() {
		setOrdering(new SingleDataTableOrdering(1, OrderingDirection.Descending));
	}
}
