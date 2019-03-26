package pl.gumi5.demosecond.domain;

public interface ProductFacade {
    //get

    ProductResponseDto findById(String id);

    //create

    ProductResponseDto create(ProductRequestDto productRequest);

    //update
    //delete
}
