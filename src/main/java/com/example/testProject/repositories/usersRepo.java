package com.example.testProject.repositories;

import com.example.testProject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepo extends JpaRepository<Users, Long> {

}
