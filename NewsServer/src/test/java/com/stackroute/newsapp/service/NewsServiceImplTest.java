package com.stackroute.newsapp.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.repository.NewsRepository;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

	@InjectMocks
	private NewsServiceImpl newsServiceImpl;
	
	@Mock
	private NewsRepository newsRepository;
	
	@Mock
	private News news;
	
	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(news);
		assertNotNull("jpaRepository creation fails: use @injectMocks on newsServicempl", newsRepository);
	}

	@Test
	public void testSaveNewsTest() throws Exception {
		when(newsRepository.save(news)).thenReturn(news);
		boolean flag = newsServiceImpl.saveNews(news);
		assertEquals("saving movie failed,the call to movieDAOImpl is returning false,check this method", true,
				flag);
		verify(newsRepository, times(1)).save(news);
		verify(newsRepository, times(1)).findById(news.getNewsId());
	}
	
	@Test(expected = NewsAlreadyExistsException.class)
	public void saveNewsTest1() throws Exception{
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		Optional<News> newsOption = Optional.of(news);
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(newsOption);
		boolean flag = newsServiceImpl.saveNews(news);
		assertEquals("News can not be saved. News Alredy exists.", false, flag);
		verify(newsRepository, times(1)).save(news);
	}
	
	@Test
	public void updateNewsTest() throws Exception {
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news1 = new News(12323, "author1", "title1", "description1", "url1", "urlToImage1", "publishedAt1", "content1", "username1");
		Optional<News> newsOption = Optional.of(news);
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(newsOption);
		News rtn = newsServiceImpl.updateNews(123, news1);
		assertEquals(rtn.getAuthor(),"author");
		verify(newsRepository, times(1)).save(news);
	}
	@Test(expected = NullPointerException.class)
	public void updateNewsTest1() throws Exception {
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(null);
		News rtn = newsServiceImpl.updateNews(123, news);
		assertEquals(rtn.getAuthor(),"author1");
		verify(newsRepository, times(1)).save(news);
	}
	@Test
	public void deleteTest() throws Exception{
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		Optional<News> newsOption = Optional.of(news);
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(newsOption);
		boolean rtn = newsServiceImpl.deleteNews(123);
		assertEquals(true, rtn);
		verify(newsRepository,times(1)).delete(news);
	}
	@Test(expected = NullPointerException.class)
	public void deleteTest1() throws Exception{
		News news = null;
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(null);
		boolean rtn = newsServiceImpl.deleteNews(123);
		assertEquals(true, rtn);
		verify(newsRepository,times(1)).delete(news);
	}
	@Test
	public void getNewsTest() throws Exception {
		List<News> list = new ArrayList<>();
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		list.add(news);
		when(newsRepository.findByUsername("username")).thenReturn(list);
		List<News> rtn = newsServiceImpl.getNews("username");
		assertEquals("author", rtn.get(0).getAuthor());
		verify(newsRepository, times(1)).findByUsername(Mockito.anyString());
	}
	@Test
	public void getNewsIdByUsernameAndTitleTest() throws Exception{
		List<News> list = new ArrayList<>();
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		list.add(news);
		when(newsRepository.findByUsername("username")).thenReturn(list);
		
		int id = newsServiceImpl.getNewsIdByUsernameAndTitle("username", "title");
		assertEquals(123, id);
	}
	@Test
	public void getNewsById() throws Exception{
		News news = new News(123, "author", "title", "description", "url", "urlToImage", "publishedAt", "content", "username");
		Optional<News> newsOption = Optional.of(news);
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(newsOption);
		News rtn = newsServiceImpl.getNewsByNewsId(123);
		assertEquals("author", rtn.getAuthor());
		verify(newsRepository, times(1)).findById(Mockito.anyInt());
	}
	@Test
	public void getNewsByUsernameAndTitile() throws Exception{
		List<News> newsSet = new ArrayList<>();
		News news2 = new News(8765, "author2", "title2", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news3 = new News(7654, "author", "title3", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news4 = new News(6543, "author", "title4", "description", "url", "urlToImage", "publishedAt", "content", "username");
		News news5 = new News(5432, "author", "title5", "description", "url", "urlToImage", "publishedAt", "content", "username");
		
		newsSet.add(news2);
		newsSet.add(news3);
		newsSet.add(news4);
		newsSet.add(news5);
		when(newsRepository.findByUsername(Mockito.anyString())).thenReturn(newsSet);
		News rtnNews = newsServiceImpl.getNewsByUsernameAndTitle("username", "title2");
		assertEquals("author2", rtnNews.getAuthor());
		
	}
}
