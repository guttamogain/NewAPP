package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.service.NewsService;


@WebMvcTest(NewsController.class)
public class NewsControllerTest {

	private MockMvc newsMockMVC;
	
	@Mock
	private NewsService newsService;
	
	@InjectMocks
	NewsController newsController;
	
	private List<News> newsSet = new ArrayList<>();
	private News news = new News(109876, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		newsMockMVC = MockMvcBuilders.standaloneSetup(newsController).build();
		 news = new News(109876, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news1 = new News(9876, "author", "title1", "description", "url", "urlToImage", "publishedAt", "content", "username");
		newsSet.add(news1);
		News news2 = new News(8765, "author", "title2", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news3 = new News(7654, "author", "title3", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news4 = new News(6543, "author", "title4", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news5 = new News(5432, "author", "title5", "description", "url", "urlToImage", "publishedAt", "content", "username");
		//For user3: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI
		//For admin: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU0ODk0NjE5N30.Kz6vHWBPp5mIpwtVxBm2YoyVeYX4x3qqvicWN536Uqc
		
		newsSet.add(news2);
		newsSet.add(news3);
		newsSet.add(news4);
		newsSet.add(news5);
	}
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void  saveNewsTest() throws Exception{
		when(newsService.saveNews(news)).thenReturn(true);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(post("/api/v1/newsservice/news").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(newsService);
	}
	
	@Test
	public void updateNewsTest() throws Exception{
		when(newsService.updateNews(Mockito.anyInt(), Mockito.any(News.class))).thenReturn(news);
		when(newsService.getNewsIdByUsernameAndTitle(Mockito.anyString(), Mockito.anyString())).thenReturn(123);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(put("/api/v1/newsservice/news/title").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).updateNews(Mockito.anyInt(), Mockito.any(News.class));
	}
	@Test
	public void deleteNewsTest() throws Exception{
		when(newsService.deleteNews(Mockito.anyInt())).thenReturn(true);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(delete("/api/v1/newsservice/news/title").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).deleteNews((Mockito.anyInt()));
	}
	
	@Test
	public void fetchNewsTest() throws Exception{
		when(newsService.getNews(Mockito.anyString())).thenReturn(newsSet);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(get("/api/v1/newsservice/news").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).getNews(Mockito.anyString());
	}
	
	@Test
	public void fetchNewsTestForExternal() throws Exception{
		when(newsService.getNews(Mockito.anyString())).thenReturn(newsSet);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(get("/api/v1/newsservice/news/external").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).getNews(Mockito.anyString());
	}
	
}
