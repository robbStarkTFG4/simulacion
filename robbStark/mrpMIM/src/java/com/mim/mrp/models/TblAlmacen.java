/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

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
@Table(name = "tbl_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAlmacen.findAll", query = "SELECT t FROM TblAlmacen t"),
    @NamedQuery(name = "TblAlmacen.findByIdtblAlmacen", query = "SELECT t FROM TblAlmacen t WHERE t.idtblAlmacen = :idtblAlmacen"),
    @NamedQuery(name = "TblAlmacen.findByCantidad", query = "SELECT t FROM TblAlmacen t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TblAlmacen.findByDescripcion", query = "SELECT t FROM TblAlmacen t WHERE t.descripcion = :descripcion")})
public class TblAlmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_almacen")
    private Integer idtblAlmacen;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "tbl_almacen_actividad_idtbl_almacen_actividad", referencedColumnName = "idtbl_almacen_actividad")
    @ManyToOne(optional = false)
    private TblAlmacenActividad tblAlmacenActividadIdtblAlmacenActividad;
    @JoinColumn(name = "TblMaterial_idTblMateria", referencedColumnName = "idTblMateria")
    @ManyToOne(optional = false)
    private Tblmaterial tblMaterialidTblMateria;
    @JoinColumn(name = "TblOrdencliente_idTblOrdencliente", referencedColumnName = "idTblOrdencliente")
    @ManyToOne(optional = false)
    private Tblordencliente tblOrdenclienteidTblOrdencliente;
    @JoinColumn(name = "tbl_locasion_idtbl_locasion", referencedColumnName = "idtbl_locasion")
    @ManyToOne(optional = false)
    private TblLocasion tblLocasionIdtblLocasion;

    public TblAlmacen() {
    }

    public TblAlmacen(Integer idtblAlmacen) {
        this.idtblAlmacen = idtblAlmacen;
    }

    public TblAlmacen(Integer idtblAlmacen, String descripcion) {
        this.idtblAlmacen = idtblAlmacen;
        this.descripcion = descripcion;
    }

    public Integer getIdtblAlmacen() {
        return idtblAlmacen;
    }

    public void setIdtblAlmacen(Integer idtblAlmacen) {
        this.idtblAlmacen = idtblAlmacen;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TblAlmacenActividad getTblAlmacenActividadIdtblAlmacenActividad() {
        return tblAlmacenActividadIdtblAlmacenActividad;
    }

    public void setTblAlmacenActividadIdtblAlmacenActividad(TblAlmacenActividad tblAlmacenActividadIdtblAlmacenActividad) {
        this.tblAlmacenActividadIdtblAlmacenActividad = tblAlmacenActividadIdtblAlmacenActividad;
    }

    public Tblmaterial getTblMaterialidTblMateria() {
        return tblMaterialidTblMateria;
    }

    public void setTblMaterialidTblMateria(Tblmaterial tblMaterialidTblMateria) {
        this.tblMaterialidTblMateria = tblMaterialidTblMateria;
    }

    public Tblordencliente getTblOrdenclienteidTblOrdencliente() {
        return tblOrdenclienteidTblOrdencliente;
    }

    public void setTblOrdenclienteidTblOrdencliente(Tblordencliente tblOrdenclienteidTblOrdencliente) {
        this.tblOrdenclienteidTblOrdencliente = tblOrdenclienteidTblOrdencliente;
    }

    public TblLocasion getTblLocasionIdtblLocasion() {
        return tblLocasionIdtblLocasion;
    }

    public void setTblLocasionIdtblLocasion(TblLocasion tblLocasionIdtblLocasion) {
        this.tblLocasionIdtblLocasion = tblLocasionIdtblLocasion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblAlmacen != null ? idtblAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAlmacen)) {
            return false;
        }
        TblAlmacen other = (TblAlmacen) object;
        if ((this.idtblAlmacen == null && other.idtblAlmacen != null) || (this.idtblAlmacen != null && !this.idtblAlmacen.equals(other.idtblAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblAlmacen[ idtblAlmacen=" + idtblAlmacen + " ]";
    }
    
}
