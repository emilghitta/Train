package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LocatorsTesting {
    String baseURL = "https://rahulshettyacademy.com/locatorspractice/";
    String expectedErrorMessage = "Incorrect username or password";
    String expectedWelcomeMessage = "Welcome to Rahul Shetty Academy";
    String expectedSuccessfullyLoggedInMessage = "You are successfully logged i.";
    String validUsername = "rahul";
    String validPassword = "rahulshettyacademy";
    String invalidUsername = "bla";
    String invalidPassword = "bla2";

    @Test
    void testingLocators() throws InterruptedException {
        //ID, name & className

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(baseURL);

        driver.findElement(By.id("inputUsername")).sendKeys(validUsername);
        driver.findElement(By.name("inputPassword")).sendKeys(invalidPassword);
        driver.findElement(By.className("signInBtn")).click();

        Thread.sleep(4000);
        Assert.assertTrue(driver.findElement(By.className("error")).getText().contains(expectedErrorMessage),"Incorrect error message is present");

        driver.quit();
    }

    @Test
    void testingLocators2() throws InterruptedException {
        //CSS selectors

        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.get(baseURL);

        driver.findElement(By.cssSelector("input#inputUsername")).sendKeys(validUsername);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(invalidPassword);
        driver.findElement(By.cssSelector("button.signInBtn")).click();

        Thread.sleep(4000);
        Assert.assertTrue(driver.findElement(By.className("error")).getText().contains(expectedErrorMessage),"Incorrect error message is present");

        driver.quit();

    }

    @Test
    void testingLocators3(){
        //Xpath and link text locators + CSS custom

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(baseURL);

        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(invalidUsername);
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys(invalidPassword);
        driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("test@gmail.com");

        driver.quit();

    }

    @Test
    void positiveLoginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        driver.manage().window().maximize();
        driver.get(baseURL);


        driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys(validUsername);
        driver.findElement(By.xpath("//input[contains(@type,'word')]")).sendKeys(validPassword);
        driver.findElement(By.cssSelector("button.submit")).click();

        Thread.sleep(2000);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'successfully logged')]")).getText(),expectedSuccessfullyLoggedInMessage,"Unexpected successfully logged in message");

        System.out.println("Also tested this");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]")).getText(),expectedWelcomeMessage,"Unexpected welcome message is present");

        getPassword(driver);
        softAssert.assertAll();


        driver.quit();
    }

    void getPassword(WebDriver driver){
        driver.get(baseURL);
        String text = driver.findElement(By.xpath("//p[contains(text(),'World')]")).getText();
        String[] textArray = text.split("on");
        System.out.println(textArray[0]);



    }



}
