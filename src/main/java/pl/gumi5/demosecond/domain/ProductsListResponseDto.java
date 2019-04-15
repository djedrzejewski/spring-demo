package pl.gumi5.demosecond.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ProductsListResponseDto {
    private final ArrayList<ProductResponseDto> products;

    @JsonCreator
    public ProductsListResponseDto(@JsonProperty("products") ArrayList<ProductResponseDto> productsList) {
        this.products = productsList;
    }

    public ArrayList<ProductResponseDto> getProducts() {
        return products;
    }
}
