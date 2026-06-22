package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseScreen {
    AndroidDriver driver;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }
    public void type(WebElement element,String text){
        element.click();
        element.click();
        if(text !=null ) {
            element.sendKeys(text);
        }
    }

    public boolean isShouldHave(WebElement element,String text,int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element,text));
    }
public void pause(int time){
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

    public void should(WebElement element, int time) {
        new WebDriverWait(driver,Duration.ofSeconds(time))
                .until(ExpectedConditions.visibilityOf(element));



    }


}
