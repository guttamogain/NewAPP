package com.stackroute.newsapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.repository.NewsRepository;

/**
 * This Service class is responsible for all User Operations.
 * 
 * @author Ashok
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsRepository newsRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public News updateNews(int newsId, News updatedNews) throws NewsNotFoundException {
		final News news = newsRepository.findById(newsId).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("Not Able to found News to update...");
		}
		populateUpdatedData(news, updatedNews);
		newsRepository.save(news);
		return news;
	}

	/**
	 * This Method is used to populate updated data in news.
	 * 
	 * @param oldNews
	 * @param newNews
	 */
	private void populateUpdatedData(News oldNews, News newNews) {
		oldNews.setContent(newNews.getContent());
		oldNews.setDescription(newNews.getDescription());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteNews(int newsId) throws NewsNotFoundException {
		final News news = newsRepository.findById(newsId).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("Not Able to found News to delete...");
		}
		newsRepository.delete(news);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<News> getNews(String username) {
		return newsRepository.findByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNewsIdByUsernameAndTitle(String username, String title) {
		List<News> newsSet = getNews(username);
		int newsId = 0;
		for (News news : newsSet) {
			if ((news.getTitle().contains(title)) || (title.contains(news.getTitle()))) {
				newsId = news.getNewsId();
				break;
			}
		}

		return newsId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException, NewsNotFoundException {
		final Optional<News> exists = newsRepository.findById(news.getNewsId());
		final News exist = getNewsByUsernameAndTitle(news.getUsername(), news.getTitle());
		if (exists.isPresent() || exist != null) {
			throw new NewsAlreadyExistsException("News can not be saved. News Alredy exists.");
		}

		newsRepository.save(news);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public News getNewsByNewsId(int newsId) {
		return newsRepository.findById(newsId).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public News getNewsByUsernameAndTitle(String username, String title) throws NewsNotFoundException {
		List<News> newsSet = getNews(username);
		News news = null;
		for (News newsset : newsSet) {
			if (newsset.getTitle().contains(title)) {
				news = newsset;
			}
		}
		return news;
	}

}
