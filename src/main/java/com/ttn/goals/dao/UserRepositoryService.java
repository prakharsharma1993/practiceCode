package com.ttn.goals.dao;

import com.ttn.goals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepositoryService  extends JpaRepository<User, Long> {


    Optional<User> findByUserIdAndActive(String userID, Boolean active);

}
