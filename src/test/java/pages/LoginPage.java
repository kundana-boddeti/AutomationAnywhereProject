package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//WebElements for login page
public class LoginPage {

    private WebDriver driver;


    @FindBy(xpath="//input[@type='text' and @name='username']")
    private WebElement userNameInput;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath="//button[@name='submitLogin']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String user, String pass) {
        System.out.println("=====Test started :)=====");
        userNameInput.sendKeys(user);
        passwordInput.sendKeys(pass);
        if(loginButton.isEnabled())
            loginButton.click();
    }
}
