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

import java.util.Calendar;
import java.util.Date;

import twitter4j.internal.org.json.JSONObject;

public class SearchResultImpl implements SearchResult {

  JSONObject searchResultJSON;

  private String body;
  private String title;
  private String byline;
  private Date date;
  private String url;

  public SearchResultImpl(JSONObject json) {
    searchResultJSON = json;
    parse();
  }

  private void parse() {
    body = JSONParseUtil.getRawString("body", searchResultJSON);
    title = JSONParseUtil.getRawString("title", searchResultJSON);
    byline = JSONParseUtil.getRawString("byline", searchResultJSON);
    parseDate();
  }

  private void parseDate() {
    String sDate = JSONParseUtil.getRawString("date", searchResultJSON);
    int year = Integer.valueOf(sDate.substring(0, 3));
    int month = Integer.valueOf(sDate.substring(4, 5));
    int day = Integer.valueOf(sDate.substring(6, 7));
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    date = new Date(cal.getTimeInMillis());
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public String getByline() {
    return byline;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public String getTitle() {
    return title;
  }

}
