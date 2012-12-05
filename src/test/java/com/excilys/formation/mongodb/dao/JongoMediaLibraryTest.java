package com.excilys.formation.mongodb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.mongodb.model.Book;
import com.excilys.formation.mongodb.model.CD;
import com.excilys.formation.mongodb.model.DVD;
import com.excilys.formation.mongodb.model.Media;

public class JongoMediaLibraryTest {

	private JongoMediaLibrary jongoMediaLibrary;

	@Before
	public void setup() throws UnknownHostException {
		this.jongoMediaLibrary = new JongoMediaLibrary();
		this.jongoMediaLibrary.drop();
	}

	@Test
	public void testSaveFindByTitleAndDelete() {
		Date date1859 = new DateTime(1859, 1, 1, 1, 1).toDate();
		Book book = new Book("A Tale of Two Cities", date1859, "Charles Dickens", "0006940471");

		this.jongoMediaLibrary.save(book);
		Media actualBook = this.jongoMediaLibrary.findMediaByTitle(book.getTitle());
		assertEquals(book, actualBook);

		this.jongoMediaLibrary.delete(actualBook);
		Media deletedBook = this.jongoMediaLibrary.findMediaByTitle(book.getTitle());
		assertNull(deletedBook);
	}

	@Test
	public void testGetSize() {
		long expectedSize = 13;
		for (int i = 0; i < expectedSize; i++) {
			DVD dvd = new DVD();
			this.jongoMediaLibrary.save(dvd);
		}

		long actualSize = this.jongoMediaLibrary.getSize();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testFindAllMedia() {
		CD cd1 = new CD("cd1", new Date());
		CD cd2 = new CD("cd2", new Date());
		CD cd3 = new CD("cd3", new Date());
		CD cd4 = new CD("cd4", new Date());
		CD cd5 = new CD("cd5", new Date());
		CD cd6 = new CD("cd6", new Date());
		this.jongoMediaLibrary.save(cd1);
		this.jongoMediaLibrary.save(cd2);
		this.jongoMediaLibrary.save(cd3);
		this.jongoMediaLibrary.save(cd4);
		this.jongoMediaLibrary.save(cd5);
		this.jongoMediaLibrary.save(cd6);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findAllMedia(3, 4);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(cd4));
		assertTrue(actualMedias.contains(cd5));
		assertTrue(actualMedias.contains(cd6));
	}

	@Test
	public void testFindMediaAfter() {
		Date date1731 = new DateTime(1731, 1, 1, 1, 1).toDate();
		CD oldCD = new CD("I'm  to old to be a CD!", date1731);
		this.jongoMediaLibrary.save(oldCD);

		Date date2008 = new DateTime(2008, 1, 1, 1, 1).toDate();
		CD notSoRecentCD = new CD("O yeah!", date2008);
		this.jongoMediaLibrary.save(notSoRecentCD);

		Date date2013 = new DateTime(2013, 1, 1, 1, 1).toDate();
		CD newCD = new CD("I'm new!", date2013);
		this.jongoMediaLibrary.save(newCD);

		Date date2000 = new DateTime(2000, 1, 1, 1, 1).toDate();
		Collection<Media> actualMedias = this.jongoMediaLibrary.findMediaAfter(date2000);
		assertEquals(2, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(newCD));
	}

	@Test
	public void testFindLastPublishedMedia() {
		Date date1731 = new DateTime(1731, 1, 1, 1, 1).toDate();
		CD oldCD = new CD("I'm  to old to be a CD!", date1731);
		this.jongoMediaLibrary.save(oldCD);

		Date date2008 = new DateTime(2008, 1, 1, 1, 1).toDate();
		CD notSoRecentCD = new CD("O yeah!", date2008);
		this.jongoMediaLibrary.save(notSoRecentCD);

		Date date2012 = new DateTime(2012, 1, 1, 1, 1).toDate();
		CD lastYearCD = new CD("Last year CD", date2012);
		this.jongoMediaLibrary.save(lastYearCD);

		Date date2013 = new DateTime(2013, 1, 1, 1, 1).toDate();
		CD newCD = new CD("I'm new!", date2013);
		this.jongoMediaLibrary.save(newCD);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findLastPublishedMedia(3);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(lastYearCD));
		assertTrue(actualMedias.contains(newCD));
	}

	@Test
	public void findByTag() {
		Book book = new Book();
		List<String> bookTags = Arrays.asList("History", "Roman", "Action");
		book.getTags().addAll(bookTags);
		this.jongoMediaLibrary.save(book);

		CD cd = new CD();
		List<String> cdTags = Arrays.asList("Music", "Classical", "Nocturne");
		cd.getTags().addAll(cdTags);
		this.jongoMediaLibrary.save(cd);

		DVD dvd = new DVD();
		List<String> dvdTags = Arrays.asList("Film", "Action", "007");
		dvd.getTags().addAll(dvdTags);
		this.jongoMediaLibrary.save(dvd);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findByTag("Action");
		assertEquals(2, actualMedias.size());
		assertTrue(actualMedias.contains(book));
		assertTrue(actualMedias.contains(dvd));
	}

	@Test
	public void findAllTags() {
		Book book = new Book();
		List<String> bookTags = Arrays.asList("History", "Roman", "Wine");
		book.getTags().addAll(bookTags);
		this.jongoMediaLibrary.save(book);

		CD cd = new CD();
		List<String> cdTags = Arrays.asList("Music", "Classical", "Nocturne");
		cd.getTags().addAll(cdTags);
		this.jongoMediaLibrary.save(cd);

		DVD dvd = new DVD();
		List<String> dvdTags = Arrays.asList("Film", "Action", "007");
		dvd.getTags().addAll(dvdTags);
		this.jongoMediaLibrary.save(dvd);

		Collection<String> actualTags = this.jongoMediaLibrary.findAllTags(0, 9);
		assertNotNull(actualTags);
		assertTrue(actualTags.containsAll(bookTags));
		assertTrue(actualTags.containsAll(cdTags));
		assertTrue(actualTags.containsAll(dvdTags));
	}

	@Test
	public void testDeleteNotViewedMediaBefore() {
		Date date1731 = new DateTime(1731, 1, 1, 1, 1).toDate();
		CD oldCD = new CD("I'm  to old to be a CD!", date1731);
		oldCD.setViewCount(2132654);
		this.jongoMediaLibrary.save(oldCD);

		Date date2008 = new DateTime(2008, 1, 1, 1, 1).toDate();
		CD notSoRecentCD = new CD("O yeah!", date2008);
		notSoRecentCD.setViewCount(0);
		this.jongoMediaLibrary.save(notSoRecentCD);

		Date date2011 = new DateTime(2011, 1, 1, 1, 1).toDate();
		CD lastYearCD = new CD("Last year CD", date2011);
		lastYearCD.setViewCount(121);
		this.jongoMediaLibrary.save(lastYearCD);

		Date date2013 = new DateTime(2013, 1, 1, 1, 1).toDate();
		CD newCD = new CD("I'm new!", date2013);
		newCD.setViewCount(0);
		this.jongoMediaLibrary.save(newCD);

		Date date2012 = new DateTime(2012, 1, 1, 1, 1).toDate();
		this.jongoMediaLibrary.deleteNotViewedMediaBefore(date2012);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findAllMedia(0, 1000);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(oldCD));
		assertTrue(actualMedias.contains(lastYearCD));
		assertTrue(actualMedias.contains(newCD));

	}
}
