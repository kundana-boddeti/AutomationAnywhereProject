package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LandingPageFormUpload extends LandingPage{

    private WebDriver driver;
    private String formNameExpected="Form"+String.valueOf(System.currentTimeMillis());
    private String formDescriptionText="form for user Kundana!";


    @FindBy(xpath="//button[@name='create-attended-form']")
    WebElement createForm;


    @FindBy(xpath="//input[@name='name']")
    WebElement formName;


    @FindBy(xpath="//input[@name='description']")
    WebElement formDescription;

    @FindBy(xpath="//span[text()='Create & edit'][1]")
    WebElement createandEditButton;

    @FindBy(xpath="//iframe[@class='modulepage-frame']")
    WebElement frameWebEle;

    //wait
    @FindBy(xpath="//div[@data-item-name='TextBox']/div")
    WebElement formtextBoxWidget; //drag and drop

    @FindBy(xpath="//div[@data-item-name='File']/div")
    WebElement formselectFileWidget; //drag and drop


    @FindBy(xpath="//div[@data-path='content']")
    WebElement contentArea; //for drag and drop


    @FindBy(xpath="//input[@name='defaultValue']")
    WebElement defaultValueText;


    @FindBy(xpath="//textarea[@name='fileFormat']")
    WebElement fileFormattextArea;


    @FindBy(xpath="//input[@aria-label='TextBox']")
    WebElement textBoxForm;

    @FindBy(linkText="browse")
    WebElement browseForUpload;

    @FindBy(xpath="//button[@name='save']")
    WebElement saveButton;

    public LandingPageFormUpload(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyFormFunctionality() throws Exception{

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        automationMenu.click();
        createTask.click();
        createForm.click();

        wait.until(ExpectedConditions.elementToBeClickable(formName));

        formName.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", formName);


        formName.sendKeys(formNameExpected);

        formDescription.click();
        formDescription.clear();
        formDescription.sendKeys(formDescriptionText);

        createandEditButton.click();

        System.out.println("form created");

        driver.switchTo().frame(frameWebEle);
        //wait.until(ExpectedConditions.visibilityOf(closeButton));

       //drag and drop
        /*Actions actions = new Actions(driver);
        actions.clickAndHold(formselectFileWidget)
                .moveToElement(contentArea,formselectFileWidget,)
                .release()
                .build()
                .perform();*/
        simulateDragAndDrop(driver,formselectFileWidget,contentArea);



        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", formtextBoxWidget);

        simulateDragAndDrop(driver,formtextBoxWidget,contentArea);

        Thread.sleep(10*1000);


        //tried these options to get the second web element to the content, but didnt help
        /*contentArea.sendKeys("");

        actions.clickAndHold(formtextBoxWidget)
                .moveToElement(contentArea)
                .release(formtextBoxWidget)
                .moveByOffset(1,1)
                .build()
                .perform();*/

        saveButton.click();

        wait.until(ExpectedConditions.visibilityOf(closeButton));

        closeButton.click();

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.visibilityOfAllElements(createdTasks));

        boolean creationFlag=false;
        for (WebElement messageBot : createdTasks){
            if (messageBot.getAttribute("aria-label").contains(formNameExpected)) {
                System.out.println("Form with text and file upload task created");
                creationFlag=true;
                break;
            }
        }
        return creationFlag;
    }

    public void simulateDragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = """
        var source = arguments[0];
        var target = arguments[1];
        var dataTransfer = {
            dropEffect: '',
            effectAllowed: 'all',
            files: [],
            items: {},
            types: [],
            setData: function(format, data) {
                this.items[format] = data;
                this.types.push(format);
            },
            getData: function(format) {
                return this.items[format];
            },
            clearData: function(format) {}
        };

        var emit = function(eventType, element) {
            var event = document.createEvent('Event');
            event.initEvent(eventType, true, false);
            event.dataTransfer = dataTransfer;
            element.dispatchEvent(event);
        };

        emit('dragstart', source);
        emit('dragenter', target);
        emit('dragover', target);
        emit('drop', target);
        emit('dragend', source);
    """;

        js.executeScript(script, source, target);
    }

}
