package com.imrzoom.desafioanotaai.controllers;

import com.imrzoom.desafioanotaai.domain.product.Product;
import com.imrzoom.desafioanotaai.domain.product.dto.ProductDTO;
import com.imrzoom.desafioanotaai.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Endpoint for managing your Product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all products!", description = "List all products!",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public ResponseEntity<List<Product>> findAll(){
        List<Product> newProduct = this.service.findAll();
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "id",summary = "Finds a product!", description = "Finds a product!",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Optional<Product>> findById(@PathVariable String id){
        Optional<Product> newProduct = this.service.findById(id);
        return ResponseEntity.ok().body(newProduct);
    }

    @PostMapping
    @Operation(summary = "Creates a product!", description = "Creates a product!",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        Product newProduct = this.service.create(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    @Operation(operationId = "id",summary = "Updates a product!", description = "Updates a product!",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = this.service.update(id, productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "id",summary = "Deletes a product!", description = "Deletes a product!",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
