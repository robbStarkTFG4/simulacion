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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tblpermisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpermisos.findAll", query = "SELECT t FROM Tblpermisos t"),
    @NamedQuery(name = "Tblpermisos.findByIdTblPermisos", query = "SELECT t FROM Tblpermisos t WHERE t.idTblPermisos = :idTblPermisos"),
    @NamedQuery(name = "Tblpermisos.findByDescripcionPerfil", query = "SELECT t FROM Tblpermisos t WHERE t.descripcionPerfil = :descripcionPerfil")})
public class Tblpermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblPermisos")
    private Integer idTblPermisos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcionPerfil")
    private String descripcionPerfil;
    @JoinColumn(name = "TblCargo_idTblCargo", referencedColumnName = "idTblCargo")
    @ManyToOne(optional = false)
    private Tblcargo tblCargoidTblCargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblPermisosidTblPermisos")
    private List<Tbldepartamento> tbldepartamentoList;

    public Tblpermisos() {
    }

    public Tblpermisos(Integer idTblPermisos) {
        this.idTblPermisos = idTblPermisos;
    }

    public Tblpermisos(Integer idTblPermisos, String descripcionPerfil) {
        this.idTblPermisos = idTblPermisos;
        this.descripcionPerfil = descripcionPerfil;
    }

    public Integer getIdTblPermisos() {
        return idTblPermisos;
    }

    public void setIdTblPermisos(Integer idTblPermisos) {
        this.idTblPermisos = idTblPermisos;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public Tblcargo getTblCargoidTblCargo() {
        return tblCargoidTblCargo;
    }

    public void setTblCargoidTblCargo(Tblcargo tblCargoidTblCargo) {
        this.tblCargoidTblCargo = tblCargoidTblCargo;
    }

    @XmlTransient
    public List<Tbldepartamento> getTbldepartamentoList() {
        return tbldepartamentoList;
    }

    public void setTbldepartamentoList(List<Tbldepartamento> tbldepartamentoList) {
        this.tbldepartamentoList = tbldepartamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblPermisos != null ? idTblPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblpermisos)) {
            return false;
        }
        Tblpermisos other = (Tblpermisos) object;
        if ((this.idTblPermisos == null && other.idTblPermisos != null) || (this.idTblPermisos != null && !this.idTblPermisos.equals(other.idTblPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.Tblpermisos[ idTblPermisos=" + idTblPermisos + " ]";
    }
    
}
