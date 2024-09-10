package com.example.CarRental.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository <Users,Long> {
    Optional<Users> findByUsernameAndDeletedFlag(String n, String username);

    @Query("SELECT u FROM Users u WHERE u.deletedFlag = :deletedFlag")
    List<Users> findByDeletedFlag(@Param("deletedFlag") String deletedFlag);

    Optional<Users> findByEmail(String email);
}
