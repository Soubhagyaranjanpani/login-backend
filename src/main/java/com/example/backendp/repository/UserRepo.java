package com.example.backendp.repository;

import com.example.backendp.model.Role;
import com.example.backendp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {



      Optional<User> findByEmail(String email);

      User findByRole(Role role);

}
