package com.app.todo.model;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unsafe,unchecked")
public final class TaskResponse$$JsonObjectMapper extends JsonMapper<TaskResponse> {
  private static final JsonMapper<Data> COM_APP_TODO_MODEL_DATA__JSONOBJECTMAPPER = LoganSquare.mapperFor(Data.class);

  @Override
  public TaskResponse parse(JsonParser jsonParser) throws IOException {
    TaskResponse instance = new TaskResponse();
    if (jsonParser.getCurrentToken() == null) {
      jsonParser.nextToken();
    }
    if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jsonParser.skipChildren();
      return null;
    }
    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
      String fieldName = jsonParser.getCurrentName();
      jsonParser.nextToken();
      parseField(instance, fieldName, jsonParser);
      jsonParser.skipChildren();
    }
    return instance;
  }

  @Override
  public void parseField(TaskResponse instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("data".equals(fieldName)) {
      if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
        ArrayList<Data> collection1 = new ArrayList<Data>();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          Data value1;
          value1 = COM_APP_TODO_MODEL_DATA__JSONOBJECTMAPPER.parse(jsonParser);
          collection1.add(value1);
        }
        instance.data = collection1;
      } else {
        instance.data = null;
      }
    }
  }

  @Override
  public void serialize(TaskResponse object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    final List<Data> lslocaldata = object.data;
    if (lslocaldata != null) {
      jsonGenerator.writeFieldName("data");
      jsonGenerator.writeStartArray();
      for (Data element1 : lslocaldata) {
        if (element1 != null) {
          COM_APP_TODO_MODEL_DATA__JSONOBJECTMAPPER.serialize(element1, jsonGenerator, true);
        }
      }
      jsonGenerator.writeEndArray();
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
