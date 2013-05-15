package de.fhb.sd.nyt.api;

import org.junit.Test;

import de.fhb.sd.nyt.data.MostPopular;
import de.fhb.sd.nyt.data.Result;

public class NYTTest {

	@Test
	public void test() {
		String apiKey = "cfe88cd84c026683a2a1f8fb156b9709:6:67675712";
		MostPopularQuery mostPopularQuery = new MostPopularQuery(
				ResourceType.MOSTVIEWED, TimePeriod.THIRTY);
		MostPopularSearch mps = new MostPopularSearch(new NYTAPIKey(apiKey));
		// List<Article> articles =
		MostPopular mp = mps.search(mostPopularQuery);
		for (Result r : mp.results)
			System.out.println(r.isAbstract);
	}

}
