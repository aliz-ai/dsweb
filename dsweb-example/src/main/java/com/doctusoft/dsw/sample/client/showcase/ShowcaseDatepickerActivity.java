package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseDatepickerActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseDatepickerActivity>{
	
	@Getter
	private ViewOf<ShowcaseDatepickerActivity> view;
	
	@ObservableProperty
	private String timeTest = "";

	@ObservableProperty
	private Date dateTimeTest;
	
	@MethodRef
	public void datePickerBindingTest(){
		if(dateTimeTest != null) {
			setTimeTest(getDateTimeTest().toString());
		} else {
			setTimeTest("There's no date selected!");
		}
	}
	
	public ShowcaseDatepickerActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseDatepickerView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseDatepickerActivity> implements Serializable {
		public Place() {
			super("showcasedatepicker", ShowcaseDatepickerActivity.class );
		}
	}

}
