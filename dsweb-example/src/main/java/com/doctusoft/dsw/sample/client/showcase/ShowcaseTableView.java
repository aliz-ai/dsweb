package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
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
			.addColumn(Columns.actionButton(this, ShowcaseActivity_.__personClicked, "View"))
			.bind(bindOnPresenter().get(ShowcaseActivity_._personList))
			.bindSelectionMode(bindOnPresenter().get(ShowcaseActivity_._selectionMode))
			.bindSelection(bindOnPresenter().get(ShowcaseActivity_._selection))
			.appendTo(container);
		new ModalDialog()
			.withHeader("Selection")
			.addContent(new Label().bind(bindOnPresenter().get(ShowcaseActivity_._modalContent)))
			.bindDialogVisible(bindOnPresenter().get(ShowcaseActivity_._modalVisible)).appendTo(container);
		new HtmlContent("<hr/>").appendTo(container);
		new Label("Change selection mode:").appendTo(container);
		new Select<SelectionMode>()
			.bind(bindOnPresenter().get(ShowcaseActivity_._selectionMode))
			.withSelectItems(SelectItems.fromEnum(SelectionMode.values()))
			.appendTo(container);
		new Label("", "div").bind(bindOnPresenter().get(ShowcaseActivity_._selectionString)).appendTo(container);
			
	}

}
