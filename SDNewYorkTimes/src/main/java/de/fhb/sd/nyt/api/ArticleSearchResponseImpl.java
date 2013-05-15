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

import java.util.List;

import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class ArticleSearchResponseImpl extends ResponseImpl implements ArticleSearchResponse {

  private int total;
  private JSONArray resultsJSON;
  private List<SearchResult> searchResults;
  private SearchResultList searchResultList;

  public ArticleSearchResponseImpl(JSONObject json) {
    super(json);
    parse();
  }

  private void parse() {
    total = JSONParseUtil.getInt("total", json);
    if (!json.isNull("results")) {
      try {
        resultsJSON = json.getJSONArray("results");
        searchResultList = new JSONFactoryImpl().searchResultListFromJSON(resultsJSON);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public int getTotal() {
    return total;
  }

  @Override
  public List<Article> getArticles() {
    return searchResultList.getArticles();
  }

  @Override
  public List<SearchResult> getSearchResults() {
    return searchResults;
  }

  @Override
  public List<JSONObject> getResultsAsJSONList() {
    return JSONParseUtil.JSONArrayToList(resultsJSON);
  }

}
