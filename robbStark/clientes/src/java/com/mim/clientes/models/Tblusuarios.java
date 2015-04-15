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
@Table(name = "tblusuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblusuarios.findAll", query = "SELECT t FROM Tblusuarios t"),
    @NamedQuery(name = "Tblusuarios.findByIdTblUsuarios", query = "SELECT t FROM Tblusuarios t WHERE t.idTblUsuarios = :idTblUsuarios"),
    @NamedQuery(name = "Tblusuarios.findByUsuario", query = "SELECT t FROM Tblusuarios t WHERE t.usuario = :usuario"),
    @NamedQuery(name = "Tblusuarios.findByCorreo", query = "SELECT t FROM Tblusuarios t WHERE t.correo = :correo"),
    @NamedQuery(name = "Tblusuarios.findByCargo", query = "SELECT t FROM Tblusuarios t WHERE t.cargo = :cargo"),
    @NamedQuery(name = "Tblusuarios.findByPassword", query = "SELECT t FROM Tblusuarios t WHERE t.password = :password")})
public class Tblusuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblUsuarios")
    private Integer idTblUsuarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblUsuariosidTblUsuarios")
    private List<TblOrdencompra> tblOrdencompraList;
    @JoinColumn(name = "idTblTipous", referencedColumnName = "idTblCargo")
    @ManyToOne(optional = false)
    private Tblcargo idTblTipous;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblUsuariosidTblUsuarios")
    private List<TblOrdenTrabajo> tblOrdenTrabajoList;

    public Tblusuarios() {
    }

    public Tblusuarios(Integer idTblUsuarios) {
        this.idTblUsuarios = idTblUsuarios;
    }

    public Tblusuarios(Integer idTblUsuarios, String usuario, String password) {
        this.idTblUsuarios = idTblUsuarios;
        this.usuario = usuario;
        this.password = password;
    }

    public Integer getIdTblUsuarios() {
        return idTblUsuarios;
    }

    public void setIdTblUsuarios(Integer idTblUsuarios) {
        this.idTblUsuarios = idTblUsuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<TblOrdencompra> getTblOrdencompraList() {
        return tblOrdencompraList;
    }

    public void setTblOrdencompraList(List<TblOrdencompra> tblOrdencompraList) {
        this.tblOrdencompraList = tblOrdencompraList;
    }

    public Tblcargo getIdTblTipous() {
        return idTblTipous;
    }

    public void setIdTblTipous(Tblcargo idTblTipous) {
        this.idTblTipous = idTblTipous;
    }

    @XmlTransient
    public List<TblOrdenTrabajo> getTblOrdenTrabajoList() {
        return tblOrdenTrabajoList;
    }

    public void setTblOrdenTrabajoList(List<TblOrdenTrabajo> tblOrdenTrabajoList) {
        this.tblOrdenTrabajoList = tblOrdenTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblUsuarios != null ? idTblUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblusuarios)) {
            return false;
        }
        Tblusuarios other = (Tblusuarios) object;
        if ((this.idTblUsuarios == null && other.idTblUsuarios != null) || (this.idTblUsuarios != null && !this.idTblUsuarios.equals(other.idTblUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.Tblusuarios[ idTblUsuarios=" + idTblUsuarios + " ]";
    }
    
}
