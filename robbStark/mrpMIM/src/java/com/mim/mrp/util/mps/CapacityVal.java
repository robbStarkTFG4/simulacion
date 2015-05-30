/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.mps;

import com.mim.mrp.controllers.MpsScheduleCtrl;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author NORE
 */
@FacesValidator("cap")
public class CapacityVal implements Validator {

    @Inject
    MpsScheduleCtrl mps;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int valor = 0;
        try {
            valor = Integer.parseInt(value.toString());
            if (mps.getCapacidadDia() < valor) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Excediste Capacidad", "no puedes guardar esa cantidad , porque exede la capacidad de produccion diaria"));
            }

        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage());
        }
    }

}
