package com.portfolio.aem.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import com.portfolio.aem.Dto.dtoProyectos;
import com.portfolio.aem.Entity.Proyectos;
import com.portfolio.aem.Security.Controller.Mensaje;
import com.portfolio.aem.Service.SProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendaem.web.app/","http://localhost:4200"})
public class CProyectos {
    @Autowired
    SProyectos proyectosService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = proyectosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id")int id){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = proyectosService.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        proyectosService.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtotproyectos){
        if(StringUtils.isBlank(dtotproyectos.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(proyectosService.existsByNombre(dtotproyectos.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = new Proyectos(
                dtotproyectos.getNombre(), dtotproyectos.getLink(),
                dtotproyectos.getDescripcion(), dtotproyectos.getImg()
            );
        proyectosService.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);  
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyectos dtotproyectos){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(proyectosService.existsByNombre(dtotproyectos.getNombre()) && proyectosService.getByNombre(dtotproyectos.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtotproyectos.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = proyectosService.getOne(id).get();
        
        proyectos.setNombre(dtotproyectos.getNombre());
        proyectos.setLink(dtotproyectos.getLink());
        proyectos.setDescripcion(dtotproyectos.getDescripcion());
        proyectos.setImg(dtotproyectos.getImg());
        
        proyectosService.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
   
}
