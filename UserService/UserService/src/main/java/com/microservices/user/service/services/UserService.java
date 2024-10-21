package com.microservices.user.service.services;

import com.microservices.user.service.dtos.PageableResponse;
import com.microservices.user.service.dtos.UserDto;

import java.util.List;

public interface UserService {

//    create
    UserDto createUser(UserDto userDto);

//    update
    UserDto updateUser(UserDto userDto,String userId);

//    delete
    void deleteUser(String userId);

//    get all user
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

//    get single user by Id
    UserDto getUserById(String userId);

//    get single user by email
    UserDto getUserByEmail(String email);
//    search user
    List<UserDto> searchUser(String keyword);
}
