/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblproductoFacade;
import com.mim.mrp.models.Tblproducto;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named("catalogo")
@ViewScoped
public class CatalogGridCtrl implements Serializable {

    private List<Tblproducto> productsList;
    private Tblproducto current;

    @EJB
    TblproductoFacade productFacade;
    @Inject
    ProductHolder holder;

    @PostConstruct
    private void init() {
        System.out.println("Catalogo Ignited");

        productsList = productFacade.findAll2();
        productsList.clear();
        productsList = productFacade.findAll2();
        /* System.out.println("sin filtar: ");
         for (Tblproducto productsList1 : productsList) {
         System.out.println(productsList1.getNombre());
         }*/
        //filterList();
    }

    private void filterList() {
        List<Tblproducto> filtered = new ArrayList<>();
        for (Tblproducto test : productsList) {
            if (!filtered.contains(test)) {
                filtered.add(test);
            }
        }
        productsList.clear();
        productsList = new ArrayList<>(filtered);
        System.out.println("Filtrada");
        for (Tblproducto productsList1 : productsList) {
            System.out.println(productsList1.getNombre());
        }
    }

    public void updateList() {
        productsList.clear();
        productsList = productFacade.findAll();

        List<Tblproducto> filtered = new ArrayList<>();
        for (Tblproducto test : productsList) {
            if (!filtered.contains(test)) {
                filtered.add(test);
            }
        }
        productsList.clear();
        productsList = new ArrayList<>(filtered);
    }

    public void redirect(Tblproducto pr) {
        try {
            //productDetail?faces-redirect=true

            //current = pr;
            holder.setCurrent(pr);
            FacesContext.getCurrentInstance().getExternalContext().redirect("productDetail.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CatalogGridCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
