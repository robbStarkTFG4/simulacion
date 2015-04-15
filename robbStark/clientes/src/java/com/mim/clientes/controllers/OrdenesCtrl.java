/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.controllers;

import com.mim.clientes.ejb.TblclientesFacade;
import com.mim.clientes.ejb.TblordenclienteFacade;
import com.mim.clientes.ejb.TblproductoFacade;
import com.mim.clientes.models.Tblordencliente;
import com.mim.clientes.models.Tblproducto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NORE
 */
@Named("ordi")
@ViewScoped
public class OrdenesCtrl implements Serializable {

    @Inject
    UserCredentials userCr;
    @EJB
    TblordenclienteFacade ordenFacade;

    @EJB
    TblproductoFacade productFacade;
    
    @EJB
    TblclientesFacade clienteFacade;

    private List<Tblordencliente> lista;
    private List<Tblproducto> productos;
    private Tblproducto selectedProduct;
    private Tblordencliente orden = new Tblordencliente();
    private Boolean enabled = true;
    private String dateMock;

    @PostConstruct
    private void init() {
        lista = ordenFacade.findAll(userCr.getUsuario());
        productos = productFacade.findAll();
    }

    public void producComboListener(ValueChangeEvent e) {
        //System.out.println("hola");
        orden.setCantidad(0);
        //orden.setTotal(0.0);
        if (e != null) {
            if (e.getNewValue() != null) {
                Tblproducto temp = (Tblproducto) e.getNewValue();
                this.selectedProduct = temp;
                enabled = false;
            } else {
                enabled = true;
            }
        } else {
            enabled = true;
        }
    }

    public void cantidadListener(ValueChangeEvent e) {
        if (e != null) {
            if (e.getNewValue() != null) {
                try {
                    Integer temp = (Integer) e.getNewValue();
                    orden.setCantidad(temp);
                    if (selectedProduct != null) {
                        orden.setMonto(temp * selectedProduct.getPrecio());
                    }
                    System.out.println("cantidad asignada: " + temp);
                } catch (NumberFormatException ex) {
                    System.out.println("es cero o nulo");
                }

            } else {
                System.out.println("es cero o nulo");
            }
        } else {
            System.out.println("es cero o nulo");
        }
    }

    public void addOrder() {

        try {
            if (orden.getCantidad() != 0) {

                orden.setTblProductoidTblProducto(selectedProduct);
                orden.setIdTblClientes(clienteFacade.findClient(userCr.getUsuario()));
                orden.setEstatus(0);
                orden.setFechacaptura(new Date());
                ordenFacade.create(orden);
                lista.add(clone(orden));
                RequestContext.getCurrentInstance().update("ordenTabla:ordenes");
                orden = new Tblordencliente();
                dateMock = null;
            } else {
                //notifica sobre cantidad
                System.out.println("la cantidad es 0");
            }
        } catch (Exception e) {
            System.out.println("fecha incorrecta");
        }
    }

    private Tblordencliente clone(Tblordencliente orden) {
        Tblordencliente or = new Tblordencliente();
        or.setCantidad(orden.getCantidad());
        or.setFechadeentrega(orden.getFechadeentrega());
        or.setTblProductoidTblProducto(orden.getTblProductoidTblProducto());
        //or.setTotal(orden.getTotal());
        return or;
    }

    // getters-setters  "boiler plate"
    public List<Tblordencliente> getLista() {
        return lista;
    }

    public void setLista(List<Tblordencliente> lista) {
        this.lista = lista;
    }

    public List<Tblproducto> getProductos() {
        return productos;
    }

    public void setProductos(List<Tblproducto> productos) {
        this.productos = productos;
    }

    public Tblproducto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Tblproducto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Tblordencliente getOrden() {
        return orden;
    }

    public void setOrden(Tblordencliente orden) {
        this.orden = orden;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDateMock() {
        return dateMock;
    }

    public void setDateMock(String dateMock) {
        this.dateMock = dateMock;
    }

}
