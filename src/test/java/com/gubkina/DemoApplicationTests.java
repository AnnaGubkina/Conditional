package com.gubkina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    private static final int PORT_DEV = 8080;
    private static final int PORT_PROD = 8081;

    @Autowired
    TestRestTemplate restTemplate;

    public static GenericContainer<?> containerDev = new GenericContainer<>("devapp")
            .withExposedPorts(PORT_DEV);

    public static GenericContainer<?> containerProd = new GenericContainer<>("prodapp")
            .withExposedPorts(PORT_PROD);

    @BeforeAll
    public static void setUp() {
        containerDev.start();
        containerProd.start();

    }

    @Test
    void contextLoadsDev() {
        final String expected = "Current profile is dev";
        String url = String.format("http://localhost:%d/profile",containerDev.getMappedPort(PORT_DEV));
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void contextLoadsProd() {
        final String expected = "Current profile is production";
        String url = String.format("http://localhost:%d/profile",containerProd.getMappedPort(PORT_PROD));
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(expected, forEntity.getBody());
    }
}
