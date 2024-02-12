package com.app.service;

import java.util.List;

import com.app.payload.PostDto;
import com.app.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto updatePost(PostDto postDto, int id);

	PostDto getPostById(int id);

	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

	void deletePost(int id);

	List<PostDto> getPostsByCategory(int categoryId);

	List<PostDto> getPostsByUser(int userId);

	List<PostDto> searchPosts(String keyword);

}
