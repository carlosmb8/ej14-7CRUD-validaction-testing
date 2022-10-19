package com.example.ej147crudvalidation.entity;

import com.example.ej147crudvalidation.dto.input.PersonaInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona"})})
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    Integer id_persona;

    @Column
    String usuario;

    @Column
    String password;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String company_email;

    @Column
    String personal_email;

    @Column
    String city;

    @Column
    boolean active;

    @Column
    Date created_date;

    @Column
    String imagen_url;

    @Column
    Date termination_date;


    public Persona(PersonaInputDTO personaDTO) {
        this.id_persona = personaDTO.getId_persona();
        this.usuario = personaDTO.getUsuario();
        this.password = personaDTO.getPassword();
        this.name = personaDTO.getName();
        this.surname = personaDTO.getSurname();
        this.company_email = personaDTO.getCompany_email();
        this.personal_email = personaDTO.getPersonal_email();
        this.city = personaDTO.getCity();
        this.active = personaDTO.isActive();
        this.created_date = personaDTO.getCreated_date();
        this.imagen_url = personaDTO.getImagen_url();
        this.termination_date = personaDTO.getTermination_date();
    }

}
