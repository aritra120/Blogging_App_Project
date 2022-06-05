package com.aritra.blogging_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aritra.blogging_app.configuration.Constants;
import com.aritra.blogging_app.payloads.ApiResponse;
import com.aritra.blogging_app.payloads.PostDto;
import com.aritra.blogging_app.payloads.PostResponse;
import com.aritra.blogging_app.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	
	@Autowired
	private PostService service;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable int userId,@PathVariable int categoryId) {
		PostDto obj = this.service.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(obj,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable int postId) {
		this.service.deletePost(postId);
		return new ApiResponse("Post deleted Successfully",true);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId) {
		PostDto obj = this.service.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(obj,HttpStatus.CREATED);
	}
	
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable int postId) {
		PostDto obj = this.service.findPostById(postId);
		return new ResponseEntity<PostDto>(obj,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("post")
	public ResponseEntity<PostResponse> findAllPosts(
			@RequestParam(value="pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue = Constants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = Constants.SORT_DIR,required = false) String sortDir
			) {
		PostResponse postResponse = this.service.findAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("user/{userId}/post")
	public ResponseEntity<List<PostDto>> findAllPostByUser(@PathVariable int userId) {
		List<PostDto> postDtos = this.service.findAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> findAllPostByCategory(@PathVariable int categoryId) {
		List<PostDto> postDtos = this.service.findAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
	}
	
}
