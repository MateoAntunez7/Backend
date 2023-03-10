package com.portfolio.aem.Controller;

import com.portfolio.aem.Entity.Persona;
import com.portfolio.aem.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontendaem.web.app/", "http://localhost:4200"})
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    IPersonaService ipersonaService;

    @GetMapping
    public List<Persona> getPersona() {
        return ipersonaService.getPersona();
    }

    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona) {
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deletePersona(@PathVariable Long id) {
        ipersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }

    @PutMapping("/editar/{id}")
    public Persona editPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        Persona persona = ipersonaService.findPersona(id);

        persona.setNombre(personaActualizada.getNombre());
        persona.setApellido(personaActualizada.getApellido());
        persona.setImg(personaActualizada.getImg());

        ipersonaService.savePersona(persona);
        return persona;
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@RequestBody Long id) {
        return ipersonaService.findPersona(id);
    }
}

