package de.fhb.sd.nyt.service;

import de.fhb.sd.domain.entity.Message;
import org.junit.Test;


public class NewYorkTimesServiceTest {
	@Test
	public void testGetMostPopular() throws Exception {
		NewYorkTimesService service = new NewYorkTimesService();
		for (Message m : service.getMessages()) {
			System.out.println(m);
		}
	}
}
