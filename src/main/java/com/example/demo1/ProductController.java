package com.example.demo1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String getProduct(Model model) {
        List<ProductEntity> productEntity = productRepository.findAll();
        model.addAttribute("products", productEntity);
        model.addAttribute("content", "fragments/list_product");
        return "index";
    }

    @GetMapping("/formProduct")
    public String getForm(@ModelAttribute ProductDTO productDTO, Model model) {
        model.addAttribute("product", productDTO);
        model.addAttribute("content", "fragments/form");
        return "index";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(
            @Valid @ModelAttribute("product") ProductDTO productDTO,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("content", "fragments/form");
            return "index";
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());

        productRepository.save(productEntity);
        return "redirect:/";
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }


}
