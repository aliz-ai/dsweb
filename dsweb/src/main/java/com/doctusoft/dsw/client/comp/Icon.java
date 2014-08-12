/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
