package com.htdinh.bookstore.dto.response;

import com.htdinh.bookstore.model.ProductVariantId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private BigDecimal id;
    private String name;
    private UserDto user;
    private String description;
    private Long stock;
    private Long rating;
    @Size(max = 50)
    private String isDraft;
    @Size(max = 50)
    private String isPublish;
    @Size(max = 50)
    private String price;
    @Size(max = 50)
    private String priceMin;
    @Size(max = 50)
    private String priceMax;
    private BigDecimal crtId;
    @Size(max = 128)
    private String crtDt;
    private BigDecimal updtId;
    @Size(max = 128)
    private String updtDt;
    private Set<ProductAttributeDto> productAttributes = new LinkedHashSet<>();
    private Set<ProductCategoryDto> productCategories = new LinkedHashSet<>();
    private Set<ProductImageDto> productImages = new LinkedHashSet<>();
    private Set<ProductVariantDto> productVariants = new LinkedHashSet<>();

    /**
     * DTO for {@link com.htdinh.bookstore.model.User}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto implements Serializable {
        private BigDecimal id;
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
        @Size(max = 50)
        private String name;
        @NotNull
        @Size(max = 50)
        private String value;
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductCategory}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductCategoryDto implements Serializable {
        private ProductCategoryIdDto id;

        /**
         * DTO for {@link com.htdinh.bookstore.model.ProductCategoryId}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ProductCategoryIdDto implements Serializable {
            @NotNull
            private BigDecimal productCategoryId;
            @NotNull
            @Size(max = 50)
            private String name;
        }
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductImage}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductImageDto implements Serializable {
        private Long id;
        @NotNull
        private String imageUrl;
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
        @Size(max = 1)
        private String isPublish;
    }
}
