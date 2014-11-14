package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseChartsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseChartsPresenter>{

	@ObservableProperty
	private String modalContent;

	@ObservableProperty
	private boolean modalVisible;
	
	@Getter
	private ViewOf<ShowcaseChartsPresenter> view;
	
	public ShowcaseChartsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseChartsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseChartsPresenter> implements Serializable {
		public Place() {
			super("showcasecharts", ShowcaseChartsPresenter.class );
		}
	}
	
	@MethodRef
	public void chartClicked(ChartItemClickParam param){
		setModalContent("Selected item: " + param.getItemIndex() + " subindex: " + param.getSubIndex());
		System.out.println( "Selected item: " + param.getItemIndex() + " subindex: " + param.getSubIndex() );
		setModalVisible(true);
	}

}
