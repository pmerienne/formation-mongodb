package com.excilys.formation.mongodb.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.mongodb.model.Media;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class SpringDataMediaLibrary implements MediaLibrary {

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Media save(Media media) {
		return this.mediaRepository.save(media);
	}

	@Override
	public void delete(Media media) {
		this.mediaRepository.delete(media);
	}

	@Override
	public Long getSize() {
		return this.mediaRepository.count();
	}

	@Override
	public void drop() {
		this.mediaRepository.deleteAll();
	}

	@Override
	public Media findMediaByTitle(String title) {
		return this.mediaRepository.findByTitle(title);
	}

	@Override
	public Collection<Media> findAllMedia(int startIndex, int maxResults) {
		int page = (int) Math.floor(startIndex / maxResults);
		Pageable pageable = new PageRequest(page, maxResults);
		Page<Media> results = this.mediaRepository.findAll(pageable);
		return results.getContent();
	}

	@Override
	public Collection<Media> findMediaAfter(Date date) {
		return this.mediaRepository.findByYearGreaterThan(date);
	}

	@Override
	public Collection<Media> findLastPublishedMedia(int maxResults) {
		Pageable pageable = new PageRequest(0, maxResults, Direction.DESC, "year");
		Page<Media> results = this.mediaRepository.findAll(pageable);
		return results.getContent();
	}

	@Override
	public Collection<Media> findByTag(String tag) {
		return this.mediaRepository.findByTags(tag);
	}

	@Override
	public void deleteNotViewedMediaBefore(Date date) {
		List<Media> toDelete = this.mediaRepository.findByViewCountAndYearLessThan(0, date);
		this.mediaRepository.delete(toDelete);
	}

	@Override
	public Collection<String> findAllTags(int startIndex, int maxResults) {
		DBObject project = new BasicDBObject("$project", new BasicDBObject("tags", true));
		DBObject unwind = new BasicDBObject("$unwind", "$tags");
		DBObject skip = new BasicDBObject("$skip", startIndex);
		DBObject limit = new BasicDBObject("$limit", maxResults);

		DBCollection dbCollection = this.mongoTemplate.getCollection(DEFAULT_COLLECTION_NAME);
		AggregationOutput output = dbCollection.aggregate(project, unwind, skip, limit);
		Iterable<DBObject> results = output.results();

		Collection<String> tags = new HashSet<String>();
		for (DBObject dbo : results) {
			String tag = String.valueOf(dbo.get("tags"));
			tags.add(tag);
		}

		return tags;
	}
}
