package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.ProductRequest;
import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.exception.BusinessValidationException;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.ProductMapper;
import com.htdinh.bookstore.model.*;
import com.htdinh.bookstore.repository.*;
import com.htdinh.bookstore.service.ProductService;
import com.htdinh.bookstore.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    

    @Override
    public Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed) {
        User user = AuthUtils.getCurrentUser();
        return productRepository.findAllById(user.getId(), seed, PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public Page<ProductResponse> getProductsByName(String searchKey, int pageNumber, int pageSize) {
        return productRepository.findAllByName(searchKey, PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    public void publishProductByShop(Long productId) {
        User user = AuthUtils.getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new BusinessValidationException("Product does not belong to the shop with id: " + user.getId());
        }

        product.setIsPublish(true);
        productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> getAllPublishForShop(int pageNumber, int pageSize) {
        User user = AuthUtils.getCurrentUser();
        return productRepository.findAllPublishProduct(user.getId(), PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    public void draftProductByShop(Long productId) {
        User user = AuthUtils.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Product does not belong to the shop with id: " + user.getId());
        }

        product.setIsPublish(false);
        productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> getAllDraftForShop(int pageNumber, int pageSize) {
        User user = AuthUtils.getCurrentUser();
        return productRepository.findAllDraftProduct(user.getId(), PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    @Transactional
    public String createProduct(ProductRequest request) {
        User user = AuthUtils.getCurrentUser();

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setIsPublish(true);
        product.setRegularPrice(request.getRegularPrice());
        product.setSalePrice(request.getSalePrice());
        product.setCrtDt(getCurrentTimestamp());
        product.setUpdtDt(getCurrentTimestamp());
        product.setUser(user);
        productRepository.save(product);

        List<Long> categoryIds = request.getCategoryIds();
        categoryIds.forEach(categoryId -> {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
            ProductCategory productCategory = new ProductCategory();
           
            productCategory.setCat(category);
            productCategory.setProduct(product);
            productCategory.setCrtDt(getCurrentTimestamp());
            productCategory.setUpdtDt(getCurrentTimestamp());

            productCategoryRepository.save(productCategory);
        });

        List<Long> attributeIds = Optional.ofNullable(request.getAttributeIds()).orElse(Collections.EMPTY_LIST);
        if (!attributeIds.isEmpty()) {
            attributeIds.forEach(attributeId -> {
                Attribute attribute = attributeRepository.findById(attributeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Attribute not found with id: " + attributeId));
                ProductAttribute productAttribute = new ProductAttribute();
    
                productAttribute.setAttribute(attribute);
                productAttribute.setProduct(product);
                productAttribute.setCrtDt(getCurrentTimestamp());
                productAttribute.setUpdtDt(getCurrentTimestamp());
    
                productAttributeRepository.save(productAttribute);
            });
        }
        return "Create product successfully";
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }


    @Override
    public Page<ProductResponse> getProductsByCategory(Long catId, int pageNumber, int pageSize) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + catId));
        
        List<ProductCategory> productCategoryList = productCategoryRepository.findProductCategoriesByCat((category));
        
        List<Product> products = new ArrayList<>();
        
        if (!productCategoryList.isEmpty()) {
            productCategoryList.forEach((item) -> {
                products.add(item.getProduct());
            });   
        }
        
        List<ProductResponse> productResponses = products.stream().map(productMapper::toProductResponse).collect(Collectors.toList());
        Pageable pageRequest = createPageRequestUsing(pageNumber, pageSize);
        
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), productResponses.size());
        
        List<ProductResponse> pageContent = productResponses.subList(start, end);
        
        return new PageImpl<>(pageContent, pageRequest, productResponses.size());
    }


    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }
}
