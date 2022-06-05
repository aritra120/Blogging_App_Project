package com.aritra.blogging_app.services;

import com.aritra.blogging_app.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,int postId);
	
	public void deleteComment(int commentId);
	
	
}
