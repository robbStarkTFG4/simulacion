/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblreceta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblrecetaFacade extends AbstractFacade<Tblreceta> {

    @EJB
    TbldetallerecetaFacade dtlFacade;

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblrecetaFacade() {
        super(Tblreceta.class);
    }

    public List<Tblreceta> findAll(Integer idTblProducto) {
        TypedQuery<Tblreceta> query = em.createQuery("SELECT c FROM Tblreceta c WHERE c.tblProductoidTblProducto.idTblProducto = :id", Tblreceta.class);
        query.setParameter("id", idTblProducto);
        List<Tblreceta> result = null;
        try {
            result = query.getResultList();
            for (Tblreceta res : result) {
                res.getTbldetallerecetaList().clear();
                res.setTbldetallerecetaList(dtlFacade.findAll(res.getIdTblReceta()));
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Tblreceta createReceta(Tblreceta createReceta) {
        this.create(createReceta);
        return createReceta;
    }

    public List<Tblreceta> findAll2(Integer idTblProducto) {
        TypedQuery<Tblreceta> query = em.createQuery("SELECT c FROM Tblreceta c WHERE c.tblProductoidTblProducto.idTblProducto = :id", Tblreceta.class);
        query.setParameter("id", idTblProducto);
        List<Tblreceta> result = null;
        try {
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void saveDiagram(Integer idTblReceta, String toJson) {
        Tblreceta temp = find(idTblReceta);
        temp.setFabricasion(toJson);
        this.edit(temp);

    }

    public List<Tblreceta> findAll3(Integer idTblProducto) {//AND c.fabricasion IS NULL
        TypedQuery<Tblreceta> query = em.createQuery("SELECT c FROM Tblreceta c WHERE c.tblProductoidTblProducto.idTblProducto = :id ", Tblreceta.class);
        query.setParameter("id", idTblProducto);
        List<Tblreceta> result = null;
        try {
            result = query.getResultList();
            for (Tblreceta res : result) {
                res.getTbldetallerecetaList().clear();
                res.setTbldetallerecetaList(dtlFacade.findAll(res.getIdTblReceta()));
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Tblreceta findReceta(Integer receta) {
        TypedQuery<Tblreceta> query = em.createQuery("SELECT c FROM Tblreceta c WHERE c.idTblReceta = :id", Tblreceta.class);
        query.setParameter("id", receta);
        return query.getSingleResult();
    }

}
