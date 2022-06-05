package com.aritra.blogging_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aritra.blogging_app.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
