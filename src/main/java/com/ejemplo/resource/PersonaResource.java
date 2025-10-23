package com.ejemplo.resource;

import com.ejemplo.dto.PersonaDto;
import com.ejemplo.entity.Persona;
import com.ejemplo.service.PersonaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Recurso REST para gestionar entidades {@link Persona}.
 * Expone operaciones CRUD y búsqueda por ciudad a través de endpoints HTTP.
 */
@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {

  @Inject
  PersonaService service;

  /**
   * Crea una nueva persona a partir de los datos proporcionados.
   *
   * @param dto el DTO con los datos de la persona
   * @return respuesta HTTP 201 con la persona creada
   */
  @POST
  public Response crear(@Valid PersonaDto dto) {
    Persona p = service.crear(dto);
    return Response.status(Response.Status.CREATED).entity(p).build();
  }

  /**
   * Lista todas las personas registradas.
   *
   * @return lista de personas
   */
  @GET
  public List<Persona> listar() {
    return service.listar();
  }

  /**
   * Obtiene una persona por su identificador.
   *
   * @param id el ID de la persona
   * @return la persona correspondiente, o null si no existe
   */
  @GET
  @Path("/{id}")
  public Persona obtener(@PathParam("id") String id) {
    return service.obtener(id);
  }

  /**
   * Actualiza los datos de una persona existente.
   *
   * @param id  el ID de la persona a actualizar
   * @param dto los nuevos datos de la persona
   * @return respuesta HTTP 200 con la persona actualizada, o 404 si no se encuentra
   */
  @PUT
  @Path("/{id}")
  public Response actualizar(@PathParam("id") String id, @Valid PersonaDto dto) {
    Persona p = service.actualizar(id, dto);
    if (p == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(p).build();
  }

  /**
   * Elimina una persona por su identificador.
   *
   * @param id el ID de la persona a eliminar
   * @return respuesta HTTP 204 si se elimina correctamente, o 404 si no se encuentra
   */
  @DELETE
  @Path("/{id}")
  public Response eliminar(@PathParam("id") String id) {
    boolean eliminado = service.eliminar(id);
    return eliminado ? Response.noContent().build() :
          Response.status(Response.Status.NOT_FOUND).build();
  }

  /**
   * Busca personas que residan en una ciudad específica.
   *
   * @param ciudad el nombre de la ciudad
   * @return lista de personas que viven en la ciudad indicada
   */
  @GET
  @Path("/buscar")
  public List<Persona> buscarPorCiudad(@QueryParam("ciudad") String ciudad) {
    return service.buscarPorCiudad(ciudad);
  }
}