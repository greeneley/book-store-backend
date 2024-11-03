package com.htdinh.bookstore.dto.response;

import com.htdinh.bookstore.model.ProductVariantId;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    Long id;
    @Size(max = 50)
    String name;
    String description;
    Long stock;
    Long rating;
    Boolean isPublish;
    BigDecimal crtId;
    @Size(max = 128)
    String crtDt;
    BigDecimal updtId;
    @Size(max = 128)
    String updtDt;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private UserDto user;
    private Set<ProductCategoryDto> productCategories = new LinkedHashSet<>();
    private Set<ProductAttributeDto> productAttributes = new LinkedHashSet<>();
    private Set<ProductVariantDto> productVariants = new LinkedHashSet<>();
    private Set<ProductImageDto> productImages = new LinkedHashSet<>();

    /**
     * DTO for {@link com.htdinh.bookstore.model.User}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto implements Serializable {
        private BigDecimal id;
        @NotNull
        @Size(max = 50)
        private String username;
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductCategory}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductCategoryDto implements Serializable {
        private Long id;
        @NotNull
        private ProductResponse.ProductCategoryDto.CategoryDto cat;

        /**
         * DTO for {@link com.htdinh.bookstore.model.Category}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CategoryDto implements Serializable {
            private Long id;
            @NotNull
            @Size(max = 128)
            private String name;
        }
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductAttribute}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductAttributeDto implements Serializable {
        private Long id;
        @NotNull
        private ProductResponse.ProductAttributeDto.AttributeDto attribute;

        /**
         * DTO for {@link com.htdinh.bookstore.model.Attribute}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class AttributeDto implements Serializable {
            private Long id;
            @Size(max = 50)
            private String name;
        }
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductVariant}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductVariantDto implements Serializable {
        private ProductVariantId id;
        @NotNull
        @Size(max = 50)
        private String price;
        @Size(max = 50)
        private String sku;
        private Long stock;
        @Size(max = 50)
        private String description;
        private Boolean isPublish;
        private BigDecimal crtId;
        @Size(max = 128)
        private String crtDt;
        private BigDecimal updtId;
        @Size(max = 128)
        private String updtDt;
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductImage}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductImageDto implements Serializable {
        private ImageDto image;

        /**
         * DTO for {@link com.htdinh.bookstore.model.Image}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ImageDto implements Serializable {
            private Long id;
            @Size(max = 128)
            private String title;
            private String url;
            @Size(max = 128)
            private String altText;
            @Size(max = 128)
            private String description;
            private BigDecimal crtId;
            @Size(max = 128)
            private String crtDt;
            private BigDecimal updtId;
            @Size(max = 128)
            private String updtDt;
        }
    }
}
