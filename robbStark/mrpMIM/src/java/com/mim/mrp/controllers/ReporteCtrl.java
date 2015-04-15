/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mim.mrp.ejb.TblProduccionActividadFacade;
import com.mim.mrp.ejb.TbldetallerecetaFacade;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblProduccionActividad;
import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblordencliente;
import com.mim.mrp.models.Tblreceta;
import com.mim.mrp.util.almacen.DetalleAlmacen;
import com.mim.mrp.util.diagram.Kinect;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NORE
 */
@Named("rt")
@ViewScoped
public class ReporteCtrl implements Serializable {

    @Inject
    OrdersHolder holder;

    @EJB
    TblProduccionActividadFacade actividadFacade;

    @EJB
    TblrecetaFacade recetaFacade;

    @EJB
    TbldetallerecetaFacade detalleRecetaFacade;

    private List<Kinect> diagramNodes;
    private Tblordencliente orden;
    private List<TblProduccionActividad> produccionActividad;
    private Tblreceta receta;
    private List<DetalleAlmacen> details = new ArrayList<>();
    private List<Tbldetallereceta> detalles;

    @PostConstruct
    private void init() {
        System.out.println("reporte ignited");
        orden = holder.getCurrentClientOrder();
        System.out.println("orden: " + orden.getIdTblClientes().getNombre());
        produccionActividad = actividadFacade.findList(orden.getIdTblOrdencliente());

        receta = recetaFacade.findReceta(orden.getTblOrdencompraList().get(0).getReceta());
        detalles = detalleRecetaFacade.findAll(receta.getIdTblReceta());
        Type type = new TypeToken<List<Kinect>>() {
        }.getType();
        diagramNodes = new Gson().fromJson(receta.getFabricasion(), type);

        for (Tbldetallereceta dls : detalles) {
            System.out.println("detalle: " + dls.getCantidad());
        }
        for (Kinect node : diagramNodes) {
            System.out.println("node: " + node.getIdFrom() + " da" + node.getIdTo());
        }
    }

    public void liberarListener(ActionEvent e) {
        System.out.println("liberar");
        details.clear();
        TblProduccionActividad act = (TblProduccionActividad) e.getComponent().getAttributes().get("forbes");
        System.out.println("act : " + act.getCantidad() + " " + act.getFecha());
        magicTrick(act.getCantidad());
        //openDialog();
    }

    private void openDialog() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 300);
        options.put("contentWidth", 300);

        RequestContext.getCurrentInstance().openDialog("/dialogo/movimientoAlmacen", options, null);
    }

    private void magicTrick(int cantidad) {
        List<TblOrdencompra> ordenesCompra = new ArrayList<>();

        for (Tbldetallereceta re : detalles) {
            TblOrdencompra shop = new TblOrdencompra();
            shop.setTblmaterial(re.getTblMaterialidTblMateria());
            shop.setCantidad(re.getCantidad());
            shop.setReceta(re.getTblRecetaidTblReceta().getIdTblReceta());

            Kinect con = null;
            //System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                System.out.println(ki);
                if (ki.getIdTo() == re.getTblMaterialidTblMateria().getIdTblMateria()) {
                    con = ki;
                }
            }

            if (con != null) {
                for (Tbldetallereceta dtl : detalles) {
                    if (dtl.getTblMaterialidTblMateria().getIdTblMateria() == con.getIdFrom()) {
                        //System.out.println("Demanda: " + (shop.getCantidad() * dtl.getCantidad()));
                        shop.setDemanda(shop.getCantidad() * dtl.getCantidad());
                    }
                }
            }

            ordenesCompra.add(shop);
        }

        for (TblOrdencompra sr : ordenesCompra) {
            if (sr.getDemanda() == null) {
                //System.out.println("El material que falta multiplicar: " + sr.getTblmaterial().getNombre());
                for (Tbldetallereceta dl : detalles) {
                    if (sr.getTblmaterial().getIdTblMateria() == dl.getTblMaterialidTblMateria().getIdTblMateria()) {
                        sr.setDemanda(dl.getCantidad() * cantidad);
                    }
                }
            }
        }

        for (TblOrdencompra re : ordenesCompra) {

            Kinect con = null;
            System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                System.out.println(ki);
                if (ki.getIdTo() == re.getTblmaterial().getIdTblMateria()) {
                    con = ki;
                }
            }

            if (con != null) {
                for (TblOrdencompra dtl : ordenesCompra) {
                    if (dtl.getTblmaterial().getIdTblMateria() == con.getIdFrom()) {

                        for (TblOrdencompra to : ordenesCompra) {
                            if (to.getTblmaterial().getIdTblMateria() == con.getIdTo()) {
                                to.setDemanda(dtl.getDemanda() * to.getDemanda());
                            }
                        }
                    }
                }
            }
        }

        //EOQ
        for (TblOrdencompra cp : ordenesCompra) {
            details.add(new DetalleAlmacen(cp.getTblmaterial().getIdTblMateria(), cp.getTblmaterial().getNombre(), cp.getDemanda()));
        }

        for (DetalleAlmacen rob : details) {
            System.out.println("nombre: " + rob.getNombre() + " cantidad: " + rob.getCantidad());
        }
    }

    public void toggleListener() {
        System.out.println("me expandi");
    }

    //GETTERS - SETTERS

    public List<TblProduccionActividad> getProduccionActividad() {
        return produccionActividad;
    }

    public void setProduccionActividad(List<TblProduccionActividad> produccionActividad) {
        this.produccionActividad = produccionActividad;
    }

    public List<DetalleAlmacen> getDetalles() {
        return details;
    }

    public void setDetalles(List<DetalleAlmacen> detalles) {
        this.details = detalles;
    }

}
