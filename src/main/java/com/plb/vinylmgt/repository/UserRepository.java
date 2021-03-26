package com.plb.vinylmgt.repository;

import com.plb.vinylmgt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLastname(String lastname);

    Optional<User> findOneByUsername(String username);
}
