package com.excilys.formation.mongodb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.Collection;

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
		Book book = new Book("A Tale of Two Cities", 1859, "Charles Dickens", "0006940471");

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
		CD cd1 = new CD("cd1");
		CD cd2 = new CD("cd2");
		CD cd3 = new CD("cd3");
		CD cd4 = new CD("cd4");
		CD cd5 = new CD("cd5");
		CD cd6 = new CD("cd6");
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
		CD oldCD = new CD("I'm  to old to be a CD!", 1731);
		this.jongoMediaLibrary.save(oldCD);

		CD notSoRecentCD = new CD("O yeah!", 2008);
		this.jongoMediaLibrary.save(notSoRecentCD);

		CD newCD = new CD("I'm new!", 2013);
		this.jongoMediaLibrary.save(newCD);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findMediaAfter(2000);
		assertEquals(2, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(newCD));
	}

	@Test
	public void testFindLastPublishedMedia() {
		CD oldCD = new CD("I'm  to old to be a CD!", 1731);
		this.jongoMediaLibrary.save(oldCD);

		CD notSoRecentCD = new CD("O yeah!", 2008);
		this.jongoMediaLibrary.save(notSoRecentCD);

		CD lastYearCD = new CD("Last year CD", 2012);
		this.jongoMediaLibrary.save(lastYearCD);

		CD newCD = new CD("I'm new!", 2013);
		this.jongoMediaLibrary.save(newCD);

		Collection<Media> actualMedias = this.jongoMediaLibrary.findLastPublishedMedia(3);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(lastYearCD));
		assertTrue(actualMedias.contains(newCD));
	}

}
