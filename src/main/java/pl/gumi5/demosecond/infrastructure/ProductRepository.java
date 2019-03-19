package pl.gumi5.demosecond.infrastructure;

import pl.gumi5.demosecond.domain.Product;

public interface ProductRepository {

    void save(Product product);

}
