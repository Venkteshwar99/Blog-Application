package com.app.service;

import java.util.List;

import com.app.entity.Post;
import com.app.payload.PostDto;

public interface PostService {

	Post createPost(PostDto postDto,int userId, int categoryId);

	Post updatePost(PostDto postDto);

	Post getPostById(int id);

	Post getAllPosts();

	void deletePost(int id);

	List<Post> getPostsByCategory(int categoryId);

	List<Post> getPostsByUser(int userId);

	List<Post> searchPosts(String keyword);

}
