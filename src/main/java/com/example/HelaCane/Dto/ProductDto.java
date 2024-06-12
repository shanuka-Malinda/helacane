package com.example.HelaCane.Dto;

import com.example.HelaCane.Constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String imgUrl;
    private String quantity;
    private String unitPrice;
    private CommonStatus commonStatus;
}
