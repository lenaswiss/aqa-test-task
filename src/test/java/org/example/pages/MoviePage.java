package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MoviePage {

    public static SelenideElement titleMoviePage = $("h1 span");
    public static SelenideElement positionMoviePage = $("[data-testid=\"award_top-rated\"]");
    public static SelenideElement ratingMoviePage = $("[data-testid = \"hero-rating-bar__aggregate-rating__score\"]");
    public static SelenideElement yearMoviePage = $("[href*=\"tt_ov_rdat\" ]");

    public static String getMovieInfoRating() {
        var rating = ratingMoviePage.text().split("/");
        return rating[0].trim();
    }

    public static String getMovieInfoYear() {
        return yearMoviePage.text();
    }

    public static String getMovieInfoTitle() {
        return titleMoviePage.text();
    }

    public static String getMovieInfoPosition() {
        var position = positionMoviePage.text().split("#");
        return position[1];
    }
}
