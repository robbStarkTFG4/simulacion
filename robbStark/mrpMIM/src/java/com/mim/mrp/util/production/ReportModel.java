/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.production;

import java.io.Serializable;

/**
 *
 * @author NORE
 */
public class ReportModel implements Serializable {

    private int id;
    private int cantidad;
    private String comentario;

    public ReportModel() {
    }

    public ReportModel(int id, int cantidad, String comentario) {
        this.id = id;
        this.cantidad = cantidad;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
