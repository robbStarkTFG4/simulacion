/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblordenclienteFacade;
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
@Named("candidate")
@ViewScoped
public class MpsCandidatesCtrl implements Serializable {

    @EJB
    TblordenclienteFacade candidatesFacade;

    private List<Tblordencliente> ordenes;
    private List<Tblordencliente> ordnesProcesadas;

    @PostConstruct
    private void init() {
        System.out.println("init candidates");
        ordenes = candidatesFacade.findAll(1);
        ordnesProcesadas = candidatesFacade.findAll2(1);
    }

    public String goToMps() {
        return "createMPS?faces-redirect=true";
    }

    public String goToMps2() {
        return "editMps?faces-redirect=true";
    }

    //GETTERS- SETTERS
    public List<Tblordencliente> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Tblordencliente> ordenes) {
        this.ordenes = ordenes;
    }

    public List<Tblordencliente> getOrdnesProcesadas() {
        return ordnesProcesadas;
    }

    public void setOrdnesProcesadas(List<Tblordencliente> ordnesProcesadas) {
        this.ordnesProcesadas = ordnesProcesadas;
    }
}
