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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tblpermisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpermisos.findAll", query = "SELECT t FROM Tblpermisos t"),
    @NamedQuery(name = "Tblpermisos.findByIdTblPermisos", query = "SELECT t FROM Tblpermisos t WHERE t.idTblPermisos = :idTblPermisos")})
public class Tblpermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblPermisos")
    private Integer idTblPermisos;
    @JoinColumn(name = "TblCargo_idTblCargo", referencedColumnName = "idTblCargo")
    @ManyToOne(optional = false)
    private Tblcargo tblCargoidTblCargo;
    @JoinColumn(name = "TblDepartamento_idTblDepartamento", referencedColumnName = "idTblDepartamento")
    @ManyToOne(optional = false)
    private Tbldepartamento tblDepartamentoidTblDepartamento;

    public Tblpermisos() {
    }

    public Tblpermisos(Integer idTblPermisos) {
        this.idTblPermisos = idTblPermisos;
    }

    public Integer getIdTblPermisos() {
        return idTblPermisos;
    }

    public void setIdTblPermisos(Integer idTblPermisos) {
        this.idTblPermisos = idTblPermisos;
    }

    public Tblcargo getTblCargoidTblCargo() {
        return tblCargoidTblCargo;
    }

    public void setTblCargoidTblCargo(Tblcargo tblCargoidTblCargo) {
        this.tblCargoidTblCargo = tblCargoidTblCargo;
    }

    public Tbldepartamento getTblDepartamentoidTblDepartamento() {
        return tblDepartamentoidTblDepartamento;
    }

    public void setTblDepartamentoidTblDepartamento(Tbldepartamento tblDepartamentoidTblDepartamento) {
        this.tblDepartamentoidTblDepartamento = tblDepartamentoidTblDepartamento;
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
        return "com.mim.clientes.models.Tblpermisos[ idTblPermisos=" + idTblPermisos + " ]";
    }
    
}
