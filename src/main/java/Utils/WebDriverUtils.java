package Utils;

import io.opentelemetry.sdk.common.CompletableResultCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class WebDriverUtils {

    public static WebDriver setUpDriver() {
        String sBrowser = "chrome";
        //String sBrowser = "edge";
        String sDriverFolder = "C:\\Selenium\\";
//        String sPathDriverChrome = sDriverFolder + "C:\\Selenium\\chromedriver.exe\\";
//        String sPathDriverFirefox = sDriverFolder + "C:\\Selenium\\geckodriver.exe\\";
//        String sPathDriverMSEdge = sDriverFolder + "C:\\Selenium\\msedgedriver.exe\\";

        String sPathDriverChrome = sDriverFolder + "chromedriver.exe";
        String sPathDriverFirefox = sDriverFolder + "geckodriver.exe";
        String sPathDriverMSEdge = sDriverFolder + "msedgedriver.exe";

        WebDriver driver = null;

        switch (sBrowser) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", sPathDriverChrome);
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", sPathDriverFirefox);
                driver = new FirefoxDriver();
                break;
            }
            case "edge": {
                System.setProperty("webdriver.msedge.driver", sPathDriverMSEdge);
                driver = new EdgeDriver();
                break;
            }
            default: {
                Assert.fail("cannot create driver.Browser is not " + sBrowser + " supported!");
            }
        }

        //setup implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

        //maximize browser
        driver.manage().window().maximize();

        DateTimeUtils.wait(3);

        return driver;
    }


    public static void quitDriver(WebDriver driver){
        if(driver != null){
            driver.quit();
        }
    }


}


