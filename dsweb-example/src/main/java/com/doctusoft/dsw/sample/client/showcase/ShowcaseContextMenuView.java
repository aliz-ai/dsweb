package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.ContextMenu;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.sample.client.AbstractViewWithNavBar;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;

public class ShowcaseContextMenuView extends AbstractViewWithNavBar<ShowcaseContextMenuPresenter> {

	public ShowcaseContextMenuView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Context Menu</h1>"));
	    
	    new DataTable<PersonDto>()
	      .addColumn(Columns.from("Id", PersonDto_._id))
	      .addColumn(Columns.obs("Name", PersonDto_._name))
	      .addColumn(Columns.from("Email", PersonDto_._email))
	      .addColumn(Columns.from("Born", PersonDto_._birthDate).format(new DateFormatter("yyyy-MM-dd")))
	      .bind(bindOnPresenter().get(ShowcaseContextMenuPresenter_._personList))
	      .bindSelectionMode(bindOnPresenter().get(ShowcaseContextMenuPresenter_._selectionMode))
	      .bindSelection(bindOnPresenter().get(ShowcaseContextMenuPresenter_._selection))
	      .appendTo(subContainer).addStyleClass("valami");
	    
	    new ContextMenu<Link>("td", "valami2")
	      .addMenuItem(new Link("Kijelölt törlése").click(presenterMethod(ShowcaseContextMenuPresenter_.__removeSelection)))
	      .appendTo(subContainer);
	}

}
