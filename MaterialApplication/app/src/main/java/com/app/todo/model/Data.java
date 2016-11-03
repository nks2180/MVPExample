package com.app.todo.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by niranjan on 11/3/16.
 */
@JsonObject
public class Data extends RealmObject {

    public static final String KEY_ID = "id";

    @JsonField(name = "id")
    @PrimaryKey
    public int id;

    @JsonField(name = "name")
    public String name;

    @JsonField(name = "state")
    public int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
