package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProductCartResponse {

    ProductDto product;
    @NotNull
    private Long quantity;

    /**
     * DTO for {@link com.htdinh.bookstore.model.Product}
     */
    @Value
    public static class ProductDto implements Serializable {
        BigDecimal id;
        @Size(max = 50)
        String name;
        String description;
        @Size(max = 50)
        String regularPrice;
        @Size(max = 50)
        String salePrice;
        Set<ProductImageDto> productImages;

        /**
         * DTO for {@link com.htdinh.bookstore.model.ProductImage}
         */
        @Value
        public static class ProductImageDto implements Serializable {
            ImageDto image;

            /**
             * DTO for {@link com.htdinh.bookstore.model.Image}
             */
            @Value
            public static class ImageDto implements Serializable {
                Long id;
                @Size(max = 128)
                String title;
                String url;
                @Size(max = 128)
                String altText;
            }
        }
    }
}
