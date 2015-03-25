/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.controllers;

import com.mim.clientes.models.Ordenes;
import com.mim.clientes.models.Producto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NORE
 */
@Named("ordi")
@ViewScoped
public class OrdenesCtrl implements Serializable {

    private List<Ordenes> lista;
    private List<Producto> productos;
    private Producto selectedProduct;
    private Ordenes orden = new Ordenes();
    private Boolean enabled = true;
    private String dateMock;

    @PostConstruct
    private void init() {
        lista = new ArrayList<>();
        lista.add(new Ordenes(new Producto("bottella chica", 7.0, "abc"), 11));
        lista.add(new Ordenes(new Producto("bottella mediana", 12.0, "abcdef"), 25));
        lista.add(new Ordenes(new Producto("bottella grande", 16.0, "abcertre"), 32));

        for (Ordenes orden : lista) {
            orden.setTotal(orden.getProducto().getPrecio() * orden.getCantidad());
            orden.setFechaEntrega(new Date());
        }

        productos = new ArrayList<>();
        productos.add(new Producto("bottella chica", 7.0, "8TY234"));
        productos.add(new Producto("bottella mediana", 12.0, "abcdef"));
        productos.add(new Producto("bottella grande", 18.0, "abcdef57"));
    }

    public void producComboListener(ValueChangeEvent e) {
        //System.out.println("hola");
        orden.setCantidad(0);
        orden.setTotal(0.0);
        if (e != null) {
            if (e.getNewValue() != null) {
                Producto temp = (Producto) e.getNewValue();
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
                        orden.setTotal(temp * selectedProduct.getPrecio());
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
        System.out.println("alejandra leon aviña");

        try {
            if (orden.getCantidad() != 0) {
               
                orden.setProducto(selectedProduct);
                lista.add(clone(orden));

                RequestContext.getCurrentInstance().update("ordenTabla:ordenes");
                orden = new Ordenes();
                dateMock = null;
            } else {
                //notifica sobre cantidad
                System.out.println("la cantidad es 0");
            }
        } catch (Exception e) {
            System.out.println("fecha incorrecta");
        }
    }

    private Ordenes clone(Ordenes orden) {
        Ordenes or = new Ordenes();
        or.setCantidad(orden.getCantidad());
        or.setFechaEntrega(orden.getFechaEntrega());
        or.setProducto(orden.getProducto());
        or.setTotal(orden.getTotal());
        return or;
    }

    // getters-setters  "boiler plate"

    public List<Ordenes> getLista() {
        return lista;
    }

    public void setLista(List<Ordenes> lista) {
        this.lista = lista;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Producto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
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
