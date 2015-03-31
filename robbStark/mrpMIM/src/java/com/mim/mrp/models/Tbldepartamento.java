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
@Table(name = "tbldepartamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbldepartamento.findAll", query = "SELECT t FROM Tbldepartamento t"),
    @NamedQuery(name = "Tbldepartamento.findByIdTblDepartamento", query = "SELECT t FROM Tbldepartamento t WHERE t.idTblDepartamento = :idTblDepartamento"),
    @NamedQuery(name = "Tbldepartamento.findByNombre", query = "SELECT t FROM Tbldepartamento t WHERE t.nombre = :nombre")})
public class Tbldepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblDepartamento")
    private Integer idTblDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @JoinColumn(name = "TblPermisos_idTblPermisos", referencedColumnName = "idTblPermisos")
    @ManyToOne(optional = false)
    private Tblpermisos tblPermisosidTblPermisos;

    public Tbldepartamento() {
    }

    public Tbldepartamento(Integer idTblDepartamento) {
        this.idTblDepartamento = idTblDepartamento;
    }

    public Tbldepartamento(Integer idTblDepartamento, String nombre) {
        this.idTblDepartamento = idTblDepartamento;
        this.nombre = nombre;
    }

    public Integer getIdTblDepartamento() {
        return idTblDepartamento;
    }

    public void setIdTblDepartamento(Integer idTblDepartamento) {
        this.idTblDepartamento = idTblDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tblpermisos getTblPermisosidTblPermisos() {
        return tblPermisosidTblPermisos;
    }

    public void setTblPermisosidTblPermisos(Tblpermisos tblPermisosidTblPermisos) {
        this.tblPermisosidTblPermisos = tblPermisosidTblPermisos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblDepartamento != null ? idTblDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbldepartamento)) {
            return false;
        }
        Tbldepartamento other = (Tbldepartamento) object;
        if ((this.idTblDepartamento == null && other.idTblDepartamento != null) || (this.idTblDepartamento != null && !this.idTblDepartamento.equals(other.idTblDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.Tbldepartamento[ idTblDepartamento=" + idTblDepartamento + " ]";
    }
    
}
