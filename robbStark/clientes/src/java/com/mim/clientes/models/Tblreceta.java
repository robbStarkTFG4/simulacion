/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.models;

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
@Table(name = "tblreceta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblreceta.findAll", query = "SELECT t FROM Tblreceta t"),
    @NamedQuery(name = "Tblreceta.findByIdTblReceta", query = "SELECT t FROM Tblreceta t WHERE t.idTblReceta = :idTblReceta"),
    @NamedQuery(name = "Tblreceta.findByDescripcion", query = "SELECT t FROM Tblreceta t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tblreceta.findByFabricasion", query = "SELECT t FROM Tblreceta t WHERE t.fabricasion = :fabricasion"),
    @NamedQuery(name = "Tblreceta.findByTiempo", query = "SELECT t FROM Tblreceta t WHERE t.tiempo = :tiempo"),
    @NamedQuery(name = "Tblreceta.findByUnidad", query = "SELECT t FROM Tblreceta t WHERE t.unidad = :unidad")})
public class Tblreceta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblReceta")
    private Integer idTblReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 800)
    @Column(name = "fabricasion")
    private String fabricasion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tiempo")
    private Double tiempo;
    @Size(max = 10)
    @Column(name = "unidad")
    private String unidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblRecetaidTblReceta")
    private List<Tbldetallereceta> tbldetallerecetaList;
    @JoinColumn(name = "TblProducto_idTblProducto", referencedColumnName = "idTblProducto")
    @ManyToOne(optional = false)
    private Tblproducto tblProductoidTblProducto;

    public Tblreceta() {
    }

    public Tblreceta(Integer idTblReceta) {
        this.idTblReceta = idTblReceta;
    }

    public Tblreceta(Integer idTblReceta, String descripcion) {
        this.idTblReceta = idTblReceta;
        this.descripcion = descripcion;
    }

    public Integer getIdTblReceta() {
        return idTblReceta;
    }

    public void setIdTblReceta(Integer idTblReceta) {
        this.idTblReceta = idTblReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricasion() {
        return fabricasion;
    }

    public void setFabricasion(String fabricasion) {
        this.fabricasion = fabricasion;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public List<Tbldetallereceta> getTbldetallerecetaList() {
        return tbldetallerecetaList;
    }

    public void setTbldetallerecetaList(List<Tbldetallereceta> tbldetallerecetaList) {
        this.tbldetallerecetaList = tbldetallerecetaList;
    }

    public Tblproducto getTblProductoidTblProducto() {
        return tblProductoidTblProducto;
    }

    public void setTblProductoidTblProducto(Tblproducto tblProductoidTblProducto) {
        this.tblProductoidTblProducto = tblProductoidTblProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblReceta != null ? idTblReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblreceta)) {
            return false;
        }
        Tblreceta other = (Tblreceta) object;
        if ((this.idTblReceta == null && other.idTblReceta != null) || (this.idTblReceta != null && !this.idTblReceta.equals(other.idTblReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.Tblreceta[ idTblReceta=" + idTblReceta + " ]";
    }
    
}
