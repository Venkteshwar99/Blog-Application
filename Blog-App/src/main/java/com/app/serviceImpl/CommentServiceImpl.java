package com.app.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Comments;
import com.app.entity.Post;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payload.CommentDto;
import com.app.repository.CommentRepo;
import com.app.repository.PostRepo;
import com.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto addComment(CommentDto commentDto, int postId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Comments comments = mapper.map(commentDto, Comments.class);

		comments.setPost(post);

		Comments savedComment = commentRepo.save(comments);

		return mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		
		Comments comments = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments", "comment id", commentId));

		commentRepo.delete(comments);
		
	}

}
