package com.example.demo1;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductDTO {

    @NotNull(message = "Please input your product name")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Please input only A-Z, a-z")
    private String name;

    @NotNull(message = "Please input a price")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
