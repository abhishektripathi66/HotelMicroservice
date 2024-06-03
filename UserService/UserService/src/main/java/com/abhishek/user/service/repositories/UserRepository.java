package com.abhishek.user.service.repositories;

import com.abhishek.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
