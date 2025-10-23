package com.ejemplo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) que representa los datos básicos de una persona.
 * Utilizado para recibir o enviar información entre capas sin exponer la entidad completa.
 */
public class PersonaDto {

  /**
   * Nombre de la persona.
   * Este campo es obligatorio y no puede estar en blanco.
   */
  @NotBlank(message = "El nombre es obligatorio")
  public String nombre;

  /**
   * Edad de la persona.
   * Debe ser un valor positivo (mayor o igual a 0).
   */
  @Min(value = 0, message = "La edad debe ser positiva")
  public int edad;

  /**
   * Ciudad de residencia de la persona.
   * Este campo es obligatorio y no puede estar en blanco.
   */
  @NotBlank(message = "La ciudad es obligatoria")
  public String ciudad;

  /**
   * Constructor de la clase.
   */
  public PersonaDto(String nombre, int edad, String ciudad) {
    this.ciudad = ciudad;
    this.nombre = nombre;
    this.edad = edad;
  }
}