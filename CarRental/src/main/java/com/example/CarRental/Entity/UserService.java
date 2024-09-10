package com.example.CarRental.Entity;

import com.example.CarRental.enums.Role;
import com.example.CarRental.service.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // Create or Update User
    public UserDto createCustomer(SignUpRequest signUpRequest) {
        Users user = new Users();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.CUSTOMER);

        Users createdCustomer = userRepo.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdCustomer.getId());

        return userDto;
    }



    public  boolean hasCustomerWiThEmail (String email){
        return userRepo.findByEmail(email).isPresent();
    }
    public List<Users> getAllUsers() {
        return userRepo.findByDeletedFlag("N"); // Only fetch active users
    }

    // Get User by ID (only if not deleted)
    public Optional<Users> getUserById(Long id) {
        Optional<Users> user = userRepo.findById(id);
        return user.filter(u -> "N".equals(u.getDeletedFlag())); // Return user if not deleted
    }

    // Soft Delete User by ID
    public void deleteUser(Long id) {
        Optional<Users> user = userRepo.findById(id);
        if (user.isPresent()) {
            Users existingUser = user.get();
            existingUser.setDeletedFlag("Y"); // Mark as deleted
            userRepo.save(existingUser);
        }
    }
}
