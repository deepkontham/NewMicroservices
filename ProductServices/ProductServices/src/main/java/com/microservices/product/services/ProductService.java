package com.microservices.product.services;

import com.microservices.product.dtos.PageableResponse;
import com.microservices.product.dtos.ProductDto;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto,String productId);

    //delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);



    //get all: live
    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);


    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);

    //create products with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);

    //update category of products
    ProductDto updateCategory(String productId,String categoryId);

    PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);


}
