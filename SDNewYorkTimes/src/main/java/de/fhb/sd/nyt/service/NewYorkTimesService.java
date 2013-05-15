/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhb.sd.nyt.service;

import java.util.logging.Logger;

import javax.ejb.Startup;
import javax.ejb.Stateless;

import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.nyt.api.MostPopularQuery;
import de.fhb.sd.nyt.api.MostPopularSearch;
import de.fhb.sd.nyt.api.NYTAPIKey;
import de.fhb.sd.nyt.api.ResourceType;
import de.fhb.sd.nyt.api.TimePeriod;

/**
 * This Bean gets articles from the NewYorkTimes.
 *
 * @author Christoph Ott
 */
@Stateless
@Startup
public class NewYorkTimesService implements NewYorkTimesLocal {

	private final static Logger LOG = Logger.getLogger(NewYorkTimesService.class.getName());

	public NewYorkTimesService() {
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	@Override
	public String getMostPopular() {
		String apiKey = "cfe88cd84c026683a2a1f8fb156b9709:6:67675712";
		MostPopularQuery mostPopularQuery = new MostPopularQuery(
				ResourceType.MOSTVIEWED, TimePeriod.THIRTY);
		MostPopularSearch mps = new MostPopularSearch(new NYTAPIKey(apiKey));
		return mps.search(mostPopularQuery);
	}

}
