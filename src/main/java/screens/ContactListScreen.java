package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@resource-id ='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    WebElement activityTextView;

    @AndroidFindBy(xpath = "//*[@content-desc ='More options']")
    WebElement menuOptions;

    @AndroidFindBy(xpath = "//*[@text='Logout']")
    WebElement logoutBtn;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement plusBtn;

    @AndroidFindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<WebElement> contactNameList;


    public boolean isActivityTitleDisplayed(String text) {
        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }


    public AuthenticationScreen logOut() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if(activityTextView.getText().equals("Contact list")) {
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);

    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        // List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowName]"));
        isShouldHave(activityTextView, "Contact list", 10);
        System.out.println("size of list " + contactNameList.size());
        boolean isPresent = false;
        for (WebElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }
}
