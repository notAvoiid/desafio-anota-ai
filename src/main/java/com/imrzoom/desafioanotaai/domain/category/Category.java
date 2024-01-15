package com.imrzoom.desafioanotaai.domain.category;

import com.imrzoom.desafioanotaai.domain.category.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category(CategoryDTO categoryDTO) {
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerId = categoryDTO.ownerID();
    }
}
