package com.ecom.repository;

import com.ecom.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
   List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
