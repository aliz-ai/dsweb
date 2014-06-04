/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.IconModel;
import com.doctusoft.dsw.client.comp.model.IconModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

/**
 *
 * @author dipacs
 */
public class IconRenderer extends BaseComponentRenderer {
    
    private BootstrapIcon previousIcon;
    private Boolean previousWhite;

    public IconRenderer(IconModel model) {
        super(JQuery.select("<i/>"), model);
        Bindings.obs(model).get(IconModel_._icon).addValueChangeListener(new ValueChangeListener<BootstrapIcon>() {

            @Override
            public void valueChanged(BootstrapIcon newValue) {
                setIcon(newValue);
            }
        });
        setIcon(model.getIcon());
        
        Bindings.obs(model).get(IconModel_._white).addValueChangeListener(new ValueChangeListener<Boolean>() {

            @Override
            public void valueChanged(Boolean newValue) {
                setWhite(newValue);
            }
        });
        setWhite(model.isWhite());
    }
    
    private void setIcon(BootstrapIcon icon) {
        if (previousIcon != null) {
            widget.removeClass(previousIcon.getClassName());
        }
        if (icon != null) {
            widget.addClass(icon.getClassName());
        }
        previousIcon = icon;
    }
    
    private void setWhite(Boolean white) {
        if (previousWhite != null) {
            widget.removeClass(BootstrapStyleClasses.ICON_WHITE);
        }
        if (Objects.firstNonNull(white, false)) {
            widget.addClass(BootstrapStyleClasses.ICON_WHITE);
        }
        previousWhite = white;
    }
    
}
