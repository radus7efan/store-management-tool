package com.demo.smt.domain.model;

import com.demo.smt.eventlisteners.ProductEventListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(ProductEventListener.class)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer quantity;

    private StockStatusEnum stockStatus;

    private Integer discount;

    private Float discountedPrice;

    private String description;
}
