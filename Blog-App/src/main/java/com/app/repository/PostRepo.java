package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Category;
import com.app.entity.Post;
import com.app.entity.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

 public List<Post> findByUser(User user);	
	
 public List<Post> findByCategory(Category category);	
}
