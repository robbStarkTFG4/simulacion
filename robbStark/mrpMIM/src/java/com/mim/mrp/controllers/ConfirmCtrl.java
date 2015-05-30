/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblAlmacenFacade;
import com.mim.mrp.ejb.TblLocasionFacade;
import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.models.TblLocasion;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.util.almacen.AlmacenDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NORE
 */
@Named("location")
@ViewScoped
public class ConfirmCtrl implements Serializable {

    @EJB
    TblAlmacenFacade alm;

    @EJB
    TblLocasionFacade locasionFacade;

    @EJB
    TblordenclienteFacade clienteFacade;

    @EJB
    TblOrdencompraFacade compraFacade;

    private List<AlmacenDTO> ordenes;

    @PostConstruct
    public void init() {
        System.out.println("confirmar locasion ignited");
        ordenes = alm.findOrdersInspection();
        for (AlmacenDTO or : ordenes) {
            switch (or.getOrden().getClase()) {
                case "A":
                    or.getAlm().setTblLocasionIdtblLocasion(new TblLocasion("A" + locasionFacade.count()));
                    break;
                case "B":
                    or.getAlm().setTblLocasionIdtblLocasion(new TblLocasion("B" + locasionFacade.count()));
                    break;
                case "C":
                    or.getAlm().setTblLocasionIdtblLocasion(new TblLocasion("C" + locasionFacade.count()));
                    break;
            }

        }
    }

    public void asignLocationListener(ActionEvent e) {
        System.out.println("asigna locasion");
        AlmacenDTO temp = (AlmacenDTO) e.getComponent().getAttributes().get("nemu");
        alm.updateLocation(temp.getAlm().getIdtblAlmacen(), temp.getAlm().getTblLocasionIdtblLocasion().getDescripcion());
        compraFacade.updateStatus(temp.getOrden().getTblOrdencompraPK(), 4);
        if (clienteFacade.shoudDoIt(temp.getOrden().getTblordencliente().getIdTblOrdencliente())) {
            System.out.println("update location");
            clienteFacade.changeStatus(temp.getOrden().getTblordencliente().getIdTblOrdencliente(), 4);
        } else {
            System.out.println("dont do it youg blood");
        }
        ordenes.remove(temp);
        RequestContext.getCurrentInstance().update("confi:lala");
    }

    //GETTER - SETTER
    public List<AlmacenDTO> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<AlmacenDTO> ordenes) {
        this.ordenes = ordenes;
    }

}
