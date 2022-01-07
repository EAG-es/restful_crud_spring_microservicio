/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.spring.restful_crud_spring.repository;

import inser.spring.restful_crud_spring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author informatica
 */
@Repository
public interface Producto_repository extends JpaRepository<Producto, String> {
    
}
