package com.aritra.blogging_app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aritra.blogging_app.entities.Comment;
import com.aritra.blogging_app.entities.Post;
import com.aritra.blogging_app.payloads.CommentDto;
import com.aritra.blogging_app.repositories.CommentRepo;
import com.aritra.blogging_app.repositories.PostRepo;
import com.aritra.blogging_app.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId).get();
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepo.findById(commentId).get();
		this.commentRepo.delete(comment);
		
	}

}
