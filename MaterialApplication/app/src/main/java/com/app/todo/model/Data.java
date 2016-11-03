package com.app.todo.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by niranjan on 11/3/16.
 */
@JsonObject
public class Data {
    @JsonField(name = "id")
    public int id;
    @JsonField(name = "name")
    public String name;
    @JsonField(name = "state")
    public int state;
}
