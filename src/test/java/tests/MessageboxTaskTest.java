package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.LandingPageTaskBot;
import pages.LoginPage;

public class MessageboxTaskTest extends BaseTest {

    @Ignore
    @Test
    private void testMessageBoxTask(){

        try {


            LandingPageTaskBot landingPagetaskBot = new LandingPageTaskBot(driver);
            Assert.assertEquals(landingPagetaskBot.verifyMessageBoxFunctionality(), true);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail("Test Failed");
        }


    }




}
