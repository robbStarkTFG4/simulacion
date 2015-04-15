/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.util.converters;

import com.mim.clientes.controllers.OrdenesCtrl;
import com.mim.clientes.models.Tblproducto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author NORE
 */
@FacesConverter("recetaConv")
public class RecetaConverter implements Converter {

    @Inject
    OrdenesCtrl orden;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        }
        try {
            for (Tblproducto li : orden.getProductos()) {
                if (li.getNombre().equals(value)) {
                    return li;
                }
            }
        } catch (NullPointerException ne) {

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            Tblproducto type = (Tblproducto) value;
            return type.getNombre();
        }
    }

}
