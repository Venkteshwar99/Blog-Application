package com.app.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.app.entity.Category;
import com.app.entity.Post;
import com.app.entity.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payload.PostDto;
import com.app.payload.PostResponse;
import com.app.repository.CategoryRepo;
import com.app.repository.PostRepo;
import com.app.repository.UserRepo;
import com.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = mapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost = postRepo.save(post);

		return mapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {

		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id));
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());

		Post updatedPost = postRepo.save(post);

		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPostById(int id) {

		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id));

		return mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDir),sortBy));



		Page<Post> pagePost = postRepo.findAll(pageable);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public void deletePost(int id) {

		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id));

		postRepo.delete(post);

	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		List<Post> posts = postRepo.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(int userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		System.out.println("UserRepo Results: " + user);
		List<Post> posts = postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		System.out.println("postsDtos" + postDtos);
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> list = postRepo.searchByTitle(keyword);
		List<PostDto> postDtos = list.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

}
