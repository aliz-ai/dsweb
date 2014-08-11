package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;

/**
 *
 * @author dipacs
 */
public class IconModel extends BaseComponentModel implements ModelObject {
    
    @ObservableProperty
    private BootstrapIcon icon;
    
    @ObservableProperty
    private boolean white;
    
    
}
