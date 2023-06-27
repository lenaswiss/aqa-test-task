package org.example;

import org.example.models.Movie;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.security.SecureRandom;

public class MoviesListTest {

    ImbdApiClient apiClient = new ImbdApiClient();
    Assertion assertion = new Assertion();
    public static final String API_KYE = "k_41npf8kq";

    /**
     * Что нам нужно  - автотест, который
     * Будет заходить на IMDB (https://www.imdb.com/)
     * Открывать список топ фильмов IMDb Top 250 Movies
     * Собирать список топ 5 фильмов - их название, рейтинг и год выхода
     * Сохранять этом список обьектами типа Movies, которые имеют поля Position, Title, Year, Rating
     * Проверять что в нем есть “Крестный отец”
     * Переходить на страницу фильма  “Крестный отец” и проверять, что данные
     * (год, название и рейтинг) отображаются те же что и на странице списка фильмов
     */
    @Test
    public void top5MoviesTest() throws IOException {
        var topFiveMoviesList = apiClient.moviesService.getTop250Movies(API_KYE).execute().body().movies.subList(0, 5);
        Movie targetedMovie = new Movie();
        for (Movie m : topFiveMoviesList) {
            if (m.title.equals("The Godfather")) {
                targetedMovie = m;
                break;
            }
        }
        var targetedMovieTitle = apiClient.moviesService.getItemTitle(API_KYE, targetedMovie.id).execute().body();
        assertion.assertTrue(targetedMovieTitle.title.equals(targetedMovie.title),
                String.format("Titles are not equal. Expected %s, but found %s", targetedMovie.title, targetedMovieTitle.title));
        assertion.assertTrue(targetedMovieTitle.year == targetedMovie.year,
                String.format("Years are not equal. Expected %s, but found %s", targetedMovieTitle.year, targetedMovie.year));
        assertion.assertTrue(targetedMovieTitle.ratings.rating == targetedMovie.rating,
                "The rating of the movie in the top 5 Movies is not equal to the movie rating on the movie title page.");
    }

    /**
     * Нужно сформировать коллекцию из всех 250 топ фильмов в виде объектов класса Movies.
     * После этого зайти на страницу случайного фильма и проверить что показатели фильма
     * (Position, Title, Year, Rating) на странице соответствуют полученным ранее с общей страницы.
     */
    @Test
    public void movieInfoTest() throws IOException {
        var randomMovie = apiClient.moviesService.getTop250Movies(API_KYE).execute().body()
                .movies.get(new SecureRandom().nextInt(250));
        var randomMovieTitle = apiClient.moviesService.getItemTitle(API_KYE, randomMovie.id).execute().body();
        assertion.assertTrue(randomMovie.title.equals(randomMovieTitle.title), "Titles are equal.");
        assertion.assertTrue(randomMovie.year == randomMovieTitle.year, "Titles are equal.");
        assertion.assertTrue(randomMovie.rating == randomMovieTitle.ratings.rating,
                String.format("Ratings are not equal. Expected value %s, but found %s", randomMovie.rating, randomMovieTitle.ratings.rating));
    }
}
