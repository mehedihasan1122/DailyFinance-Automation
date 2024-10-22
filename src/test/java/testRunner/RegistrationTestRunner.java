package testRunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {


    @Test(priority = 1,description = "user can register by all providing data")
    public void userRegistrationByAllFields() throws InterruptedException, IOException,  ParseException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker = new Faker();

        userReg.btnRegister.get(1).click();

        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = "mehedirayhan1122+41@gmail.com";
        String password = "1234";
        String phonenumber="01644"+ Utils.generateRandomNumber(100000,999999);
        String address=faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phonenumber);
        userModel.setAddress(address);

        userReg.doRegistration(userModel);


        doRegistrationAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);
        Thread.sleep(2000);

    }


    @Test(priority = 2,description = "user can register by mandatory info")
    public void UserRegistrationByOnlyMandatoryFields() throws IOException, ParseException, InterruptedException {



        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker = new Faker();
        userReg.btnRegister.get(1).click();

        String firstname = faker.name().firstName();

        String email = "mehedirayhan1122+42@gmail.com";
        String password = "1234";
        String phonenumber="01644"+ Utils.generateRandomNumber(100000,999999); //random number generate from utils class

        UserModel userModel = new UserModel();

        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phonenumber);

        userReg.doRegistration(userModel);
        doRegistrationAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstname);

        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);


        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

    }

    @Test(priority = 3,description = "user can register Without mandatory info")
    public void UserRegistrationWithoutMandatoryFields() throws IOException, ParseException, InterruptedException {

        RegistrationPage userReg=new RegistrationPage(driver);
        Thread.sleep(2000);
        userReg.btnRegister.get(1).click();
        userReg.btnSubmit.click();
        String validationError = userReg.txtFirstName.getAttribute("validationMessage");
        Assert.assertTrue(validationError.contains("Please fill out this field"));

    }






    public void doRegistrationAssertion() throws InterruptedException {

        Thread.sleep(4000);

       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));

        String SuccessMessageActual = driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(SuccessMessageActual);
        String SuccessMessageExpected = "successfully";
        Assert.assertTrue(SuccessMessageActual.contains(SuccessMessageExpected));

    }

}
