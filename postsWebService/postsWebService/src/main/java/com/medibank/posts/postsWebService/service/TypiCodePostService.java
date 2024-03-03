/**
 * 
 */
package com.medibank.posts.postsWebService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medibank.posts.postsWebService.model.TypiCodePost;

/**
 * Service class for various operations on TypiCodePosts
 */
@Service
public class TypiCodePostService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.url}")
	private String apiUrl;

	public ResponseEntity<List<TypiCodePost>> getAllPosts() {
		return restTemplate.exchange(apiUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TypiCodePost>>() {
				});
	}

	public ResponseEntity<TypiCodePost> getPostById(int postId) {
		return restTemplate.getForEntity(apiUrl + "/" + postId, TypiCodePost.class);
	}

}
