package ch.ubs.m295.demo.integrationTests;

import ch.ubs.m295.demo.controllers.TestServiceController;
import ch.ubs.m295.demo.dto.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestServiceControllerTest {

      @Autowired
      private TestRestTemplate restTemplate;

      @LocalServerPort
      private int port;

      @Autowired
      private TestServiceController testServiceController;

      //confirm that the testServiceController returns an object of type student using http
      @Test
      public void test() {
            assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test", Student.class)).isNotNull();
      }
}
