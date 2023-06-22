package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.AfterMethod;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BasePage {

    public static SelenideElement contextMenu = $("#iconContext-menu");
    public static SelenideElement contextMenuMovie = $("#iconContext-movie");
    public static SelenideElement contextMenuTop250 = $("[href = '/chart/top/?ref_=nv_mv_250']");

    public static void openTop250MoviesPage(){
        contextMenu.click();
        contextMenuTop250.click();
    }

    @AfterMethod
    public void afterMethods() {
        closeWebDriver();
    }
}
