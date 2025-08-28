package tests;


import Util.ConfigReader;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class RestLoginFeatureTest{

    protected String userName= ConfigReader.getProperty("user");
    protected String password=ConfigReader.getProperty("password");
    @Test
    public void testLoginRestAPI(){

        System.out.println("=====Rest Login test started=====");
        RestAssured.baseURI="https://community.cloud.automationanywhere.digital";

        String rsp=given()
                .header("Content-Type","application/json")

                .body("{\n" +
                        "\"username\": \""+userName+"\",\n" +
                        "\"password\": \""+password+"\",\n" +
                        "\"captcha\": {}\n" +
                        "}")
                .when()
                .post("/v2/authentication")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=new JsonPath(rsp);

        System.out.println("===========Got response:test completed=========");
        Assert.assertEquals(userName,js.getString("user.email"));

    }



}
