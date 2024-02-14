package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.CommentDto;
import com.app.service.CommentService;

@RestController 
@RequestMapping("/api")
public class CommentController {
	
	@Autowired private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable("postId") int postId){
		
		CommentDto comment = commentService.addComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
	}
	

	@DeleteMapping("/comments")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") int id){
		
		commentService.deleteComment(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
		
	}

}
