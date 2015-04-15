/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.diagram;

import com.mim.mrp.models.Tblmaterial;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author NORE
 */
public class DiagramNode extends Element {
    
    private Tblmaterial mtl;

    public DiagramNode(Object data, String x, String y) {
        super(data, x, y);
    }
    
    public Tblmaterial getMtl() {
        return mtl;
    }
    
    public void setMtl(Tblmaterial mtl) {
        this.mtl = mtl;
    }
    
}
