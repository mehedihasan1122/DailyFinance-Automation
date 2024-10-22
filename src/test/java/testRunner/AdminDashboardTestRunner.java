package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AdminDashboardTestRunner extends Setup {
    LoginTestRunner loginTestRunner = new LoginTestRunner();
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        if(System.getProperty("username") !=null && System.getProperty("password") !=null){
            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        }else {
            loginPage.doLogin("admin@test.com", "admin123" );
        }


    }

    @Test(priority = 1, description = "check the last registered user is displayed on the admin dashboard")
    public void checkUser() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);


        String firstName = (String) userObj.get("firstName");
        String email =(String) userObj.get("email");
        String phoneNumber = (String) userObj.get("phoneNumber");



        List<WebElement> Data = driver.findElements(By.xpath("//tbody/tr[1]/td"));

        String dbFirstname =Data.get(0).getText();
        System.out.println(dbFirstname);
        Assert.assertTrue(dbFirstname.contains(firstName));

        String dbEmail = Data.get(2).getText();
        System.out.println(dbEmail);
        Assert.assertTrue(dbEmail.contains(email));

        String dbPhoneNumber =Data.get(3).getText();
        System.out.println(dbPhoneNumber);
        Assert.assertTrue(dbPhoneNumber.contains(phoneNumber));
    }



}
