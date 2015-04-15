/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.models.Tblproducto;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("holder")
@SessionScoped
public class ProductHolder implements Serializable {

    private Tblproducto current;

    public Tblproducto getCurrent() {
        return current;
    }

    public void setCurrent(Tblproducto current) {
        this.current = current;
    }

}
