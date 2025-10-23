package com.ejemplo.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.stream.Collectors;

/**
 * Manejador global de excepciones para {@link ConstraintViolationException}.
 * Captura errores de validación de Bean Validation y devuelve una respuesta
 * HTTP 400 con los mensajes de error.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  /**
   * Convierte una {@link ConstraintViolationException} en una respuesta HTTP 400
   * (Bad Request).
   * Agrupa todos los mensajes de violación en una sola cadena separada por comas.
   *
   * @param exception la excepción de validación capturada
   * @return una respuesta HTTP con el mensaje de error agrupado
   */
  @Override
  public Response toResponse(ConstraintViolationException exception) {
    String mensaje = exception.getConstraintViolations()
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.joining(", "));

    return Response.status(Response.Status.BAD_REQUEST)
          .entity(new ErrorResponse(mensaje))
          .build();
  }

  /**
   * Clase auxiliar para representar el cuerpo de la respuesta de error.
   * Contiene un único campo con el mensaje de error.
   */
  public static class ErrorResponse {
    /**
     * Mensaje de error agrupado proveniente de las violaciones de validación.
     */
    public String mensaje;

    /**
     * Constructor que inicializa el mensaje de error.
     *
     * @param mensaje el mensaje de error a incluir en la respuesta
     */
    public ErrorResponse(String mensaje) {
      this.mensaje = mensaje;
    }
  }
}