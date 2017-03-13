package com.zackyzhang.fragmentdi.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 3/13/17.
 */

public class GithubRepo {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

