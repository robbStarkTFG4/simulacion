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
@Table(name = "tblcargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcargo.findAll", query = "SELECT t FROM Tblcargo t"),
    @NamedQuery(name = "Tblcargo.findByIdTblCargo", query = "SELECT t FROM Tblcargo t WHERE t.idTblCargo = :idTblCargo"),
    @NamedQuery(name = "Tblcargo.findByDescripcion", query = "SELECT t FROM Tblcargo t WHERE t.descripcion = :descripcion")})
public class Tblcargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblCargo")
    private Integer idTblCargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTblTipous")
    private List<Tblusuarios> tblusuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCargoidTblCargo")
    private List<Tblpermisos> tblpermisosList;

    public Tblcargo() {
    }

    public Tblcargo(Integer idTblCargo) {
        this.idTblCargo = idTblCargo;
    }

    public Tblcargo(Integer idTblCargo, String descripcion) {
        this.idTblCargo = idTblCargo;
        this.descripcion = descripcion;
    }

    public Integer getIdTblCargo() {
        return idTblCargo;
    }

    public void setIdTblCargo(Integer idTblCargo) {
        this.idTblCargo = idTblCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Tblusuarios> getTblusuariosList() {
        return tblusuariosList;
    }

    public void setTblusuariosList(List<Tblusuarios> tblusuariosList) {
        this.tblusuariosList = tblusuariosList;
    }

    @XmlTransient
    public List<Tblpermisos> getTblpermisosList() {
        return tblpermisosList;
    }

    public void setTblpermisosList(List<Tblpermisos> tblpermisosList) {
        this.tblpermisosList = tblpermisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblCargo != null ? idTblCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcargo)) {
            return false;
        }
        Tblcargo other = (Tblcargo) object;
        if ((this.idTblCargo == null && other.idTblCargo != null) || (this.idTblCargo != null && !this.idTblCargo.equals(other.idTblCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.Tblcargo[ idTblCargo=" + idTblCargo + " ]";
    }
    
}
