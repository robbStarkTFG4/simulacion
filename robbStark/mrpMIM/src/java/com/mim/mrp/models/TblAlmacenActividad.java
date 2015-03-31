/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_almacen_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAlmacenActividad.findAll", query = "SELECT t FROM TblAlmacenActividad t"),
    @NamedQuery(name = "TblAlmacenActividad.findByIdtblAlmacenActividad", query = "SELECT t FROM TblAlmacenActividad t WHERE t.idtblAlmacenActividad = :idtblAlmacenActividad"),
    @NamedQuery(name = "TblAlmacenActividad.findByTipoActividad", query = "SELECT t FROM TblAlmacenActividad t WHERE t.tipoActividad = :tipoActividad")})
public class TblAlmacenActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_almacen_actividad")
    private Integer idtblAlmacenActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoActividad")
    private String tipoActividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblAlmacenActividadIdtblAlmacenActividad")
    private List<TblAlmacen> tblAlmacenList;

    public TblAlmacenActividad() {
    }

    public TblAlmacenActividad(Integer idtblAlmacenActividad) {
        this.idtblAlmacenActividad = idtblAlmacenActividad;
    }

    public TblAlmacenActividad(Integer idtblAlmacenActividad, String tipoActividad) {
        this.idtblAlmacenActividad = idtblAlmacenActividad;
        this.tipoActividad = tipoActividad;
    }

    public Integer getIdtblAlmacenActividad() {
        return idtblAlmacenActividad;
    }

    public void setIdtblAlmacenActividad(Integer idtblAlmacenActividad) {
        this.idtblAlmacenActividad = idtblAlmacenActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    @XmlTransient
    public List<TblAlmacen> getTblAlmacenList() {
        return tblAlmacenList;
    }

    public void setTblAlmacenList(List<TblAlmacen> tblAlmacenList) {
        this.tblAlmacenList = tblAlmacenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblAlmacenActividad != null ? idtblAlmacenActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAlmacenActividad)) {
            return false;
        }
        TblAlmacenActividad other = (TblAlmacenActividad) object;
        if ((this.idtblAlmacenActividad == null && other.idtblAlmacenActividad != null) || (this.idtblAlmacenActividad != null && !this.idtblAlmacenActividad.equals(other.idtblAlmacenActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblAlmacenActividad[ idtblAlmacenActividad=" + idtblAlmacenActividad + " ]";
    }
    
}
