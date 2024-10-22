package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

@Test(priority = 1,description = "Admin login with wrong creds")
public void adminLoginWithWrongCreds() {
    loginPage = new LoginPage(driver);
    loginPage.doLogin("admin@test.com", "wrong");

    String ErrorMessageActual = driver.findElement(By.tagName("p")).getText();
    String ErrorMessageExpected = "Invalid";
    Assert.assertTrue(ErrorMessageActual.contains(ErrorMessageExpected));

    clearCreds();

    }


@Test(priority = 2,description = "Admin login with right creds")
public void adminLoginWithRightCreds(){
    loginPage = new LoginPage(driver);

    loginPage.doLogin("admin@test.com","admin123");

    String headerActual =  driver.findElement(By.tagName("h2")).getText();
    String hederExpected = "Admin Dashboard";
    Assert.assertTrue(headerActual.contains(hederExpected));
    loginPage.doLogout();
    }

public void clearCreds(){
     loginPage = new LoginPage(driver);
     loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
     loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);
     loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
     loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);

    }
}
