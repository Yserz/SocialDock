package de.fhb.sd.nyt.service;

import org.junit.Test;


public class NewYorkTimesServiceTest {
	@Test
	public void testGetMostPopular() throws Exception {
		NewYorkTimesService servie = new NewYorkTimesService();
		System.out.println(servie.getMostPopular().get(1).getMessage());
	}
}
