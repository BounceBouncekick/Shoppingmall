package com.example.shopcooperation.dto;


import com.example.shopcooperation.entity.Product;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductDto {
    private Long id;
    private String boardwriter;
    private String name;
    private int price;
    private int stockQuantity;
    private String productname;

    @Builder
    public ProductDto(String boardwriter, String name, int price, int stockQuantity, String productname) {
        this.boardwriter = boardwriter;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productname = productname;
    }

    public static ProductDto toEntity(Product product){
        return ProductDto.builder()
                .boardwriter(product.getBoardwriter())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productname(product.getProductname())
                .build();
    }
}
