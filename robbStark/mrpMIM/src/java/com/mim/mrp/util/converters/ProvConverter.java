/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.converters;

import com.mim.mrp.controllers.ClientOrderCtrl;
import com.mim.mrp.models.TblProveedores;
import com.mim.mrp.models.Tblreceta;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author NORE
 */
@FacesConverter("provConverter")
public class ProvConverter implements Converter {

    @Inject
    ClientOrderCtrl provList;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        }

        List<TblProveedores> lista = provList.getProvList();
        for (TblProveedores li : lista) {
            if (li.getEmpresa().equals(value)) {
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
            TblProveedores type = (TblProveedores) value;
            return type.getEmpresa();
        }
    }

}
