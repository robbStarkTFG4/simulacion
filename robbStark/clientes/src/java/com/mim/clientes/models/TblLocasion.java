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
@Table(name = "tbl_locasion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLocasion.findAll", query = "SELECT t FROM TblLocasion t"),
    @NamedQuery(name = "TblLocasion.findByIdtblLocasion", query = "SELECT t FROM TblLocasion t WHERE t.idtblLocasion = :idtblLocasion"),
    @NamedQuery(name = "TblLocasion.findByDescripcion", query = "SELECT t FROM TblLocasion t WHERE t.descripcion = :descripcion")})
public class TblLocasion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_locasion")
    private Integer idtblLocasion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblLocasionIdtblLocasion")
    private List<TblAlmacen> tblAlmacenList;

    public TblLocasion() {
    }

    public TblLocasion(Integer idtblLocasion) {
        this.idtblLocasion = idtblLocasion;
    }

    public TblLocasion(Integer idtblLocasion, String descripcion) {
        this.idtblLocasion = idtblLocasion;
        this.descripcion = descripcion;
    }

    public Integer getIdtblLocasion() {
        return idtblLocasion;
    }

    public void setIdtblLocasion(Integer idtblLocasion) {
        this.idtblLocasion = idtblLocasion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idtblLocasion != null ? idtblLocasion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLocasion)) {
            return false;
        }
        TblLocasion other = (TblLocasion) object;
        if ((this.idtblLocasion == null && other.idtblLocasion != null) || (this.idtblLocasion != null && !this.idtblLocasion.equals(other.idtblLocasion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.TblLocasion[ idtblLocasion=" + idtblLocasion + " ]";
    }
    
}
