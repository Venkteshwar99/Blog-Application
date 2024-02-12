package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Category;
import com.app.entity.Post;
import com.app.entity.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	public List<Post> findByUser(User user);

	public List<Post> findByCategory(Category category);

	@Query(value = "SELECT * FROM Posts e WHERE " + "e.post_id LIKE %:name% OR "
			+ "e.post_title LIKE %:name%", nativeQuery = true)
	public List<Post> searchByTitle(@Param("name") String title);

}
