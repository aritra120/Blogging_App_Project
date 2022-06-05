package com.aritra.blogging_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aritra.blogging_app.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
