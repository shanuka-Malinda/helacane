package com.example.HelaCane.Controller;

import com.example.HelaCane.Dto.CartDto;
import com.example.HelaCane.Services.CartService;
import com.example.HelaCane.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
 private  final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addCart")
    public CommonResponse addCart(@RequestBody CartDto cartDto){
        return cartService.addCart(cartDto);
    }
}
