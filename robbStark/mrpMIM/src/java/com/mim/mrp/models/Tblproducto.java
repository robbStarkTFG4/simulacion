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
@Table(name = "tblproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproducto.findAll", query = "SELECT t FROM Tblproducto t"),
    @NamedQuery(name = "Tblproducto.findByIdTblProducto", query = "SELECT t FROM Tblproducto t WHERE t.idTblProducto = :idTblProducto"),
    @NamedQuery(name = "Tblproducto.findByNombre", query = "SELECT t FROM Tblproducto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tblproducto.findByPrecio", query = "SELECT t FROM Tblproducto t WHERE t.precio = :precio"),
    @NamedQuery(name = "Tblproducto.findByModelo", query = "SELECT t FROM Tblproducto t WHERE t.modelo = :modelo")})
public class Tblproducto implements Serializable {
    @Size(max = 120)
    @Column(name = "imagen")
    private String imagen;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblProducto")
    private Integer idTblProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "precio")
    private String precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "modelo")
    private String modelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoidTblProducto")
    private List<TblOrdenTrabajo> tblOrdenTrabajoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoidTblProducto")
    private List<Tblordencliente> tblordenclienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductoidTblProducto")
    private List<Tblreceta> tblrecetaList;

    public Tblproducto() {
    }

    public Tblproducto(Integer idTblProducto) {
        this.idTblProducto = idTblProducto;
    }

    public Tblproducto(Integer idTblProducto, String nombre, String precio, String modelo) {
        this.idTblProducto = idTblProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.modelo = modelo;
    }

    public Integer getIdTblProducto() {
        return idTblProducto;
    }

    public void setIdTblProducto(Integer idTblProducto) {
        this.idTblProducto = idTblProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @XmlTransient
    public List<TblOrdenTrabajo> getTblOrdenTrabajoList() {
        return tblOrdenTrabajoList;
    }

    public void setTblOrdenTrabajoList(List<TblOrdenTrabajo> tblOrdenTrabajoList) {
        this.tblOrdenTrabajoList = tblOrdenTrabajoList;
    }

    @XmlTransient
    public List<Tblordencliente> getTblordenclienteList() {
        return tblordenclienteList;
    }

    public void setTblordenclienteList(List<Tblordencliente> tblordenclienteList) {
        this.tblordenclienteList = tblordenclienteList;
    }

    @XmlTransient
    public List<Tblreceta> getTblrecetaList() {
        return tblrecetaList;
    }

    public void setTblrecetaList(List<Tblreceta> tblrecetaList) {
        this.tblrecetaList = tblrecetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblProducto != null ? idTblProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproducto)) {
            return false;
        }
        Tblproducto other = (Tblproducto) object;
        if ((this.idTblProducto == null && other.idTblProducto != null) || (this.idTblProducto != null && !this.idTblProducto.equals(other.idTblProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.Tblproducto[ idTblProducto=" + idTblProducto + " ]";
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
