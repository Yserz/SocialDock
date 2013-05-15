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

public interface Query {

  String getSimpleQueryString();

  Date getBeginDate();

  void setBeginDate(Date beginDate);

  Date getEndDate();

  void setEndDate(Date endDate);

  ResponseFormat getResponseFormat();

  void setResponseFormat(ResponseFormat format);

  int getOffset();

  void setOffset(int offset);

  Rank getRank();

  void setRank(Rank rank);

  List<Facet> getFacets();

  void setFacets(List<Facet> facets);

  void addFacet(Facet facet);

  void removeFacet(Facet facet);

  // void clearReturnFields();
  // Set<Field> getReturnFields();
  //
  // void setReturnFields(Set<Field> returnFields);

  // void returnBodyField(boolean b);
  // void returnTitleField(boolean b);
  // void returnBylineField(boolean b);
  // void returnDateField(boolean b);
  // void returnAbstractField(boolean b);
  // void returnURLField(boolean b);
  // void returnLeadParagraphField(boolean b);

}
