/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.ejb.TblPrecioMaterialFacade;
import com.mim.mrp.ejb.TblProveedoresFacade;
import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.ejb.TblusuariosFacade;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblOrdencompraPK;
import com.mim.mrp.models.TblPrecioMaterial;
import com.mim.mrp.models.TblProveedores;
import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblmaterial;
import com.mim.mrp.models.Tblreceta;
import com.mim.mrp.models.Tblusuarios;
import com.mim.mrp.util.diagram.Kinect;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author NORE
 */
@Named("ordClDtl")
@ViewScoped
public class ClientOrderCtrl implements Serializable {

    @Inject
    OrdersHolder orders;

    @EJB
    TblrecetaFacade recetaFacade;

    @EJB
    TblProveedoresFacade provFacade;

    @EJB
    TblPrecioMaterialFacade precioFacade;

    @EJB
    TblOrdencompraFacade ordenCompraFacade;

    @EJB
    TblusuariosFacade userFacade;

    @EJB
    TblordenclienteFacade clienteFacade;

    private List<Tblreceta> recetas;
    private List<Tbldetallereceta> detalles;
    private List<TblOrdencompra> ordenesCompra = new ArrayList<>();
    private Tblreceta selectedRes;
    private List<Kinect> diagramNodes;
    private List<TblProveedores> provList;

    private TblOrdencompra currentOrder;
    private String precio;
    private String tiempoEntrega;
    private Double porcentaje;
    private TblProveedores acceptedProv;

    @PostConstruct
    private void init() {
        System.out.println("order client detail ignited");
        //recetas = recetaFacade.findAll3(orders.getCurrentClientOrder().getTblProductoidTblProducto().getIdTblProducto());
        //provList = provFacade.findAll();
        updateRecetas();
    }

    public void recetaListener(Tblreceta receta) {
        //System.out.println("select Listener");
        ordenesCompra.clear();
        //System.out.println(((Tblreceta) e.getNewValue()).getDescripcion());
        detalles = receta.getTbldetallerecetaList();

        Type type = new TypeToken<List<Kinect>>() {
        }.getType();
        diagramNodes = new Gson().fromJson(receta.getFabricasion(), type);

        magicTrick();

        for (Tbldetallereceta re : detalles) {
            //System.out.println("dasdas: " + re.getTblMaterialidTblMateria().getNombre() + " cantidad: " + re.getCantidad());
        }
    }

