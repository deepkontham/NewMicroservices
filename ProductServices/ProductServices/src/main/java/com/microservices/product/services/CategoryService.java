package com.microservices.product.services;

import com.microservices.product.dtos.CategoryDto;
import com.microservices.product.dtos.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);
}
