package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.ProductSummaryDTO;
import com.htdinh.bookstore.dto.response.CategoryResponse;
import com.htdinh.bookstore.mapper.CategoryMapper;
import com.htdinh.bookstore.model.Category;
import com.htdinh.bookstore.repository.CategoryRepository;
import com.htdinh.bookstore.service.CategoryService;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductService productService;
    
    @Override
    @Transactional
    public String createCategory(String catName) {
        if ((categoryRepository.findByName(catName)).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        
        categoryRepository.save(Category.builder().name(catName).crtDt(getCurrentTimestamp()).updtDt(getCurrentTimestamp()).build());

        return "Create category successfully";
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> getAllCategoryHierarchy() {
        List<Category> categories = categoryRepository.findAllCategoryHierarchy();
        
        Map<Long, CategoryResponse> map = new HashMap<>();
        for(Category category: categories) {
            Long cartId = category.getId();
            List<ProductSummaryDTO> books = productService.getProductsByCategory(cartId, 0, 10).getContent();
            map.put(cartId, CategoryResponse.builder().id(category.getId())
                    .name(category.getName()).slug(category.getSlug()).parentCatId(category.getParentCatId()).children(new ArrayList<>()).books(books).build());
        }
        
        List<CategoryResponse> roots = new ArrayList<>();
        for (CategoryResponse dto: map.values()) {
            if (dto.getParentCatId() == null) {
                roots.add(dto);
            } else {
                CategoryResponse parent = map.get(dto.getParentCatId());
                if (parent != null) {
                    parent.getChildren().add(dto);
                }
            }
        }
        return roots;
    }
    
    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
