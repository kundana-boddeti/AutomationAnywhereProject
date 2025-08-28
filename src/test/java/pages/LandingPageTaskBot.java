package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LandingPageTaskBot extends LandingPage{

    private WebDriver driver;
    private final String messageBoxName="TaskBot"+String.valueOf(System.currentTimeMillis());
    private final String descriptionName="this is a message box bot";
    private final String searchBot="Message box";
    private final String message="Message created for user";



    @FindBy(xpath="//button[@name='createTaskbot']")
    private WebElement taskBotdropDown;

    @FindBy(xpath="//button[@role='radio' and @aria-label='macOS']")
    private WebElement OSbutton;

    @FindBy(xpath="//input[@name='name']")
    private WebElement nameText;

    @FindBy(xpath="//input[@name='description']")
    private WebElement descriptionText;

    @FindBy(xpath="//span[text()='Create & edit']")
    private WebElement createEditButton;

    //20seconds time

    @FindBy(xpath="//input[@placeholder='Search actions']")
    private WebElement searchText;


    //  type in message box and double click on
    @FindBy(xpath="//button[@name='item-button']//span[text()='Message box'][1]")
    private WebElement messageBox;

    // able to add title
    @FindBy(xpath="//div[@name='title']")
    private WebElement messageTitle;

    @FindBy(xpath="//div[@name='content']")
    private WebElement messageContent;

    @FindBy(xpath="//div[@name='scrollLines']")
    private WebElement messagescrollLines;

    @FindBy(xpath="//span[@role='checkbox']//input[@name='closeMsgBox']")
    private WebElement messagecloseBox;

    @FindBy(xpath="//div[@name='timeOut']")
    private WebElement messageTimeout;

    public LandingPageTaskBot(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyMessageBoxFunctionality() {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));

        automationMenu.click();
        createTask.click();
        taskBotdropDown.click();
        OSbutton.click();

        nameText.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", nameText);

        nameText.sendKeys(messageBoxName);

        descriptionText.click();
        descriptionText.clear();
        descriptionText.sendKeys(descriptionName);


        createEditButton.click();

        wait.until(ExpectedConditions.visibilityOf(searchText));

        searchText.click();
        searchText.clear();
        searchText.sendKeys(searchBot);


        Actions actions = new Actions(driver);
        actions.doubleClick(messageBox).perform();


        messageContent.click();
        messageContent.clear();
        messageContent.sendKeys(message);


         if(!(messagecloseBox.isSelected())) {
             js.executeScript("arguments[0].click();", messagecloseBox);
         }


        saveButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(closeButton));

        closeButton.click();

        System.out.println("Message box created");

        wait.until(ExpectedConditions.visibilityOfAllElements(createdTasks));

        boolean creationFlag=false;
        for (WebElement messageBot : createdTasks){
            if (messageBot.getAttribute("aria-label").contains(messageBoxName)) {
                System.out.println("Message box task created");
                creationFlag=true;
                break;
            }
    }
        return creationFlag;


    }


}
