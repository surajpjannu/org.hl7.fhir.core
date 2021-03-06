package org.hl7.fhir.r5.openapi;

/*-
 * #%L
 * org.hl7.fhir.r5
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ServerVariableWriter extends BaseWriter {

  public ServerVariableWriter(JsonObject object) {
    super(object);
  }

  public ServerVariableWriter enumValue(List<String> values) {
    JsonArray enums = forceArray("enum");
    for (String s : values)
      enums.add(new JsonPrimitive(s));
    return this;            
  }
  
  public ServerVariableWriter defaultValue(String value) {
    object.addProperty("default", value);
    return this;            
  }
  
  public ServerVariableWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
}
