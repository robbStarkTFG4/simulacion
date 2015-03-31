/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblproductoFacade;
import com.mim.mrp.models.Tblproducto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("catalogo")
@SessionScoped
public class CatalogGridCtrl implements Serializable {

    private List<Tblproducto> productsList;
    private Tblproducto current;
    @EJB
    TblproductoFacade productFacade;

    @PostConstruct
    private void init() {
        productsList = productFacade.findAll();
    }

    public void updateList() {
        productsList.clear();
        productsList = productFacade.findAll();
    }

    //Getters-Setters

    public List<Tblproducto> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Tblproducto> productsList) {
        this.productsList = productsList;
    }

    public Tblproducto getCurrent() {
        return current;
    }

    public void setCurrent(Tblproducto current) {
        this.current = current;
    }

}
