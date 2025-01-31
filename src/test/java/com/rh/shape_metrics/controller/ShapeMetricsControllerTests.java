package com.rh.shape_metrics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rh.shape_metrics.model.ShapeMetricsRequest;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.rh.shape_metrics.model.ShapeType.SQUARE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShapeMetricsControllerTests {
	@Autowired
	private ObjectMapper objectMapper;
	private static final String BASE_URI = "http://localhost";
	private static final String SHAPE_METRICS_CALCULATION_URI = "shape/metrics/calculate";
	@LocalServerPort
	int port;

	@BeforeEach
	public void beforeBase() {
		RestAssured.baseURI = BASE_URI;
		RestAssured.port = port;
	}

    @Test
	@SneakyThrows
	void shouldCalculateSquareAreaAndPerimeter() {
		ShapeMetricsRequest request = ShapeMetricsRequest.builder()
				.type(SQUARE)
				.context(Map.of(
					"side", 5.5
				))
				.build();
		String requestBody = objectMapper.writeValueAsString(request);

		given()
				.header(CONTENT_TYPE, "application/json")
				.body(requestBody)
				.post(SHAPE_METRICS_CALCULATION_URI)
				.then()
				.log().all()
				.statusCode(HttpStatus.OK.value())
				.assertThat()
				.body("area", equalTo(30.25F))
				.body("perimeter", equalTo(22F));
	}


	@ParameterizedTest
	@ValueSource(doubles = {0, -1.3, 1001})
	@SneakyThrows
	void shouldReturnBadRequestWhenSideIsInvalid(Double side) {
		ShapeMetricsRequest request = ShapeMetricsRequest.builder()
				.type(SQUARE)
				.context(Map.of(
						"side", side
				))
				.build();
		String requestBody = objectMapper.writeValueAsString(request);

		given()
				.header(CONTENT_TYPE, "application/json")
				.body(requestBody)
				.post(SHAPE_METRICS_CALCULATION_URI)
				.then()
				.log().all()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.assertThat()
				.body(equalTo("Square metrics request must include a valid side."));
	}

}
