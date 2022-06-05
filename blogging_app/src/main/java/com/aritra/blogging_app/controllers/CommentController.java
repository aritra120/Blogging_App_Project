package com.aritra.blogging_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aritra.blogging_app.payloads.ApiResponse;
import com.aritra.blogging_app.payloads.CommentDto;
import com.aritra.blogging_app.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId) {
		CommentDto obj = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(obj,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ApiResponse deleteComment(@PathVariable int commentId) {
		this.commentService.deleteComment(commentId);
		return new ApiResponse("Comment Deleted Successfully",true);
	}
	
}
