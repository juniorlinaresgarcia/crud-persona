package com.ejemplo.service;

import com.ejemplo.dto.PersonaDto;
import com.ejemplo.entity.Persona;
import com.ejemplo.repository.PersonaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * Servicio de negocio para gestionar operaciones relacionadas con {@link Persona}.
 * Encapsula la lógica de creación, lectura, actualización, eliminación y búsqueda.
 */
@ApplicationScoped
public class PersonaService {

  @Inject
  PersonaRepository repo;

  private static final Logger log = LogManager.getLogger(PersonaService.class);

  /**
   * Crea una nueva entidad {@link Persona} a partir de un DTO.
   *
   * @param dto los datos de la persona a crear
   * @return la entidad persistida
   */
  public Persona crear(PersonaDto dto) {
    Persona p = new Persona();
    p.nombre = dto.nombre;
    p.edad = dto.edad;
    p.ciudad = dto.ciudad;
    repo.persist(p);
    return p;
  }

  /**
   * Lista todas las personas almacenadas en la base de datos.
   *
   * @return lista completa de personas
   */
  public List<Persona> listar() {
    log.info("INICIO - metodo listar");
    return repo.listAll();
  }

  /**
   * Obtiene una persona por su identificador.
   *
   * @param id el ID en formato hexadecimal de MongoDB
   * @return la persona correspondiente, o null si no existe
   */
  public Persona obtener(String id) {
    return repo.findById(new ObjectId(id));
  }

  /**
   * Actualiza los datos de una persona existente.
   *
   * @param id el ID de la persona a actualizar
   * @param dto los nuevos datos a aplicar
   * @return la persona actualizada, o null si no se encuentra
   */
  public Persona actualizar(String id, PersonaDto dto) {
    Persona actual = repo.findById(new ObjectId(id));
    if (actual == null) {
      return null;
    }
    actual.nombre = dto.nombre;
    actual.edad = dto.edad;
    actual.ciudad = dto.ciudad;
    repo.update(actual);
    return actual;
  }

  /**
   * Elimina una persona por su identificador.
   *
   * @param id el ID de la persona a eliminar
   * @return true si se eliminó correctamente, false si no se encontró
   */
  public boolean eliminar(String id) {
    return repo.deleteById(new ObjectId(id));
  }

  /**
   * Busca personas que residan en una ciudad específica.
   *
   * @param ciudad el nombre de la ciudad
   * @return lista de personas que viven en la ciudad indicada
   */
  public List<Persona> buscarPorCiudad(String ciudad) {
    return repo.buscarPorCiudad(ciudad);
  }
}