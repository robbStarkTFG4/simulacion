/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblPrecioMaterial;
import com.mim.mrp.models.TblProveedores;
import java.util.ArrayList;
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
public class TblProveedoresFacade extends AbstractFacade<TblProveedores> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblProveedoresFacade() {
        super(TblProveedores.class);
    }

    public List<TblProveedores> findAll(Integer idTblMateria) {
        TypedQuery<TblPrecioMaterial> query = em.createQuery("SELECT c FROM TblPrecioMaterial c WHERE c.tblMaterialidTblMateria.idTblMateria = :id", TblPrecioMaterial.class);
        query.setParameter("id", idTblMateria);
        try {
            List<TblPrecioMaterial> temp = query.getResultList();
            List<TblProveedores> lista = new ArrayList<>();
            for (TblPrecioMaterial mtl : temp) {
                lista.add(mtl.getTblProveedoresIdtblProveedores());
            }
            System.out.println("Los Proveedores que ofrecen el producto: ");
            for (TblProveedores pr : lista) {
                System.out.println("proveedor: " + pr.getEmpresa());
            }
            return lista;
        } catch (Exception e) {
            System.out.println("something went wrong!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e.getMessage());
            return null;
        }
    }

}
