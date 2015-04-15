/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblAlmacenActividadFacade;
import com.mim.mrp.ejb.TblAlmacenFacade;
import com.mim.mrp.ejb.TblLocasionFacade;
import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.models.TblAlmacen;
import com.mim.mrp.models.TblLocasion;
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
@Named("inspeccion")
@ViewScoped
public class InspectCtrl implements Serializable {

    @EJB
    TblOrdencompraFacade compraFacade;

    @EJB
    TblAlmacenFacade almaFacade;

    @EJB
    TblLocasionFacade locasionFacade;

    @EJB
    TblAlmacenActividadFacade actividadFacade;
    private List<TblOrdencompra> ordenes;

    @EJB
    TblordenclienteFacade clienteFacade;

    @PostConstruct
    private void init() {
        ordenes = compraFacade.findAvailable2();
    }

    public void validar(ActionEvent e) {
        TblOrdencompra orden = (TblOrdencompra) e.getComponent().getAttributes().get("inspiracion");
        System.out.println("material nombre: " + orden.getTblmaterial().getNombre());

        TblAlmacen alm = new TblAlmacen();
        alm.setCantidad(orden.getDemanda());
        alm.setDescripcion("material nombre: " + orden.getTblmaterial().getNombre() + " cantidad: " + orden.getDemanda() + " proveedor: " + orden.getTblProveedoresIdtblProveedores().getEmpresa());
        alm.setTblAlmacenActividadIdtblAlmacenActividad(actividadFacade.find(3));
        alm.setTblLocasionIdtblLocasion(locasionFacade.createLocasion(new TblLocasion(String.valueOf(orden.getTblordencliente().getIdTblOrdencliente()))));
        alm.setTblMaterialidTblMateria(orden.getTblmaterial());
        alm.setTblOrdenclienteidTblOrdencliente(orden.getTblordencliente());

        almaFacade.create(alm);
        compraFacade.updateStatus(orden.getTblOrdencompraPK(), 3);
        ordenes.remove(orden);

        boolean update = clienteFacade.checkOrders(orden.getTblordencliente().getIdTblOrdencliente());
        if (update) {
            clienteFacade.changeStatus(orden.getTblordencliente().getIdTblOrdencliente(), 3);
        }
    }

    // GETTER - SETTER
    public List<TblOrdencompra> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<TblOrdencompra> ordenes) {
        this.ordenes = ordenes;
    }
}
