package de.fhb.sd.nyt.util;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Result {
	public String section;
	public String title;
	public Date published_date;
	@SerializedName("abstract")
	public String isAbstract;
}
