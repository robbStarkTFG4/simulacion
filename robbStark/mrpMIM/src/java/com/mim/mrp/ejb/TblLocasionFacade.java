/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblLocasion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblLocasionFacade extends AbstractFacade<TblLocasion> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblLocasionFacade() {
        super(TblLocasion.class);
    }

    public TblLocasion createLocasion(TblLocasion tblLocasion) {

        TypedQuery<TblLocasion> query = em.createQuery("SELECT c FROM TblLocasion c WHERE c.descripcion = :des", TblLocasion.class);
        query.setParameter("des", tblLocasion.getDescripcion());
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            em.persist(tblLocasion);
            em.flush();
            return tblLocasion;
        }

    }

}