    private void magicTrick() {
        for (Tbldetallereceta re : detalles) {
            TblOrdencompra shop = new TblOrdencompra();
            shop.setTblmaterial(re.getTblMaterialidTblMateria());
            shop.setCantidad(re.getCantidad());
            shop.setReceta(re.getTblRecetaidTblReceta().getIdTblReceta());
            Kinect con = null;
            //System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                //System.out.println(ki);
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
                        sr.setDemanda(dl.getCantidad() * orders.getCurrentClientOrder().getCantidad());
                    }
                }
            }
        }

        for (TblOrdencompra re : ordenesCompra) {

            Kinect con = null;
            //System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                //System.out.println(ki);
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
    }

    private void magicTrick2() {

        for (Tbldetallereceta re : detalles) {
            TblOrdencompra shop = null;
            for (TblOrdencompra cp : ordenesCompra) {
                if (cp.getTblmaterial().getIdTblMateria() == re.getTblMaterialidTblMateria().getIdTblMateria()) {
                    shop = cp;
                }
            }
            shop.setCantidad(re.getCantidad());

            Kinect con = null;
            //System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                //System.out.println(ki);
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
        }

//
        for (TblOrdencompra sr : ordenesCompra) {
            if (sr.getDemanda() == null) {
                //System.out.println("El material que falta multiplicar: " + sr.getTblmaterial().getNombre());
                for (Tbldetallereceta dl : detalles) {
                    if (sr.getTblmaterial().getIdTblMateria() == dl.getTblMaterialidTblMateria().getIdTblMateria()) {
                        sr.setDemanda(dl.getCantidad() * orders.getCurrentClientOrder().getCantidad());
                    }
                }
            }
        }

        for (TblOrdencompra re : ordenesCompra) {

            Kinect con = null;
            //System.out.println("Conexiones");
            for (Kinect ki : diagramNodes) {
                //System.out.println(ki);
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
    }

    public void provListener(ValueChangeEvent e) {
        if (e.getNewValue() != null) {
            //System.out.println("proveedores listener: " + ((TblProveedores) e.getNewValue()).getEmpresa());
            TblProveedores prov = ((TblProveedores) e.getNewValue());
            Object[] res = precioFacade.find(prov.getIdtblProveedores(), currentOrder.getTblmaterial().getIdTblMateria());
            if (res != null) {
                double val = (double) res[0];
                tiempoEntrega = String.valueOf(res[1]);
                precio = String.valueOf(val);
                porcentaje = prov.getIndice();
            }

        } else {
            precio = "";
            tiempoEntrega = "";
            porcentaje = 0.0;
        }
    }

    public void aceptarProveedor(ActionEvent e) { // aqui calcular los dias de entrega LA SUMA
        System.out.println("ACEPTAAAAAAAAAAAAAAAR PROVEEEEEEEEEEDOR");
        double h = Double.parseDouble(precio) * 0.15;
        double eoq = Math.sqrt((2 * currentOrder.getDemanda() * Double.parseDouble(precio)) / (h));

        magicTrick2();
        currentOrder.setDemanda(Double.valueOf((Math.floor(currentOrder.getDemanda() * porcentaje))).intValue());

        currentOrder.setPu(Double.parseDouble(precio));
        currentOrder.setTblProveedoresIdtblProveedores(acceptedProv);
        currentOrder.setMonto(currentOrder.getPu() * currentOrder.getDemanda());
        currentOrder.setFechaEntrega(addDays(new Date(), Integer.parseInt(tiempoEntrega)));
        precio = "";
        tiempoEntrega = "";
        porcentaje = 0.0;
        acceptedProv = null;
        //System.out.println("proveedor  aceptado: " + acceptedProv.getEmpresa());
        //RequestContext.getCurrentInstance().closeDialog(null);
        //ordenesCompra.add(currentOrder);
        //RequestContext.getCurrentInstance().update(precio);
    }

    private void openDialog() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 300);
        options.put("contentWidth", 300);

        RequestContext.getCurrentInstance().openDialog("/dialogo/chooseProve", options, null);
    }

    public void chooseProvListener(ActionEvent e) {
        precio = "";
        tiempoEntrega = "";
        porcentaje = 0.0;
        //System.out.println("abre dialogo WQEEEEEEEEEEEEEWEWQEQWEWQEW");
        currentOrder = (TblOrdencompra) e.getComponent().getAttributes().get("ordenCompra");
        provList = provFacade.findAll(currentOrder.getTblmaterial().getIdTblMateria());
        //openDialog();
        RequestContext.getCurrentInstance().update("ord:dialogito");
        //RequestContext.getCurrentInstance().execute("PF('dlg2').show();");
    }

    public void updateRecetas() {
        recetas = recetaFacade.findAll3(orders.getCurrentClientOrder().getTblProductoidTblProducto().getIdTblProducto());
        selectedRes = recetas.get(0);
        recetaListener(selectedRes);
    }

    public void limpiaProveedor() {
        //System.out.println("limpiar proveedor");
        acceptedProv = null;
    }

    public String generarListener() {

        boolean create = false;
        if (!ordenesCompra.isEmpty()) {
            for (TblOrdencompra ord : ordenesCompra) {
                if (ord.getTblProveedoresIdtblProveedores() == null) {
                    create = true;
                }
            }
        } else {
            create = true;
        }
        if (!create) {
            //System.out.println("Generar wiiiiiii");
            for (TblOrdencompra ord : ordenesCompra) {
                ord.setTblOrdencompraPK(new TblOrdencompraPK(ordenCompraFacade.count() + 1, ord.getTblmaterial().getIdTblMateria(), orders.getCurrentClientOrder().getIdTblOrdencliente()));
                ord.setTblUsuariosidTblUsuarios(userFacade.find(1));
                ord.setFechaCompra(new Date());
                ord.setEstatus(1);
                ordenCompraFacade.create(ord);
                clienteFacade.changeStatus(orders.getCurrentClientOrder().getIdTblOrdencliente(), 1);
            }
        }
        orders.setCurrentClientOrder(null);
        return "ordenesCliente?faces-redirect=true";
    }

    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public void handleClose(CloseEvent event) {
        System.out.println("se cerro el dialogo");
    }

    //Getters - Setters
    public List<Tblreceta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Tblreceta> recetas) {
        this.recetas = recetas;
    }

    public List<Tbldetallereceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Tbldetallereceta> detalles) {
        this.detalles = detalles;
    }

    public Tblreceta getSelectedRes() {
        return selectedRes;
    }

    public void setSelectedRes(Tblreceta selectedRes) {
        this.selectedRes = selectedRes;
    }

    public List<TblOrdencompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<TblOrdencompra> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }

    public List<TblProveedores> getProvList() {
        return provList;
    }

    public void setProvList(List<TblProveedores> provList) {
        this.provList = provList;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public TblProveedores getAcceptedProv() {
        return acceptedProv;
    }

    public void setAcceptedProv(TblProveedores acceptedProv) {
        this.acceptedProv = acceptedProv;
    }

}
