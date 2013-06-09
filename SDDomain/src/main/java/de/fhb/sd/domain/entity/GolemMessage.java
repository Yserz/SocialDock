package de.fhb.sd.domain.entity;

import javax.persistence.Entity;

/**
 * @author Christoph Ott
 */
@Entity
public class GolemMessage extends Message {
	private String imageURLSmall;
	private String imageURLMedium;
	private String title;

	public String getImageURLSmall() {
		return imageURLSmall;
	}

	public void setImageURLSmall(String imageURLSmall) {
		this.imageURLSmall = imageURLSmall;
	}

	public String getImageURLMedium() {
		return imageURLMedium;
	}

	public void setImageURLMedium(String imageURLMedium) {
		this.imageURLMedium = imageURLMedium;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
