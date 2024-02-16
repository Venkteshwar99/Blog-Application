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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Post Controller", description = "Post Management API's")
@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@Operation(summary = "Create a Post", description = "Creates a new Post")
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable int userId,
			@PathVariable int categoryId) {

		PostDto post = postService.createPost(dto, userId, categoryId);

		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a Post by ID", description = "Update a Post object by specifying its ID.")
	@PutMapping("/update/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable int postId) {

		PostDto post = postService.updatePost(dto, postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	@Operation(summary = "Delete a User by ID", description = "Delete a Post object by specifying its ID.")
	@DeleteMapping("/delete/post/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId) {

		postService.deletePost(postId);

		return ResponseEntity.status(HttpStatus.OK).body("deleted post with post Id: " + postId);

	}

	@Operation(summary = "Retrieve a Post by User ID", description = "Get a Post object by specifying User ID.")
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") int userId) {

		List<PostDto> postsByUser = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	@Operation(summary = "Retrieve a Post by Category ID", description = "Get a Post object by specifying Category ID.")
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") int categoryId) {

		List<PostDto> postsByCategory = postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);

	}

	@Operation(summary = "Retrieve a Post by ID", description = "Get a Post object by specifying its ID.")
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable("postId") int postId) {

		PostDto postsById = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postsById, HttpStatus.OK);

	}

	@Operation(summary = "Fetch all Posts", description = "Fetches all Post entities and their data from data source")
	@GetMapping("/posts/findAll")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

	}

	@Operation(summary = "Fetch all Posts by speciyfing keyword", description = "Fetches all Post entities and their data from data source by specifying keyword")
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword) {

		List<PostDto> list = postService.searchPosts(keyword);

		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	@Operation(summary = "Add Post Photo", description = "Adds a new Post Image")
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int postId)
			throws IOException {

		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, file);

		postDto.setImageName(fileName);

		PostDto updatedPost = postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}

	/**
	 * Downloads an image with the specified image name.
	 *
	 * @param imageName The name of the image to be downloaded.
	 * @param response  The HttpServletResponse object to write the image content
	 *                  to.
	 * @throws IOException If there is an I/O error during the download process.
	 */
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		// Get the input stream for the specified resource (image)
		InputStream resource = fileService.getResource(path, imageName);

		// Set the response content type to JPEG (you can adjust this based on your
		// image type)
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);

		// Copy the image content from the input stream to the response output stream
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
