/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author NORE
 */
@Embeddable
public class TblOrdencompraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idtbl_ordenCompra")
    private int idtblordenCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TblMaterial_idTblMateria")
    private int tblMaterialidTblMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TblOrdencliente_idTblOrdencliente")
    private int tblOrdenclienteidTblOrdencliente;

    public TblOrdencompraPK() {
    }

    public TblOrdencompraPK(int idtblordenCompra, int tblMaterialidTblMateria, int tblOrdenclienteidTblOrdencliente) {
        this.idtblordenCompra = idtblordenCompra;
        this.tblMaterialidTblMateria = tblMaterialidTblMateria;
        this.tblOrdenclienteidTblOrdencliente = tblOrdenclienteidTblOrdencliente;
    }

    public int getIdtblordenCompra() {
        return idtblordenCompra;
    }

    public void setIdtblordenCompra(int idtblordenCompra) {
        this.idtblordenCompra = idtblordenCompra;
    }

    public int getTblMaterialidTblMateria() {
        return tblMaterialidTblMateria;
    }

    public void setTblMaterialidTblMateria(int tblMaterialidTblMateria) {
        this.tblMaterialidTblMateria = tblMaterialidTblMateria;
    }

    public int getTblOrdenclienteidTblOrdencliente() {
        return tblOrdenclienteidTblOrdencliente;
    }

    public void setTblOrdenclienteidTblOrdencliente(int tblOrdenclienteidTblOrdencliente) {
        this.tblOrdenclienteidTblOrdencliente = tblOrdenclienteidTblOrdencliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idtblordenCompra;
        hash += (int) tblMaterialidTblMateria;
        hash += (int) tblOrdenclienteidTblOrdencliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrdencompraPK)) {
            return false;
        }
        TblOrdencompraPK other = (TblOrdencompraPK) object;
        if (this.idtblordenCompra != other.idtblordenCompra) {
            return false;
        }
        if (this.tblMaterialidTblMateria != other.tblMaterialidTblMateria) {
            return false;
        }
        if (this.tblOrdenclienteidTblOrdencliente != other.tblOrdenclienteidTblOrdencliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblOrdencompraPK[ idtblordenCompra=" + idtblordenCompra + ", tblMaterialidTblMateria=" + tblMaterialidTblMateria + ", tblOrdenclienteidTblOrdencliente=" + tblOrdenclienteidTblOrdencliente + " ]";
    }
    
}
