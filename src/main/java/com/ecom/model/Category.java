package com.ecom.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Getter
@Setter
public class Category {
    @Id
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product> products;
}
