package com.example.HelaCane.Dto;

import com.example.HelaCane.Constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String id;
    private String userId;
    private Integer qty;
    private CommonStatus commonStatus;
    private List<Long> productIds;
}
