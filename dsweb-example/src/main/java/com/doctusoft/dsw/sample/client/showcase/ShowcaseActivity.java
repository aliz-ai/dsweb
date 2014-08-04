package com.doctusoft.dsw.sample.client.showcase;

import java.util.Date;
import java.util.List;

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
import com.doctusoft.dsw.sample.client.BaseActivity;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ShowcaseActivity extends BaseActivity<ShowcaseActivity, ShowcasePlace> {
	
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
	private ObservableList<String> tagSuggestions = new ObservableList<String>();
	
	@ObservableProperty
	private String timeTest = "";
	
	@ObservableProperty
	private Date dateTimeTest;
	
	public ShowcaseActivity(ClientFactory clientFactory, ShowcasePlace showcasePlace) {
		super(clientFactory, showcasePlace);
		setItem(place.getItem());
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
		}
	}
		
}