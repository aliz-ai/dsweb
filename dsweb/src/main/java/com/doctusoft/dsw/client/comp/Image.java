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
import com.doctusoft.dsw.client.comp.model.ImageModel;
import com.doctusoft.dsw.client.comp.model.ImageModel_;

/**
 *
 * @author dipacs
 */
public class Image extends BaseComponent<Image, ImageModel> {

    public Image() {
        super(new ImageModel());
    }
    
    public void setSrc(String src) {
		model.setSrc(src);
	}
	
	public void setAlt(String alt) {
		model.setAlt(alt);
	}
    
    public Image withSrc(String src) {
		model.setSrc(src);
        return this;
	}
	
	public Image withAlt(String alt) {
		model.setAlt(alt);
        return this;
	}
    
    public Image bindSrc(final ValueBinding<String> srcBinding) {
        Bindings.bind(srcBinding, Bindings.obs(model).get(ImageModel_._src));
        return this;
    }
    
    public Image bindAlt(final ValueBinding<String> altBinding) {
        Bindings.bind(altBinding, Bindings.obs(model).get(ImageModel_._alt));
        return this;
    }
    
}
