package pl.gumi5.demosecond.infrastructure;

import pl.gumi5.demosecond.domain.Product;
import pl.gumi5.demosecond.domain.ProductRequestDto;

import java.util.ArrayList;

public interface ProductRepository {

    void save(Product product);

    ArrayList<Product> findAll();
    Product findById(String id);
    Product update(Product productToEdit, ProductRequestDto editedProduct);
    Product delete(Product product);
}
