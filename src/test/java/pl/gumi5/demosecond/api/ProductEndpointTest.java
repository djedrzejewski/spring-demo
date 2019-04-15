package pl.gumi5.demosecond.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.gumi5.demosecond.DemoSecondApplicationTests;
import pl.gumi5.demosecond.domain.ProductFacade;
import pl.gumi5.demosecond.domain.ProductRequestDto;
import pl.gumi5.demosecond.domain.ProductResponseDto;
import pl.gumi5.demosecond.domain.ProductsListResponseDto;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductEndpointTest  extends DemoSecondApplicationTests {

    @Autowired
    ProductFacade productFacade;

    @Test
    public void shouldGetListOfExistingProducts(){
        //given
        ProductRequestDto requestDto1 = new ProductRequestDto("product1");
        ProductResponseDto existingProduct1 = productFacade.create(requestDto1);
        ProductRequestDto requestDto2 = new ProductRequestDto("product2");
        ProductResponseDto existingProduct2 = productFacade.create(requestDto2);
        final String url = "http://localhost:"+ port + "/products";

        //when
        ResponseEntity<ProductsListResponseDto> result = httpClient.getForEntity(url, ProductsListResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

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
    public void shouldReturn404OnGetNonExistingProduct(){
        //given
        final String url = "http://localhost:"+ port + "/products/" + "123";

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(404);
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

    @Test
    public void shouldUpdateProduct(){
        //given
        final String createdProductName = "startowa-nazwa";
        final String editedProductName = "wyedytowana-nazwa";

        ProductResponseDto createdProduct = productFacade.create(new ProductRequestDto(createdProductName));
        ProductRequestDto productToEdit = new ProductRequestDto(editedProductName);
        final String productJson = mapToJson(productToEdit);

        final String url = "http://localhost:"+ port + "/products/"+createdProduct.getId();

        //when
        ResponseEntity<ProductResponseDto> updateResult = httpClient.exchange(
                url,
                HttpMethod.PUT,
                getHttpRequest(productJson),
                ProductResponseDto.class
        );

        //then
        assertThat(updateResult.getStatusCodeValue()).isEqualTo(200);
        assertThat(updateResult.getBody().getId()).isEqualTo(createdProduct.getId());
        assertThat(updateResult.getBody().getName()).isEqualTo(productToEdit.getName());
    }

    @Test
    public void shouldReturn404OnUpdateNonExistingProduct(){
        //given
        final String url = "http://localhost:"+ port + "/products/" + "123";
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);
        ProductRequestDto productToEdit = new ProductRequestDto("new-product-name");
        final String productJson = mapToJson(productToEdit);

        //when
        ResponseEntity<ProductResponseDto> updateResult = httpClient.exchange(
                url,
                HttpMethod.PUT,
                getHttpRequest(productJson),
                ProductResponseDto.class
        );

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void shouldDeleteProduct(){
        //given
        final String createdProductName = "nazwa";
        ProductResponseDto createdProduct = productFacade.create(new ProductRequestDto(createdProductName));
        final String url = "http://localhost:"+ port + "/products/"+createdProduct.getId();

        // when
        httpClient.delete(url);
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(404);
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
