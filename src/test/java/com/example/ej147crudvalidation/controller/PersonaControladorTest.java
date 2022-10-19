package com.example.ej147crudvalidation.controller;

import com.example.ej147crudvalidation.application.PersonaService;
import com.example.ej147crudvalidation.dto.input.PersonaInputDTO;
import com.example.ej147crudvalidation.dto.output.PersonaOutputDTO;
import com.example.ej147crudvalidation.entity.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonaControlador.class)
@RunWith(JUnit4.class)
class PersonaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PersonaService personaService;


    private List<Persona> listaPersonas = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.listaPersonas.add(new Persona(1, "paco1", "12345", "Paco", "León", "a@gmail.com", "b@gmail.com", "jaen", true, new Date(2022, 8, 25, 00, 00, 00), "a.jpg", new Date(2022, 8, 25, 00, 00, 00)));
        this.listaPersonas.add(new Persona(2, "lolalolita", "12345", "lola", "Quesada", "a@gmail.com", "b@gmail.com", "jaen", true, new Date(2022, 8, 25, 00, 00, 00), "a.jpg", new Date(2022, 8, 25, 00, 00, 00)));
    }



    @Test
    void damePersonaPorId()  throws Exception {

        final Integer id_persona = 1;

        LocalDate localDate = LocalDate.now();
        Date date1=java.sql.Date.valueOf(localDate);
        System.out.println(localDate);
        System.out.println(date1);

        final PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(1, "paco1", "12345", "Paco", "León", "a@gmail.com", "b@gmail.com",
                "jaen", true, date1, "a.jpg", date1);

        given(personaService.buscarPersonaPorId(id_persona)).willReturn(personaOutputDTO);

        this.mockMvc.perform(get("/personas/{id}", id_persona).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_persona", is(personaOutputDTO.getId_persona())))
                .andExpect(jsonPath("$.usuario", is(personaOutputDTO.getUsuario())))
                .andExpect(jsonPath("$.password", is(personaOutputDTO.getPassword())))
                .andExpect(jsonPath("$.name", is(personaOutputDTO.getName())))
                .andExpect(jsonPath("$.surname", is(personaOutputDTO.getSurname())))
                .andExpect(jsonPath("$.company_email", is(personaOutputDTO.getCompany_email())))
                .andExpect(jsonPath("$.personal_email", is(personaOutputDTO.getPersonal_email())))
                .andExpect(jsonPath("$.city", is(personaOutputDTO.getCity())))
                .andExpect(jsonPath("$.active", is(personaOutputDTO.isActive())))
                .andExpect(jsonPath("$.created_date", is((personaOutputDTO.getCreated_date().toString()))))
                .andExpect(jsonPath("$.imagen_url", is(personaOutputDTO.getImagen_url())))
                .andExpect(jsonPath("$.termination_date", is(personaOutputDTO.getTermination_date().toString())));
    }

    @Test
    void damePersonas() throws Exception{
        given(personaService.dameAllPersonas()).willReturn(listaPersonas);

        this.mockMvc.perform(get("/personas")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(listaPersonas.size())));
    }

    @Test
    void insertaPersona() throws Exception{
        LocalDate localDate = LocalDate.now();
        Date date1=java.sql.Date.valueOf(localDate);

        PersonaInputDTO personaInputDTO = new PersonaInputDTO(1,"paco1", "12345", "Paco", "León", "a@gmail.com", "b@gmail.com", "jaen", true, date1, "a.jpg", date1);
        Persona persona = new Persona(personaInputDTO);
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);

        given(personaService.insertarPersona(any(PersonaInputDTO.class))).willReturn(personaOutputDTO);
