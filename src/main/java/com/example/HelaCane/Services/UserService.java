package com.example.HelaCane.Services;

import com.example.HelaCane.Dto.UserDto;
import com.example.HelaCane.Entity.UserEntity;
import com.example.HelaCane.Util.CommonResponse;

public interface UserService {
    CommonResponse registerUser(UserDto userDto);

    CommonResponse getAllUser();

    boolean userNameExists(String username);

    boolean emailExits(String email);

    CommonResponse updateUser(UserDto userDto);

    UserEntity findById(Long id);
    UserDto findByIdForGet(Long id);

    CommonResponse deleteUser(UserDto userDto);
}
