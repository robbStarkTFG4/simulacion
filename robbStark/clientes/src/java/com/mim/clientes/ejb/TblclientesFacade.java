/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.ejb;

import com.mim.clientes.models.Tblclientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblclientesFacade extends AbstractFacade<Tblclientes> {

    @PersistenceContext(unitName = "clientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblclientesFacade() {
        super(Tblclientes.class);
    }

    public boolean find(String usuario, String password) {
        Query query = em.createQuery("SELECT c FROM Tblclientes c WHERE c.usuario = :user AND c.contraseña = :password");
        query.setParameter("user", usuario);
        query.setParameter("password", password);

        try {
            return query.getSingleResult() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Tblclientes findClient(String usuario) {
        TypedQuery<Tblclientes> query = em.createQuery("SELECT c FROM Tblclientes c WHERE c.usuario = :usr", Tblclientes.class);
        query.setParameter("usr", usuario);
        Tblclientes cliente = query.getSingleResult();
        System.out.println("Nombre Cliente: " + cliente.getNombre() + " " + cliente.getApelidoPaterno());
        return cliente;
    }

}
