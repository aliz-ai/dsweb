package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
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
