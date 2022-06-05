package com.aritra.blogging_app.services;

import java.util.List;

import com.aritra.blogging_app.payloads.PostDto;
import com.aritra.blogging_app.payloads.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto, int userId, int categoryId);
	
	public void deletePost(int postId);
	
	public PostDto updatePost(PostDto postDto, int postId);
	
	public PostDto findPostById(int postId);
	
	public PostResponse findAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	public List<PostDto> findAllPostByUser(int userId);
	
	public List<PostDto> findAllPostByCategory(int categoryId);
	
}
