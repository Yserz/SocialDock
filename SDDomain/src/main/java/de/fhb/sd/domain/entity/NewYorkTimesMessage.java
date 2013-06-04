/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.domain.entity;

import de.fhb.sd.domain.entity.Message;

import javax.persistence.Entity;

/**
 * @author Christoph Ott
 */
@Entity
public class NewYorkTimesMessage extends Message {
	private String section;
	private String title;

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
