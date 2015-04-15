/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_produccion_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProduccionActividad.findAll", query = "SELECT t FROM TblProduccionActividad t"),
    @NamedQuery(name = "TblProduccionActividad.findByIdtblProduccionActividad", query = "SELECT t FROM TblProduccionActividad t WHERE t.idtblProduccionActividad = :idtblProduccionActividad"),
    @NamedQuery(name = "TblProduccionActividad.findByFecha", query = "SELECT t FROM TblProduccionActividad t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblProduccionActividad.findByCantidad", query = "SELECT t FROM TblProduccionActividad t WHERE t.cantidad = :cantidad")})
public class TblProduccionActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_produccion_actividad")
    private Integer idtblProduccionActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "tbl_plan_producction_idtbl_plan_producction", referencedColumnName = "idtbl_plan_producction")
    @ManyToOne(optional = false)
    private TblPlanProducction tblPlanProducctionIdtblPlanProducction;

    public TblProduccionActividad() {
    }

    public TblProduccionActividad(Integer idtblProduccionActividad) {
        this.idtblProduccionActividad = idtblProduccionActividad;
    }

    public TblProduccionActividad(Integer idtblProduccionActividad, Date fecha, int cantidad) {
        this.idtblProduccionActividad = idtblProduccionActividad;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public TblProduccionActividad(Date fecha, int cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Integer getIdtblProduccionActividad() {
        return idtblProduccionActividad;
    }

    public void setIdtblProduccionActividad(Integer idtblProduccionActividad) {
        this.idtblProduccionActividad = idtblProduccionActividad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TblPlanProducction getTblPlanProducctionIdtblPlanProducction() {
        return tblPlanProducctionIdtblPlanProducction;
    }

    public void setTblPlanProducctionIdtblPlanProducction(TblPlanProducction tblPlanProducctionIdtblPlanProducction) {
        this.tblPlanProducctionIdtblPlanProducction = tblPlanProducctionIdtblPlanProducction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblProduccionActividad != null ? idtblProduccionActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProduccionActividad)) {
            return false;
        }
        TblProduccionActividad other = (TblProduccionActividad) object;
        if ((this.idtblProduccionActividad == null && other.idtblProduccionActividad != null) || (this.idtblProduccionActividad != null && !this.idtblProduccionActividad.equals(other.idtblProduccionActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblProduccionActividad[ idtblProduccionActividad=" + idtblProduccionActividad + " ]";
    }

}
