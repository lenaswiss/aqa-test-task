package org.example.pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class Top250MoviesPage {

    public static ElementsCollection moviesList = $$("tbody tr");
    public static ElementsCollection ratings = $$("td.ratingColumn.imdbRating");
    public static ElementsCollection titles = $$("td.titleColumn a");
    //public static SelenideElement moviePageLink = $("td a");
    public static ElementsCollection positions = $$("td.titleColumn");
    public static ElementsCollection years = $$("td .secondaryInfo");


    public static List<Movie> getTop250MoviesList() {
        List<Movie> top250Movies = new ArrayList<>();
        var ratingsList = ratings.texts();
        var positionList = positions.texts();
        var yearsList = years.texts();
        var titleList = titles.texts();
        for (int i = 0; i < titleList.size(); i++) {
            String yearFormat = yearsList.get(i).replaceAll("^\\(|\\)$", "");
            var positionFormat = positionList.get(i).split(Pattern.quote("."));
            Movie movie = new Movie(titleList.get(i), positionFormat[0], yearFormat, ratingsList.get(i));
            top250Movies.add(movie);
        }
        return top250Movies;
    }

    public static void openMoviePageByPosition(String position) {
        $(String.format("[data-value = \"%s\"]", position)).parent().click();
    }

    public static Movie findFirstMovieByTitle(List<Movie> movieList, String title) {
        Movie foundMovie = new Movie();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).title.toLowerCase().contains(title.toLowerCase())) {
                foundMovie = movieList.get(i);
                break;
            }
        }
        return foundMovie;
    }

    public static class Movie {

        public String title;
        public String position;
        public String year;
        public String rating;

        Movie() {
        }

        Movie(String title, String position, String year, String rating) {
            this.position = position;
            this.title = title;
            this.year = year;
            this.rating = rating;
        }

    }


}