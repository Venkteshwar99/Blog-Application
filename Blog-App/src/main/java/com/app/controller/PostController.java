package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.config.AppConstants;
import com.app.payload.PostDto;
import com.app.payload.PostResponse;
import com.app.service.FileService;
import com.app.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable int userId,
			@PathVariable int categoryId) {

		PostDto post = postService.createPost(dto, userId, categoryId);

		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@PutMapping("/update/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable int postId) {

		PostDto post = postService.updatePost(dto, postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	@DeleteMapping("/delete/post/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId) {

		postService.deletePost(postId);

		return ResponseEntity.status(HttpStatus.OK).body("deleted post with post Id: " + postId);

	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") int userId) {

		List<PostDto> postsByUser = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") int categoryId) {

		List<PostDto> postsByCategory = postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable("postId") int postId) {

		PostDto postsById = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postsById, HttpStatus.OK);

	}

	@GetMapping("/posts/findAll")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword) {

		List<PostDto> list = postService.searchPosts(keyword);

		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file,
			@PathVariable int postId) throws IOException{

		PostDto postDto = postService.getPostById(postId);
		String fileName =fileService.uploadImage(path, file);

		postDto.setImageName(fileName);

		PostDto updatedPost =  postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}
	
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException{
		
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
