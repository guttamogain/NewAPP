package com.stackroute.newsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stackroute.newsapp.domain.News;

/**
 * This Repository Class responsible for Crud operations of News.
 * 
 * @author Ashok
 *
 */
public interface NewsRepository extends JpaRepository<News, Integer> {

	/**
	 * This Method is to fetch List of news by using Username.
	 * 
	 * @param username
	 * @return List<News>
	 */
	@Query("SELECT n FROM News n where n.username = :username")
	List<News> findByUsername(String username);

}
