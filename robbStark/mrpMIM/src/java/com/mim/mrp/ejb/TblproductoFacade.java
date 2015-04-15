/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.Tblproducto;
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
public class TblproductoFacade extends AbstractFacade<Tblproducto> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblproductoFacade() {
        super(Tblproducto.class);
    }

    public List<Tblproducto> findAll2() {
        TypedQuery<Tblproducto> query = em.createQuery("SELECT c FROM Tblproducto c", Tblproducto.class);
        List<Tblproducto> res = query.getResultList();
        System.out.println("Resultados: ");
        for (Tblproducto re : res) {
            System.out.println(re.getNombre());
        }
        return res;
    }

}
