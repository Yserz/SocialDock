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
package de.fhb.sd.google.service;

import de.fhb.sd.api.google.GoogleLocal;
import java.util.logging.Logger;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 * This Bean connects to Google and streams messages.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Stateless
@Startup
public class GoogleService implements GoogleLocal {

	private final static Logger LOG = Logger.getLogger(GoogleService.class.getName());

	public GoogleService() {
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}
}
