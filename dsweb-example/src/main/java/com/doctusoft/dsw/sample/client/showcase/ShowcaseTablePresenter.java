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
import com.doctusoft.dsw.client.comp.datatable.ButtonColumnDescriptor;
import com.doctusoft.dsw.client.comp.datatable.ColumnDescriptor;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.client.comp.datatable.OrderingDirection;
import com.doctusoft.dsw.client.comp.datatable.PropertyColumnDescriptor;
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

	@ObservableProperty
	private ObservableList<ColumnDescriptor<PersonDto>> columnDescriptors = new ObservableList<ColumnDescriptor<PersonDto>>();

	private List<ColumnDescriptor<PersonDto>> defaultColumns = Lists.newArrayList();

	{
		defaultColumns.add(new PropertyColumnDescriptor<PersonDto, Long>("Id", PersonDto_._id));
		defaultColumns.add(new PropertyColumnDescriptor<PersonDto, String>("Name", PersonDto_._name));
		defaultColumns.add(new PropertyColumnDescriptor<PersonDto, String>("Email", PersonDto_._email));
		defaultColumns.add(new PropertyColumnDescriptor<PersonDto, Date>("Born", PersonDto_._birthDate).withConverter(new DateFormatter("yyyy-MM-dd")));
		defaultColumns.add(new ButtonColumnDescriptor<ShowcaseTablePresenter, PersonDto>("View", this, ShowcaseTablePresenter_.__personClicked));
	}

	public ShowcaseTablePresenter(final Place place, final ClientFactory clientFactory ) {
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
			public void valueChanged(final SingleDataTableOrdering newValue) {
				setOrderingInfo("Ordering: " + newValue);
			}
		});
		columnDescriptors.addAll(defaultColumns);
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTablePresenter> implements Serializable {
		public Place() {
			super("showcasetables", ShowcaseTablePresenter.class );
		}
	}

	@MethodRef
	public void personClicked(final PersonDto personDto) {
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

	@MethodRef
	public void clearColumns() {
		setColumnDescriptors(new ObservableList<ColumnDescriptor<PersonDto>>(defaultColumns));
	}

	@MethodRef
	public void addEmail() {
		columnDescriptors.add(new PropertyColumnDescriptor<PersonDto, String>("Email", PersonDto_._email));
	}

	@MethodRef
	public void removeEmail() {
		columnDescriptors.remove(new PropertyColumnDescriptor<PersonDto, String>("Email", PersonDto_._email));
	}

}
