package com.imrzoom.desafioanotaai.controllers;

import com.imrzoom.desafioanotaai.domain.product.Product;
import com.imrzoom.desafioanotaai.domain.product.dto.ProductDTO;
import com.imrzoom.desafioanotaai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> newProduct = this.service.findAll();
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable String id){
        Optional<Product> newProduct = this.service.findById(id);
        return ResponseEntity.ok().body(newProduct);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        Product newProduct = this.service.create(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = this.service.update(id, productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
