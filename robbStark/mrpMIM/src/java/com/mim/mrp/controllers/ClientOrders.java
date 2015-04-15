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
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("pedidos")
@ViewScoped
public class ClientOrders implements Serializable {

    @EJB
    TblordenclienteFacade ordenClienteFacade;
    

    private List<Tblordencliente> ordenesList;
    private List<Tblordencliente> filteredList;

    @PostConstruct
    private void init() {
        System.out.println("pedidos view ignited");
        ordenesList = ordenClienteFacade.findAll(0);
    }
    
    public void shoProductImageListener(ActionEvent e){
        System.out.println("mostrar imagen");
    }

    public String moveToDetail(){
            
        return "clientOrderDetail?faces-redirect=true";
    }
    //Getters - Setters
    public List<Tblordencliente> getOrdenesList() {
        return ordenesList;
    }

    public void setOrdenesList(List<Tblordencliente> ordenesList) {
        this.ordenesList = ordenesList;
    }

    public List<Tblordencliente> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<Tblordencliente> filteredList) {
        this.filteredList = filteredList;
    }

}
