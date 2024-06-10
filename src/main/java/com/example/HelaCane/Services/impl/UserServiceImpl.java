package com.example.HelaCane.Services.impl;

import com.example.HelaCane.Constant.CommonMsg;
import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Dto.UserDto;
import com.example.HelaCane.Entity.UserEntity;
import com.example.HelaCane.Repository.UserRepo;
import com.example.HelaCane.Services.UserService;
import com.example.HelaCane.Util.CommonResponse;
import com.example.HelaCane.Util.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public CommonResponse registerUser(UserDto userDto) {
        CommonResponse commonResponse=new CommonResponse();
        UserEntity userEntity;
        try {
            List<String> validationList=this.UserValidation(userDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
            userEntity=castUserDtoToEntity(userDto);
            userEntity=userRepo.save(userEntity);
            commonResponse.setStatus(true);
            commonResponse.setPayload(Collections.singletonList(userEntity));
        }catch (Exception e){
            LOGGER.error("/**************** Exception in UserService -> RegisterNewUser()", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while registering user."));
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getAllUser() {
        CommonResponse commonResponse=new CommonResponse();
        try {
            List<Object> userList=userRepo.findAll().stream()
                    .filter(userEntity -> userEntity.getCommonStatus()!= CommonStatus.DELETE)
                    .map(this::castUserEntityToDto)
                    .collect(Collectors.toList());
        commonResponse.setStatus(true);
        commonResponse.setPayload(userList);
        }catch (Exception e){
            LOGGER.error("/**************** Exception in UserService -> getAllUsers()", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while fetching Users."));
        }
        return commonResponse;
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepo.existsByUserName(username);
    }

    @Override
    public CommonResponse updateUser(UserDto userDto) {
        CommonResponse commonResponse=new CommonResponse();
        UserEntity userEntity;
        try{
            List<String> validationList=this.UserValidation(userDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
           userEntity=userRepo.findById(Long.valueOf(userDto.getId())).get();
          if(userEntity!=null){
             userEntity.setUserName(userDto.getUserName());
             userEntity.setEmail(userDto.getEmail());
             userEntity.setTel(userDto.getTel());
             userEntity.setPassword(userDto.getPassword());
             userRepo.save(userEntity);
          }else {
            commonResponse.setErrorMessages(Collections.singletonList("Profile not found"));
          }
        }catch (Exception e){
            LOGGER.error("/**************** Exception in UserService -> updateUser()", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while updating the user."));
        }
        return commonResponse;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity userEntity=userRepo.findById(id).orElse(null);
        return userEntity;
    }
    @Override
    public UserDto findByIdForGet(Long id) {
        UserEntity userEntity=userRepo.findById(id).orElse(null);
        return castUserEntityToDto(userEntity);
    }

     private String getCurrentDate(){
        return LocalDate.now().toString();
     }
    public UserDto castUserEntityToDto(UserEntity userEntity) {
        UserDto userDto=new UserDto();
        userDto.setId(userEntity.getId().toString());
        userDto.setUserName(userEntity.getUserName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setTel(userEntity.getTel());
        userDto.setRole(userEntity.getRole());
        userDto.setRegDate(userEntity.getRegDate());
        userDto.setCommonStatus(userEntity.getCommonStatus());
        return userDto;
    }


    private UserEntity castUserDtoToEntity(UserDto userDto) {
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setRole(userDto.getRole());
        userEntity.setTel(userDto.getTel());
        userEntity.setRegDate(LocalDate.now().toString());
        userEntity.setPassword(getEncodedPassword(userDto.getPassword()));
        userEntity.setCommonStatus(userDto.getCommonStatus());

        return userEntity;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private List<String> UserValidation(UserDto userDto) {
        List<String> validationList = new ArrayList<>();
          if (CommonValidation.stringNullValidation(userDto.getUserName())){
              validationList.add(CommonMsg.EMPTY_USERNAME);
          }
        if (CommonValidation.stringNullValidation(userDto.getEmail())){
            validationList.add(CommonMsg.EMPTY_EMAIL);
        }
        if (CommonValidation.stringNullValidation(userDto.getPassword())){
            validationList.add(CommonMsg.EMPTY_PASSWORD);
        }
        if (CommonValidation.stringNullValidation(userDto.getRegDate())){
            validationList.add(CommonMsg.EMPTY_DATE);
        }
        if (CommonValidation.stringNullValidation(userDto.getTel())){
            validationList.add(CommonMsg.EMPTY_USERNAME);
        }
        return validationList;
    }
}
