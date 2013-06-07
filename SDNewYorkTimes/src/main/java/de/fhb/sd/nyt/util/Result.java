package de.fhb.sd.nyt.util;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result {
	public String section;
	public String title;
	public String published_date;
	public String byline;
	@SerializedName("abstract")
	public String isAbstract;
	public List<Media> media;

	public String getSmallImageURL(){
		if (media != null && !media.isEmpty()) {
			for (Media m : media){
				if (m.metaData.get(0).height < 100){
					return m.metaData.get(0).url;
				}
			}
		}
		return "";
	}
}
