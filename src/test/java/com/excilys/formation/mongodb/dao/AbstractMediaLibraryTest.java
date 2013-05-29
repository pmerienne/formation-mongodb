package com.excilys.formation.mongodb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.excilys.formation.mongodb.model.Book;
import com.excilys.formation.mongodb.model.CD;
import com.excilys.formation.mongodb.model.DVD;
import com.excilys.formation.mongodb.model.Media;

public abstract class AbstractMediaLibraryTest {

	protected MediaLibrary mediaLibrary;

	@Test
	public void testSaveFindByTitleAndDelete() {
		Book book = new Book("A Tale of Two Cities", 1859, "Charles Dickens", "0006940471");

		this.mediaLibrary.save(book);
		Media actualBook = this.mediaLibrary.findMediaByTitle(book.getTitle());
		assertEquals(book, actualBook);

		this.mediaLibrary.delete(actualBook);
		Media deletedBook = this.mediaLibrary.findMediaByTitle(book.getTitle());
		assertNull(deletedBook);
	}

	@Test
	public void testGetSize() {
		long expectedSize = 13;
		for (int i = 0; i < expectedSize; i++) {
			DVD dvd = new DVD();
			this.mediaLibrary.save(dvd);
		}

		long actualSize = this.mediaLibrary.getSize();
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
		this.mediaLibrary.save(cd1);
		this.mediaLibrary.save(cd2);
		this.mediaLibrary.save(cd3);
		this.mediaLibrary.save(cd4);
		this.mediaLibrary.save(cd5);
		this.mediaLibrary.save(cd6);

		Collection<Media> actualMedias = this.mediaLibrary.findAllMedia(3, 4);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(cd4));
		assertTrue(actualMedias.contains(cd5));
		assertTrue(actualMedias.contains(cd6));
	}

	@Test
	public void testFindMediaAfter() {
		CD oldCD = new CD("I'm  to old to be a CD!", 1731);
		this.mediaLibrary.save(oldCD);

		CD notSoRecentCD = new CD("O yeah!", 2008);
		this.mediaLibrary.save(notSoRecentCD);

		CD newCD = new CD("I'm new!", 2013);
		this.mediaLibrary.save(newCD);

		Collection<Media> actualMedias = this.mediaLibrary.findMediaAfter(2000);
		assertEquals(2, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(newCD));
	}

	@Test
	public void testFindLastPublishedMedia() {
		CD oldCD = new CD("I'm  to old to be a CD!", 1731);
		this.mediaLibrary.save(oldCD);

		CD notSoRecentCD = new CD("O yeah!", 2008);
		this.mediaLibrary.save(notSoRecentCD);

		CD lastYearCD = new CD("Last year CD", 2012);
		this.mediaLibrary.save(lastYearCD);

		CD newCD = new CD("I'm new!", 2013);
		this.mediaLibrary.save(newCD);

		Collection<Media> actualMedias = this.mediaLibrary.findLastPublishedMedia(3);
		assertEquals(3, actualMedias.size());
		assertTrue(actualMedias.contains(notSoRecentCD));
		assertTrue(actualMedias.contains(lastYearCD));
		assertTrue(actualMedias.contains(newCD));
	}

}
