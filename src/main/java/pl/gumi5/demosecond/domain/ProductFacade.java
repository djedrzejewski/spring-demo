package pl.gumi5.demosecond.domain;

public interface ProductFacade {

    ProductResponseDto findById(String id);
    ProductResponseDto create(ProductRequestDto productRequest);
    ProductResponseDto update(String id, ProductRequestDto productRequestDto);
    //delete
}
