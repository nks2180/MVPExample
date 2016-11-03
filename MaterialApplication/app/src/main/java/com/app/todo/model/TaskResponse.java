package com.app.todo.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by mohitesh on 03/11/16.
 */

@JsonObject
public class TaskResponse {

    @JsonField(name = "data")
    public List<Data> data;

}
