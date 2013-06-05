package de.fhb.sd.nyt.util;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
	public String type;
	public String subtype;
	@SerializedName("media-metadata")
	public List<MediaMetaData> metaData;
}
