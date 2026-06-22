package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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


    public boolean isActivityTitleDisplayed(String text) {
        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }


    public AuthenticationScreen logout(){
        menuOptions.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }
}
