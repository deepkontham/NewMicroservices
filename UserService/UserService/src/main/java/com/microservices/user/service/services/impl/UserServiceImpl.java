package com.microservices.user.service.services.impl;

import com.microservices.user.service.dtos.PageableResponse;
import com.microservices.user.service.dtos.UserDto;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.helper.Helper;
import com.microservices.user.service.repositories.UserRepository;
import com.microservices.user.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${user.profile.image.path}")
    private String imagePath;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        //generate unique id
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        // dto-->entity
        User user=dtoToEntity(userDto);
        User savedUser=userRepository.save(user);

        //entity -->user
        UserDto newDto=entityToDto(savedUser);
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setImageName(userDto.getImageName());

        //save data
        User updatedUser=userRepository.save(user);
        UserDto updatedDto=entityToDto(updatedUser);

        return updatedDto;

    }

    @Override
    public void deleteUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given Id"));
        //delete user profile image
        String fullPath=imagePath + user.getImageName();
        try
        {
            Path path= Paths.get(fullPath);
            Files.delete(path);
        }
        catch(NoSuchFileException ex)
        {
            logger.info("User Image not found in folder ");
            ex.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //delete the user
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize , String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable= PageRequest.of(pageNumber, pageSize,sort);
        Page<User> page=userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given Id"));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with given EmailId"));

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users=userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList=users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }
    private UserDto entityToDto(User savedUser) {
        // TODO Auto-generated method stub

//		UserDto userDto=UserDto.builder()
//			.userId(savedUser.getUserId())
//			.name(savedUser.getName())
//			.email(savedUser.getEmail())
//			.password(savedUser.getPassword())
//                .phone(savedUser.getPhone())
//			.about(savedUser.getAbout())
//			.gender(savedUser.getGender())
//			.imageName(savedUser.getImageName())
//			.build();
//        return userDto;
        return modelMapper.map(savedUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {

//		User user=User.builder()
//			.userId((String) userDto.getUserId())
//			.name((String) userDto.getName())
//			.email((String) userDto.getEmail())
//			.password((String) userDto.getPassword())
//                .phone((String) userDto.getPhone())
//			.about((String) userDto.getAbout())
//			.gender(userDto.getGender())
//			.imageName(userDto.getImageName())
//			.build();
//        return user;
        return modelMapper.map(userDto,User.class);
    }
}
