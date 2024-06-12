package com.example.HelaCane.Services.impl;

import com.example.HelaCane.Constant.CommonMsg;
import com.example.HelaCane.Dto.ProductDto;
import com.example.HelaCane.Entity.ProductEntity;
import com.example.HelaCane.Repository.ProductRepo;
import com.example.HelaCane.Services.ProductService;
import com.example.HelaCane.Util.CommonResponse;
import com.example.HelaCane.Util.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public CommonResponse addProduct(ProductDto productDto) {
        CommonResponse commonResponse=new CommonResponse();
        ProductEntity productEntity;
        try {
            List<String> validationList=this.ProductValidation(productDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
            productEntity=castProductDtoToEntity(productDto);
            productEntity=productRepo.save(productEntity);
            commonResponse.setStatus(true);
            commonResponse.setPayload(Collections.singletonList(productEntity));
        }catch (Exception e){
            LOGGER.error("/**************** Exception in ProductService -> addNewProduct()", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while adding new product."));
        }
        return commonResponse;
    }

    @Override
    public ProductEntity findById(List<Long> id) {
//        ProductEntity productEntity= (ProductEntity) productRepo.findAllById(id);
//        return castProductEntityToDto(productEntity);
        return (ProductEntity) productRepo.findAllById(id);
    }

    public ProductDto castProductEntityToDto(ProductEntity productEntity) {
        ProductDto productDto=new ProductDto();
        productDto.setId(String.valueOf(productEntity.getId()));
        productDto.setName(productEntity.getName());
        productDto.setDescription(productEntity.getDescription());
        productDto.setQuantity(String.valueOf(productEntity.getQuantity()));
        productDto.setImgUrl(productEntity.getImgUrl());
        productDto.setCommonStatus(productEntity.getCommonStatus());
        productDto.setUnitPrice(String.valueOf(productEntity.getUnitPrice()));

        return productDto;
    }
    private ProductEntity castProductDtoToEntity(ProductDto productDto) {
        ProductEntity productEntity=new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setImgUrl(productDto.getImgUrl());
        productEntity.setQuantity(Integer.valueOf(productDto.getQuantity()));
        productEntity.setUnitPrice(Double.valueOf(productDto.getUnitPrice()));
        productEntity.setCommonStatus(productDto.getCommonStatus());

        return productEntity;
    }

    private List<String> ProductValidation(ProductDto productDto) {
        List<String> validationList = new ArrayList<>();
        if (CommonValidation.stringNullValidation(productDto.getName())){
            validationList.add(CommonMsg.EMPTY_NAME);
        }
        if (CommonValidation.stringNullValidation(productDto.getDescription())){
            validationList.add(CommonMsg.EMPTY_PRODUCT_DESCRIPTION);
        }
        if (CommonValidation.stringNullValidation(productDto.getUnitPrice())){
            validationList.add(CommonMsg.EMPTY_PRODUCT_PRICE);
        }

        return validationList;
    }
}
