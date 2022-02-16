package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Costomer;

@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository  userRepository;


    //get all user
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //create a rest user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("usuario no existe: " +id));
        return ResponseEntity.ok(user);

    }


    //update user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("usuario no existe: "+id));

        user.setNombre(userDetails.getNombre());
        user.setEmail(userDetails.getEmail());
        user.setDireccion(userDetails.getDireccion());
        user.setTelefono(userDetails.getTelefono());
        user.setPassword(userDetails.getPassword());

        User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }


    //DELETE user REST API
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser( @PathVariable Long id){

        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("usuario no existe: "+id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put(" user deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}