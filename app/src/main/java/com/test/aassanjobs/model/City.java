package com.test.aassanjobs.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class City {
    @SerializedName("id")
    long id;

    @SerializedName("name")
    String name;

    @SerializedName("slug")
    String slug;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return name;
    }
}
