/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.gwt;

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
