package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LandingPage {

    @FindBy(xpath="//span[text()='Automation']")
    protected WebElement automationMenu;

    @FindBy(xpath="//span[text()='Create'][1]")
    protected WebElement createTask;

    @FindBy(xpath="//span[text()='Save']")
    protected WebElement saveButton;

    @FindBy(xpath="//span[text()='Close']")
    protected WebElement closeButton;

    @FindBy(xpath="//div[@class='datatable-rows']//a")
    protected List<WebElement> createdTasks;
}
