package com.doctusoft.dsw.sample.client.showcase;

/*
 * #%L
 * dsweb-example
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.Properties;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.client.comp.TagOptions;
import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity_;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ShowcaseActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseActivity> {

	@Getter
	private ViewOf<ShowcaseActivity> view;
	
	@ObservableProperty
	private ShowcaseItem item;

	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();

	@ObservableProperty
	private String modalContent;

	@ObservableProperty
	private boolean modalVisible;

	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.Single;

	@ObservableProperty
	private ObservableList<PersonDto> selection = new ObservableList<PersonDto>();

	@ObservableProperty
	private String selectionString = "";

	@ObservableProperty
	private ObservableList<TagOption> tagOptions = new ObservableList<TagOption>();

	@ObservableProperty
	private ObservableList<TagOption> tagOptionSuggestions = new ObservableList<TagOption>();

	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();

	@ObservableProperty
	private String tagsJoined = "";

	@ObservableProperty
	private ObservableList<String> tagSuggestions = new ObservableList<String>();

	@ObservableProperty
	private String timeTest = "";

	@ObservableProperty
	private Date dateTimeTest;

	private static int counter = 0;

	@ObservableProperty
	private String content2 = "<p>ohplease</p><p>hello</p>";

	@ObservableProperty
	ObservableList<String> options = new ObservableList<String>();
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseActivity> implements Serializable {
		public Place() {
			super("showcase", ShowcaseActivity.class );
		}
	}
	
	public ShowcaseActivity(Place place, ClientFactory clientFactory) {
		view = clientFactory.getShowcaseView();
		//setItem(place.getItem());
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
		tagSuggestions.add("Valami");
		tagOptionSuggestions.addAll( TagOptions.fromStrings( "Ez", "Az", "mi", "sas" ) );
		TagOption tagOp = new TagOption();
		tagOp.setName( "Ez már más" );
		tagOp.setStyleClass( "label label-warning" );
		TagOption tagOp2 = new TagOption();
		tagOp2.setName( "Ez már megint más" );
		tagOp2.setStyleClass( "label label-success" );
		tagOptionSuggestions.add( tagOp2 );
		tagOptionSuggestions.add( tagOp );
		new ListChangeListener(Bindings.obs(this).get(ShowcaseActivity_._tags)) {
			@Override
			protected void changed() {
				setTagsJoined("Selected tags: " + Joiner.on(",").join(tags));
			}
		};
		options.add("Bela");
		options.add("jozsi");
		options.add("Nora");
		options.add("ELemer");
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

	@MethodRef
	public void chartClicked(ChartItemClickParam param){
		setModalContent("Selected item: " + param.getItemIndex() + " subindex: " + param.getSubIndex());
		System.out.println( "Selected item: " + param.getItemIndex() + " subindex: " + param.getSubIndex() );
		setModalVisible(true);
	}
	@MethodRef
	public void datePickerBindingTest(){
		if(dateTimeTest != null) {
			setTimeTest(getDateTimeTest().toString());
		} else {
			setTimeTest("There's no date selected!");
		}
	}

	@MethodRef
	public void addOption() {
		options.add(1, "option" + counter++);
	}

}