package org.example.models;

public class Title {
    public String id;
    public String title;
    public String type;
    public int year;
    public Ratings ratings;

    @Override
    public String toString() {
        return "Title{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", ratings=" + ratings +
                '}';
    }
}
