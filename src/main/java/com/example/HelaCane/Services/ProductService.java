package com.example.HelaCane.Services;

import com.example.HelaCane.Dto.ProductDto;
import com.example.HelaCane.Entity.ProductEntity;
import com.example.HelaCane.Util.CommonResponse;

import java.util.List;

public interface ProductService {
    CommonResponse addProduct(ProductDto productDto);
    ProductEntity findById(List<Long> id);
}
