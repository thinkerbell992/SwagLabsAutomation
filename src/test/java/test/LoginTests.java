package test;
import Utils.DateTimeUtils;
import Utils.WebDriverUtils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

//dodavanje suita na celu klasu
@Test(groups ={Groups.REGRESSION, Groups.LOGIN})

public class LoginTests {

    //WebDriver driver = null;

//    @BeforeMethod
//    public void setUp(){
//        driver = new ChromeDriver();
//        DateTimeUtils.wait(3);
//    }



    @Test(groups = {Groups.SANITY})
    //test for successful login
    public void testSuccessfulLogin() {

//dodato u webdriverutils
//        String sPathDriverChrome = "C:\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
//
//        System.setProperty("Web driver.chrome.driver", sPathDriverChrome);


        WebDriver driver= WebDriverUtils.setUpDriver();
        //driver = new ChromeDriver();

        DateTimeUtils.wait(3);

        try { //try i finally wrapup kada se ne zatvara u task manager-u chrome driver to handle


//            driver.manage().window().maximize();
//            //globalno ce onda posle svakog elementa cekati ne mora da se dodaje thread sleep a moze i z aodredjeni elemnt samo da se doda
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//            //Thread.sleep(3000);
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));


            driver.get("https://www.saucedemo.com/");
            DateTimeUtils.wait(3);

            //kada dohvatamo url
            String sUrl = driver.getCurrentUrl();
            System.out.println("url dohvaceni je: " + sUrl);

            WebElement UsernameTextField = driver.findElement(By.id("user-name")); // moze i ovde da se doda .sendKeys("standard_user"); ali je bolja praksa ovo dole
            UsernameTextField.sendKeys("standard_user");
            //Thread.sleep(1000);
            DateTimeUtils.wait(5);

            WebElement passwordTextField = driver.findElement(By.id("password"));
            passwordTextField.sendKeys("secret_sauce");
            //Thread.sleep(1000);
            DateTimeUtils.wait(1);

            WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait0.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));

//            //loginButton.isDispayled(); za login button da saceka prikaz
//            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(7));
//            wait1.until(ExpectedConditions.visibilityOf(loginButton));
//
//            // loginButton.isEnabled(); ceka dok se ne enable loging button
//            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
//            wait2.until(ExpectedConditions.elementToBeClickable(loginButton));
//
//
//            //samo za odredjen element wait (EXPLCIT WAIT)
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));

            WebElement LoginButton = driver.findElement(By.id("login-button"));
            LoginButton.click();
            //Thread.sleep(3000);
            DateTimeUtils.wait(5);

            String sActualUrl = driver.getCurrentUrl();
            String sExpectedUrl = "https://www.saucedemo.com/inventory.html";
            WebDriverWait wait4= new WebDriverWait(driver, Duration.ofSeconds(5));
            wait4.until(ExpectedConditions.urlToBe(sExpectedUrl));
            DateTimeUtils.wait(5);
            WebDriverWait wait3= new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement pageTitle = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header_container']//span[@class='title']")));
            String sActualTitle = pageTitle.getText();
            String sExpectedTitle = "Products";

            Assert.assertEquals(sActualTitle,sExpectedTitle, "Wrong Page Title");

        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }

    @Test //moze u zagradi da se doda (regression) na test level ili na celu klasu
    public void testUnsuccessfulLoginWrongPassword()  {

//        String sPathDriverChrome = "C:\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
//
//        System.setProperty("webdriver.chrome.driver", sPathDriverChrome);
//
       WebDriver driver = WebDriverUtils.setUpDriver();
        DateTimeUtils.wait(3);

        try { //try i finally wrapup kada se ne zatvara u task manager-u chrome driver to handle
// setapovano u webdriver utils
//            driver.manage().window().maximize();
//            DateTimeUtils.wait(2);

            driver.get("https://www.saucedemo.com/");

            String sUrl = driver.getCurrentUrl();
            System.out.println("url koji smo dohvatili je: " + sUrl);

            WebElement UsernameTextField = driver.findElement(By.id("user-name"));
            UsernameTextField.sendKeys("standard_user");
            DateTimeUtils.wait(2);

            WebElement PasswordTextField = driver.findElement(By.id("password"));
            PasswordTextField.sendKeys("kdsjladja");
            DateTimeUtils.wait(2);

            WebElement LoginButton = driver.findElement(By.id("login-button"));
            LoginButton.click();
            DateTimeUtils.wait(2);


            String sActualUrl = driver.getCurrentUrl();
            String sExpectedUrl = "https://www.saucedemo.com/";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.urlToBe(sExpectedUrl));
            Assert.assertEquals(sActualUrl,sExpectedUrl, "Not proper url!");
            DateTimeUtils.wait(2);
            String sExpectedErrorMessage = "Epic sad face: Username and password do not match any user in this service";
            WebElement ErrorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            String sActualErrorMessage= ErrorMessage.getText();
            //Assert.assertEquals(sActualErrorMessage, sExpectedErrorMessage, "wrong message");

        } finally{
          WebDriverUtils.quitDriver(driver);
        }

    }

//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
