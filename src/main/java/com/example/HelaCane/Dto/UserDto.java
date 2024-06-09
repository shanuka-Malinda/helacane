package com.example.HelaCane.Dto;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Constant.OrderType;
import com.example.HelaCane.Constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String userName;
    private String email;
    private String tel;
    private String regDate;
    private String password;
    private Role role;
    private CommonStatus commonStatus;
}
