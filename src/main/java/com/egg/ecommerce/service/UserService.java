package com.egg.ecommerce.service;


import com.egg.ecommerce.DTOs.UserDataResponse;
import com.egg.ecommerce.entity.Address;
import com.egg.ecommerce.entity.User;
import com.egg.ecommerce.repository.AddressRepository;
import com.egg.ecommerce.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ObjectMapper mapper;

    public void createAddress(Long id, Address address){
        User user = userRepository.findById(id).orElseThrow();

        address.setUser(user);

        addressRepository.save(address);

    }

    public List<UserDataResponse> getAllUsers(){
        List<User> userList = userRepository.findAll();

        List<UserDataResponse> userDataResponseList = new ArrayList<>();

        for(User user:userList){
            UserDataResponse userData;
            userData = mapper.convertValue(user,UserDataResponse.class);
            userDataResponseList.add(userData);
        }

        return userDataResponseList;
    }

    public void deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            userRepository.deleteById(id);
        }

        throw new RuntimeException("User not found");
    }

}
