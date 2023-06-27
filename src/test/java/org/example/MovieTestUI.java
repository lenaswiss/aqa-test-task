package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.example.models.Movie;
import org.example.pages.BasePage;
import org.example.pages.MoviePage;
import org.example.pages.Top250MoviesPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class MovieTestUI {

    private static Movie searchingMovie;

    @BeforeMethod
    public void beforeMethod() {
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        open("https://www.imdb.com/");
        Configuration.timeout = 6000;
    }

    /**
     * Что нам нужно  - автотест, который
     * Будет заходить на IMDB (https://www.imdb.com/)
     * Открывать список топ фильмов IMDb Top 250 Movies
     * Собирать список топ 5 фильмов - их название, рейтинг и год выхода
     * Сохранять этом список обьектами типа Movies, которые имеют поля Position, Title, Year, Rating
     * Проверять что в нем есть “Крестный отец”
     * Переходить на страницу фильма  “Крестный отец” и проверять, что данные (год, название и рейтинг)
     * отображаются те же что и на странице списка фильмов
     * Мы хотим, чтобы этот тест был доступен на Github, а любой наш сотрудник мог их запустить их на
     * своем компьютере локально. И чтобы у него была инструкция, что для этого нужно.
     * Все сотрудники умеют использовать github и у них есть java, но не все умеют запускать тесты
     */
    @Test
    public void godfatherMovieTest() {
        BasePage.openTop250MoviesPage();
        var top5MoviesList = Top250MoviesPage.getTop250MoviesList().subList(0, 5);
        var foundMovie = Top250MoviesPage.findFirstMovieByTitle(top5MoviesList, "The Godfather");
        Top250MoviesPage.openMoviePageByPosition(foundMovie.position);
        Assert.assertTrue(MoviePage.getMovieInfoTitle().equals(foundMovie.title), "Titles are not equal");
        Assert.assertTrue(MoviePage.getMovieInfoYear().equals(foundMovie.year), "Year on the Top 250 list page is not equal to the year on the Movie page ");
        Assert.assertTrue(MoviePage.getMovieInfoPosition().equals(foundMovie.position), "Wrong movie Position on the Movie page.");
        Assert.assertTrue(MoviePage.getMovieInfoRating().equals(foundMovie.rating), "Movie rating does not match on the pages.");
    }

    /**
     * Задача со звездочкой)
     * Нужно сформировать коллекцию из всех 250 топ фильмов в виде объектов класса Movies.
     * После этого зайти на страницу случайного фильма и проверить что показатели фильма (Position,
     * Title, Year, Rating) на странице соответствуют полученным ранее с общей страницы.
     */
    @Test
    public void moviePageTest() {
        BasePage.openTop250MoviesPage();
        var foundMovie = Top250MoviesPage.getTop250MoviesList().get(new SecureRandom().nextInt(250));
        Top250MoviesPage.openMoviePageByPosition(foundMovie.position);
        System.out.println(MoviePage.getMovieInfoRating() + " and" + foundMovie.rating);
        Assert.assertTrue(MoviePage.getMovieInfoTitle().equals(foundMovie.title), "Titles are not equal");
        Assert.assertTrue(MoviePage.getMovieInfoYear().equals(foundMovie.year), "Year on the Top 250 list page is not equal to the year on the Movie page ");
        Assert.assertTrue(MoviePage.getMovieInfoPosition().equals(foundMovie.position), "Wrong movie Position on the Movie page.");
        Assert.assertTrue(MoviePage.getMovieInfoRating().equals(foundMovie.rating), "Movie rating does not match on the pages.");
    }

    @AfterMethod
    public static void afterMethods() {
        closeWebDriver();
    }
}
