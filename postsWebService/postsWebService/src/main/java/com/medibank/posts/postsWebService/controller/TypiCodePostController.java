/**
 * 
 */
package com.medibank.posts.postsWebService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.medibank.posts.postsWebService.exception.InternalServerException;
import com.medibank.posts.postsWebService.exception.TypiCodePostNotFoundException;
import com.medibank.posts.postsWebService.model.TypiCodePost;
import com.medibank.posts.postsWebService.service.TypiCodePostService;

/**
 * Rest Controller for handling various API calls for TypiCodePosts
 */

@RestController()
public class TypiCodePostController {

	@Autowired
	private TypiCodePostService typiCodePostService;

	@GetMapping("/posts")
	public List<TypiCodePost> getAllPosts() {
		ResponseEntity<List<TypiCodePost>> typiCodePosts;
		try {
			typiCodePosts = typiCodePostService.getAllPosts();
		} catch (HttpClientErrorException e) {
			throw new TypiCodePostNotFoundException(e, "TypiCodePost Not Found");
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		return typiCodePosts.getBody();
	}

	@GetMapping("/posts/{id}")
	public TypiCodePost getPost(@PathVariable("id") int postId) {
		ResponseEntity<TypiCodePost> post;
		try {
			post = typiCodePostService.getPostById(postId);
		} catch (HttpClientErrorException e) {
			throw new TypiCodePostNotFoundException(e, "TypiCodePost Not Found");
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		return post.getBody();
	}

}
