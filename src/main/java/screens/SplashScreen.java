package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SplashScreen extends BaseScreen {
    public SplashScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    WebElement versionTextView;

    public String getCurrentVersion(){
        return versionTextView.getText();
    }

    public AuthenticationScreen checkCurrentVersion(String version){
      isShouldHave(versionTextView, version,5);
        return new AuthenticationScreen(driver);
    }





}



