package com.aritra.blogging_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aritra.blogging_app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
