package de.fhb.sd.nyt.service;

import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class NewYorkTimesServiceTest {
	@Test
	public void testGetMostPopular() throws Exception {
		NewYorkTimesService service = new NewYorkTimesService();
		for (Message m : service.getMessages()) {
			System.out.println(((NewYorkTimesMessage)m).getTitle());
			System.out.println(m.getPublished());
		}
	}
}
