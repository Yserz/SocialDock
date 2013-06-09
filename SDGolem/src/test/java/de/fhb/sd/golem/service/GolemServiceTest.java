package de.fhb.sd.golem.service;

import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GolemServiceTest {
	@Test
	public void testGetMessages() throws Exception {
		GolemService service = new GolemService();
		for (Message m : service.getMessages()) {
			System.out.println(((GolemMessage)m).getTitle());
			System.out.println(m.getPublished());
		}
	}
}
