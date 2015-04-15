/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.converters;

import com.mim.mrp.controllers.CatalogGridCtrl;
import com.mim.mrp.controllers.DiagramCtrl;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.models.Tblreceta;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author NORE
 */
@FacesConverter("diagramConv")
public class RecetaConverter implements Converter {

    @Inject
    DiagramCtrl diagram;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.trim().equals("")) {
            return null;
        }

        List<Tblreceta> lista = diagram.getRecetasList();
        for (Tblreceta li : lista) {
            if (li.getDescripcion().equals(value)) {
                return li;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            Tblreceta type = (Tblreceta) value;
            return type.getDescripcion();
        }
    }

}
