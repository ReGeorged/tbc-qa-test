package com.tbc.qa.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TaskTwoTest extends Base{

    @BeforeMethod
    void SetUp(){
        initialize();

    }

    @Test(priority = 1)
    void PublisherAndTitleMatchTest() throws InterruptedException {
        List publisherList  = $$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div//div/div[4][contains(text(),'')]")).texts();
        List bookNameList =$$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]/div/div[2]/div/span/a[contains(text(),'')]")).texts();

        $(By.xpath("//*[@id=\"searchBox\"]")).sendKeys("JavaScript");

        int matchedCount = 0;
        for (int i = 1; i<=bookNameList.size();i++){
            if(publisherList.get(i).toString().equals("O\'Reilly Media") && bookNameList.get(i).toString().contains("JavaScript")) {
                System.out.println(bookNameList.get(i));
                matchedCount += 1;

            }
        }
        Assert.assertEquals(matchedCount,10,"no match!");


    }

    @Test(priority = 2)
    void CheckFirstBook(){
        List bookNameList =$$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]/div/div[2]/div/span/a[contains(text(),'JavaScript')]")).texts();

        $(By.xpath("//*[@id=\"searchBox\"]")).sendKeys("JavaScript");
        Assert.assertTrue(bookNameList.get(0).toString().equals("Learning JavaScript Design Patterns"));
    }

    @Test(priority = 3)
    void UseSearchAndFilterResults() throws InterruptedException {
        List bookNameList =$$(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[*]/div/div[2]/div/span/a[contains(text(),'JavaScript')]")).texts();

        for(int  i= 0; i<bookNameList.size()-1;i++) {
            String currentBook = bookNameList.get(i).toString();
            String currentBookXPath = $$(By.xpath("//*[@id=\"userName-value\"][contains(text(),'"+currentBook+"')]")  ).toString();

            $(By.xpath("//*[@id=\"searchBox\"]")).sendKeys("JavaScript");
            $(By.xpath("//a[contains(text(),'"+currentBook+"')]")).click();

            Assert.assertTrue(currentBookXPath.contains(currentBook));
            back();
        }
    }

    @AfterMethod
    void TearDown(){
        closeWebDriver();
    }
}
