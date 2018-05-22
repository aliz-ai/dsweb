/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.gwt;

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


import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.CellModel;
import com.doctusoft.dsw.client.comp.model.CellModel_;
import com.google.gwt.core.client.GWT;
import com.xedge.jquery.client.JQuery;

/**
 *
 * @author dipacs
 */
public class CellRenderer extends BaseComponentRenderer {
	
	public static RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);
	
    private int actualSpan = -1;
    private int actualOffset = -1;

	private ChildrenRenderer childrenRenderer;
	
	public CellRenderer(CellModel model) {
		super(JQuery.select("<div/>"), model);
		childrenRenderer = new ChildrenRenderer(widget, (ObservableValueBinding) Bindings.obs(model).get(AbstractContainerModel_._children));
        Bindings.obs(model).get(CellModel_._offset).addValueChangeListener(new ValueChangeListener<Integer>() {

            @Override
            public void valueChanged(Integer newValue) {
                refreshOffset(newValue);
            }
        });
        Bindings.obs(model).get(CellModel_._span).addValueChangeListener(new ValueChangeListener<Integer>() {

            @Override
            public void valueChanged(Integer newValue) {
                refreshSpan(newValue);
            }
        });
        refreshOffset(model.getOffset());
        refreshSpan(model.getSpan());
	}
	
    private void refreshOffset(Integer newValue) {
        removeOffsetClass();
        actualOffset = -1;
        if (newValue != null && newValue > 0 && newValue < 12) {
            actualOffset = newValue;
            widget.addClass("offset" + actualOffset);
        }
    }
    
    private void removeOffsetClass() {
        if (actualOffset > -1) {
            widget.removeClass("offset" + actualOffset);
        }
    }
    
    private void refreshSpan(Integer newValue) {
        removeSpanClass();
        actualSpan = -1;
        if (newValue != null && newValue > 0 && newValue <= 12) {
            actualSpan = newValue;
            widget.addClass("span" + actualSpan);
        }
    }
    
    private void removeSpanClass() {
        if (actualSpan > -1) {
            widget.removeClass("span" + actualSpan);
        }
    }

    @Override
    public void detach() {
    	super.detach();
    	childrenRenderer.detach();
    }
    
    @Override
    public void reattach() {
    	super.reattach();
    	childrenRenderer.reattach();
    }
}
