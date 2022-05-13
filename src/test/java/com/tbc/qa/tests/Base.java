package com.tbc.qa.tests;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.*;

public class Base {



    public void initialize(){
        clearBrowserCookies();
        open("https://demoqa.com/books");

        Configuration.browserSize = "1920x1080";
    }

}
