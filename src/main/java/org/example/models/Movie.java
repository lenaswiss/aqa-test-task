package org.example.models;

import com.google.gson.annotations.SerializedName;

public class Movie {

    public String id;
    @SerializedName("rank")
    public int position;
    public String title;
    public int year;
    @SerializedName("imDbRating")
    public float rating;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
