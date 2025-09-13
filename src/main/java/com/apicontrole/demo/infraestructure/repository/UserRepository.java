package com.apicontrole.demo.infraestructure.repository;

import com.apicontrole.demo.infraestructure.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
