package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.ComponentColumn;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;

public class ShowcaseTableView extends ContainerWithPresenter<ShowcaseActivity> {

	public ShowcaseTableView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Tables</h1>"));
		new DataTable<PersonDto>()
			.addColumn(Columns.from("Id", PersonDto_._id))
			.addColumn(Columns.from("Name", PersonDto_._name))
			.addColumn(Columns.from("Email", PersonDto_._email))
			.addColumn(Columns.from("Born", PersonDto_._birthDate).format(new DateFormatter("yyyy-MM-dd")))
			.addColumn(new ComponentColumn<PersonDto>("") {
				@Override
				public HasComponentModel getComponent(PersonDto item) {
					return new Button("View").click(presenterMethod(ShowcaseActivity_.__personClicked, item));
				}
			})
			.bind(bindOnPresenter().get(ShowcaseActivity_._personList))
			.appendTo(container);
		new ModalDialog().addContent(new Label().bind(bindOnPresenter().get(ShowcaseActivity_._modalContent)))
			.bindDialogVisible(bindOnPresenter().get(ShowcaseActivity_._modalVisible)).appendTo(container);
	}

}
