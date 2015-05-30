/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.models.Tblordencliente;
import com.mim.mrp.models.Tblproducto;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("pr")
@ViewScoped
public class ProductionReportCtrl implements Serializable {

    @EJB
    TblordenclienteFacade clienteFacade;

    @Inject
    OrdersHolder holder;

    private List<Tblordencliente> productsList;

    @PostConstruct
    private void init() {
        System.out.println("production report ignited");
        productsList = clienteFacade.findAll2(5);
    }

    public String redirect(Tblordencliente pr) {

        holder.setCurrentClientOrder(pr);
        return "ReportesPro?faces-redirect=true";

    }

    //GETTER - SETTER
    public List<Tblordencliente> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Tblordencliente> productsList) {
        this.productsList = productsList;
    }

}
