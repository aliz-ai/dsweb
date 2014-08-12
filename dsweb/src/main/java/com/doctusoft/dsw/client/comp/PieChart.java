
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

import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.PieChartItemModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;

public class PieChart extends AbstractChart<PieChart, PieChartModel> {
	
	private ListBindingListener<PieChartItemModel> itemsListener;
	
	public PieChart() {
		super( new PieChartModel() );
	}
	
	public PieChart( PieChartModel model ) {
		super( model );
	}
	
	public void setItems(List<PieChartItemModel> items){
		model.setPieChartItems( new ObservableList<PieChartItemModel>( items ) );
	}
	
	public PieChart withItems(List<PieChartItemModel> items){
		setItems( items );
		return this;
	}
	
	public PieChart bind(ObservableValueBinding<? extends List<PieChartItemModel>> listBinding ){
		if (itemsListener != null) {
			// remove previous binding
			itemsListener.remove();
		}
		itemsListener = new ListBindingListener<PieChartItemModel>( listBinding ) {
			
			@Override
			public void inserted( ObservableList<PieChartItemModel> list, int index, PieChartItemModel element ) {
				model.getPieChartItems().add( index, element );
			}
			
			@Override
			public void removed( ObservableList<PieChartItemModel> list, int index, PieChartItemModel element ) {
				model.getPieChartItems().remove( index );
			}
		};
		return this;
	}
	
}
