package com.app.serviceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Post;
import com.app.payload.PostDto;
import com.app.repository.CategoryRepo;
import com.app.repository.PostRepo;
import com.app.repository.UserRepo;
import com.app.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired PostRepo postRepo;
	
	@Autowired UserRepo userRepo;
	
	@Autowired CategoryRepo categoryRepo;
	
	@Autowired ModelMapper mapper;
	
	
	@Override
	public Post createPost(PostDto postDto,int userId, int categoryId) {
	Post post =	mapper.map(postDto, Post.class);
	post.setImageName("default.jpg");
	post.setDate(new Date());
	post.setCategory(null);
		
		return null;
	}

	@Override
	public Post updatePost(PostDto postDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getPostsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
