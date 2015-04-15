/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.mps;

import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.Tblordencliente;
import java.io.Serializable;

/**
 *
 * @author NORE
 */
public class ProductionEvent implements Serializable{

    private TblOrdencompra ordenCompra;
    private Tblordencliente ordenCliente;
    private int cantidad;
    private String descripcion;

    public ProductionEvent() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TblOrdencompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(TblOrdencompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Tblordencliente getOrdenCliente() {
        return ordenCliente;
    }

    public void setOrdenCliente(Tblordencliente ordenCliente) {
        this.ordenCliente = ordenCliente;
    }

}
