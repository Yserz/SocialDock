package de.fhb.sd.web.data.nyt;

import com.google.gson.annotations.SerializedName;

public class Result {
	public String section;
	public String title;
	
	@SerializedName("abstract")
	public String isAbstract;
}
