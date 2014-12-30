
package com.doctusoft.dsw.client.comp.model;

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


import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;


public class AbstractChartModel extends BaseComponentModel implements ModelObject {

	private static int idCounter = 1;

	@ObservableProperty
	private LegendPosition legendPosition = LegendPosition.EAST;

	@ObservableProperty
	private String title;

	@ObservableProperty
	private boolean showTooltip = true;

	@ObservableProperty
	private ChartItemClickedEvent rowClickedEvent = new ChartItemClickedEvent();

	public static enum LegendPosition {
		NORTH,
		SOUTH,
		WEST,
		EAST;

		public String getAbbreviation(){
			return this.name().substring( 0, 1 ).toLowerCase();
		}
	}

	protected AbstractChartModel() {
		super();

		// generate random id for charts by default. It is necessary to multiple chart components on one view
		// http://www.jqplot.com/deploy/dist/examples/selectorSyntax.html
		setId("chart_" + idCounter++);
	}

}

