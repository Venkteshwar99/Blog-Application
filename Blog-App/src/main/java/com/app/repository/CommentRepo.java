package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Comments;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Integer>{

}
