package com.example.ej147crudvalidation.application;

import com.example.ej147crudvalidation.dto.input.PersonaInputDTO;
import com.example.ej147crudvalidation.dto.output.PersonaOutputDTO;
import com.example.ej147crudvalidation.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaService {
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO);
    public PersonaOutputDTO editarPersona(Integer id, PersonaInputDTO personaInputDTO);
    public void eliminarPersona(Integer id);
    public PersonaOutputDTO buscarPersonaPorId(Integer id);
    public List<Persona> dameAllPersonas() throws Exception;


}
