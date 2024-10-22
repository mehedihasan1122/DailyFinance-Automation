package pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

public class UserProfilePage {
    WebDriver driver;
    @FindBy(tagName = "button")
    public List<WebElement> buttons;

    @FindBy(className = "upload-input")
    public WebElement imgFileSelect;

    public UserProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;


    }

    public void uploadProfileImage() throws InterruptedException {
        buttons.get(1).click(); // Edit Button
        imgFileSelect.sendKeys(System.getProperty("user.dir")+ "./src/test/resources/profileImage.jpg");
         Thread.sleep(2000);
        buttons.get(1).click();// Upload Image Button

        // Image upload Alert
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        buttons.get(2).click();// update button

      Thread.sleep(3000);
      Alert alert = driver.switchTo().alert();
      String userUpdateMsgExpected = "User updated successfully!";
     String userUpdateMsgActual = alert.getText();
     Assert.assertTrue(userUpdateMsgActual.contains(userUpdateMsgExpected));
      alert.accept();
    }





}
