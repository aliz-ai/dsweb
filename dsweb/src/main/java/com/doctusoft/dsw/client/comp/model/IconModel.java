/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;

/**
 *
 * @author dipacs
 */
public class IconModel extends BaseComponentModel {
    
    @ObservableProperty
    private BootstrapIcon icon;
    
    @ObservableProperty
    private boolean white;
    
    @Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return IconModel_._observableProperties;
	}
    
}
