package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTests {
    String baseURl = "https://rahulshettyacademy.com/";
    String expectedPageTitle = "Rahul Shetty Academy";

    @Test
    void basicChromeTest(){

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(baseURl);
        System.out.println(driver.getPageSource());


        Assert.assertEquals(driver.getTitle(),expectedPageTitle,"Incorrect page title is present");
        Assert.assertEquals(driver.getCurrentUrl(),baseURl,"We are not on the correct url");

        driver.quit();

    }

    @Test
    void basicFirefoxTest(){
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.get(baseURl);

        Assert.assertEquals(driver.getTitle(),expectedPageTitle,"Incorrect page title is present");
        Assert.assertEquals(driver.getCurrentUrl(),baseURl,"We are not on the correct url");

        driver.quit();
    }

    @Test
    void basicMicrosoftEdgeTest(){
        System.setProperty("webdriver.edge.driver","src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.get(baseURl);
        driver.manage().window().maximize();

        Assert.assertEquals(driver.getTitle(),expectedPageTitle,"Incorrect page title is present");
        Assert.assertEquals(driver.getCurrentUrl(),baseURl,"We are not on the correct url");

        driver.quit();
    }


}
