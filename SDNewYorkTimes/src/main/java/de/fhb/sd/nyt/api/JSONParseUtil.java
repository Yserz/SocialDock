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

public class JSONParseUtil {

  public static List<JSONObject> JSONArrayToList(JSONArray array) {
    List<JSONObject> json = new ArrayList<JSONObject>();
    try {
      for (int i = 0; i < array.length(); i++) {
        json.add(array.getJSONObject(i));
      }
    } catch (JSONException e) {
      return null;
    }
    return json;
  }

  public static int getInt(String key, JSONObject json) {
    try {
      if (json.isNull(key)) {
        return -1;
      } else {
        return json.getInt(key);
      }
    } catch (JSONException e) {
      return -1;
    }
  }

  public static long getLong(String key, JSONObject json) {
    try {
      if (json.isNull(key)) {
        return -1;
      } else {
        return json.getLong(key);
      }
    } catch (JSONException e) {
      return -1;
    }
  }

  public static String getRawString(String key, JSONObject json) {
    try {
      if (json.isNull(key)) {
        return null;
      } else {
        return json.getString(key);
      }
    } catch (JSONException e) {
      return null;
    }
  }

  public static JSONArray getJSONArray(String key, JSONObject json) {
    try {
      if (json.isNull(key)) {
        return null;
      } else {
        return json.getJSONArray(key);
      }
    } catch (JSONException e) {
      return null;
    }
  }

  public static int getBooleanAsInt(String key, JSONObject json) {
    try {
      if (!json.isNull(key)) {
        return Integer.valueOf(json.getString(key));
      }
    } catch (JSONException e) {
    }
    return -1;
  }
}
