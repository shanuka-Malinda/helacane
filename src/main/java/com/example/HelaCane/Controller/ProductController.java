package com.example.HelaCane.Controller;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Dto.ProductDto;
import com.example.HelaCane.Services.ProductService;
import com.example.HelaCane.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductController {
    private  final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public CommonResponse addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
}
