package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.LandingPageFormUpload;
import pages.LoginPage;

public class FormwithuploadTest extends BaseTest {

    @Test
    public void testFormwithUpload(){
        try {
            LandingPageFormUpload landingPageFormUpload = new LandingPageFormUpload(driver);
            Assert.assertEquals(landingPageFormUpload.verifyFormFunctionality(), true);
            System.out.println("======Test completed !!!======");

        }
        catch(Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");

        }


    }
}
