/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.comp;

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
