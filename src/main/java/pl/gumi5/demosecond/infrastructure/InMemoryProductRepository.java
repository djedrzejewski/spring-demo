package pl.gumi5.demosecond.infrastructure;

import org.springframework.stereotype.Repository;
import pl.gumi5.demosecond.domain.Product;
import pl.gumi5.demosecond.domain.ProductRequestDto;
import pl.gumi5.demosecond.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Repository
class InMemoryProductRepository implements ProductRepository {

    private  final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(String id) {
        if(products.containsKey(id)){
            return products.get(id);
        }else{
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product update(Product productToEdit, ProductRequestDto editedProduct){

        if(products.containsKey(productToEdit.getId())){
            Product newProduct = new Product(
                    productToEdit.getId(),
                    editedProduct.getName(),
                    productToEdit.getCreatedAt()
            );

            products.replace(productToEdit.getId(), newProduct);
            return newProduct;
        }else{
            throw new ProductNotFoundException();
        }
    }

}
