/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.Tbldetallereceta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TbldetallerecetaFacade extends AbstractFacade<Tbldetallereceta> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TbldetallerecetaFacade() {
        super(Tbldetallereceta.class);
    }

    public void createDtl(Tbldetallereceta dt) {
        em.persist(dt);
        em.flush();
    }

    public List<Tbldetallereceta> findAll(Integer idTblReceta) {
        TypedQuery<Tbldetallereceta> query = em.createQuery("SELECT c FROM Tbldetallereceta c WHERE c.tblRecetaidTblReceta.idTblReceta = :id", Tbldetallereceta.class);
        query.setParameter("id", idTblReceta);
        return query.getResultList();
    }

}
