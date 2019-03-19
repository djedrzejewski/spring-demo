package pl.gumi5.demosecond.infrastructure;

import org.springframework.stereotype.Repository;
import pl.gumi5.demosecond.domain.Product;

import java.util.HashMap;
import java.util.Map;

@Repository
class InMemoryProductRepository implements ProductRepository {

    private  final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }
}
