package pl.gumi5.demosecond.domain;

import org.springframework.stereotype.Component;
import pl.gumi5.demosecond.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
class ProductFacadeImpl implements ProductFacade {

    private final ProductRepository productRepository;

    public ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductsListResponseDto findAll() {
        ArrayList<Product> products = productRepository.findAll();
        ArrayList<ProductResponseDto> productsToReturn = new ArrayList<ProductResponseDto>();
        for (Product product: products) {
            productsToReturn.add(new ProductResponseDto(product.getId(), product.getName()));
        }
        return new ProductsListResponseDto(productsToReturn);
    }

    @Override
    public ProductResponseDto findById(String id){
        Product product = productRepository.findById(id);
        return new ProductResponseDto(product.getId(), product.getName());
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {

        if(!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createdAt);

        productRepository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequestDto) {
        if(!productRequestDto.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        Product product = productRepository.findById(id);
        Product updated = productRepository.update(product, productRequestDto);

        return new ProductResponseDto(
                updated.getId(),
                updated.getName()
        );
    }

    @Override
    public ProductResponseDto delete(String id) {
        Product product = productRepository.findById(id);
        productRepository.delete(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }


}
