package com.sansa.FundacioEsplaiHackathon.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String name);
    Optional<User> findByUsername(String username);
}
