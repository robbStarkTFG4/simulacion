/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.util.abc.AbcModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("abc")
@ViewScoped
public class AbcCtrl implements Serializable {

    @Inject
    OrdersHolder holder;

    @EJB
    TblOrdencompraFacade ordenFacade;

    @EJB
    TblordenclienteFacade clienteFacade;

    private List<TblOrdencompra> compraMtl;
    private List<AbcModel> listModel = new ArrayList<>();
    private double sumaDolares = 0.0;
    private double claseA = 0.0;
    private double claseB = 0.0;
    private double claseC = 0.0;

    @PostConstruct
    private void init() {
        System.out.println("abc ignited");
        compraMtl = ordenFacade.findAll(holder.getCurrentClientOrder().getIdTblOrdencliente());
        //Collections.sort(compraMtl, new TblOrdencompra());
        //Collections.reverse(compraMtl);
        for (TblOrdencompra cp : compraMtl) {
            System.out.println("demanda: " + cp.getDemanda());
            listModel.add(new AbcModel(cp.getTblOrdencompraPK().getIdtblordenCompra(), cp.getTblmaterial().getNombre(), cp.getDemanda(), cp.getPu(), cp.getDemanda() * cp.getPu()));
            sumaDolares = sumaDolares + (cp.getDemanda() * cp.getPu());
        }
        Collections.sort(listModel, new AbcModel());
        Collections.reverse(listModel);

        for (AbcModel mod : listModel) {
            mod.setVolumenPorcientoDlls((mod.getVolumenDolares() / sumaDolares) * 100);
        }

        for (AbcModel cd : listModel) {
            if (claseA <= 75) {
                claseA = claseA + cd.getVolumenPorcientoDlls();
                cd.setClase("A");
            } else {
                if (claseB <= 17) {
                    claseB = claseB + cd.getVolumenPorcientoDlls();
                    cd.setClase("B");
                } else {
                    if (claseC <= 8) {
                        claseC = claseC + cd.getVolumenPorcientoDlls();
                        cd.setClase("C");
                    }
                }
            }
        }

    }

    public String guardar() {

        for (AbcModel md : listModel) {
            for (TblOrdencompra compraMtl1 : compraMtl) {
                if (md.getId() == compraMtl1.getTblOrdencompraPK().getIdtblordenCompra()) {
                    compraMtl1.setClase(md.getClase());
                }
            }
        }

        for (TblOrdencompra compraMtl1 : compraMtl) {
            ordenFacade.updateClass(compraMtl1.getTblOrdencompraPK(), compraMtl1.getClase());
        }

        clienteFacade.changeStatus(holder.getCurrentClientOrder().getIdTblOrdencliente(), 2);
        
        return "almacen?faces-redirect=true";
    }

    //GETTER -SETTER
    public List<TblOrdencompra> getCompraMtl() {
        return compraMtl;
    }

    public void setCompraMtl(List<TblOrdencompra> compraMtl) {
        this.compraMtl = compraMtl;
    }

    public List<AbcModel> getListModel() {
        return listModel;
    }

    public void setListModel(List<AbcModel> listModel) {
        this.listModel = listModel;
    }
}
