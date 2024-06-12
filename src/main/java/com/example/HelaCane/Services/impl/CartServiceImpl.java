package com.example.HelaCane.Services.impl;


import com.example.HelaCane.Constant.CommonMsg;
import com.example.HelaCane.Dto.CartDto;
import com.example.HelaCane.Entity.CartEntity;
import com.example.HelaCane.Entity.ProductEntity;
import com.example.HelaCane.Repository.CartRepo;
import com.example.HelaCane.Services.CartService;
import com.example.HelaCane.Services.ProductService;
import com.example.HelaCane.Services.UserService;
import com.example.HelaCane.Util.CommonResponse;
import com.example.HelaCane.Util.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class CartServiceImpl implements CartService {
   private final CartRepo cartRepo;
   private final ProductService productService;
   private final UserService userService;


   @Autowired
    public CartServiceImpl(CartRepo cartRepo, ProductService productService, UserService userService) {
       this.cartRepo = cartRepo;
       this.productService = productService;
       this.userService = userService;
   }


   @Override
   public CommonResponse addCart(CartDto cartDto) {
      CommonResponse commonResponse=new CommonResponse();
      CartEntity cartEntity;
      try {
         List<String> validationList=this.CartValidation(cartDto);
         if (!validationList.isEmpty()) {
            commonResponse.setErrorMessages(validationList);
            return commonResponse;
         }
         cartEntity=castCartDtoToEntity(cartDto);
         cartEntity=cartRepo.save(cartEntity);
         commonResponse.setStatus(true);
         commonResponse.setPayload(Collections.singletonList(cartEntity));
      }catch (Exception e){
         LOGGER.error("/**************** Exception in CartService -> addToCart()", e);
         commonResponse.setStatus(false);
         commonResponse.setErrorMessages(Collections.singletonList("An error occurred while adding product to cart."));
      }
      return commonResponse;
   }

   private CartEntity castCartDtoToEntity(CartDto cartDto) {
      CartEntity cartEntity=new CartEntity();
      cartEntity.setUser(userService.findById(Long.valueOf(cartDto.getUserId())));
      cartEntity.setProduct(productService.findById(cartDto.getProductIds()));


      return cartEntity;
   }

   private List<String> CartValidation(CartDto cartDto) {
      List<String> validationList = new ArrayList<>();
      if (CommonValidation.stringNullValidation(cartDto.getUserId())){
         validationList.add(CommonMsg.EMPTY_USERID);
      }
      return validationList;
   }
}
