package com.excilys.formation.mongodb.dao;

import java.net.UnknownHostException;

import org.junit.Before;

public class JongoMediaLibraryTest extends AbstractMediaLibraryTest {

	@Before
	public void setup() throws UnknownHostException {
		this.mediaLibrary = new JongoMediaLibrary();
		this.mediaLibrary.drop();
	}

}
