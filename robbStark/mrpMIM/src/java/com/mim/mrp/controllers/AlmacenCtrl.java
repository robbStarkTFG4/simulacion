/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.Tblordencliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("alm")
@ViewScoped
public class AlmacenCtrl implements Serializable {

    @EJB
    TblordenclienteFacade candidatesFacade;

    private List<Tblordencliente> ordnesProcesadas;
    private List<Tblordencliente> ordenesConABC;

    @PostConstruct
    private void init() {
        System.out.println("init almacen abc");
        ordnesProcesadas = candidatesFacade.findAll2(1);
        ordenesConABC = candidatesFacade.findAll2(2);

    }

    public String goToMps() {
        return "materialOrder?faces-redirect=true";
    }

    public String goToMps2() {
        return "editMps?faces-redirect=true";
    }
    
    public String goToMps3() {
        return null;
    }

    //GETTERS- SETTERS
    public List<Tblordencliente> getOrdnesProcesadas() {
        return ordnesProcesadas;
    }

    public void setOrdnesProcesadas(List<Tblordencliente> ordnesProcesadas) {
        this.ordnesProcesadas = ordnesProcesadas;
    }

    public List<Tblordencliente> getOrdenesConABC() {
        return ordenesConABC;
    }

    public void setOrdenesConABC(List<Tblordencliente> ordenesConABC) {
        this.ordenesConABC = ordenesConABC;
    }
}
