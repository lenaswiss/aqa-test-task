package org.example.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {

    @SerializedName("items")
    public List<Movie> movies;

    @Override
    public String toString() {
        return "Movies{" +
                "movies=" + movies +
                '}';
    }
}
