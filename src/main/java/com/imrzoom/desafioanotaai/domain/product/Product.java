package com.imrzoom.desafioanotaai.domain.product;

import com.imrzoom.desafioanotaai.domain.category.Category;
import com.imrzoom.desafioanotaai.domain.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "product")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public Product(ProductDTO dto){
        this.title = dto.title();
        this.description = dto.description();
        this.ownerId = dto.ownerId();
        this.price = dto.price();
    }
}
