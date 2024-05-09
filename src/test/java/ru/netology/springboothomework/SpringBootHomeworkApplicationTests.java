package ru.netology.springboothomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8070);
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8071);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> entityFromDevApp = restTemplate.getForEntity("http://localhost:"
                + devApp.getMappedPort(8070) + "/profile", String.class);
        ResponseEntity<String> entityFromProdApp = restTemplate.getForEntity("http://localhost:"
                + prodApp.getMappedPort(8071) + "/profile", String.class);
        Assertions.assertEquals(entityFromDevApp.getBody(), "Current profile is dev");
        Assertions.assertEquals(entityFromProdApp.getBody(), "Current profile is production");
    }

}
