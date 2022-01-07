/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.spring.restful_crud_spring.service;

import inser.spring.restful_crud_spring.model.Producto;
import static inser.spring.restful_crud_spring.model.Producto.SELECT_findAll_order;
import static inser.spring.restful_crud_spring.model.Producto.SELECT_findByCodigoProducto_order;
import inser.spring.restful_crud_spring.repository.AbstractFacade;
import inser.spring.restful_crud_spring.repository.EntityManagerConfig;
import static inser.spring.restful_crud_spring.repository.PersistenceConfig.jdbc_contraseña_tex;
import static inser.spring.restful_crud_spring.repository.PersistenceConfig.jdbc_usuario_tex;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import inser.spring.restful_crud_spring.repository.Producto_repository;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 * @author informatica
 */
@Service
public class Producto_service extends AbstractFacade<Producto> {
    @PersistenceContext
    public EntityManager entityManager;
    @Autowired
    public Environment environment;
    
//    public Producto_repository producto_repository;
    public Map<String, String> propiedades_mapa = new HashMap();
    
    public Producto_service(Producto_repository producto_repository) {
        super(Producto.class);
//        this.producto_repository = producto_repository;
    }

    @Override
    public void create(Producto entity) {
        super.create(entity);
    }

    @Override
    public void edit(Producto entity) {
        super.edit(entity);
//        if (getEntityManager() != null) {
//            producto_repository.saveAndFlush(entity);
//        }        
    }

    public void remove(String id) {
        Producto producto = super.find(id);
        super.remove(producto);
//        if (getEntityManager() != null) {
//            Optional<Producto> optional_producto = producto_repository.findById(id);
//            if (optional_producto.isPresent()) {
//                Producto producto = optional_producto.get();
//                producto_repository.delete(producto);
//            }
//        }        
    }

    public Producto find(String id) {
        Producto producto = super.find(id);
        return producto;
//        if (getEntityManager() != null) {
//            Optional<Producto> optional_producto = producto_repository.findById(id);
//            Producto producto = optional_producto.get();
//            return producto;
//        } else {
//            return null;
//        }        
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos_lista = new LinkedList<>(super.findAll());
        return productos_lista;
//        if (getEntityManager() != null) {
//            List<Producto> productos_lista = producto_repository.findAll();
//            return productos_lista;
//        } else {
//            return null;
//        }        
    }

    public List<Producto> findRange(Integer from, Integer to) {
        List<Producto> productos_lista = super.findRange(new int[]{from, to});
        return productos_lista;
    }

    public String countREST() {
        return String.valueOf(super.count());
//        if (getEntityManager() != null) {
//            return String.valueOf(producto_repository.count());
//        } else {
//            return null;
//        }
    }

    /**
     * Obtiene un EntityManage para el usuario y contraseña del mapa, que sustituye al anterior
     * @return 
     */
    @Override
    public EntityManager getEntityManager() {
        boolean ret = true;
        EntityManager nuevo_EntityManager;
        String usuario;
        String contraseña;
        String [] error = { "" };
        usuario = propiedades_mapa.get(jdbc_usuario_tex);
        contraseña = propiedades_mapa.get(jdbc_contraseña_tex);
        nuevo_EntityManager = EntityManagerConfig.getEntityManager(entityManager
                , environment, usuario, contraseña, propiedades_mapa, error);
        if (nuevo_EntityManager != null) {
            // Cambiar el EntityManager
            entityManager = nuevo_EntityManager;
        } else {
            if (error[0].isBlank()) { 
                // No hay error. Sigue el EntityManager actual 
                nuevo_EntityManager = entityManager;
            }
        }
        return nuevo_EntityManager;
    }

    public List<Producto> findLike_descripcion(Integer from, Integer to, String descripcion) {
        List<Producto> productos_lista;
        TypedQuery<Producto> typedQuery = getEntityManager().createNamedQuery("Producto.findByCodigoProducto", Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        return productos_lista;
    }
    
    public List<Producto> find_orden(Integer from
            , Integer to
            , String campo_ordenacion
            , String asc) {
        List<Producto> productos_lista;
        String consulta = SELECT_findAll_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        return productos_lista;
    }

    public List<Producto> findLike_descripcion_orden(Integer from
            , Integer to
            , String descripcion
            , String campo_ordenacion
            , String asc) {
        List<Producto> productos_lista;
        String consulta = SELECT_findByCodigoProducto_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        return productos_lista;
    }
    
}
