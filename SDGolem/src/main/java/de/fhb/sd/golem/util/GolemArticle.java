package de.fhb.sd.golem.util;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GolemArticle {
	public String abstracttext;
	public String articleid;
	public String headline;
	public String url;
	@SerializedName("images")
	public Integer numOfImages;

}
