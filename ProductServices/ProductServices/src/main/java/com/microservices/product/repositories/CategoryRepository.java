package com.microservices.product.repositories;

import com.microservices.product.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
