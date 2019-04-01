package pl.gumi5.demosecond.infrastructure;

import pl.gumi5.demosecond.domain.Product;
import pl.gumi5.demosecond.domain.ProductRequestDto;

public interface ProductRepository {

    void save(Product product);

    Product findById(String id);

    Product update(Product productToEdit, ProductRequestDto editedProduct);
}
