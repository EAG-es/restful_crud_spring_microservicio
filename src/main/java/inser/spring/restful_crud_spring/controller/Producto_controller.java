/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.spring.restful_crud_spring.controller;

import static inser.spring.restful_crud_spring.controller.Producto_controller.path;
import static inser.spring.restful_crud_spring.controller.Producto_controller.path_base;
import inser.spring.restful_crud_spring.model.LinkedList_envuelta;
import inser.spring.restful_crud_spring.model.Producto;
import static inser.spring.restful_crud_spring.model.Producto.SELECT_findAll_order;
import static inser.spring.restful_crud_spring.model.Producto.SELECT_findByCodigoProducto_order;
import static inser.spring.restful_crud_spring.repository.PersistenceConfig.jdbc_contraseña_tex;
import static inser.spring.restful_crud_spring.repository.PersistenceConfig.jdbc_usuario_tex;
import inser.spring.restful_crud_spring.service.Producto_service;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author informatica
 */
@RestController
@RequestMapping(path) 
public class Producto_controller {
    public static final String path_base = "/webresources"; 
    public static final String path = path_base + "/inser.restful.restful_crud.producto"; 
    public Producto_service producto_service;
       
    public Producto_controller(Producto_service producto_service) {
        this.producto_service = producto_service;
    }

    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param entity 
     */
    @PostMapping()
    @Transactional
    public void create(@RequestHeader(required = true) Map<String, String> headers
            , @RequestBody(required = true) Producto entity) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        producto_service.create(entity);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param entity 
     */
    @PutMapping()
    @Transactional
    public void edit(@RequestHeader(required = true) Map<String, String> headers
            , @RequestBody(required = true) Producto entity) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        producto_service.edit(entity);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param id 
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void remove(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("id") String id) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        producto_service.remove(id);
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    @ResponseBody  // devuelve JSON
    public Producto find(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("id") String id) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        Producto producto = producto_service.find(id);
        return producto;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @return 
     */
    @GetMapping()
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Producto> findAll_envuelta(@RequestHeader(required = true) Map<String, String> headers) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        List<Producto> productos_lista = new LinkedList<>(producto_service.findAll());
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @return 
     */
    @GetMapping("/{from}/{to}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Producto> findRange(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        List<Producto> productos_lista = producto_service.findRange(from, to);
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @return 
     */
    @GetMapping("/count")
    public String countREST(@RequestHeader(required = true) Map<String, String> headers) {
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        return String.valueOf(producto_service.countREST());
    }

    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @param descripcion
     * @return 
     */
    @PostMapping("/{from}/{to}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Producto> findLike_descripcion(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @RequestBody(required = true) String descripcion) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        List<Producto> productos_lista;
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        TypedQuery<Producto> typedQuery = producto_service.getEntityManager().createNamedQuery("Producto.findByCodigoProducto", Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    /**
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param headers
     * @param from
     * @param to
     * @param campo_ordenacion
     * @param asc
     * @return 
     */
    @GetMapping("/{from}/{to}/{campo_ordenacion}/{asc}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Producto> find_orden(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @PathVariable("campo_ordenacion") String campo_ordenacion
            , @PathVariable("asc") String asc) {
        List<Producto> productos_lista;
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        String consulta = SELECT_findAll_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = producto_service.getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    /**
     * POST: Los campos de texto no se deben enviar en el Path debido a los caracteres especiales que pueden contener
     * Si la petición no tiene los datos requeridos (required) devuelve un error 405, 400, 4xx
     * @param from
     * @param to
     * @param descripcion
     * @param campo_ordenacion
     * @param asc
     * @return 
     */
    @PostMapping("/{from}/{to}/{campo_ordenacion}/{asc}")
    @ResponseBody  // devuelve JSON
    public LinkedList_envuelta<Producto> findLike_descripcion_orden(@RequestHeader(required = true) Map<String, String> headers
            , @PathVariable("from") Integer from
            , @PathVariable("to") Integer to
            , @RequestBody(required = true) String descripcion
            , @PathVariable("campo_ordenacion") String campo_ordenacion
            , @PathVariable("asc") String asc) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        List<Producto> productos_lista;
        String texto;     
        texto = headers.get(jdbc_usuario_tex);
        producto_service.propiedades_mapa.put(jdbc_usuario_tex, texto);
        texto = headers.get(jdbc_contraseña_tex);
        producto_service.propiedades_mapa.put(jdbc_contraseña_tex, texto);
        String consulta = SELECT_findByCodigoProducto_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = producto_service.getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    
}
