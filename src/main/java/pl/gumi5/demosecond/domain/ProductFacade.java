package pl.gumi5.demosecond.domain;

public interface ProductFacade {

    ProductsListResponseDto findAll();
    ProductResponseDto findById(String id);
    ProductResponseDto create(ProductRequestDto productRequest);
    ProductResponseDto update(String id, ProductRequestDto productRequestDto);
    ProductResponseDto delete(String id);
}
