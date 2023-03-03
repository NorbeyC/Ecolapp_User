package com.ecolapp.user.service;

import com.ecolapp.user.exception.UserNotFoundException;
import com.ecolapp.user.repository.UserRepository;
import com.ecolapp.user.repository.User;
import com.ecolapp.user.repository.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDB implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User save(UserDto userDto) {
        return userRepository.save(new User(userDto));
    }


    @Override
    public User update(UserDto userDto, String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException(userId);
        User user = optionalUser.get();
        user.update(userDto);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

}
