package com.doctusoft.dsw.sample.client.showcase;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.BarChart;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.PieChart;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel.LegendPosition;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel.BarDirection;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseChartsView extends BaseShowcaseView<ShowcaseChartsPresenter> {

	@Getter
	private final BarChart barChart;

	private Label modalContent;

	@Getter
	private final PieChart pieChart;

	public ShowcaseChartsView() {
		modalContent = new Label();
		modalContent.bind(bindOnPresenter().get(ShowcaseChartsPresenter_._modalContent));

		new ModalDialog().addContent(modalContent).withHeader("Chart item selection").bindDialogVisible(bindOnPresenter().get(ShowcaseChartsPresenter_._modalVisible)).appendTo(subContainer);

		List<PieChartItemModel> items = new ArrayList<PieChartItemModel>();
		items.add( new PieChartItemModel( 12, "Gyula" ) );
		items.add( new PieChartItemModel( 22, "Béla" ) );
		pieChart = new PieChart()
		.appendTo( subContainer )
		.withItems( items )
		.withLegendPosition( LegendPosition.EAST )
		.withTitle( "Proba chart" )
		.withShowToolTip( true )
		.rowClick( presenterMethod( ShowcaseChartsPresenter_.__chartClicked ) );

		List<BarChartItemModel> items2 = new ArrayList<BarChartItemModel>();

		items2.add( createDummyBarChartItemModel( 1, 2, 3, "tel" ));
		items2.add( createDummyBarChartItemModel( 1, -2, 8, "nyar" ));
		items2.add( createDummyBarChartItemModel( 41, 2, 3, "osz" ));
		items2.add( createDummyBarChartItemModel( -1, 12, 3, "tavasz" ));

		ArrayList<String> seriesTitles = new ArrayList<String>();
		seriesTitles.add( "egy" );
		seriesTitles.add( "ket" );
		seriesTitles.add( "ha" );
		barChart = new BarChart()
		.appendTo( subContainer )
		.withItems( items2 )
		.withTitle( "hello" )
		.rowClick( presenterMethod( ShowcaseChartsPresenter_.__chartClicked ) )
		.withBarDirection( BarDirection.HORIZONTAL)
		.withSeriesTitles( seriesTitles );
		new Button("Add new chart value").appendTo(subContainer).click( new EmptyEventHandler() {

			@Override
			public void handle() {
				barChart.getModel().getBarChartItems().add( createDummyBarChartItemModel( 12, 20, 1, "aaa" ) );
				pieChart.getModel().getPieChartItems().add( new PieChartItemModel( 20, "AAAAAAAA" ) );
			}
		} );
	}

	private BarChartItemModel createDummyBarChartItemModel(final int v1, final int v2, final int v3, final String nev){
		ArrayList<Integer> list1 = new ArrayList<Integer>(  );
		list1.add( v1 );
		list1.add( v2 );
		list1.add( v3 );
		return new BarChartItemModel(list1, nev);
	}
}
