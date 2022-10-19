package com.example.ej147crudvalidation.repository;

import com.example.ej147crudvalidation.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

}
