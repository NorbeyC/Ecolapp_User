package com.ecolapp.user.controller;

import com.ecolapp.user.exception.UserNotFoundException;
import com.ecolapp.user.repository.User;
import com.ecolapp.user.repository.UserDto;
import com.ecolapp.user.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {


    @Autowired private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        var savedUser = userService.save(userDto);
        URI createdUserUri = URI.create("/v1/users/" + savedUser.getId());
        return ResponseEntity.created(createdUserUri).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        User updatedUser = userService.update(userDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    //@RolesAllowed(ADMIN_ROLE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
