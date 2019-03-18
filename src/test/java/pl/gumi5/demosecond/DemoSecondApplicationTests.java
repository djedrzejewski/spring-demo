package pl.gumi5.demosecond;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoSecondApplicationTests {

	@Autowired
	TestRestTemplate httpClient;

	@LocalServerPort
	int port;

	@Test
	public void shouldReturnGreetings() {
		//given
		final String url = "http://localhost:"+ port + "/hello";
		//when
		ResponseEntity<String> entity = httpClient.getForEntity(url, String.class);
		//then
		Assertions.assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(entity.getBody()).isEqualTo("Hello World!");
	}

}
