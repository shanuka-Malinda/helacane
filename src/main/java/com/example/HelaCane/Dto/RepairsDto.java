package com.example.HelaCane.Dto;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Constant.OrderStatus;
import com.example.HelaCane.Constant.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairsDto {
    private String id;
    private String description;
    private String qty;
    private String imgUrl;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private CommonStatus commonStatus;
    private UserDto userDto;
}
