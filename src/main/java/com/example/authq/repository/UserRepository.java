package com.example.authq.repository;

import com.example.authq.model.User;
import com.example.authq.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select new com.example.authq.model.UserDTO(u) from User u where u.username = :username and u.password = :password")
    UserDTO findUserDTO(@Param("username") String username, @Param("password") String password);
    @Query("select new com.example.authq.model.UserDTO(u) from User u where u.username = :username")
    UserDTO findUserByUsername(@Param("username") String username);
}
