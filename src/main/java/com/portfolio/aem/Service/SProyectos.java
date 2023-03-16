/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.aem.Service;

import com.portfolio.aem.Entity.Proyectos;
import com.portfolio.aem.Repository.RProyectos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SProyectos {
    @Autowired
     RProyectos rProyectos;
     
     public List<Proyectos> list(){
         return rProyectos.findAll();
     }
     
     public Optional<Proyectos> getOne(int id){
         return rProyectos.findById(id);
     }
     
     public Optional<Proyectos> getByNombre(String nombre){
         return rProyectos.findByNombre(nombre);
     }
     
     public void save(Proyectos expe){
         rProyectos.save(expe);
     }
     
     public void delete(int id){
         rProyectos.deleteById(id);
     }
     
     public boolean existsById(int id){
         return rProyectos.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return rProyectos.existsByNombre(nombre);
     }
    
}
