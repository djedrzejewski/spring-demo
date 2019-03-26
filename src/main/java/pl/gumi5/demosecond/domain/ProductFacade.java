package pl.gumi5.demosecond.domain;

public interface ProductFacade {
    //get

    ProductResponseDto get(ProductRequestDto productRequest);

    //create

    ProductResponseDto create(ProductRequestDto productRequest);

    //update
    //delete
}
