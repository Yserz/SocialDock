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

import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/**
 * @author Chris Downes
 * 
 */
public class ArticleImpl implements Article {

  private String articleAbstract;
  private String author;
  private String body;
  private String byline;
  private List<Facet> classifiersFacet;
  private List<Facet> columnFacet;
  public boolean hasComments;
  public String getDate;
  public Facet getDayOfWeekFacet;
  public Facet getDesFacet;
  public boolean getFee;
  public Facet getGeoFacet;
  public String getLeadParagraph;
  public Facet getMaterialTypeFacet;

  JSONObject json;

  public ArticleImpl(JSONObject json) throws JSONException {
    this.json = new JSONObject(json.toString());
    parse();
  }

  private void parse() throws JSONException {
    System.out.println(json);
    articleAbstract = JSONParseUtil.getRawString("body", json);
    author = JSONParseUtil.getRawString("author", json);
    body = JSONParseUtil.getRawString("body", json);
    byline = JSONParseUtil.getRawString("byline", json);
    classifiersFacet = null;
    columnFacet = null;
    if (hasComments = JSONParseUtil.getBooleanAsInt("comments", json) == -1) {
      hasComments = false;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getAbstract()
   */
  @Override
  public String getAbstract() {
    return articleAbstract;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getAuthor()
   */
  @Override
  public String getAuthor() {
    return author;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getBody()
   */
  @Override
  public String getBody() {
    return body;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getByline()
   */
  @Override
  public String getByline() {
    return byline;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getClassifiersFacet()
   */
  @Override
  public List<Facet> getClassifiersFacet() {
    return classifiersFacet;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getColumnFacet()
   */
  @Override
  public List<Facet> getColumnFacet() {
    return columnFacet;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#hasComments()
   */
  @Override
  public boolean hasComments() {
    return hasComments;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getDate()
   */
  @Override
  public String getDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getDayOfWeekFacet()
   */
  @Override
  public Facet getDayOfWeekFacet() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getDesFacet()
   */
  @Override
  public Facet getDesFacet() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getFee()
   */
  @Override
  public boolean getFee() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getGeoFacet()
   */
  @Override
  public Facet getGeoFacet() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getLeadParagraph()
   */
  @Override
  public String getLeadParagraph() {
    return body;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.downes.nyt4j.Article#getMaterialTypeFacet()
   */
  @Override
  public Facet getMaterialTypeFacet() {
    return null;
  }

}
