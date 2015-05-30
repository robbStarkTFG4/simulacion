/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.almacen;

import com.mim.mrp.models.TblAlmacen;
import com.mim.mrp.models.TblOrdencompra;

/**
 *
 * @author NORE
 */
public class AlmacenDTO {

    private TblOrdencompra orden;
    private TblAlmacen alm;

    public AlmacenDTO() {
    }

    public AlmacenDTO(TblOrdencompra orden, TblAlmacen alm) {
        this.orden = orden;
        this.alm = alm;
    }

    public TblOrdencompra getOrden() {
        return orden;
    }

    public void setOrden(TblOrdencompra orden) {
        this.orden = orden;
    }

    public TblAlmacen getAlm() {
        return alm;
    }

    public void setAlm(TblAlmacen alm) {
        this.alm = alm;
    }

}
