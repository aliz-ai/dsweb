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
                setSrc(newValue);
            }
        });
        setSrc(model.getSrc());
        
        Bindings.obs(model).get(ImageModel_._alt).addValueChangeListener(new ValueChangeListener<String>() {

            @Override
            public void valueChanged(String newValue) {
                setAlt(newValue);
            }
        });
        setAlt(model.getAlt());
        
    }
    
    private void setSrc(String src) {
        widget.attr("src", src == null ? "" : src);
    }
    
    private void setAlt(String alt) {
        widget.attr("alt", alt == null ? "" : alt);
    }
    
}
