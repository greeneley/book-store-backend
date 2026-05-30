package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.ProductCartResponse;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.ProductCart;
import com.htdinh.bookstore.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductCartMapper {

    @Mapping(source = "product", target = "product")
    ProductCartResponse toProductCartResponse(ProductCart productCart);

    @Mapping(source = "productImages", target = "thumbnailUrl", qualifiedByName = "firstImageUrl")
    @Mapping(source = "regularPrice", target = "regularPrice", numberFormat = "#.##")
    @Mapping(source = "salePrice", target = "salePrice", numberFormat = "#.##")
    ProductCartResponse.ProductDto toProductDto(Product product);

    @Named("firstImageUrl")
    default String firstImageUrl(Set<ProductImage> images) {
        if (images == null || images.isEmpty()) return null;
        return images.stream()
                .filter(pi -> pi.getImage() != null)
                .map(pi -> pi.getImage().getUrl())
                .findFirst()
                .orElse(null);
    }
}
