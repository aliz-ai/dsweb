/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.ImageModel;
import com.doctusoft.dsw.client.comp.model.ImageModel_;
import com.google.common.base.Preconditions;
import com.xedge.jquery.client.JQuery;

/**
 *
 * @author dipacs
 */
public class ImageRenderer extends BaseComponentRenderer {

    public ImageRenderer(ImageModel model) {
        super(JQuery.select("<img/>"), model);
        Bindings.obs(model).get(ImageModel_._src).addValueChangeListener(new ValueChangeListener<String>() {

            @Override
            public void valueChanged(String newValue) {
                setAttribute("src", newValue);
            }
        });
        setAttribute("src", model.getSrc());
        
        Bindings.obs(model).get(ImageModel_._alt).addValueChangeListener(new ValueChangeListener<String>() {

            @Override
            public void valueChanged(String newValue) {
                setAttribute("alt", newValue);
            }
        });
        setAttribute("alt", model.getAlt());
        
    }
    
    private void setAttribute(String name, String value) {
        Preconditions.checkNotNull(name);
        widget.attr(name, value == null ? "" : value);
    }
    
}
