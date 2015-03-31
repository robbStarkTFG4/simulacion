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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "TblAlmacen.findByCantidad", query = "SELECT t FROM TblAlmacen t WHERE t.cantidad = :cantidad")})
public class TblAlmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_almacen")
    private Integer idtblAlmacen;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "tbl_almacen_actividad_idtbl_almacen_actividad", referencedColumnName = "idtbl_almacen_actividad")
    @ManyToOne(optional = false)
    private TblAlmacenActividad tblAlmacenActividadIdtblAlmacenActividad;
    @JoinColumn(name = "TblMaterial_idTblMateria", referencedColumnName = "idTblMateria")
    @ManyToOne(optional = false)
    private Tblmaterial tblMaterialidTblMateria;
    @JoinColumns({
        @JoinColumn(name = "tbl_ordenCompra_idtbl_ordenCompra", referencedColumnName = "idtbl_ordenCompra"),
        @JoinColumn(name = "tbl_ordenCompra_TblMaterial_idTblMateria", referencedColumnName = "TblMaterial_idTblMateria"),
        @JoinColumn(name = "tbl_ordenCompra_TblOrdencliente_idTblOrdencliente", referencedColumnName = "TblOrdencliente_idTblOrdencliente")})
    @ManyToOne(optional = false)
    private TblOrdencompra tblOrdencompra;
    @JoinColumn(name = "tbl_orden_trabajo_idtbl_orden_trabajo", referencedColumnName = "idtbl_orden_trabajo")
    @ManyToOne(optional = false)
    private TblOrdenTrabajo tblOrdenTrabajoIdtblOrdenTrabajo;

    public TblAlmacen() {
    }

    public TblAlmacen(Integer idtblAlmacen) {
        this.idtblAlmacen = idtblAlmacen;
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

    public TblOrdencompra getTblOrdencompra() {
        return tblOrdencompra;
    }

    public void setTblOrdencompra(TblOrdencompra tblOrdencompra) {
        this.tblOrdencompra = tblOrdencompra;
    }

    public TblOrdenTrabajo getTblOrdenTrabajoIdtblOrdenTrabajo() {
        return tblOrdenTrabajoIdtblOrdenTrabajo;
    }

    public void setTblOrdenTrabajoIdtblOrdenTrabajo(TblOrdenTrabajo tblOrdenTrabajoIdtblOrdenTrabajo) {
        this.tblOrdenTrabajoIdtblOrdenTrabajo = tblOrdenTrabajoIdtblOrdenTrabajo;
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
