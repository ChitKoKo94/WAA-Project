package com.shopping.Planet;

import com.shopping.Planet.DTO.ProductDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;

import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class RestTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testOneProduct() {
        ProductDTO productDTO = new ProductDTO(
                "1",
                "100011",
                "OLAY",
                BigDecimal.valueOf(25.5),
                "Glycolic Acid",
                2
        );
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products").then()
                .statusCode(201);

        given()
                .when()
                .get("products/search?number=" + productDTO.getProductNumber())
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("productNumber",hasItems("100011"))
                .body("name",hasItems("OLAY"))
                .body("price",hasItems(Float.parseFloat("25.5")))
                .body("stock",hasItems(2));

        given()
                .when()
                .delete("products/100011");
    }

    @Test
    public void testGetAllProducts() {
        ProductDTO productDTO = new ProductDTO(
                "18",
                "100018",
                "OLAY",
                BigDecimal.valueOf(25.5),
                "Glycolic Acid",
                2
        );
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products").then()
                .statusCode(201);

        ProductDTO productDTO1 = new ProductDTO(
                "19",
                "100019",
                "Ordinary",
                BigDecimal.valueOf(30),
                "Hydraulic Acid",
                7
        );
        given()
                .contentType("application/json")
                .body(productDTO1)
                .when().post("/products").then()
                .statusCode(201);
        given()
                .when()
                .get("products")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber", hasItems("100018", "100019"))
                .body("name",hasItems("OLAY", "Ordinary"))
                .body("price",hasItems(Float.parseFloat("25.5"), Integer.parseInt("30")))
                .body("stock",hasItems(2, 7));
        given()
                .when()
                .delete("products/100018");
        given()
                .when()
                .delete("products/100019");
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO(
                "20001",
                "100012",
                "Nike",
                BigDecimal.valueOf(225.5),
                "Shoes",
                7
        );
        ProductDTO productDTO1 = new ProductDTO(
                "20001",
                "100012",
                "Nike",
                BigDecimal.valueOf(225.5),
                "running shoes",
                3
        );
        given()
                .contentType("application/json")
                .body(productDTO)
                .when().post("/products").then()
                .statusCode(201);
        given()
                .contentType("application/json")
                .body(productDTO1)
                .when().put("/products").then()
                .statusCode(200);
        given()
                .when()
                .delete("products/100012");
    }

}
