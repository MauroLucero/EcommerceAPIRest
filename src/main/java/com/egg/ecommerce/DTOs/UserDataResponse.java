package com.egg.ecommerce.DTOs;

import com.egg.ecommerce.entity.Address;

import java.util.List;

public class UserDataResponse {

    String username;
    String firstName;
    String lastName;
    String email;
    List<Address> addressList;

}
