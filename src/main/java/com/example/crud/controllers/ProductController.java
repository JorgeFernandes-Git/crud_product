package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProduct;
import com.example.crud.domain.product.RequestProductId;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);

    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
//        System.out.println(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> product = repository.findById(data.id());
        if (product.isPresent()) {
            Product newProduct = product.get();
            newProduct.setName(data.name());
            newProduct.setPrice_in_cents(data.price_in_cents());
//            repository.save(newProduct);
            return ResponseEntity.ok(newProduct);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

//    @PutMapping("/{id}")
//    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data) {
//        Product product = repository.getReferenceById(id);
//        product.setName(data.name());
//        product.setPrice_in_cents(data.price_in_cents());
//        return ResponseEntity.ok(product);
//    }

//    @DeleteMapping
//    public ResponseEntity<Void> deleteProduct(@RequestBody @Valid RequestProductId data) {
//        Optional<Product> product = repository.findById(data.id());
//        if (product.isPresent()) {
//            repository.deleteById(data.id());
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteProduct(@PathVariable String id) {
//        repository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            Product newProduct = product.get();
            newProduct.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
