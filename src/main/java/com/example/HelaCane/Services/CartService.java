package com.example.HelaCane.Services;

import com.example.HelaCane.Dto.CartDto;
import com.example.HelaCane.Util.CommonResponse;

public interface CartService {
    CommonResponse addCart(CartDto cartDto);
}
