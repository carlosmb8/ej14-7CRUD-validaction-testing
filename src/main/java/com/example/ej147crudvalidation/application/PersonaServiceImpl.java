package com.example.ej147crudvalidation.application;

import com.example.ej147crudvalidation.dto.input.PersonaInputDTO;
import com.example.ej147crudvalidation.dto.output.PersonaOutputDTO;
import com.example.ej147crudvalidation.entity.Persona;
import com.example.ej147crudvalidation.excepciones.UnprocessableEntityException;
import com.example.ej147crudvalidation.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO) {
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocessableEntityException();
        } else {
            Persona persona = new Persona(personaDTO);
            personaRepo.save(persona);
            PersonaOutputDTO saveOutputDTO = new PersonaOutputDTO(persona);
            return saveOutputDTO;
        }
    }

    @Override
    public PersonaOutputDTO editarPersona(Integer id, PersonaInputDTO personaInputDTO) {

        Optional<Persona> personaOptional = personaRepo.findById(id);
        if (personaOptional.isEmpty()) {

            throw new EntityNotFoundException();
        }

        Persona persona = personaOptional.get();

        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.isActive());
        persona.setImagen_url(personaInputDTO.getImagen_url());

        personaRepo.save(persona);

        return new PersonaOutputDTO(persona);

    }

    @Override
    public void eliminarPersona(Integer id) {
        try {

            personaRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public PersonaOutputDTO buscarPersonaPorId(Integer id) {

//        return Optional .ofNullable(personaRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

                Persona persona = personaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<Persona> dameAllPersonas() throws Exception {
        try {

            return personaRepo.findAll();
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }

}
