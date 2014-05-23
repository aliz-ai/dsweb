/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

/**
 *
 * @author dipacs
 */
public class ImageModel extends BaseComponentModel {
    
    @ObservableProperty
    private String src;
    
    @ObservableProperty
    private String alt;
    
    @Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return ImageModel_._observableProperties;
	}
    
}
