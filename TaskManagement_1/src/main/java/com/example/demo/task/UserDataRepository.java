package com.example.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {

    Optional<UserData> findByName(String username);
}
