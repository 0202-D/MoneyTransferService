package ru.netology.dmitriypetrov.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.dmitriypetrov.moneytransferservice.dto.OperationRequestDto;
import ru.netology.dmitriypetrov.moneytransferservice.dto.RequestVerifyDto;
import ru.netology.dmitriypetrov.moneytransferservice.model.Amount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class MoneyTransferServiceContainerTests {
    private final static int PORT = 5500;

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> myapp = new GenericContainer<>("myapp:latest")
            .withExposedPorts(PORT);

    @Test
    void moneyTransferTest() {
        OperationRequestDto request = new OperationRequestDto(
                "1111111111111111",
                "1212121212345678",
                "06/24",
                "123",
                new Amount(10000, "RUB")
        );

        ResponseEntity<String> forEntity = restTemplate.postForEntity(
                "http://localhost:" + myapp.getMappedPort(PORT) + "/transfer", request, String.class);
        System.out.println(forEntity.getBody());
        String expected = "{\"operationId\":" + "\"1\"}";
        String actual = forEntity.getBody();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void confirmOperationTest() {
        RequestVerifyDto request = new RequestVerifyDto("1", "0000");

        ResponseEntity<String> forEntity = restTemplate.postForEntity(
                "http://localhost:" + myapp.getMappedPort(PORT) + "/confirmOperation", request, String.class);
        System.out.println(forEntity.getBody());
        String expected = "{\"operationId\":" + "\"1\"}";
        String actual = forEntity.getBody();
        Assertions.assertEquals(expected, actual);
    }

}