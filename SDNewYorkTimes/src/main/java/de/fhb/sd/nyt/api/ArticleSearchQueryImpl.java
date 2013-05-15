// Copyright 2012 Chris Downes. 
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// //////////////////////////////////////////////////////////////////////////////
package de.fhb.sd.nyt.api;

import java.net.MalformedURLException;
import java.net.URL;

import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class ArticleSearchQueryImpl implements ArticleSearchQuery {

  private NYTAPIKey apiKey;
  private static String ARTICLE_SEARCH_BASE_URL = "http://api.nytimes.com/svc/search/v1/article";
  private JSONObject responseJSON;

  public ArticleSearchQueryImpl(NYTAPIKey key) {
    apiKey = key;
  }

  @Override
  public ArticleSearchResponse query(Query query) {
    return null;
  }

  @Override
  public ArticleSearchResponse query(String query) {
    try {
      return query(new URL(pBase() + pQuery(query) + pAPIKey()));
    } catch (MalformedURLException e) {
      return null;
    }
  }

  private ArticleSearchResponse query(URL url) {
    System.out.println(url.toString());
    ArticleSearchResponse searchResult = null;
    try {
      responseJSON = new JSONObject(HttpRequest.request(url));
      System.out.println(responseJSON.toString());
      if (null != responseJSON) {
        searchResult = new JSONFactoryImpl().searchResponseFromJSON(responseJSON);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return searchResult;
  }

  private String pBase() {
    return ARTICLE_SEARCH_BASE_URL;
  }

  private String pAPIKey() {
    return "&api-key=" + apiKey.getKey();
  }

  private String pQuery(String query) {
    return "?query=" + query;
  }

}
