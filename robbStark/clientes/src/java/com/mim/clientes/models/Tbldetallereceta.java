/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbldetallereceta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbldetallereceta.findAll", query = "SELECT t FROM Tbldetallereceta t"),
    @NamedQuery(name = "Tbldetallereceta.findByIdTblDetalleReceta", query = "SELECT t FROM Tbldetallereceta t WHERE t.idTblDetalleReceta = :idTblDetalleReceta"),
    @NamedQuery(name = "Tbldetallereceta.findByCantidad", query = "SELECT t FROM Tbldetallereceta t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Tbldetallereceta.findByUnidad", query = "SELECT t FROM Tbldetallereceta t WHERE t.unidad = :unidad")})
public class Tbldetallereceta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblDetalleReceta")
    private Integer idTblDetalleReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Size(max = 45)
    @Column(name = "unidad")
    private String unidad;
    @JoinColumn(name = "TblMaterial_idTblMateria", referencedColumnName = "idTblMateria")
    @ManyToOne(optional = false)
    private Tblmaterial tblMaterialidTblMateria;
    @JoinColumn(name = "TblReceta_idTblReceta", referencedColumnName = "idTblReceta")
    @ManyToOne(optional = false)
    private Tblreceta tblRecetaidTblReceta;

    public Tbldetallereceta() {
    }

    public Tbldetallereceta(Integer idTblDetalleReceta) {
        this.idTblDetalleReceta = idTblDetalleReceta;
    }

    public Tbldetallereceta(Integer idTblDetalleReceta, int cantidad) {
        this.idTblDetalleReceta = idTblDetalleReceta;
        this.cantidad = cantidad;
    }

    public Integer getIdTblDetalleReceta() {
        return idTblDetalleReceta;
    }

    public void setIdTblDetalleReceta(Integer idTblDetalleReceta) {
        this.idTblDetalleReceta = idTblDetalleReceta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Tblmaterial getTblMaterialidTblMateria() {
        return tblMaterialidTblMateria;
    }

    public void setTblMaterialidTblMateria(Tblmaterial tblMaterialidTblMateria) {
        this.tblMaterialidTblMateria = tblMaterialidTblMateria;
    }

    public Tblreceta getTblRecetaidTblReceta() {
        return tblRecetaidTblReceta;
    }

    public void setTblRecetaidTblReceta(Tblreceta tblRecetaidTblReceta) {
        this.tblRecetaidTblReceta = tblRecetaidTblReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblDetalleReceta != null ? idTblDetalleReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbldetallereceta)) {
            return false;
        }
        Tbldetallereceta other = (Tbldetallereceta) object;
        if ((this.idTblDetalleReceta == null && other.idTblDetalleReceta != null) || (this.idTblDetalleReceta != null && !this.idTblDetalleReceta.equals(other.idTblDetalleReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.Tbldetallereceta[ idTblDetalleReceta=" + idTblDetalleReceta + " ]";
    }
    
}
