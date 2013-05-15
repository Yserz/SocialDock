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

import java.util.ArrayList;
import java.util.List;

import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class SearchResultListImpl implements SearchResultList {

  JSONArray json;
  List<JSONObject> searchResultsJSONList;
  List<Article> articles;
  List<SearchResult> searchResults;

  SearchResultListImpl(JSONArray json) throws JSONException {
    this.json = json;
    searchResultsJSONList = null;
    parse();
  }

  private void parse() throws JSONException {
    searchResultsJSONList = new ArrayList<JSONObject>();
    searchResults = new ArrayList<SearchResult>();
    articles = new ArrayList<Article>();
    for (int i = 0; i < json.length(); i++) {
      JSONObject ob = json.getJSONObject(i);
      searchResultsJSONList.add(ob);
      JSONFactoryImpl factory = new JSONFactoryImpl();
      searchResults.add(factory.searchResultFromJSON(ob));
      articles.add(factory.articleFromJSON(ob));
    }
  }

  @Override
  public List<Article> getArticles() {
    return articles;
  }

  @Override
  public List<SearchResult> getSearchResults() {
    return searchResults;
  }

}
