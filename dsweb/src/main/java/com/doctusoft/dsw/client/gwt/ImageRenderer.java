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
import com.doctusoft.dsw.client.comp.model.ImageModel;
import com.doctusoft.dsw.client.comp.model.ImageModel_;
import com.xedge.jquery.client.JQuery;

/**
 *
 * @author dipacs
 */
public class ImageRenderer extends BaseComponentRenderer {

    public ImageRenderer(ImageModel model) {
        super(JQuery.select("<img/>"), model);
        addChangeListenerAndApply(ImageModel_._src, model, new ValueChangeListener<String>() {

            @Override
            public void valueChanged(String newValue) {
                setAttribute("src", newValue);
            }
        });
        
        addChangeListenerAndApply(ImageModel_._alt, model, new ValueChangeListener<String>() {

            @Override
            public void valueChanged(String newValue) {
                setAttribute("alt", newValue);
            }
        });
        
    }
    
    private void setAttribute(String name, String value) {
        widget.attr(name, value == null ? "" : value);
    }
    
}
