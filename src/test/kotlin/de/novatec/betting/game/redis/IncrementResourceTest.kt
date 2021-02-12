package de.novatec.betting.game.redis

import org.junit.jupiter.api.Test

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`


@QuarkusTest
class IncrementResourceTest {
    @Test
    fun testRedisOperations() {
        // verify that we have nothing
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments")
            .then()
            .statusCode(200)
            .body("size()", `is`(0))

        // create a first increment key with an initial value of 0
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body("{\"key\":\"first-key\",\"value\":0}")
            .`when`()
            .post("/increments")
            .then()
            .statusCode(200)
            .body("key", `is`("first-key"))
            .body("value", `is`(0))

        // create a second increment key with an initial value of 10
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body("{\"key\":\"second-key\",\"value\":10}")
            .`when`()
            .post("/increments")
            .then()
            .statusCode(200)
            .body("key", `is`("second-key"))
            .body("value", `is`(10))

        // increment first key by 1
        given()
            .contentType(ContentType.JSON)
            .body("1")
            .`when`()
            .put("/increments/first-key")
            .then()
            .statusCode(204)

        // verify that key has been incremented
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments/first-key")
            .then()
            .statusCode(200)
            .body("key", `is`("first-key"))
            .body("value", `is`(1))

        // increment second key by 1000
        given()
            .contentType(ContentType.JSON)
            .body("1000")
            .`when`()
            .put("/increments/second-key")
            .then()
            .statusCode(204)

        // verify that key has been incremented
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments/second-key")
            .then()
            .statusCode(200)
            .body("key", `is`("second-key"))
            .body("value", `is`(1010))

        // verify that we have two keys in registered
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments")
            .then()
            .statusCode(200)
            .body("size()", `is`(2))

        // delete first key
        given()
            .accept(ContentType.JSON)
            .`when`()
            .delete("/increments/first-key")
            .then()
            .statusCode(204)

        // verify that we have one key left after deletion
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments")
            .then()
            .statusCode(200)
            .body("size()", `is`(1))

        // delete second key
        given()
            .accept(ContentType.JSON)
            .`when`()
            .delete("/increments/second-key")
            .then()
            .statusCode(204)

        // verify that there is no key left
        given()
            .accept(ContentType.JSON)
            .`when`()
            .get("/increments")
            .then()
            .statusCode(200)
            .body("size()", `is`(0))
    }
}
