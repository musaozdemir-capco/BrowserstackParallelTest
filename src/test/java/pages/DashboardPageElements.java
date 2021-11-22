package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.PageInitializer;

public class DashboardPageElements extends PageInitializer {

@FindBy(xpath = "//*[@id=\"main-outlet\"]/div[2]/header/div[3]/div/div/div/div/h1")
public WebElement greetingMessage;











    public DashboardPageElements(){
        PageFactory.initElements(driver , this);
    }
}
