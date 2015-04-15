/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.diagram;

import java.io.Serializable;

/**
 *
 * @author NORE
 */

public class NetworkElement implements Serializable {

    private String name;
    private String image;
    private String descripcion;
    private Double duracion;
    private String unidad;

    public NetworkElement() {
    }

    public NetworkElement(String name) {
        this.name = name;
    }

    public NetworkElement(String name, String descripcion, Double duracion, String unidad) {
        this.name = name;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.unidad = unidad;
    }

    public NetworkElement(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public String toString() {
        return name;
    }

}
