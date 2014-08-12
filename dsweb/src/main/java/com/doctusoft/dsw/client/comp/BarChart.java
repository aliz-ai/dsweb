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


import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractChartModel_;
import com.doctusoft.dsw.client.comp.model.BarChartItemModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel.BarDirection;
import com.doctusoft.dsw.client.comp.model.BarChartModel_;


public class BarChart extends AbstractChart<BarChart, BarChartModel>{
	
	private ListBindingListener<BarChartItemModel> itemsListener;
	
	public BarChart() {
		super( new BarChartModel() );
	}
	
	public BarChart( BarChartModel model ) {
		super( model );
	}
	
	public BarChart withBarDirection(BarDirection barDirection) {
		model.setBarDirection( barDirection );
		return this;
	}
	
	public BarChart withItems(List<BarChartItemModel> items){
		model.setBarChartItems( new  ObservableList<BarChartItemModel>(items ) );
		return this;
	}
	
	public BarChart withSeriesTitles(List<String> seriesTitles){
		model.setSeriesTitles( new ObservableList<String>( seriesTitles ) );
		return this;
	}
	
	public BarChart bindValues(ObservableValueBinding<? extends List<BarChartItemModel>> listBinding ){
		if (itemsListener != null) {
			// remove previous binding
			itemsListener.remove();
		}
		itemsListener = new ListBindingListener<BarChartItemModel>( listBinding ) {
			
			@Override
			public void inserted( ObservableList<BarChartItemModel> list, int index, BarChartItemModel element ) {
				model.getBarChartItems().add( index, element );
			}
			
			@Override
			public void removed( ObservableList<BarChartItemModel> list, int index, BarChartItemModel element ) {
				model.getBarChartItems().remove( index );
			}
		};
		return this;
	}
	
	public BarChart bindTitle(ValueBinding<String> sourceBinding){
		Bindings.bind( sourceBinding, Bindings.obs( model ).get( AbstractChartModel_._title) );
		return this;
	}
	
	public BarChart bindSeriesTitles(ValueBinding<ObservableList<String>> sourceBinding){
		Bindings.bind( sourceBinding, Bindings.obs( model ).get( BarChartModel_._seriesTitles) );
		return this;
	}
	
}
