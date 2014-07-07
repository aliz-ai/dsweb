package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.datatable.PropertyColumn;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;

public class ShowcaseTableView implements HasComponentModel {

	private Container container = new Container();
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	public ShowcaseTableView() {
		personList.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu"));
		personList.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu"));
		personList.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu"));
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Tables</h1>"));
		new DataTable<PersonDto>().appendTo(container)
			.addColumn(new PropertyColumn<PersonDto>("Id", PersonDto_._id))
			.addColumn(new PropertyColumn<PersonDto>("Name", PersonDto_._name))
			.addColumn(new PropertyColumn<PersonDto>("Email", PersonDto_._email))
			.bind(Bindings.obs(this).get(ShowcaseTableView_._personList));
		
	}

	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}
}
