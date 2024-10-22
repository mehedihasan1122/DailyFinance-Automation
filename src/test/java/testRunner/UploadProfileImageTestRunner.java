package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserProfilePage;

import java.io.FileReader;
import java.io.IOException;

public class UploadProfileImageTestRunner extends Setup {
@Test(priority = 1,description = "Last registered user login")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email= (String) userObj.get("email");
        String password = (String) userObj.get("password");
        loginPage.doLogin(email,password);


}

    @Test( priority = 2, description = "Update User Photo")
    public  void updateProfilePic() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);


        loginPage.btnProfileIcon.click();
        loginPage.btnProfileMenuItems.get(0).click();

       UserProfilePage userProfilePage = new UserProfilePage(driver);
       userProfilePage.uploadProfileImage();


    }


}