//        when(personaService.insertarPersona(any(PersonaInputDTO.class))).thenReturn(personaOutputDTO);

        this.mockMvc.perform(post("/personas/insertar")
                                    .content(objectMapper.writeValueAsString(personaInputDTO))
                                    .contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(status().isOk())
                                    .andExpect(jsonPath("$.id_persona", is(personaOutputDTO.getId_persona())))
                                    .andExpect(jsonPath("$.usuario", is(personaOutputDTO.getUsuario())))
                                    .andExpect(jsonPath("$.password", is(personaOutputDTO.getPassword())))
                                    .andExpect(jsonPath("$.name", is(personaOutputDTO.getName())))
                                    .andExpect(jsonPath("$.surname", is(personaOutputDTO.getSurname())))
                                    .andExpect(jsonPath("$.company_email", is(personaOutputDTO.getCompany_email())))
                                    .andExpect(jsonPath("$.personal_email", is(personaOutputDTO.getPersonal_email())))
                                    .andExpect(jsonPath("$.city", is(personaOutputDTO.getCity())))
                                    .andExpect(jsonPath("$.active", is(personaOutputDTO.isActive())))
                                    .andExpect(jsonPath("$.created_date", is((personaOutputDTO.getCreated_date().toString()))))
                                    .andExpect(jsonPath("$.imagen_url", is(personaOutputDTO.getImagen_url())))
                                    .andExpect(jsonPath("$.termination_date", is(personaOutputDTO.getTermination_date().toString())));
    }

    @Test
    void editarPersona() throws Exception{
        LocalDate localDate = LocalDate.now();
        Date date1=java.sql.Date.valueOf(localDate);

        PersonaInputDTO personaInputDTO = new PersonaInputDTO(1,"paco1", "12345", "Paco", "León", "a@gmail.com", "b@gmail.com", "jaen", true, date1, "a.jpg", date1);
        Persona persona = new Persona(personaInputDTO);
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);

        Integer id_persona = 1;

        given(personaService.buscarPersonaPorId(id_persona)).willReturn(personaOutputDTO);
//        given(personaService.editarPersona(id_persona,personaInputDTO)).willAnswer((invocation) -> invocation.getArgument(0));
        given(personaService.editarPersona(any(Integer.class),any(PersonaInputDTO.class))).willReturn(personaOutputDTO);
//        when(personaService.editarPersona(any(Integer.class),any(PersonaInputDTO.class))).thenReturn(personaOutputDTO);

        this.mockMvc.perform(put("/personas/editar").param("id", id_persona.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaInputDTO)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id_persona", is(personaOutputDTO.getId_persona())))
                        .andExpect(jsonPath("$.usuario", is(personaOutputDTO.getUsuario())))
                        .andExpect(jsonPath("$.password", is(personaOutputDTO.getPassword())))
                        .andExpect(jsonPath("$.name", is(personaOutputDTO.getName())))
                        .andExpect(jsonPath("$.surname", is(personaOutputDTO.getSurname())))
                        .andExpect(jsonPath("$.company_email", is(personaOutputDTO.getCompany_email())))
                        .andExpect(jsonPath("$.personal_email", is(personaOutputDTO.getPersonal_email())))
                        .andExpect(jsonPath("$.city", is(personaOutputDTO.getCity())))
                        .andExpect(jsonPath("$.active", is(personaOutputDTO.isActive())))
                        .andExpect(jsonPath("$.created_date", is((personaOutputDTO.getCreated_date().toString()))))
                        .andExpect(jsonPath("$.imagen_url", is(personaOutputDTO.getImagen_url())))
                        .andExpect(jsonPath("$.termination_date", is(personaOutputDTO.getTermination_date().toString())));
    }


    @Test
    void eliminarPersona() throws Exception{
        Integer id_persona = 1;
        Persona persona = new Persona(1, "paco1", "12345", "Paco", "León", "a@gmail.com", "b@gmail.com", "jaen", true, new Date(2022, 8, 25, 00, 00, 00), "a.jpg", new Date(2022, 8, 25, 00, 00, 00));
        String message = "La persona con el id: " + id_persona + " ha sido borrada correctamete";

        given(personaService.buscarPersonaPorId(id_persona)).willReturn(new PersonaOutputDTO(persona));
        doNothing().when(personaService).eliminarPersona(id_persona);

        this.mockMvc.perform(delete("/personas/eliminar").param("id", persona.getId_persona().toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", is(message)));
    }
}