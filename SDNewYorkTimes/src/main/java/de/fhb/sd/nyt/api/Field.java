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

public enum Field {

  BODY("body"), BYLINE("byline"), DATE("date"), LEAD_PARAGRAPH("lead_paragraph"), TITLE("title"), URL(
      "url"), ABSTRACT("abstract"), GEO_FACET("geo_facet"), ORG_FACET("org_facet"), PER_FACET(
      "per_facet"), CLASSIFIERS_FACET("classifiers_facet");

  private String string;

  Field(String s) {
    string = s;
  }

  public String toString() {
    return string;
  }

}
