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

import java.util.Date;
import java.util.List;

public class QueryImpl implements Query {

  private String simpleQueryString;

  private Date beginDate;
  private Date endDate;
  private ResponseFormat responseFormat;
  private int offset;
  private Rank rank;

  private List<Facet> facets;

  public QueryImpl(String simpleQuery) {
    simpleQueryString = simpleQuery;
  }

  public QueryImpl() {

  }

  @Override
  public Date getBeginDate() {
    return beginDate;
  }

  @Override
  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  @Override
  public Date getEndDate() {
    return endDate;
  }

  @Override
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Override
  public ResponseFormat getResponseFormat() {
    return responseFormat;
  }

  @Override
  public void setResponseFormat(ResponseFormat format) {
    this.responseFormat = format;
  }

  @Override
  public int getOffset() {
    return offset;
  }

  @Override
  public void setOffset(int offset) {
    this.offset = offset;
  }

  @Override
  public Rank getRank() {
    return rank;
  }

  @Override
  public void setRank(Rank rank) {
    this.rank = rank;
  }

  @Override
  public String getSimpleQueryString() {
    return simpleQueryString;
  }

  @Override
  public List<Facet> getFacets() {
    return facets;
  }

  @Override
  public void setFacets(List<Facet> facets) {
    this.facets = facets;

  }

  @Override
  public void addFacet(Facet facet) {
    facets.add(facet);
  }

  @Override
  public void removeFacet(Facet facet) {
    facets.remove(facet);
  }

}
