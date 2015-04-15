/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblPrecioMaterial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NORE
 */
@Stateless
public class TblPrecioMaterialFacade extends AbstractFacade<TblPrecioMaterial> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblPrecioMaterialFacade() {
        super(TblPrecioMaterial.class);
    }

    public Object[] find(Integer idtblProveedores, Integer idTblMateria) {
        Query query = em.createQuery("SELECT c.precio,c.tiempoEntrega FROM TblPrecioMaterial c WHERE c.tblMaterialidTblMateria.idTblMateria = :idMat AND c.tblProveedoresIdtblProveedores.idtblProveedores = :idProv ");
        query.setParameter("idProv", idtblProveedores);
        query.setParameter("idMat", idTblMateria);

        //System.out.println("wqeqweqw !!!!!!!! el precio es: " + query.getSingleResult());
        try {
            double val = (double) ((Object[]) query.getSingleResult())[0];
            //System.out.println("FECHA ENTREGA: " + ((Object[]) query.getSingleResult())[1]);
            return ((Object[]) query.getSingleResult());
        } catch (Exception e) {
            return null;
        }

    }

}
