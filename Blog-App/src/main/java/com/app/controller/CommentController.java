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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Comment Controller", description = "Comment Management API's")
@RestController 
@RequestMapping("/api/v1")
public class CommentController {
	
	@Autowired private CommentService commentService;
	
	@Operation(summary = "Create A Comment To Post By Post Id", description = "Creates A new Comment To A Post By Post Id")
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable("postId") int postId){
		
		CommentDto comment = commentService.addComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
	}
	
	@Operation(summary = "Delete a Comment by ID", description = "Delete a Comment object by specifying its ID.")
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") int id){
		
		commentService.deleteComment(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
		
	}

}
