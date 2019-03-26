package pl.gumi5.demosecond.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import pl.gumi5.demosecond.DemoSecondApplicationTests;

public class HelloWorldEndpointTest extends DemoSecondApplicationTests {

    @Test
    public void shouldReturnGreetings() {
        //given
        final String url = "http://localhost:"+ port + "/hello";
        //when
        ResponseEntity<String> entity = httpClient.getForEntity(url, String.class);
        //then
        Assertions.assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(entity.getBody()).isEqualTo("Hello Heroku World!");
    }
}
