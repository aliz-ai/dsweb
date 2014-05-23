/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.IconModel;
import com.doctusoft.dsw.client.comp.model.IconModel_;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;

/**
 *
 * @author dipacs
 */
public class Icon extends BaseComponent<Icon, IconModel> {

    public Icon(BootstrapIcon icon) {
        this(icon, false);
    }
    
    public Icon(BootstrapIcon icon, boolean white) {
        super(new IconModel());
        model.setIcon(icon);
        model.setWhite(white);
    }
    
    public void setIcon(BootstrapIcon icon) {
        model.setIcon(icon);
    }
    
    public void setWhite(boolean white) {
        model.setWhite(white);
    }
    
    public Icon bindIcon(ValueBinding<BootstrapIcon> iconBinding) {
		Bindings.bind(iconBinding, Bindings.obs(model).get(IconModel_._icon));
		return this;
	}
    
    public Icon bindWhite(ValueBinding<Boolean> whiteBinding) {
		Bindings.bind(whiteBinding, Bindings.obs(model).get(IconModel_._white));
		return this;
	}
    
}
