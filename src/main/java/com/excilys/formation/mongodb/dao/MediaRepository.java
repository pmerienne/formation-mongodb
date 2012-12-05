package com.excilys.formation.mongodb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.excilys.formation.mongodb.model.Media;

public interface MediaRepository extends MongoRepository<Media, String> {

	Media findByTitle(String title);
	
	List<Media> findByYearGreaterThan(Date year);
	
	List<Media> findByTags(String tag);
	
	List<Media> findByViewCountAndYearLessThan(int viewCount, Date year);
}
