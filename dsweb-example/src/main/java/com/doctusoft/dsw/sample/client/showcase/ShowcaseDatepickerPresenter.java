package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseDatepickerPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseDatepickerPresenter>{
	
	@Getter
	private ViewOf<ShowcaseDatepickerPresenter> view;
	
	@ObservableProperty
	private String dateAsString = "";

	@ObservableProperty
	private Date date = new Date();
	
	@ObservableProperty	
	private String format = "yyyy.mm.dd";
	
	public ShowcaseDatepickerPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseDatepickerView();
		ShowcaseDatepickerPresenter_._date.addChangeListener(this, new ValueChangeListener<Date>() {
			@Override
			public void valueChanged(Date newValue) {
				if(date != null) {
					setDateAsString(date.toString());
				} else {
					setDateAsString("There's no date selected!");
				}
			}
		});
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseDatepickerPresenter> implements Serializable {
		public Place() {
			super("showcasedatepicker", ShowcaseDatepickerPresenter.class );
		}
	}
	
}
