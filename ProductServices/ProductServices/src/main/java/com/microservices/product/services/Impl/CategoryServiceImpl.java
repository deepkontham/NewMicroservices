package com.microservices.product.services.Impl;

import com.microservices.product.dtos.CategoryDto;
import com.microservices.product.dtos.PageableResponse;
import com.microservices.product.entities.Category;
import com.microservices.product.exception.ResourceNotFoundException;
import com.microservices.product.helper.Helper;
import com.microservices.product.repositories.CategoryRepository;
import com.microservices.product.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        //creating category id :randomlly
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category=mapper.map(categoryDto, Category.class);
        Category savedCategory=categoryRepository.save(category);
        return mapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get category of given id

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id !!"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);

        return mapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id !!"));
        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);

        return pageableResponse;

    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given Id !!"));
        return mapper.map(category,CategoryDto.class);
    }
}
