package com.example.HelaCane.Entity;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Util.CommonResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imgUrl;

    @Column
    private Integer quantity;

    @Column
    private Double unitPrice;

    @Enumerated
    private CommonStatus commonStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<CartEntity> Cart = new HashSet<>();
}
