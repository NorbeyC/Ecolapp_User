package com.ecolapp.user.service;


import com.ecolapp.user.repository.User;
import com.ecolapp.user.repository.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserDto userDto);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> all();

    void deleteById(String id);


    User update(UserDto userDto, String userId);

}
