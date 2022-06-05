package com.aritra.blogging_app.services.impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aritra.blogging_app.entities.Category;
import com.aritra.blogging_app.entities.Post;
import com.aritra.blogging_app.entities.User;
import com.aritra.blogging_app.payloads.PostDto;
import com.aritra.blogging_app.payloads.PostResponse;
import com.aritra.blogging_app.repositories.CategoryRepo;
import com.aritra.blogging_app.repositories.PostRepo;
import com.aritra.blogging_app.repositories.UserRepo;
import com.aritra.blogging_app.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		
		User user = this.userRepo.findById(userId).get();
		Category category = this.categoryRepo.findById(categoryId).get();
		
		Post obj = this.modelMapper.map(postDto, Post.class);
		obj.setAddedDate(new Date());
		obj.setImageName("default.jpg");
		obj.setCategory(category);
		obj.setUser(user);
		
		Post newObj = this.repo.save(obj);
		return this.modelMapper.map(newObj, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.repo.findById(postId).get();
		this.repo.delete(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.repo.findById(postId).get();
		Post newPost = this.modelMapper.map(postDto, Post.class);
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		Post upd = this.repo.save(post);
		return this.modelMapper.map(upd, PostDto.class);
	}

	@Override
	public PostDto findPostById(int postId) {
		Post post = this.repo.findById(postId).get();
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse findAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir) {
		
		//Strat of sorting
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		//End of sorting
		
		
		//Start of Pagging
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post>pagePost = this.repo.findAll(p);
		List<Post>allPosts = pagePost.getContent();
		//End of pagging
		
		
		List<PostDto>postDtos = allPosts.stream()
				.map((obj)->this.modelMapper.map(obj,PostDto.class))
				.collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		postResponse.setResponse(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> findAllPostByUser(int userId) {
		User user = this.userRepo.findById(userId).get();
		List<Post>posts = user.getPosts();
		List<PostDto>postDtos = posts.stream()
				.map((obj)->this.modelMapper.map(obj, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> findAllPostByCategory(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId).get();
		List<Post>posts = category.getPosts();
		List<PostDto>postDtos = posts.stream()
				.map((obj)->this.modelMapper.map(obj, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}
	
	
	

}
