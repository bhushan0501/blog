package com.blog.blog.Service.impl;

import com.blog.blog.Dto.UserDto;
import com.blog.blog.Entity.User;
import com.blog.blog.Repository.UserRepository;
import com.blog.blog.Service.UserService;
import com.blog.blog.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDto userDto;
    @Autowired
    private User user;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private ModelMapper modelMapper;

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        User user = this.dtoToUser(userDto);
//        User savedUser = this.userRepository.save(user);
//        return this.userToDto(savedUser);
//    }
@Override
public UserDto createUser(UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    User savedUser = userRepository.save(user);
    return userToDto(savedUser);
}

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // Update the attributes of the existingUser with data from userDto
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());

        // Save the updated entity
        User updatedUser = this.userRepository.save(existingUser);

        return this.userToDto(updatedUser);
    }



    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        // TODO: Implement the get all users logic
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        // TODO: Implement the delete user logic
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
    public UserDto userToDto(User user) {
//        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());
        return userDto;
    }

}