package com.medibank.posts.postsWebService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.medibank.posts.postsWebService.exception.TypiCodePostNotFoundException;
import com.medibank.posts.postsWebService.model.TypiCodePost;
import com.medibank.posts.postsWebService.service.TypiCodePostService;

@ExtendWith(MockitoExtension.class)
public class TestTypiCodePostController {
	@InjectMocks
	TypiCodePostService service;

	@Mock
	TypiCodePost data;

	@Mock
	private RestTemplate restTemplate;

	@Value("${api.url}")
	private String apiUrl;

	List<TypiCodePost> list;
	TypiCodePost postOne;
	TypiCodePost postTwo;
	TypiCodePost postThree;

	@BeforeEach
	void setUp() {
		list = new ArrayList<TypiCodePost>();
		postOne = new TypiCodePost(1, 1, "test_title_1", "test_body_1");
		postTwo = new TypiCodePost(1, 2, "test_title_1", "test_body_2");
		postThree = new TypiCodePost(1, 3, "test_title_3", "test_body_3");

		list.add(postOne);
		list.add(postTwo);
		list.add(postThree);

	}

	@Test
	void testGetAllPosts() {

		Mockito.when(restTemplate.exchange(apiUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TypiCodePost>>() {
				})).thenReturn(new ResponseEntity<>(list, HttpStatus.OK));

		List<TypiCodePost> postsist;

		postsist = service.getAllPosts().getBody();

		assertEquals(3, postsist.size());
	}

	@Test
	void testGetPostById() {

		Mockito.when(restTemplate.getForEntity(apiUrl + "/1", TypiCodePost.class))
				.thenReturn(new ResponseEntity<TypiCodePost>(postOne, HttpStatus.OK));

		TypiCodePost post = service.getPostById(1).getBody();

		assertEquals(1, post.getId());

	}

	@Test()
	void testExceptionHandling() {
		Mockito.when(restTemplate.getForEntity(apiUrl + "/458", TypiCodePost.class))
				.thenThrow(new TypiCodePostNotFoundException(null,"TypiCodePost Not Found"));

		Exception exception = assertThrows(TypiCodePostNotFoundException.class, () -> {
			service.getPostById(458).getBody();
		});

		String expectedMessage = "TypiCodePost Not Found";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
