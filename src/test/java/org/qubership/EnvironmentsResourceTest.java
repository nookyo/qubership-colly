package org.qubership;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class EnvironmentsResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/environments/tick")
                .then()
                .statusCode(200)
                .body(containsString("demo-k8s"))
        ;
    }

}