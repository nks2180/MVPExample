package com.app.todo.model;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class TaskResponse$Data$$JsonObjectMapper extends JsonMapper<TaskResponse.Data> {
  @Override
  public TaskResponse.Data parse(JsonParser jsonParser) throws IOException {
    TaskResponse.Data instance = new TaskResponse.Data();
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
  public void parseField(TaskResponse.Data instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("id".equals(fieldName)) {
      instance.id = jsonParser.getValueAsInt();
    } else if ("name".equals(fieldName)) {
      instance.name = jsonParser.getValueAsString(null);
    } else if ("state".equals(fieldName)) {
      instance.state = jsonParser.getValueAsInt();
    }
  }

  @Override
  public void serialize(TaskResponse.Data object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    jsonGenerator.writeNumberField("id", object.id);
    if (object.name != null) {
      jsonGenerator.writeStringField("name", object.name);
    }
    jsonGenerator.writeNumberField("state", object.state);
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
