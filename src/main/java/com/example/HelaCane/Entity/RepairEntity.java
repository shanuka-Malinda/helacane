package com.example.HelaCane.Entity;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Constant.OrderStatus;
import com.example.HelaCane.Constant.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair")
public class RepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private String qty;

    @Column
    private String imgUrl;

    @Enumerated
    private OrderType orderType;

    @Enumerated
    private OrderStatus orderStatus;

    @Enumerated
    private CommonStatus commonStatus;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserEntity userEntity;
}
