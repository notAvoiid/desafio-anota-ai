package com.imrzoom.desafioanotaai.services;

import com.imrzoom.desafioanotaai.domain.category.Category;
import com.imrzoom.desafioanotaai.domain.product.Product;
import com.imrzoom.desafioanotaai.domain.product.dto.ProductDTO;
import com.imrzoom.desafioanotaai.exceptions.CategoryNotFoundException;
import com.imrzoom.desafioanotaai.exceptions.ProductNotFoundException;
import com.imrzoom.desafioanotaai.repositories.ProductRepository;
import com.imrzoom.desafioanotaai.services.aws.AwsSnsService;
import com.imrzoom.desafioanotaai.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final AwsSnsService awsSnsService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService awsSnsService) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.awsSnsService = awsSnsService;
    }

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return this.productRepository.findById(id);
    }

    public Product create(ProductDTO productDTO) {
        this.categoryService.findById(productDTO.categoryId()).orElseThrow(
                () -> new CategoryNotFoundException(String.format("%s not found!", productDTO.categoryId()))
        );

        Product newProduct = new Product(productDTO);

        this.productRepository.save(newProduct);
        this.awsSnsService.publish(new MessageDTO(newProduct.toString()));

        return newProduct;
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (productDTO.categoryId() != null) {
            this.categoryService.findById(productDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);
            product.setCategory(productDTO.categoryId());
        }

        if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if (!(productDTO.price() == null)) product.setPrice(productDTO.price());

        this.productRepository.save(product);
        this.awsSnsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id) {
        Product category = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(category);

    }

}
