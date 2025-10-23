package com.ejemplo.resource;

import com.ejemplo.dto.PersonaDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class PersonaResourceTest {

    @Test
    void testListarPersonas() {
        given()
              .when().get("/personas")
              .then()
              .statusCode(200)
              .body("$", notNullValue());
    }

    @Test
    void testCrearPersonaValida() {
        PersonaDto dto = new PersonaDto("Lucía", 30, "Lima");

        given()
              .contentType(ContentType.JSON)
              .body(dto)
              .when().post("/personas")
              .then()
              .statusCode(201)
              .body("nombre", equalTo("Lucía"));
    }

    @Test
    void testCrearPersonaConDatosVacios() {
        PersonaDto dto = new PersonaDto("", 0, "");

        given()
              .contentType(ContentType.JSON)
              .body(dto)
              .when().post("/personas")
              .then()
              .statusCode(400);
    }

    @Test
    void testObtenerPersonaPorIdInvalido() {
        given()
              .when().get("/personas/000000000000000000000000")
              .then()
              .statusCode(204);
    }

    @Test
    void testActualizarPersonaInexistente() {
        PersonaDto dto = new PersonaDto("Mario", 40, "Cusco");

        given()
              .contentType(ContentType.JSON)
              .body(dto)
              .when().put("/personas/000000000000000000000000")
              .then()
              .statusCode(404);
    }

    @Test
    void testActualizarPersonaValida() {
        PersonaDto dto = new PersonaDto("Mario", 40, "Cusco");

        given()
              .contentType(ContentType.JSON)
              .body(dto)
              .when().put("/personas/68eca5daa86cc9aade76adef")
              .then()
              .statusCode(200);
    }

    @Test
    void testEliminarPersonaInexistente() {
        given()
              .when().delete("/personas/000000000000000000000000")
              .then()
              .statusCode(404);
    }

    @Test
    void testBuscarPorCiudadSinResultados() {
        given()
              .when().get("/personas/buscar?ciudad=Iquitos")
              .then()
              .statusCode(200)
              .body("$", hasSize(0));
    }
}