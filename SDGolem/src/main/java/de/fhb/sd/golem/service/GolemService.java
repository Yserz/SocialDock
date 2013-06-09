package de.fhb.sd.golem.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.golem.util.*;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * This Bean connects to Twitter and streams messages.
 *
 * @author Christoph Ott
 */
@Stateless
@Startup
public class GolemService implements GolemLocal {

	private final static Logger LOG = Logger.getLogger(GolemService.class.getName());
	//Key der die App identifizieren
	private final String devKey = "4369a21316d648c8c847d40ab0225936";
	private List<Message> messages;
	private Date lastTimeExecuted = new Date();

	public GolemService() {
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public List<Message> getMessages() {
		if (messages != null && (new Date().getTime() - lastTimeExecuted.getTime()) < 300000) {
			return messages;
		}
		messages = new ArrayList<>();
		try {
			initGolemTopArticles();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	private void initGolemTopArticles() throws MalformedURLException {
		URL golemURL = new URL("http://api.golem.de/api/article/top/?key=" + devKey + "&format=json");
		String jsonString = HttpRequest.request(golemURL);
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<GolemDataList<GolemArticle>>() {
		}.getType();
		GolemDataList<GolemArticle> data = gson.fromJson(jsonString, type);
		for (GolemArticle golemArticle : data.data) {
			long articleId = Long.valueOf(golemArticle.articleid);
			GolemMetaData metaData = getGolemMetaData(articleId);
			GolemMessage golemMessage = new GolemMessage();
			golemMessage.setId(articleId);
			golemMessage.setPublished(new Date(metaData.date));
			golemMessage.setTitle(golemArticle.headline);
			golemMessage.setURL(golemArticle.headline);
			golemMessage.setAuthor(metaData.author);
			golemMessage.setMessage(golemArticle.abstracttext);
			if (golemArticle.numOfImages > 0) {
				List<GolemImageContainer> container = getGolemImageContainer(golemMessage.getId());
				golemMessage.setImageURLSmall(container.get(0).small.url);
				golemMessage.setImageURLMedium(container.get(0).medium.url);
			}else{
				golemMessage.setImageURLSmall("");
				golemMessage.setImageURLMedium("");
			}
			messages.add(golemMessage);
		}
	}

	private List<GolemImageContainer> getGolemImageContainer(long articleId) throws MalformedURLException {
		URL golemURL = new URL("http://api.golem.de/api/article/images/" + articleId + "/?key=" + devKey + "&format=json");
		String jsonString = HttpRequest.request(golemURL);
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<GolemDataList<GolemImageContainer>>() {
		}.getType();
		GolemDataList<GolemImageContainer> data = gson.fromJson(jsonString, type);
		return data.data;
	}

	private GolemMetaData getGolemMetaData(long articleId) throws MalformedURLException {
		URL golemURL = new URL("http://api.golem.de/api/article/meta/" + articleId + "/?key=" + devKey + "&format=json");
		String jsonString = HttpRequest.request(golemURL);
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<GolemData<GolemMetaData>>() {
		}.getType();
		GolemData<GolemMetaData> data = gson.fromJson(jsonString, type);
		data.data.date = data.data.date * 1000l; // Getting UNIX-Timestamp
		return data.data;
	}
}
