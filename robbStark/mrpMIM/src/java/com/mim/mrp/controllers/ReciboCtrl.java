/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.models.TblOrdencompra;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("recibo")
@ViewScoped
public class ReciboCtrl implements Serializable {

    @EJB
    TblOrdencompraFacade compraFacade;
    private List<TblOrdencompra> ordenes;

    @PostConstruct
    private void init() {
        ordenes = compraFacade.findAvailable();
    }

    public void marcar(ActionEvent e) {
        TblOrdencompra orden = (TblOrdencompra) e.getComponent().getAttributes().get("mr");

        System.out.println("marca orden compra: " + orden.getTblmaterial().getNombre());
        ordenes.remove(orden);
        //marcar orden con estatus 2
        compraFacade.updateStatus(orden.getTblOrdencompraPK(),2);
    }

    // GETTER - SETTER
    public List<TblOrdencompra> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<TblOrdencompra> ordenes) {
        this.ordenes = ordenes;
    }
}
