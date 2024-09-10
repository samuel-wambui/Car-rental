package com.example.CarRental.Entity;

import com.example.CarRental.service.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    // Create or Update User
    @PostMapping("/signup")
    public ResponseEntity<?> SignUpCustomer(@RequestBody SignUpRequest signUpRequest) {
        if(userService.hasCustomerWiThEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>("customer already exists with the email",HttpStatus.NOT_ACCEPTABLE);
        }
      UserDto createdCustomerDto= userService.createCustomer(signUpRequest);
      if(createdCustomerDto==null) return  new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
  return new ResponseEntity<>(createdCustomerDto,HttpStatus.OK);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete User by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

