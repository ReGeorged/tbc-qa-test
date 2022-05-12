package com.tbc.qa.tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.testng.TestRunner.PriorityWeight.priority;

public class TaskOneTest {

    @BeforeMethod
    void setUp(){
        open("https://demoqa.com/books");
        Configuration.browserSize = "1920x1080";


    }

    @Test(priority = 1)
    void checkUp(){
        Assert.assertTrue($(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div")).isDisplayed());
    }


    @Test(priority = 2)
    void BookImageTest() throws Exception {
        int bookCount = $$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]")).size()-2;
        for(int i = 1; i<=bookCount;i++) {
            try {
                $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]" +"/div/div[1]/img"));
                }catch (Exception e){
                throw new Exception("#"+i+" book image not found");
            }
        }
    }

    @Test(priority = 3)
    void BookTitleTest() throws Exception{
        int bookCount = $$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]")).size()-2;

        for(int i = 1; i<=bookCount;i++) {
            try {
                Assert.assertTrue($(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]" +"/div/div[2]/div/span/a")).getText()!="");
            }catch (Exception e){
                throw new Exception("#"+i+" book title not found");
            }
        }
    }

    @Test(priority = 4)
    void BookAuthorTest() throws Exception{
        //Thread.sleep(20000);
        int bookCount = $$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]")).size()-2;
        for(int i = 1; i<=bookCount;i++) {
            try {
                Assert.assertTrue($(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]" +"/div/div[3][normalize-space(text())]")).getText()!="");
            }catch (Exception e){
                throw new Exception("#"+i+" book author not found");
            }
        }
    }
    @Test(priority = 5)
    void BookPublisherTest()throws Exception{
        Thread.sleep(20000);
        int bookCount = $$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]")).size()-2;

        for(int i = 1; i<=bookCount;i++) {
            try {
                 Assert.assertTrue($(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]" +"/div/div[4][normalize-space(text())]")).getText()!="");
            }catch (Exception e){
                throw new Exception("#"+i+" book publisher not found");
            }
        }

    }

    @AfterMethod
    void TearDown(){
        closeWebDriver();
    }






}
