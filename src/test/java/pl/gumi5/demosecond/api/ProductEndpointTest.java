package pl.gumi5.demosecond.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import pl.gumi5.demosecond.DemoSecondApplicationTests;
import pl.gumi5.demosecond.domain.Product;
import pl.gumi5.demosecond.domain.ProductFacade;
import pl.gumi5.demosecond.domain.ProductRequestDto;
import pl.gumi5.demosecond.domain.ProductResponseDto;

import static org.assertj.core.api.Assertions.*;

public class ProductEndpointTest  extends DemoSecondApplicationTests {

    @Autowired
    ProductFacade productFacade;

    @Test
    public void shouldGetExistingProduct(){
        //given
        ProductRequestDto requestDto = new ProductRequestDto("product");
        ProductResponseDto existingProduct = productFacade.create(requestDto);
        final String url = "http://localhost:"+ port + "/products/" + existingProduct.getId();

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualToComparingFieldByField(existingProduct);
    }

    @Test
    public void shouldCreateProduct(){
        //given
        final String url = "http://localhost:"+ port + "/products";
        final ProductRequestDto product = new ProductRequestDto("typowanazwa");
        String productJson = mapToJson(product);

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.postForEntity(
                url,
                getHttpRequest(productJson),
                ProductResponseDto.class
        );

        //then

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("typowanazwa");
    }


    String mapToJson(ProductRequestDto product){
        try {
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getHttpRequest(String json){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type","application/json");
        return new HttpEntity<>(json, httpHeaders);
    }
}
