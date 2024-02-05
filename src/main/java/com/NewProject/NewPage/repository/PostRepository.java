package com.NewProject.NewPage.repository;

import com.NewProject.NewPage.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}

// crud repository will not help you to do pagination
// in crud find all method iterable
// in jpa find all method return list
// pagination concept in curd repository not there
// if you want to apply pagination and sorting you have to use jpa repository
