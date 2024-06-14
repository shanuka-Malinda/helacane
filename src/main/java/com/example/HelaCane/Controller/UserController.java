package com.example.HelaCane.Controller;

import com.example.HelaCane.Dto.UserDto;
import com.example.HelaCane.Entity.UserEntity;
import com.example.HelaCane.Services.UserService;
import com.example.HelaCane.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerNewUser")
    public CommonResponse registerNewUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }
    @GetMapping("/getAllUser")
    public CommonResponse getAllUser(){
        return userService.getAllUser();
    }
    @PutMapping("/updateUser")
    public CommonResponse updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        boolean exists = userService.userNameExists(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/findUserById")
    public UserEntity findUserByID(@RequestParam Long id){
        return userService.findById(id);
    }

    @PostMapping("deleteUserAccount")
    public CommonResponse deleteUser(@RequestBody UserDto userDto){
        return userService.deleteUser(userDto);
    }
}
