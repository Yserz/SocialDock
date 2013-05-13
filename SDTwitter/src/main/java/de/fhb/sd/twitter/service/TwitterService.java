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
package de.fhb.sd.twitter.service;

import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.twitter.domain.TwitterMessage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;

/**
 * This Bean connects to Twitter and streams messages.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Stateless
@Startup
public class TwitterService implements TwitterLocal {

	private final static Logger LOG = Logger.getLogger(TwitterService.class.getName());
	//Keys die die App identifizieren
	private final String consumerKey = "XofYnF58nnR1fBIwGq3dQ";
	private final String consumerKeySecure = "XtXFcPUzhjQAoDTRQTA7jm3Pw2m3IRX1fDf3kALqBUg";
	//Keys die den Account des Users identifizieren
	private final String token = "403358935-CXqlVYe8nKLBm9buxU55vES9HSBdgG5fbCLfOo";
	private final String tokenSecret = "2W6d3aNWLYTLcxWCsXDoBesDsiJADh7B0iWxERa9AnU";
	private TwitterStream twitterStream;

	public TwitterService() {
		twitterStream = new TwitterStreamFactory().getInstance();
		AccessToken givenAccessToken = new AccessToken(token, tokenSecret);
		twitterStream.setOAuthConsumer(consumerKey, consumerKeySecure);
		twitterStream.setOAuthAccessToken(givenAccessToken);

		twitterStream.addListener(new BaseUserStreamListener());
	}

	public String hello() {
		return "hello";
	}

	@Override
	public void start() {
		twitterStream.sample();
	}

	@Override
	public void stop() {
		twitterStream.shutdown();
	}

	private class BaseUserStreamListener implements UserStreamListener {

		@Override
		public void onDeletionNotice(long l, long l1) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onFriendList(long[] longs) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onFavorite(User user, User user1, Status status) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUnfavorite(User user, User user1, Status status) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onFollow(User user, User user1) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onDirectMessage(DirectMessage dm) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListMemberAddition(User user, User user1, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListMemberDeletion(User user, User user1, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListSubscription(User user, User user1, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListUnsubscription(User user, User user1, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListCreation(User user, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListUpdate(User user, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserListDeletion(User user, UserList ul) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUserProfileUpdate(User user) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onBlock(User user, User user1) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onUnblock(User user, User user1) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}
		//########################

		@Override
		public void onStatus(Status status) {
			LOG.log(Level.INFO, "AktStatus: {0}, FromUser: {1}, AktStatusraw: {2}",
					new Object[]{status.getText(),
				status.getUser().getScreenName(),
				status});

			Message message = new TwitterMessage();
			message.setAuthor(status.getText());
			message.setMessage(status.getText());

			//TODO Push data (maybe websockets or jms)
		}

		@Override
		public void onDeletionNotice(StatusDeletionNotice sdn) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}
		//########################

		@Override
		public void onTrackLimitationNotice(int i) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}

		@Override
		public void onScrubGeo(long l, long l1) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}
		//########################

		@Override
		public void onException(Exception excptn) {
			if (excptn instanceof TwitterException) {
				LOG.log(Level.SEVERE, handleTwitterException((TwitterException) excptn), excptn);
				//TODO
			} else {
				LOG.log(Level.SEVERE, null, excptn);
				//TODO
			}
		}

		@Override
		public void onStallWarning(StallWarning sw) {
//			LOG.log(Level.SEVERE, "Not supported yet.");
		}
	}

	/**
	 * Methode um gebündelt Twitterexception zu behandeln und in
	 * Klartextmeldungen ausgeben zu koennen.
	 *
	 * @param ex
	 */
	public String handleTwitterException(Exception e) {
		if (e instanceof TwitterException) {
			TwitterException ex = (TwitterException) e;
			if (400 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "Rate limit exceeded. Remaining requests " + ex.getRateLimitStatus().getRemaining() + ". "
						+ "\nThe next reset is " + new Date(ex.getRateLimitStatus().getResetTimeInSeconds() + (System.currentTimeMillis() * 1000)), ex);
				return "Rate limit exceeded. Remaining requests " + ex.getRateLimitStatus().getRemaining() + ". "
						+ "\nThe next reset is " + new Date(ex.getRateLimitStatus().getResetTimeInSeconds() + (System.currentTimeMillis() * 1000));
			} else if (401 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "Authentication credentials were missing or incorrect.", ex);
				return "Authentication credentials were missing or incorrect.";
			} else if (403 == ex.getStatusCode()) {
				LOG.log(Level.SEVERE, "Duplicated status.", ex);
				return "Duplicated status.";
			} else if (404 == ex.getStatusCode()) {
				LOG.log(Level.SEVERE, "The URI requested is invalid or the resource requested, such as a user, does not exists.", ex);
				return "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			} else if (406 == ex.getStatusCode()) {
				LOG.log(Level.SEVERE, "Request returned - invalid format is specified in the request.", ex);
				return "Request returned - invalid format is specified in the request.";
			} else if (420 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "Too many logins with your account in a short time.", ex);
				return "Too many logins with your account in a short time.";
			} else if (500 == ex.getStatusCode()) {
				LOG.log(Level.SEVERE, "Something is broken. Please post to the group so the Twitter team can investigate.", ex);
				return "Something is broken. Please post to the group so the Twitter team can investigate.";
			} else if (502 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "Twitter is down or being upgraded.", ex);
				return "Twitter is down or being upgraded.";
			} else if (503 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "The Twitter servers are up, but overloaded with requests. Try again later.", ex);
				return "The Twitter servers are up, but overloaded with requests. Try again later.";
			} else if (-1 == ex.getStatusCode()) {
				stop();
				LOG.log(Level.SEVERE, "Can not connect to the internet or the host is down.", ex);
				return "Can not connect to the internet or the host is down.";
			} else {
				stop();
				LOG.log(Level.SEVERE, "Unknown twitter-error occured.", ex);
				return "Unknown twitter-error occured.";
			}
		} else {
			LOG.log(Level.SEVERE, "Thats not a TwitterException.", e);
			return "Thats not a TwitterException.";
		}

	}
}
