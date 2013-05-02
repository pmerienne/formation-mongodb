package com.excilys.formation.mongodb.dao;

import java.net.UnknownHostException;
import java.util.Collection;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.excilys.formation.mongodb.model.Media;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.Mongo;

public class JongoMediaLibrary implements MediaLibrary {

	private MongoCollection mediaCollection;

	public JongoMediaLibrary() throws UnknownHostException {
		this(DEFAULT_IP, DEFAULT_PORT, DEFAULT_DB_NAME, DEFAULT_COLLECTION_NAME);
	}

	public JongoMediaLibrary(String ip, Integer port, String dbName, String mediaCollectionName) throws UnknownHostException {
		Mongo mongo = new Mongo(ip, port);
		DB db = mongo.getDB(dbName);

		Jongo jongo = new Jongo(db);
		this.mediaCollection = jongo.getCollection(mediaCollectionName);
	}

	@Override
	public Media save(Media media) {
		this.mediaCollection.save(media);
		return media;
	}

	@Override
	public void delete(Media media) {
		if (media == null || media.getId() == null) {
			throw new IllegalArgumentException("Media or media id can't be null : " + media);
		}

		this.mediaCollection.remove(media.getId());
	}

	@Override
	public Long getSize() {
		return this.mediaCollection.count();
	}

	@Override
	public Media findMediaByTitle(String title) {
		return this.mediaCollection.findOne("{title: #}", title).as(Media.class);
	}

	@Override
	public Collection<Media> findAllMedia(int startIndex, int maxResults) {
		Iterable<Media> medias = this.mediaCollection.find("{}").skip(startIndex).limit(maxResults).as(Media.class);
		return Lists.newArrayList(medias);
	}

	@Override
	public Collection<Media> findMediaAfter(Integer year) {
		Iterable<Media> medias = this.mediaCollection.find("{year : {$gte: #}}", year).as(Media.class);
		return Lists.newArrayList(medias);
	}

	@Override
	public Collection<Media> findLastPublishedMedia(int maxResults) {
		Iterable<Media> medias = this.mediaCollection.find("{}").sort("{year:-1}").limit(maxResults).as(Media.class);
		return Lists.newArrayList(medias);
	}

	@Override
	public void drop() {
		this.mediaCollection.drop();
	}
}
