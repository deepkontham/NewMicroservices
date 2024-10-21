package com.microservices.product.services.Impl;

import com.microservices.product.dtos.PageableResponse;
import com.microservices.product.dtos.ProductDto;
import com.microservices.product.entities.Category;
import com.microservices.product.entities.Product;
import com.microservices.product.exception.ResourceNotFoundException;
import com.microservices.product.helper.Helper;
import com.microservices.product.repositories.CategoryRepository;
import com.microservices.product.repositories.ProductRepository;
import com.microservices.product.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
//    private Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        //product Id
        String productId= UUID.randomUUID().toString();
        product.setProductId(productId);

        //added
        product.setAddedDate(new Date());
        Product saveProduct = productRepository.save(product);
        return modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch the product of given Id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given ID"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());

        //save the entity
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given ID"));

//        //delete user profile image
//        String fullPath=imagePath + product.getProductImageName();
//        try
//        {
//            Path path= Paths.get(fullPath);
//            Files.delete(path);
//        }
//        catch(NoSuchFileException ex)
//        {
//            logger.info("User Image not found in folder ");
//            ex.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given ID"));

        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, String categoryId) {

        //fetch the category from database
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Product product = modelMapper.map(productDto, Product.class);

        //product Id
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);

        //added
        product.setAddedDate(new Date());
        product.setCategory(category);
        Product saveProduct = productRepository.save(product);
        return modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto updateCategory(String productId, String categoryId) {
        //product fetch
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product of given id is not found !!"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category of given id is not found"));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);


        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllOfCategory(String categoryId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category of given id is not found"));
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());

        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page=productRepository.findByCategory(category,pageable);

        return Helper.getPageableResponse(page,ProductDto.class);
    }
}
