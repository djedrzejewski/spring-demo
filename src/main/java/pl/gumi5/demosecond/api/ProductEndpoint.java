package pl.gumi5.demosecond.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.gumi5.demosecond.domain.ProductFacade;
import pl.gumi5.demosecond.domain.ProductRequestDto;
import pl.gumi5.demosecond.domain.ProductResponseDto;

@RestController
@RequestMapping("/products")
class ProductEndpoint {

    private final ProductFacade productFacade;

    @Autowired
    ProductEndpoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/{id}")
    ProductResponseDto getProduct(@PathVariable("id") String id){
        return productFacade.findById(id);
    }

    @PostMapping
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        return productFacade.create(productRequestDto);
    }

    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable("id") String id, @RequestBody ProductRequestDto productRequestDto){
        productFacade.findById(id);
        return productFacade.update(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    ProductResponseDto deleteProduct(@PathVariable("id") String id){
        productFacade.findById(id);
        return productFacade.delete(id);
    }
}
