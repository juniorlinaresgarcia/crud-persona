package com.ejemplo.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * Entidad que representa una persona en la base de datos MongoDB.
 * Esta clase está mapeada a la colección "personas" y extiende {@link PanacheMongoEntity}
 * para facilitar operaciones CRUD con Panache.
 */
@MongoEntity(collection = "personas")
public class Persona extends PanacheMongoEntity {

  /**
   * Nombre de la persona.
   */
  public String nombre;

  /**
   * Edad de la persona.
   */
  public int edad;

  /**
   * Ciudad de residencia de la persona.
   */
  public String ciudad;
}