/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.Tblordencliente;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("ordHolder")
@SessionScoped
public class OrdersHolder implements Serializable {

    private Tblordencliente currentClientOrder;
    private TblOrdencompra currentCompraOrder;

    public Tblordencliente getCurrentClientOrder() {
        return currentClientOrder;
    }

    public void setCurrentClientOrder(Tblordencliente currentClientOrder) {
        this.currentClientOrder = currentClientOrder;
    }

    public TblOrdencompra getCurrentCompraOrder() {
        return currentCompraOrder;
    }

    public void setCurrentCompraOrder(TblOrdencompra currentCompraOrder) {
        this.currentCompraOrder = currentCompraOrder;
    }

}
