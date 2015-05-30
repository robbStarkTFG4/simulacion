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
@Named("envios")
@ViewScoped
public class EnviosCtrl implements Serializable {

    @EJB
    TblordenclienteFacade clienteFacade;

    @Inject
    OrdersHolder holder;
    private List<Tblordencliente> finishedOrders;

    @PostConstruct
    private void init() {
        System.out.println("envios ignited");
        finishedOrders = clienteFacade.findAll2(6);
    }

    public void shoProductImageListener() {

    }

    public void moveToDetail() {
        System.out.println("enviar pedido");
        System.out.println("holder: " + holder.getCurrentClientOrder().getFechadeentrega());
        clienteFacade.changeStatus(holder.getCurrentClientOrder().getIdTblOrdencliente(), 7);
        finishedOrders.remove(holder.getCurrentClientOrder());
    }

    //GETTERS - SETTERS
    public List<Tblordencliente> getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(List<Tblordencliente> finishedOrders) {
        this.finishedOrders = finishedOrders;
    }

}
