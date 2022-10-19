package com.example.ej147crudvalidation.controller;

import com.example.ej147crudvalidation.application.PersonaService;
import com.example.ej147crudvalidation.dto.input.PersonaInputDTO;
import com.example.ej147crudvalidation.dto.output.PersonaOutputDTO;
import com.example.ej147crudvalidation.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaControlador {

    @Autowired
    PersonaService personaService;

    @GetMapping("personas/{id}")
    public PersonaOutputDTO damePersonaPorId(@PathVariable Integer id) {
            return personaService.buscarPersonaPorId(id);
    }


    @GetMapping("personas")
    public Iterable<Persona> damePersonas() throws Exception{

        return personaService.dameAllPersonas();
    }

    @PostMapping("personas/insertar")
    public PersonaOutputDTO insertaPersona(@RequestBody PersonaInputDTO personaDTO) {

        return personaService.insertarPersona(personaDTO);


    }

    @PutMapping("personas/editar")
    public PersonaOutputDTO editarPersona(@RequestParam Integer id, @RequestBody PersonaInputDTO persona) {
        return personaService.editarPersona(id, persona);
    }

    @DeleteMapping("personas/eliminar")
    public String eliminarPersona(@RequestParam Integer id) {
        personaService.eliminarPersona(id);
        return "La persona con el id: " + id + " ha sido borrada correctamete";
    }
}
