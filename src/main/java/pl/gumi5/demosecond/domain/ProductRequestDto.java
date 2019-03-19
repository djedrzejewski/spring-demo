package pl.gumi5.demosecond.domain;

public class ProductRequestDto {

    private final String name;

    public ProductRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isValid(){
        return name != null && name.isBlank();
    }
    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
