package com.stackroute.newsapp.service;

import java.util.List;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

/**
 * This Class is Responsible for services offered.
 * 
 * @author Ashok
 *
 */
public interface NewsService {

	/**
	 * This Method is used to save news.
	 * 
	 * @param news
	 * @return boolean
	 * @throws NewsAlreadyExistsException
	 * @throws NewsNotFoundException
	 */
	boolean saveNews(News news) throws NewsAlreadyExistsException, NewsNotFoundException;

	/**
	 * This Method is used to update news.
	 * 
	 * @param newsId
	 * @param News
	 * @return News
	 * @throws NewsNotFoundException
	 */
	News updateNews(int newsId, News News) throws NewsNotFoundException;

	/**
	 * This Method is used to delete News.
	 * 
	 * @param newsId
	 * @return boolean
	 * @throws NewsNotFoundException
	 */
	boolean deleteNews(int newsId) throws NewsNotFoundException;

	/**
	 * This Method is used to retrieve news by using username and title.
	 * 
	 * @param username
	 * @param title
	 * @return
	 * @throws NewsNotFoundException
	 */
	News getNewsByUsernameAndTitle(String username, String title) throws NewsNotFoundException;

	/**
	 * This Method is used to retrieve all news by using username.
	 * 
	 * @param username
	 * @return List<News>
	 */
	List<News> getNews(String username);

	/**
	 * This Method is used to fetch newsId by using username and title.
	 * 
	 * @param username
	 * @param title
	 * @return int
	 */
	int getNewsIdByUsernameAndTitle(String username, String title);

	/**
	 * This Method is fetch news details by using newsId.
	 * 
	 * @param newsId
	 * @return News
	 */
	News getNewsByNewsId(int newsId);
}
