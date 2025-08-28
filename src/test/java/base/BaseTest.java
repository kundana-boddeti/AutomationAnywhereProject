package base;

import Util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected String browser = ConfigReader.getProperty("browser");
    protected String url = ConfigReader.getProperty("baseURL");
    protected String userName= ConfigReader.getProperty("user");
    protected String password=ConfigReader.getProperty("password");
    protected String headless=ConfigReader.getProperty("headless");


    protected WebDriver driver;

    @BeforeMethod
    protected void setDriver() {
        if(browser.equalsIgnoreCase("Chrome") && headless.equalsIgnoreCase("true")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--window-size=1920,1080"); // Optional: set screen size
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("Chrome") && headless.equalsIgnoreCase("false")){
            driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName,password);
        System.out.println("Login completed successfully");


    }

    @AfterMethod
    protected void quitDriver(){
        driver.quit();
    }

}
