package com.app.service;

import com.app.payload.CommentDto;

public interface CommentService {
	
	CommentDto addComment(CommentDto commentDto, int postId);
	
	void deleteComment(int commentId);

}
