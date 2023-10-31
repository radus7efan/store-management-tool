package com.demo.smt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "items")
@AllArgsConstructor
public class ItemEntity {

    @Id
    @NotNull
    private String id;

    @NotNull
    private String title;

    @NotNull
    private Float price;

    @NotNull
    private Integer quantity;

    private StockStatus stockStatus;

    private Integer discount;

    private Float discountedPrice;

    private String description;
}
