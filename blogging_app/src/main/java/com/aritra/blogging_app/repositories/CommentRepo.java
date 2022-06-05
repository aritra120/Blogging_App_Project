package com.aritra.blogging_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aritra.blogging_app.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
