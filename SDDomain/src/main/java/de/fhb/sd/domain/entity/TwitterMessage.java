/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.domain.entity;

import javax.persistence.Entity;

/**
 * @author MacYser
 */
@Entity
public class TwitterMessage extends Message {
	private String profileImageUrlOfUser;

	public String getProfileImageUrlOfUser() {
		return profileImageUrlOfUser;
	}

	public void setProfileImageUrlOfUser(String profileImageUrlOfUser) {
		this.profileImageUrlOfUser = profileImageUrlOfUser;
	}
}
