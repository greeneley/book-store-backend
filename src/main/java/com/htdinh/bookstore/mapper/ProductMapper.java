package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.model.Image;
import com.htdinh.bookstore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.LinkedHashSet;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "thumbnail", expression = "java(findThumbnailImage(product))")
    @Mapping(target = "productImages", expression = "java(filterNonThumbnailImages(product))")
    ProductResponse toProductResponse(Product product);

    ProductResponse.ImageDto imageToImageDto(Image image);

    default ProductResponse.ImageDto findThumbnailImage(Product product) {
        if (product.getProductImages() == null) {
            return null;
        }

        return product.getProductImages().stream().filter((item) -> Boolean.TRUE.equals(item.getImage().getIsThumbnail())).findFirst().map((item) -> this.imageToImageDto(item.getImage())).orElse(null);
    }


    default Set<ProductResponse.ImageDto> filterNonThumbnailImages(Product product) {
        if (product.getProductImages() == null) {
            return Collections.emptySet();
        }
        return product.getProductImages().stream()
                .filter(item -> !Boolean.TRUE.equals(item.getImage().getIsThumbnail()))
                .map((item) -> this.imageToImageDto(item.getImage()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

