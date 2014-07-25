package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.AbstractChartModel;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel.LegendPosition;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel_;
import com.doctusoft.dsw.client.comp.model.ChartItemClickParam;
import com.doctusoft.dsw.client.comp.model.ChartItemClickedEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEventHandler;
import com.google.common.base.Supplier;


@SuppressWarnings( "unchecked" )
public class AbstractChart<T extends AbstractChart<T, M>, M extends AbstractChartModel> extends BaseComponent<T, M>{
	
	public AbstractChart( M model ) {
		super( model );
	}
	
	public T rowClick( final ParametricEventHandler<ChartItemClickParam> chartItemClickHandler ) {
		bindEvent(AbstractChartModel_._rowClickedEvent, new ParametricEventHandler<ChartItemClickParam>() {
			@Override
			public void handle(ChartItemClickParam parameter) {
				chartItemClickHandler.handle(parameter);
			}
		}, new Supplier<ChartItemClickedEvent>() {
			@Override
			public ChartItemClickedEvent get() {
				return new ChartItemClickedEvent();
			}
		});
		return (T)this;
	}
	
	public T withId( String id ) {
		model.setId( id );
		return (T)this;
	}
	
	public T withLegendPosition(LegendPosition position){
		model.setLegendPosition( position );
		return (T)this;
	}
	
	public T withShowToolTip(boolean showTooltip){
		model.setShowTooltip( showTooltip );
		return (T)this;
	}
	
	public T withTitle(String title){
		model.setTitle( title );
		return (T)this;
	}
	
}
