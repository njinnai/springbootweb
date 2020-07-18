package hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class HelloControllerTest {

  @Test
  void byString() {
    final HelloWorldController helloController = new HelloWorldController();
    Assertions.assertAll(
        () -> Assertions.assertEquals(helloController.byString(), HelloWorldController.HELLO_WORLD)
    );
  }

  @Test
  void byBytes() {
    final HelloWorldController helloController = new HelloWorldController();
    Assertions.assertAll(
        () -> Assertions.assertEquals(Arrays.toString(helloController.byBytes()),
            Arrays.toString(HelloWorldController.HELLO_WORLD.getBytes()))
    );
  }

  @Test
  void byStream() {
    final HelloWorldController helloController = new HelloWorldController();
    final ResponseEntity<InputStreamResource> response = helloController.byStream();
    try (final InputStream input = response.getBody().getInputStream()) {
      final byte[] body = input.readAllBytes();
      Assertions.assertAll(
          () -> Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK),
          () -> Assertions.assertEquals(Arrays.toString(body),
              Arrays.toString(HelloWorldController.HELLO_WORLD.getBytes()))
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
