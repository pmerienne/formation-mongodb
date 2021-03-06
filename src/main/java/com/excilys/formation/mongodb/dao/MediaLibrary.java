package com.excilys.formation.mongodb.dao;

import java.util.Collection;

import com.excilys.formation.mongodb.model.Media;

public interface MediaLibrary {

	final static String DEFAULT_IP = "127.0.0.1";
	final static Integer DEFAULT_PORT = 27017;
	final static String DEFAULT_DB_NAME = "medialibrary";
	final static String DEFAULT_COLLECTION_NAME = "media";

	/**
	 * Saves a given {@link Media}
	 * 
	 * @param media
	 * @return
	 */
	Media save(Media media);

	/**
	 * Deletes a given {@link Media}
	 * 
	 * @param media
	 * @throws IllegalArgumentException
	 *             if the media is null or has no id
	 */
	void delete(Media media);

	/**
	 * Counts all stored {@link Media}
	 * 
	 * @return
	 */
	Long getSize();

	/**
	 * Drops all recorded {@link Media}
	 */
	void drop();

	/**
	 * Finds a {@link Media} by it's title
	 * 
	 * @param title
	 * @return the {@link Media} or <code>null</code> if no {@link Media} was found with th egiven title
	 * 
	 */
	Media findMediaByTitle(String title);

	/**
	 * Finds all media and limit results.
	 * 
	 * @param startIndex
	 * @param maxResults
	 * @return
	 */
	Collection<Media> findAllMedia(int startIndex, int maxResults);

	/**
	 * Finds all media created after a given year
	 * 
	 * @param year
	 * @return
	 */
	Collection<Media> findMediaAfter(Integer year);

	/**
	 * Find latest media and limit results.
	 * 
	 * @param maxResults
	 * @return
	 */
	Collection<Media> findLastPublishedMedia(int maxResults);

}
