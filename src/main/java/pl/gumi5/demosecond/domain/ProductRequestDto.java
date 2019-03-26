package pl.gumi5.demosecond.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto {

    private final String name;

    @JsonCreator
    public ProductRequestDto(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isValid(){
        return name != null && !name.isBlank();
    }
    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
