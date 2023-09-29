package com.example.crud.controllers;

import com.example.crud.domain.user.RequestUser;
import com.example.crud.domain.user.User;
import com.example.crud.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var allUsers = repository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Valid RequestUser data) {
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<User> updateLocation(@RequestBody String id, String newLocation) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            User newUser = user.get();
            newUser.setLocation(newLocation);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody String id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
