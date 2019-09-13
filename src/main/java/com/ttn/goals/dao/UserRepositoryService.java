package com.ttn.goals.dao;

import com.ttn.goals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryService  extends JpaRepository<User, Long> {



}
