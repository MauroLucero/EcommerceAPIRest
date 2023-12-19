package com.egg.ecommerce.controller;


import com.egg.ecommerce.DTOs.UserDataResponse;
import com.egg.ecommerce.entity.Address;
import com.egg.ecommerce.entity.User;
import com.egg.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/address/{id}")
    public ResponseEntity createAddress(@PathVariable Long id, @RequestBody Address address){

        userService.createAddress(id,address);

        return ResponseEntity.ok("Address created");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDataResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}