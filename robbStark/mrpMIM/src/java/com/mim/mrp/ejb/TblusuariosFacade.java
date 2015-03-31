/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.Tblusuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblusuariosFacade extends AbstractFacade<Tblusuarios> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblusuariosFacade() {
        super(Tblusuarios.class);
    }

    public Tblusuarios findUser(String usuario, String password) {
        TypedQuery<Tblusuarios> query = em.createQuery("SELECT c FROM Tblusuarios c WHERE c.usuario = :user and c.password = :pass", Tblusuarios.class);
        query.setParameter("user", usuario);
        query.setParameter("pass", password);
        return query.getSingleResult();
    }

}
