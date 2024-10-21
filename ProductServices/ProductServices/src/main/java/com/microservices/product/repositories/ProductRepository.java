package com.microservices.product.repositories;

import com.microservices.product.entities.Category;
import com.microservices.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    //search
    Page<Product>  findByTitleContaining(String subtitle, Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);

}
