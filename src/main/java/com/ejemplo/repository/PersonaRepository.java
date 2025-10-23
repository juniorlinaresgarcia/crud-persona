package com.ejemplo.repository;

import com.ejemplo.entity.Persona;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Repositorio para operaciones de persistencia sobre entidades {@link Persona}.
 * Utiliza PanacheMongoRepository para simplificar el acceso a MongoDB.
 */
@ApplicationScoped
public class PersonaRepository implements PanacheMongoRepository<Persona> {

  /**
   * Busca todas las personas que residen en una ciudad específica.
   *
   * @param ciudad el nombre de la ciudad por la cual se desea filtrar
   * @return una lista de personas que tienen como ciudad la especificada
   */
  public List<Persona> buscarPorCiudad(String ciudad) {
    return find("ciudad", ciudad).list();
  }
}